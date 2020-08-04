package com.ipincloud.iotbj.srv.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.api.utils.AlgorithmFaceUtils;
import com.ipincloud.iotbj.api.utils.hik.ApiModel;
import com.ipincloud.iotbj.api.utils.hik.ApiService;
import com.ipincloud.iotbj.api.utils.hik.HikException;
import com.ipincloud.iotbj.face.dao.FaceDao;
import com.ipincloud.iotbj.srv.dao.OrgDao;
import com.ipincloud.iotbj.srv.dao.UserDao;
import com.ipincloud.iotbj.srv.dao.UserRoleDao;
import com.ipincloud.iotbj.srv.domain.Org;
import com.ipincloud.iotbj.srv.domain.User;
import com.ipincloud.iotbj.srv.service.OrgService;
import com.ipincloud.iotbj.utils.FileUtils;
import com.ipincloud.iotbj.utils.ParaUtils;
import com.ipincloud.iotbj.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

//(Org) 服务实现类
//generate by redcloud,2020-07-15 18:51:58
@Service("OrgService")
@Transactional
public class OrgServiceImpl implements OrgService {
    @Resource
    private OrgDao orgDao;
    @Resource
    private UserDao userDao;
    @Resource
    private UserRoleDao userRoleDao;
    @Resource
    private FaceDao faceDao;

    @Value("${hikEnable}")
    private boolean hikEnable;

    @Value("${algorithm_face.face_register}")
    private String algorithmFaceRegisterUrl;

    @Value("${localhostUri}")
    private String localhostUri;

    //@param id 主键
    //@return 实例对象Org
    @Override
    public Org queryById(Long id) {
        return this.orgDao.queryById(id);
    }

    //@param jsonObj 过滤条件等
    //@return 对象Org 树形查询
    @Override
    public List<Map> orgTree(JSONObject jsonObj) {

        if (ParaUtils.notHaveColVal(jsonObj, "parent_id") != null && ParaUtils.notHaveColVal(jsonObj, "parent_id").length() > 0) {
            return this.orgDao.queryTreeHp(jsonObj);
        } else {
            return this.orgDao.queryTreeNp(jsonObj);
        }
    }


    //@param jsonObj 调用参数
    //@return 实例对象Org
    @Override
    public JSONObject addInst(JSONObject jsonObj) {
        jsonObj = ParaUtils.removeSurplusCol(jsonObj, "id,type,parent_id,sort,data_scope,stop,memo,parent_title,title");
        Org org = orgDao.queryById(jsonObj.getLong("parent_id"));
        String parentOrgIndexCode = org.getOrgIndexCode();
        jsonObj.put("orgIndexCode", UUID.randomUUID().toString());
        jsonObj.put("parentOrgIndexCode", parentOrgIndexCode);

        this.orgDao.addInst(jsonObj);
        /*
        if (hikEnable) {
            List<JSONObject> list = new ArrayList();
            JSONObject dept = new JSONObject();
            dept.put("clientId", jsonObj.getLong("id"));
            dept.put("orgIndexCode", jsonObj.getString("orgIndexCode"));
            dept.put("orgName", jsonObj.getString("title"));
            dept.put("parentIndexCode", parentOrgIndexCode);
            list.add(dept);
            ApiService.addBatchOrg(list);
        }
        */
        return jsonObj;

    }

    //@param jsonObj 调用参数
    //@return 影响记录数Org
    @Override
    public void updateInst(JSONObject jsonObj) {
        jsonObj = ParaUtils.removeSurplusCol(jsonObj, "id,type,parent_id,sort,data_scope,stop,memo,parent_title,title");
        this.orgDao.updateInst(jsonObj);
        Org org = orgDao.queryById(jsonObj.getLong("id"));
        /*
        if (hikEnable) {
            JSONObject dept = new JSONObject();
            dept.put("orgIndexCode", org.getOrgIndexCode());
            dept.put("orgName", org.getTitle());
            ApiService.updateOrg(dept);
        }
         */
    }

