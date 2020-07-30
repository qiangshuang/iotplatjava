package com.ipincloud.iotbj.srv.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.api.utils.hik.ApiService;
import com.ipincloud.iotbj.api.utils.hik.HikException;
import com.ipincloud.iotbj.api.utils.hik.ApiModel;
import com.ipincloud.iotbj.srv.dao.OrgDao;
import com.ipincloud.iotbj.srv.dao.UserDao;
import com.ipincloud.iotbj.srv.dao.UserRoleDao;
import com.ipincloud.iotbj.srv.domain.Org;
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

    @Value("${hikEnable}")
    private boolean hikEnable;

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
		String parentOrgIndexCode = "";
        if (org!=null && StringUtils.isNotEmpty(org.getOrgIndexCode())) {
            parentOrgIndexCode = org.getOrgIndexCode();
        }
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
        Org org = orgDao.queryById(jsonObj.getLong("parent_id"));
        String parentOrgIndexCode = org.getOrgIndexCode();
        jsonObjFirst.put("orgIndexCode", UUID.randomUUID().toString());
        jsonObjFirst.put("parentOrgIndexCode", parentOrgIndexCode);
        this.orgDao.addInst(jsonObjFirst);
        JSONObject jsonObjSecond = ParaUtils.copyJsonObjectCols(jsonObj, "mobile,user_name,pwd,id,title,parent_id,parent_title,lastlogin,job_title,job_id,thirdin,created,updated,photo,idnumber,cardnumber,mcardno,gender,personId");
        jsonObjSecond.put("id", jsonObjFirst.get("id"));
		jsonObjSecond.put("ceated",System.currentTimeMillis());
        this.userDao.addInst(jsonObjSecond);

		String personId = "";
        if (hikEnable) {
			//通过身份证查询海康是否存在人员
            JSONObject certifi = new JSONObject();
            certifi.put("certificateType", "111");
            certifi.put("certificateNo", jsonObjSecond.getString("idnumber"));
            JSONObject hikperson = ApiService.getPersonbycertificateno(certifi);
            if (hikperson != null) {
                throw new HikException("海康平台已存在此身份证人员");
            }

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
            //person.put("orgIndexCode", jsonObjFirst.getString("orgIndexCode"));
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
            ApiService.addPerson(person);
        }
		if (Objects.equals("", personId)) {
            jsonObj.put("personId", personId);
            userDao.updateInst(jsonObj);
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
		jsonObjSecond.put("updated",System.currentTimeMillis());
        this.userDao.updateInst(jsonObjSecond);

        if (hikEnable) {
            JSONObject person = new JSONObject();
            person.put("personName", jsonObjSecond.getString("title"));
            if (Objects.equals("男", jsonObjSecond.getString("gender"))) {
                person.put("gender", "1");
            } else if (Objects.equals("女", jsonObjSecond.getString("gender"))) {
                person.put("gender", "2");
            } else {
                person.put("gender", "0");
            }
            person.put("orgIndexCode", jsonObjFirst.getString("orgIndexCode"));
            person.put("phoneNo", jsonObjSecond.getString("mobile"));
            person.put("certificateType", "111");
            person.put("certificateNo", jsonObjSecond.getString("idnumber"));
            if (Objects.equals("", jsonObjSecond.getString("user_name"))) {
                person.put("jobNo", jsonObjSecond.getString("user_name"));
            } else {
                person.put("jobNo", jsonObjSecond.getString("jobno"));
            }
            ApiService.updatePerson(person);
        }
    }

    //@param jsonObj 调用参数  
    //@return 影响记录数 
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer deletesOrgUserUserRoleInst(JSONObject jsonObj) {
        if (hikEnable) {
            List<JSONObject> users = orgDao.listDeletePerson(jsonObj);
            JSONObject deleteObject = new JSONObject();
            JSONArray indexCodes = new JSONArray();
            if (users != null) {
                for (int i = 0; i < users.size(); i++) {
                    indexCodes.add(users.get(i).getString("personId"));
                }
            }
            deleteObject.put("personIds", indexCodes);
            ApiService.deletePerson(deleteObject);
        }

        Integer delNum1 = this.orgDao.deletesInst(jsonObj);
        Integer delNum2 = 0;
        delNum2 = this.userDao.deletesInst(jsonObj);
        delNum1 = delNum1 + delNum2;
        delNum2 = this.userRoleDao.deletesInst(jsonObj);
        delNum1 = delNum1 + delNum2;
        return delNum1;
    }
}