    //@param jsonObj 调用参数
    //@return 影响记录数
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer deletesOrgInst(JSONObject jsonObj) {
        /*
        if (hikEnable) {
            List<JSONObject> orgs = orgDao.listDeleteOrg(jsonObj);
            JSONObject deleteObject = new JSONObject();
            JSONArray indexCodes = new JSONArray();
            if (orgs != null) {
                for (int i = 0; i < orgs.size(); i++) {
                    indexCodes.add(orgs.get(i).getString("orgIndexCode"));
                }
            }
            deleteObject.put("indexCodes", indexCodes);
            ApiService.deleteBatchOrg(deleteObject);
        }
        */
        Integer delNum1 = this.orgDao.deletesInst(jsonObj);

        return delNum1;
    }

    //@param jsonObj 过滤条件等
    //@return 对象Org 查询
    @Override
    public List<Map> orgQuery(JSONObject jsonObj) {
        return this.orgDao.orgQuery(jsonObj);

    }

    //@param jsonObj 过滤条件等
    //@return 对象查询Org 分页
    @Override
    public Map orgList(JSONObject jsonObj) {

        int totalRec = this.orgDao.countOrgList(jsonObj);
        jsonObj = ParaUtils.checkStartIndex(jsonObj, totalRec);
        List<Map> pageData = this.orgDao.orgList(jsonObj);
        Map retMap = new HashMap();
        retMap.put("pageData", pageData);
        retMap.put("totalRec", totalRec);
        retMap.put("cp", jsonObj.get("cp"));
        retMap.put("rop", jsonObj.get("rop"));
        return retMap;
    }


    //@param jsonObj 调用参数
    //@return jsonObj
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JSONObject addOrgUserInstAttr(JSONObject jsonObj) {
        JSONObject jsonObjFirst = ParaUtils.copyJsonObjectCols(jsonObj, "id,type,parent_id,sort,data_scope,stop,memo,parent_title,title,orgIndexCode");
//        Org org = orgDao.queryById(jsonObj.getLong("parent_id"));
//        String parentOrgIndexCode = "";
//        if (org != null && StringUtils.isNotEmpty(org.getOrgIndexCode())) {
//            parentOrgIndexCode = org.getOrgIndexCode();
//        }
        jsonObjFirst.put("orgIndexCode", UUID.randomUUID().toString());
//        jsonObjFirst.put("parentOrgIndexCode", parentOrgIndexCode);
        jsonObjFirst.put("type","user");
        this.orgDao.addInst(jsonObjFirst);
        JSONObject jsonObjSecond = ParaUtils.copyJsonObjectCols(jsonObj, "mobile,user_name,pwd,id,title,parent_id,parent_title,lastlogin,job_title,job_id,thirdin,created,updated,photo,idnumber,cardnumber,mcardno,gender,personId");
        jsonObjSecond.put("id", jsonObjFirst.get("id"));
        jsonObjSecond.put("created", System.currentTimeMillis());
        jsonObjSecond.put("userGroup", "场内人员");
        this.userDao.addInst(jsonObjSecond);

        String personId = null;
        if (hikEnable) {
            //通过身份证查询海康是否存在人员
            JSONObject certifi = new JSONObject();
            certifi.put("certificateType", "111");
            certifi.put("certificateNo", jsonObjSecond.getString("idnumber"));
            JSONObject hikperson = ApiService.getPersonbycertificateno(certifi);
            if (hikperson == null) {
                JSONObject person = new JSONObject();
                person.put("personName", jsonObjSecond.getString("title"));
                if (Objects.equals("男", jsonObjSecond.getString("gender"))) {
                    person.put("gender", "1");
                } else if (Objects.equals("女", jsonObjSecond.getString("gender"))) {
                    person.put("gender", "2");
                } else {
                    person.put("gender", "0");
                }
                ApiModel.HikOrg hikOrg = ApiService.getOrgRoot();
                if (hikOrg == null) {
                    throw new HikException("海康平台的根部门不存在");
                }
                person.put("orgIndexCode", hikOrg.orgIndexCode);
                person.put("phoneNo", jsonObjSecond.getString("mobile"));
                person.put("certificateType", "111");
                person.put("certificateNo", jsonObjSecond.getString("idnumber"));
                if (Objects.equals("", jsonObjSecond.getString("user_name"))) {
                    person.put("jobNo", jsonObjSecond.getString("user_name"));
                } else {
                    person.put("jobNo", jsonObjSecond.getString("jobno"));
                }
                List<Map> list = new ArrayList();
                Map face = new HashMap();
                String str = "";
                if (StringUtils.isNotEmpty(jsonObjSecond.getString("photo"))) {
                    str = FileUtils.readImgBase64Code(jsonObjSecond.getString("photo"));
                }
                face.put("faceData", str);
                list.add(face);
                person.put("faces", list);
                personId = ApiService.addPerson(person);
                if (StringUtils.isEmpty(personId)) {
                    throw new HikException("海康平台添加人员失败");
                }
            }else{
                throw new HikException("海康平台上已经有此人");
            }

        }
        if (personId != null) {
            jsonObjSecond.put("personId", personId);
            userDao.updateInst(jsonObjSecond);
            AlgorithmFaceUtils.registerFace(algorithmFaceRegisterUrl, personId, jsonObjSecond.getString("photo"));
        }

        return jsonObj;
    }

    //@param jsonObj 调用参数
    //@return 影响记录数
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateOrgUserInstAttr(JSONObject jsonObj) {
        JSONObject jsonObjFirst = ParaUtils.copyJsonObjectCols(jsonObj, "id,type,parent_id,sort,data_scope,stop,memo,parent_title,title,orgIndexCode");
        this.orgDao.updateInst(jsonObjFirst);
        JSONObject jsonObjSecond = ParaUtils.copyJsonObjectCols(jsonObj, "mobile,user_name,pwd,id,title,parent_id,parent_title,lastlogin,job_title,job_id,thirdin,created,updated,photo,idnumber,cardnumber,mcardno,gender,personId");
        jsonObjSecond.put("id", jsonObjFirst.get("id"));
        this.userDao.updateInst(jsonObjSecond);


        String personId = jsonObjSecond.getString("personId");
        User user = null;
        if (StringUtils.isEmpty(personId)) {
            user = userDao.queryById(jsonObjSecond.getLong("id"));
            personId = user.getPersonId();
        }

        if (hikEnable) {
            JSONObject person = new JSONObject();
            person.put("personId", personId);
            person.put("personName", jsonObjSecond.getString("title"));
            if (Objects.equals("男", jsonObjSecond.getString("gender"))) {
                person.put("gender", "1");
            } else if (Objects.equals("女", jsonObjSecond.getString("gender"))) {
                person.put("gender", "2");
            } else {
                person.put("gender", "0");
            }
            person.put("phoneNo", jsonObjSecond.getString("mobile"));
            person.put("certificateType", "111");
            person.put("certificateNo", jsonObjSecond.getString("idnumber"));
            if (Objects.equals("", jsonObjSecond.getString("user_name"))) {
                person.put("jobNo", jsonObjSecond.getString("user_name"));
            } else {
                person.put("jobNo", jsonObjSecond.getString("jobno"));
            }
            String str = "";
            if (StringUtils.isNotEmpty(jsonObjSecond.getString("photo"))) {
                str = FileUtils.readImgBase64Code(jsonObjSecond.getString("photo"));
            }
            person.put("faces", str);
            ApiService.updatePerson(person);
        }
        if (personId != null) {
            AlgorithmFaceUtils.registerFace(algorithmFaceRegisterUrl, personId, jsonObjSecond.getString("photo"));
        }
    }

    //@param jsonObj 调用参数
    //@return 影响记录数
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer deletesOrgUserUserRoleInst(JSONObject jsonObj) {

        if (hikEnable) {
            if (jsonObj != null && jsonObj.getJSONArray("user").size() > 0) {
                JSONArray deleteIds = jsonObj.getJSONArray("user").getJSONObject(0).getJSONArray("val");
                if (deleteIds != null && deleteIds.size() > 0) {
                    List<String> personIds = orgDao.queryByIds(deleteIds.toJavaList(Long.class));
                    if (personIds != null && personIds.size() > 0) {
                        List<String> validPeronIds=new ArrayList<>();
                        for(String personId:personIds){
                            if(StringUtils.isNotEmpty(personId)){
                                validPeronIds.add(personId);
                            }
                        }
                        if (validPeronIds.size() > 0) {
                            //1.删除权限配置
                            deletePersonPolicy(validPeronIds);
                            //2.删除海康人员
                            JSONObject deletePersonInfo = new JSONObject();
                            deletePersonInfo.put("personIds", validPeronIds);
                            ApiService.deletePerson(deletePersonInfo);
                            //3.删除本地权限库
                            faceDao.deletePolicyByPersonId(validPeronIds);
                        }
                    }
                }
            }
        }

        Integer delNum1 = this.orgDao.deletesInst(jsonObj);
        Integer delNum2 = 0;
        delNum2 = this.userDao.deletesInst(jsonObj);
        delNum1 = delNum1 + delNum2;
        delNum2 = this.userRoleDao.deletesInst(jsonObj);
        delNum1 = delNum1 + delNum2;
        return delNum1;
    }

    public void deletePersonPolicy(List<String> validPeronIds) {
        List<JSONObject> policyList = faceDao.listPolicyByPersonIds(validPeronIds);
        for (int i = 0; i < validPeronIds.size(); i++) {
            Set<JSONObject> resourceInfos = new HashSet<>();
            Set<JSONObject> personInfos = new HashSet<>();
            String personId = validPeronIds.get(i);
            if (StringUtils.isNotEmpty(personId)) {
                //添加人员信息
                JSONObject person = faceDao.findUserByPersonId(personId);
                JSONObject personInfo = new JSONObject();
                personInfo.put("personId", person.getString("personId"));
                personInfo.put("operatorType", 2);
//              personInfo.put("startTime", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(new Date(policy.getLong("starttime"))));
//              personInfo.put("endTime", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(new Date(policy.getLong("endtime"))));
                personInfo.put("personType", "1");
                personInfo.put("name", person.getString("title"));
                List<JSONObject> cards = new ArrayList<>();
                JSONObject card = new JSONObject();
                card.put("card", Objects.equals("", person.getString("mobile")) ? person.getString("idnumber").replace("X", "0").replace("x", "0") : person.getString("mobile"));
                card.put("status", 0);
                card.put("cardType", 1);
                cards.add(card);
                personInfo.put("cards", cards);
                JSONObject face = new JSONObject();
                face.put("card", null);
                Map faceData = new HashMap<>();
                String photo = person.getString("photo");
                if (StringUtils.isEmpty(photo)) {
                    break;
                }
                String imgPath = localhostUri + "/face/img?imgPath=" + FileUtils.getRealFilePath(photo);
                faceData.put("f" + person.getString("id"), imgPath);
                face.put("data", faceData);
                personInfo.put("face", face);
                personInfos.add(personInfo);
            }

            for (int j = 0; j < policyList.size(); j++) {
                JSONObject policy = policyList.get(j);
                if (Objects.equals(personId, policy.getString("personId"))) {
                    //添加资源信息
                    if (StringUtils.isNotEmpty(policy.getString("acsDevIndexCode"))) {
                        JSONObject resourceInfo = new JSONObject();
                        List<JSONObject> doors = faceDao.findGatewayByIndexCode(policy.getString("acsDevIndexCode"));
                        resourceInfo.put("resourceIndexCode", doors.get(0).getString("doorIndexCode"));
                        resourceInfo.put("resourceType", doors.get(0).getString("channelType"));
                        JSONArray channelNos = new JSONArray();
                        if (doors != null) {
                            for (int k = 0; k < doors.size(); k++) {
                                channelNos.add(doors.get(k).getInteger("channelNo"));
                            }
                        } else {
                            channelNos.add(1);
                        }
                        resourceInfo.put("channelNos", channelNos);
                        resourceInfos.add(resourceInfo);
                    }
                }
            }
            //调用第三方接口进行删除权限
            if (hikEnable) {
                if (resourceInfos.size() > 0 && personInfos.size() > 0) {
                    ApiService.authDownload(resourceInfos, personInfos, true);
                }
            }
        }

    }
}
