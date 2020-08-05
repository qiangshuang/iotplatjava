package com.ipincloud.iotbj.face.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.api.utils.hik.ApiModel;
import com.ipincloud.iotbj.api.utils.hik.ApiService;
import com.ipincloud.iotbj.api.utils.hik.HikException;
import com.ipincloud.iotbj.face.dao.FaceDao;
import com.ipincloud.iotbj.face.service.FaceService;
import com.ipincloud.iotbj.oa.Guest;
import com.ipincloud.iotbj.oa.OAApi;
import com.ipincloud.iotbj.srv.dao.OrgDao;
import com.ipincloud.iotbj.srv.dao.UserDao;
import com.ipincloud.iotbj.srv.domain.Org;
import com.ipincloud.iotbj.srv.domain.User;
import com.ipincloud.iotbj.sys.domain.ResponseBean;
import com.ipincloud.iotbj.utils.FileUtils;
import com.ipincloud.iotbj.utils.ParaUtils;
import com.ipincloud.iotbj.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;


@Service
@Transactional
public class FaceServiceImpl implements FaceService {
    @Autowired
    FaceDao faceDao;
    @Autowired
    UserDao userDao;
    @Autowired
    OrgDao orgDao;

    @Autowired
    OAApi oaApi;

    @Value("${hikEnable}")
    private boolean hikEnable;

    @Value("${localhostUri}")
    private String localhostUri;

    @Override
    public Object syncAcsdev(JSONObject jsonObj) {

        if (hikEnable) {
            JSONObject para = new JSONObject();
            para.put("pageNo", 1);
            para.put("pageSize", 1000);
            if (StringUtils.isNotEmpty(jsonObj.getString("title"))) {
                para.put("doorName", jsonObj.getString("title"));
            }
            JSONObject jsonObject = ApiService.ascDevSync(para);

            List<ApiModel.HikDoor> list1 = jsonObject.getJSONArray("list").toJavaList(ApiModel.HikDoor.class);
            List<Map> list2 = faceDao.listGatewayByName(new JSONObject(), null);
            Set<String> set = new HashSet();
            for (int i = 0; i < list2.size(); i++) {
                set.add(list2.get(i).get("doorIndexCode").toString());
            }
            JSONArray list = new JSONArray();
            for (int i = 0; i < list1.size(); i++) {
                String acsDevIndexCode = list1.get(i).doorIndexCode;
                if (set == null || set.size() < 1) {
                    list1.get(i).acsDevName = list1.get(i).doorName;
                    list1.get(i).acsDevIndexCode = list1.get(i).acsDevIndexCode;
                    list.add(list1.get(i));
                }
                for (int j = 0; j < set.size(); j++) {
                    if (!set.contains(acsDevIndexCode)) {
                        list1.get(i).acsDevName = list1.get(i).doorName;
                        list1.get(i).acsDevIndexCode = list1.get(i).acsDevIndexCode;
                        list.add(list1.get(i));
                        break;
                    }
                }
            }

            jsonObj.put("pageData", list);
            jsonObj.put("totalRec", jsonObject.getInteger("total"));

            return new ResponseBean(200, "SUCCESS", "操作成功", jsonObj);
        }

        int totalRec = faceDao.countAcsdev(jsonObj);
        jsonObj = ParaUtils.checkStartIndex(jsonObj, totalRec);
        List<Map> list = faceDao.listAcsdevByName(jsonObj);
        jsonObj.put("pageData", list);
        jsonObj.put("totalRec", totalRec);
        return new ResponseBean(200, "SUCCESS", "操作成功", jsonObj);
    }

    @Override
    public Object gatewayadd(JSONArray jsonObj) {
        for (int i = 0; i < jsonObj.size(); i++) {
            JSONObject region = faceDao.findRegionByIndexCode(jsonObj.getJSONObject(i).getString("regionIndexCode"));
            if (region != null) {
                jsonObj.getJSONObject(i).put("region_id", (Long) region.get("id"));
                jsonObj.getJSONObject(i).put("region_title", region.get("title").toString());
            }
            faceDao.insertGateway(jsonObj.getJSONObject(i));
        }
        return new ResponseBean(200, "SUCCESS", "操作成功", jsonObj);
    }

    @Override
    public Object gatewayup(JSONObject jsonObj) {
        if (jsonObj == null || jsonObj.getLong("id") < 1) {
            return new ResponseBean(200, "FAILED", "操作失败", null);
        }
        jsonObj.put("updated", System.currentTimeMillis());
        faceDao.updateGateway(jsonObj);
        return new ResponseBean(200, "SUCCESS", "操作成功", jsonObj);
    }

    @Override
    public Object regionlist(JSONObject jsonObj) {
        List<Map> list = faceDao.listRegionByName(jsonObj);
        return new ResponseBean(200, "SUCCESS", "操作成功", list);
    }

    @Override
    public Object gatewaylist(JSONObject jsonObj) {
        List<JSONObject> childRegion = new ArrayList<>();
        Set<Long> regionIds = new HashSet<>();

        List<JSONObject> regions = faceDao.findRegions();
        Long pid = jsonObj.getLong("region_id") == null ? 0L : jsonObj.getLong("region_id");
        regionRecursion(childRegion, regions, pid);

        for (JSONObject jsonObject : childRegion) {
            regionIds.add(jsonObject.getLong("id"));
        }
        regionIds.add(pid);
        int totalRec = 0;
        List<Map> list = new ArrayList<>();
        if (regionIds.size() > 0) {
            totalRec = faceDao.countGateway(jsonObj, regionIds);
            jsonObj = ParaUtils.checkStartIndex(jsonObj, totalRec);
            list = faceDao.listGatewayByName(jsonObj, regionIds);
        }

        jsonObj.put("pageData", list);
        jsonObj.put("totalRec", totalRec);
        return new ResponseBean(200, "SUCCESS", "操作成功", jsonObj);
    }

    private void regionRecursion(List<JSONObject> childRegion, List<JSONObject> regionList, Long pid) {
        for (JSONObject region : regionList) {
            if (region.getLong("parent_id") != null) {
                //遍历出父id等于参数的id，add进子节点集合
                if (pid.toString().equals(region.getString("parent_id"))) {
                    //递归遍历下一级
                    childRegion.add(region);
                    Long regionId = region.getLong("id");
                    orgRecursion(childRegion, regionList, regionId);

                }
            }
        }
    }

    @Override
    public Object gatewaydel(JSONArray jsonObj) {
        faceDao.deleteGatewayById(jsonObj);
        return new ResponseBean(200, "SUCCESS", "操作成功", jsonObj);
    }

    @Override
    public Object gatewayopen(JSONObject jsonObj) {
        Long id = jsonObj.getLong("id");
        Map gateway = faceDao.findGatewayById(id);
        String acsDevIndexCode = String.valueOf(gateway.get("acsDevIndexCode"));
        if (StringUtils.isEmpty(acsDevIndexCode)) {
            return new ResponseBean(200, "FAILED", "操作失败", null);
        }
        boolean bool = true;

        if (hikEnable) {
            bool = ApiService.gatewayOpen(acsDevIndexCode);
        }
        if (!bool) {
            return new ResponseBean(200, "FAILED", "操作失败", null);
        }
        faceDao.updateGatewayState(acsDevIndexCode);
        return new ResponseBean(200, "SUCCESS", "操作成功", null);
    }

    @Override
    public Object policylist(JSONObject jsonObj) {
        int totalRec = faceDao.countPolicy(jsonObj);
        jsonObj = ParaUtils.checkStartIndex(jsonObj, totalRec);
        List<Map> list = faceDao.listPolicy(jsonObj);
        jsonObj.put("pageData", list);
        jsonObj.put("totalRec", totalRec);
        return new ResponseBean(200, "SUCCESS", "操作成功", jsonObj);
    }

    //    @Override
//    public Object policyadd(JSONObject jsonObj) {
//        JSONArray gateways = jsonObj.getJSONArray("gateways");
//        JSONArray persons = jsonObj.getJSONArray("persons");
//        if (gateways != null && persons != null && (gateways.size() * persons.size()) > 100) {
//            return new ResponseBean(200, "FAILED", "单次数量超出100", null);
//        }
//        Long starttime = jsonObj.getLong("starttime");
//        Long endtime = jsonObj.getLong("endtime");
//        if (starttime == null || starttime == 0 || endtime == null || endtime == 0) {
//            return new ResponseBean(200, "FAILED", "未配置通行时间", null);
//        }
//
//        Set<String> personIds = new HashSet<>();
//        Set<Map> acsDevIndexCodes = new HashSet<>();
//
//        JSONObject policy = new JSONObject();
//        for (int i = 0; i < gateways.size(); i++) {
//            JSONObject joi = gateways.getJSONObject(i);
//            for (int j = 0; j < persons.size(); j++) {
//                JSONObject joj = persons.getJSONObject(j);
//                policy.put("region_id", joi.getLong("region_id"));
//                policy.put("gateway_id", joi.getLong("gateway_id"));
//                policy.put("user_id", joj.getLong("user_id"));
//                policy.put("org_id", joj.getLong("org_id"));
//
//                if (StringUtils.isNotEmpty(joi.getString("acsDevIndexCode")) && StringUtils.isNotEmpty(joj.getString("personId"))) {
//                    policy.put("acsDevIndexCode", joi.getString("acsDevIndexCode"));
//                    policy.put("personId", joj.getString("personId"));
//                }
//                if (StringUtils.isNotEmpty(policy.getString("personId"))) {
//                    personIds.add(policy.getString("personId"));
//                }
//                if (StringUtils.isNotEmpty(policy.getString("acsDevIndexCode"))) {
//                    Map resource = new HashMap();
//                    resource.put("resourceIndexCode", policy.getString("acsDevIndexCode"));
//                    resource.put("resourceType", "door");
//                    acsDevIndexCodes.add(resource);
//                }
//
//                policy.put("starttime", starttime);
//                policy.put("endtime", endtime);
//                policy.put("created", System.currentTimeMillis());
//                policy.put("updated", System.currentTimeMillis());
//                policy.put("state", "配置通过");
//
//                faceDao.insertPolicy(policy);
//                policy.clear();
//            }
//        }
//        if (hikEnable) {
//            policyallow(personIds, acsDevIndexCodes, starttime, endtime);
//        }
//
//        return new ResponseBean(200, "SUCCESS", "操作成功", jsonObj);
//    }
    @Override
    public Object policyadd(JSONObject jsonObj) {
        JSONArray gateways = jsonObj.getJSONArray("gateways");
        JSONArray persons = jsonObj.getJSONArray("persons");
        if (gateways != null && persons != null && (gateways.size() > 100 || persons.size() > 1000)) {
            return new ResponseBean(200, "FAILED", "设备单次数量超出100或人员数量超过1000", null);
        }
        Long starttime = jsonObj.getLong("starttime");
        Long endtime = jsonObj.getLong("endtime");
        if (starttime == null || starttime == 0 || endtime == null || endtime == 0) {
            return new ResponseBean(200, "FAILED", "未配置通行时间", null);
        }
        JSONObject policy = new JSONObject();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        String startTime = simpleDateFormat.format(new Date(starttime));
        String endTime = simpleDateFormat.format(new Date(endtime));
        Set<JSONObject> resourceInfos = new HashSet<>();
        Set<JSONObject> personInfos = new HashSet<>();
        Set<String> personIds = new HashSet<>();
        //List<JSONObject> errInfos = new ArrayList<>();
        for (int i = 0; i < gateways.size(); i++) {
            JSONObject joi = gateways.getJSONObject(i);
            for (int j = 0; j < persons.size(); j++) {
                JSONObject joj = persons.getJSONObject(j);
                //添加人员信息
                if (StringUtils.isNotEmpty(joj.getString("personId"))) {
                    personIds.add(joj.getString("personId"));
                    JSONObject person = faceDao.findUserByPersonId(joj.getString("personId"));
                    JSONObject personInfo = new JSONObject();
                    personInfo.put("personId", person.getString("personId"));
                    personInfo.put("operatorType", 1);
                    personInfo.put("startTime", startTime);
                    personInfo.put("endTime", endTime);
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
                } else {
                    break;
                }
                //添加资源信息
                if (StringUtils.isNotEmpty(joi.getString("acsDevIndexCode"))) {
                    //resourceInfo.put("resourceIndexCode", policy.getString("acsDevIndexCode"));
                    //resourceInfo.put("resourceType", "acsDevice");
                    List<JSONObject> doors = faceDao.findGatewayByIndexCode(joi.getString("acsDevIndexCode"));
                    //JSONObject resourceInfo = new JSONObject();
                    if (doors != null) {
                        for (int k = 0; k < doors.size(); k++) {
                            JSONObject resourceInfo = new JSONObject();
                            resourceInfo.put("resourceIndexCode", doors.get(k).getString("doorIndexCode"));
                            resourceInfo.put("resourceType", doors.get(k).getString("channelType"));
                            JSONArray channelNos = new JSONArray();
                            channelNos.add(doors.get(k).getInteger("channelNo"));
                            resourceInfo.put("channelNos", channelNos);
                            resourceInfos.add(resourceInfo);
                        }

                    } else {
                        //channelNos.add(1);
                    }
                    //resourceInfo.put("channelNos", channelNos);
                    //resourceInfos.add(resourceInfo);
                } else {
                    break;
                }
                policy.put("acsDevIndexCode", joi.getString("acsDevIndexCode"));
                policy.put("personId", joj.getString("personId"));
                policy.put("region_id", joi.getLong("region_id"));
                policy.put("gateway_id", joi.getLong("gateway_id"));
                policy.put("user_id", joj.getLong("user_id"));
                policy.put("org_id", joj.getLong("org_id"));
                policy.put("starttime", starttime);
                policy.put("endtime", endtime);
                policy.put("created", System.currentTimeMillis());
                policy.put("updated", System.currentTimeMillis());
                policy.put("state", "配置通过");
                //faceDao.insertPolicy(policy);
                faceDao.insertOrUpdatePolicy(policy);
                policy.clear();
            }
        }
        if (hikEnable) {
            ApiService.authDownload(resourceInfos, personInfos, true);
            ApiService.authDownloadSearchList(resourceInfos, personIds);
        }
        return new ResponseBean(200, "SUCCESS", "操作成功", jsonObj);
    }

    public void policyallow(Set<String> personIds, Set<Map> acsDevIndexCodes, long starttime, long endtime) {
        if (personIds == null || acsDevIndexCodes == null) {
            return;
        }
        JSONObject param = new JSONObject();
        List<Map> personDatas = new ArrayList<>();
        Map person = new HashMap<>();
        person.put("personDataType", "person");
        person.put("indexCodes", personIds);
        personDatas.add(person);

        param.put("personDatas", personDatas);
        param.put("resourceInfos", acsDevIndexCodes);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        String startTime = simpleDateFormat.format(new Date(starttime));
        String endTime = simpleDateFormat.format(new Date(endtime));
        param.put("startTime", startTime);
        param.put("endTime", endTime);
        ApiService.authconf(param);
    }

    public Object policyprohibit(JSONObject jsonObj) {
        Long policyId = jsonObj.getLong("id");
        if (policyId == null || policyId == 0) {
            return new ResponseBean(200, "FAILED", "操作失败", jsonObj);
        }
        String state = "禁止";
        faceDao.updatePolicyState(policyId, state);
        return new ResponseBean(200, "SUCCESS", "操作成功", jsonObj);
    }

    @Override
    public Object policydel(JSONArray jsonObj) {
        if (jsonObj == null || jsonObj.size() < 1) {
            return new ResponseBean(200, "FAILED", "操作失败", null);
        }
        List<JSONObject> policyList = faceDao.listPolicyByIds(jsonObj);
        Set<JSONObject> resourceInfos = new HashSet<>();
        Set<JSONObject> personInfos = new HashSet<>();
        for (int i = 0; i < policyList.size(); i++) {
            JSONObject policy = policyList.get(i);
            //添加人员信息
            if (StringUtils.isNotEmpty(policy.getString("personId"))) {
                JSONObject person = faceDao.findUserByPersonId(policy.getString("personId"));
                JSONObject personInfo = new JSONObject();
                personInfo.put("personId", person.getString("personId"));
                personInfo.put("operatorType", 2);
                personInfo.put("startTime", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(new Date(policy.getLong("starttime"))));
                personInfo.put("endTime", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(new Date(policy.getLong("endtime"))));
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
            } else {
                break;
            }
            //添加资源信息
            if (StringUtils.isNotEmpty(policy.getString("acsDevIndexCode"))) {
                JSONObject resourceInfo = new JSONObject();
                //resourceInfo.put("resourceIndexCode", policy.getString("acsDevIndexCode"));
                //resourceInfo.put("resourceType", "acsDevice");
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
            } else {
                break;
            }
        }
        //调用第三方接口进行删除权限
        if (hikEnable) {
            if (resourceInfos.size() > 0 && personInfos.size() > 0) {
                ApiService.authDownload(resourceInfos, personInfos, true);
                //ApiService.authDownloadSearchList(resourceInfos, personIds);
            }
        }

        faceDao.deletePolicyById(jsonObj);
        return new ResponseBean(200, "SUCCESS", "操作成功", jsonObj);
    }

    @Override
    public Object visithistorylist(JSONObject jsonObj) {
        int totalRec = faceDao.countHistory(jsonObj);
        jsonObj = ParaUtils.checkStartIndex(jsonObj, totalRec);
        List<Map> list = faceDao.listHistory(jsonObj);
        jsonObj.put("pageData", list);
        jsonObj.put("totalRec", totalRec);
        return new ResponseBean(200, "SUCCESS", "操作成功", jsonObj);
    }

    @Override
    public Object visitpersonadd(JSONObject jsonObj) {
        JSONObject jsonObjFirst = null;
        JSONObject jsonObjSecond = null;
        JSONObject visit = new JSONObject();
        JSONObject user = faceDao.getUserByIdnumber(jsonObj.getString("idnumber"));
        Org org = orgDao.queryByName("外来访客");
        Long userId = null;
        if (user == null) {
            jsonObjFirst = ParaUtils.copyJsonObjectCols(jsonObj, "id,type,parent_id,sort,data_scope,stop,memo,parent_title,title,orgIndexCode,parentOrgIndexCode");
            jsonObjFirst.put("type", "user");
            this.orgDao.addInst(jsonObjFirst);
            jsonObjSecond = ParaUtils.copyJsonObjectCols(jsonObj, "mobile,user_name,pwd,id,title,parent_id,parent_title,lastlogin,job_title,job_id,thirdin,created,updated,photo,idnumber,cardnumber,mcardno,gender,personId");
            jsonObjSecond.put("id", jsonObjFirst.get("id"));
            jsonObjSecond.put("created", System.currentTimeMillis());
            jsonObjSecond.put("userGroup", "外来访客");
            if (org != null) {
                jsonObjSecond.put("parent_id", org.getId());
                jsonObjSecond.put("parent_title", org.getTitle());
            }
            this.userDao.addInst(jsonObjSecond);
            userId = jsonObjSecond.getLong("id");
        } else {
            userId = user.getLong("id");
        }
        User newUser = userDao.queryById(userId);

        visit.put("visit_id", newUser.getId());
        visit.put("visit_title", newUser.getTitle());
        visit.put("interview_ids", jsonObj.getString("interview_ids"));
        visit.put("interview_titles", jsonObj.getString("interview_titles"));
        visit.put("gateway_title", "东二门");
        visit.put("gateway_id", '0');
        visit.put("conftime", System.currentTimeMillis());
        // 8：00至第二天8点
        Date date = new Date();
        date.setHours(8);
        date.setMinutes(0);
        date.setSeconds(0);
        Long starttime = date.getTime();
        visit.put("starttime", starttime);
        Date dateT = new Date();
        dateT.setDate(dateT.getDate() + 1);
        dateT.setHours(8);
        dateT.setMinutes(0);
        dateT.setSeconds(0);
        Long endtime = dateT.getTime();
        visit.put("endtime", endtime);
        visit.put("state", "申请中");
        visit.put("created", System.currentTimeMillis());
        visit.put("updated", System.currentTimeMillis());
        faceDao.insertVisit(visit);
        //向oa系统推送访问记录
        String id = visit.getString("id");
        String guId = newUser.getId() + "";
        String name = newUser.getTitle();
        String certificateNum = newUser.getIdnumber();
        String mobile = newUser.getMobile();
        String gender = newUser.getGender();
        //String targetUserId = visit.getString("interview_ids");
        //String targetUserName = visit.getString("interview_titles");
        String interviewIds = visit.getString("interview_ids");
        String targetUserId = "";
        String msgId = "";
        if (StringUtils.isNotEmpty(interviewIds)) {
            String[] interviewId = interviewIds.split(",");
            for (int i = 0; i < interviewId.length; i++) {
                User person = userDao.queryById((long) Integer.parseInt(interviewId[i]));
                if (person != null) {
                    targetUserId += person.getPersonId() + ",";
                    msgId += visit.getString("id") + ",";
                }
            }
        }
        if (StringUtils.isNotEmpty(targetUserId)) {
            targetUserId = targetUserId.substring(0, targetUserId.length() - 1);
            msgId = msgId.substring(0, msgId.length() - 1);
            String targetUserName = visit.getString("interview_titles");
            //targetUserId = "d6a6d739436a0e804ad275d02d7a58bc";
            //String targetUserName = "张原";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
            String startTime = simpleDateFormat.format(new Date(visit.getLong("starttime")));
            String endTime = simpleDateFormat.format(new Date(visit.getLong("endtime")));
            String createTime = simpleDateFormat.format(new Date());
            String status = visit.getString("state");

            String imgpath = FileUtils.getRealFilePath(newUser.getPhoto());
            String facePic = localhostUri + "/face/img?imgPath=" + imgpath;

            Guest guest = new Guest(id, guId, name, certificateNum, mobile, gender,
                    targetUserId, targetUserName, startTime, endTime, facePic, createTime, status);
            //向oa工作台推送审核消息
            oaApi.sendNewGuestMessage(msgId, guest);
        }

        return new ResponseBean(200, "SUCCESS", "操作成功", null);
    }

    @Override
    public Object visitpersoncheck(JSONObject jsonObj) {
        String idnumber = jsonObj.getString("idnumber");
        if (StringUtils.isEmpty(idnumber) || idnumber.length() != 15 && idnumber.length() != 18) {
            return new ResponseBean(200, "FAILED", "参数有误", null);
        }
        JSONObject user = faceDao.getUserByIdnumber(idnumber);
        if (user == null || user.getLong("id") < 1) {
            jsonObj.put("state", 0);
            jsonObj.put("message", "系统未录入此人");
            jsonObj.put("user", null);
        } else {
            jsonObj.put("state", 1);
            jsonObj.put("message", "系统已录入此人");

            Long userId = user.getLong("id");
            JSONObject visit = faceDao.findVisitByUser(userId);
            if (visit != null) {
                Long starttime = visit.getLong("starttime");
                Long endtime = visit.getLong("endtime");
                Long now = System.currentTimeMillis();

                user.remove("id");
                user.put("id", visit.getLong("id"));
                user.put("visit_id", userId);

                user.put("interview_ids", visit.getString("interview_ids"));
                user.put("interview_titles", visit.getString("interview_titles"));
                user.put("starttime", starttime);
                user.put("endtime", endtime);
                if (now < starttime || now > endtime) {
                    user.put("timemsg", "无有效通行时间");
                }
                jsonObj.put("user", user);
            }
        }
        return new ResponseBean(200, "SUCCESS", "操作成功", jsonObj);
    }

    @Override
    public Object userlist(JSONObject jsonObj) {
        List<JSONObject> childOrg = new ArrayList<>();
        Set<Long> orgIds = new HashSet<>();

        List<JSONObject> orgs = faceDao.findOrgs();
        orgRecursion(childOrg, orgs, jsonObj.getLong("parent_id"));

        for (JSONObject jsonObject : childOrg) {
            orgIds.add(jsonObject.getLong("id"));
        }
        orgIds.add(jsonObj.getLong("parent_id"));
        int totalRec = 0;
        List<Map> list = new ArrayList<>();
        if (orgIds.size() > 0) {
            totalRec = faceDao.countUser(jsonObj, orgIds);
            jsonObj = ParaUtils.checkStartIndex(jsonObj, totalRec);
            list = faceDao.listUserByName(jsonObj, orgIds);
        }

        jsonObj.put("pageData", list);
        jsonObj.put("totalRec", totalRec);
        return new ResponseBean(200, "SUCCESS", "操作成功", jsonObj);
    }

    private void orgRecursion(List<JSONObject> childOrg, List<JSONObject> orgList, Long pid) {
        for (JSONObject org : orgList) {
            if (org.getLong("parent_id") != null) {
                //遍历出父id等于参数的id，add进子节点集合
                if (pid.toString().equals(org.getString("parent_id"))) {
                    //递归遍历下一级
                    childOrg.add(org);
                    Long orgId = org.getLong("id");
                    orgRecursion(childOrg, orgList, orgId);

                }
            }
        }
    }

    @Override
    public Object visitList(JSONObject jsonObj) {
        int totalRec = faceDao.countVisit(jsonObj);
        jsonObj = ParaUtils.checkStartIndex(jsonObj, totalRec);
        List<Map> list = faceDao.listVisitByName(jsonObj);
        jsonObj.put("pageData", list);
        jsonObj.put("totalRec", totalRec);

        return new ResponseBean(200, "SUCCESS", "操作成功", jsonObj);
    }

    @Override
    public Object visitpersonup(JSONObject jsonObj) {
        if (jsonObj == null || jsonObj.getLong("id") < 1) {
            return new ResponseBean(200, "FAILED", "操作失败", null);
        }
        JSONObject user = new JSONObject();
        user.put("id", jsonObj.getLong("visit_id"));
        user.put("mobile", jsonObj.getLong("mobile"));
        user.put("photo", jsonObj.getLong("photo"));
        userDao.updateInst(user);

        JSONObject visit = new JSONObject();
        visit.put("id", jsonObj.getString("id"));
        visit.put("interview_ids", jsonObj.getString("interview_ids"));
        visit.put("interview_titles", jsonObj.getString("interview_titles"));
        visit.put("conftime", System.currentTimeMillis());
        visit.put("starttime", System.currentTimeMillis());
        visit.put("endtime", System.currentTimeMillis() + (24 * 3600 * 1000));
        visit.put("state", "申请中");
        visit.put("updated", System.currentTimeMillis());
        faceDao.updateVisit(visit);
        return new ResponseBean(200, "SUCCESS", "操作成功", jsonObj);
    }

    @Override
    public Object visitpersondel(JSONArray jsonObj) {
        if (jsonObj == null || jsonObj.size() < 1) {
            return new ResponseBean(200, "FAILED", "操作失败", null);
        }
        faceDao.deleteVisitById(jsonObj);
        return new ResponseBean(200, "SUCCESS", "操作成功", jsonObj);
    }

    @Override
    public Object visitresult(JSONObject jsonObj) {
        Long visitId = jsonObj.getLong("id");
        JSONObject visitlog = faceDao.findVisitById(visitId);
        if (visitlog == null) {
            return new ResponseBean(200, "FAILED", "访客申请不存在", null);
        }
        User user = userDao.queryById(visitlog.getLong("visit_id"));
        if (user == null) {
            return new ResponseBean(200, "FAILED", "系统没有此用户", null);
        }

        JSONObject visit = new JSONObject();
        visit.put("id", visitId);
        visit.put("user_title", user.getTitle());
        visit.put("mobile", user.getMobile());
        visit.put("parent_title", user.getParentTitle());
        visit.put("idnumber", user.getIdnumber());
        visit.put("photo", user.getPhoto());
        visit.put("gender", user.getGender());
        visit.put("starttime", visitlog.getLong("starttime"));
        visit.put("endtime", visitlog.getLong("endtime"));
        visit.put("interview_titles", visitlog.getString("interview_titles"));
        visit.put("gateway_title", visitlog.getString("gateway_title"));

        List<JSONObject> list = new ArrayList<>();
        if (StringUtils.isNotEmpty(visitlog.getString("interview_ids"))) {
            list = faceDao.findVisitByInterview(visitId, visitlog.getString("interview_ids"));
        }
        visit.put("visitHistory", list);

        return new ResponseBean(200, "SUCCESS", "操作成功", visit);
    }

    @Override
    public Object visitallow(JSONObject jsonObj) {
        Long visitId = jsonObj.getLong("id");
        JSONObject visitlog = faceDao.findVisitById(visitId);
        if (visitlog == null) {
            return new ResponseBean(200, "FAILED", "访客申请不存在", null);
        }
        if (!Objects.equals("申请中", visitlog.getString("state"))) {
            return new ResponseBean(200, "FAILED", "该申请已处理", null);
        }
        User user = userDao.queryById(visitlog.getLong("visit_id"));
        if (user == null) {
            return new ResponseBean(200, "FAILED", "系统没有此用户", null);
        }
        // 向海康添加人员
        String personId = "";
        if (hikEnable) {
            //通过身份证查询海康是否存在人员
            JSONObject certifi = new JSONObject();
            certifi.put("certificateType", "111");
            certifi.put("certificateNo", user.getIdnumber());
            JSONObject hikperson = ApiService.getPersonbycertificateno(certifi);
            if (hikperson == null) {
                JSONObject person = new JSONObject();
                person.put("personName", user.getTitle());
                if (Objects.equals("男", user.getGender())) {
                    person.put("gender", "1");
                } else if (Objects.equals("女", user.getGender())) {
                    person.put("gender", "2");
                } else {
                    person.put("gender", "0");
                }
                ApiModel.HikOrg hikOrg = ApiService.getOrgRoot();
                if (hikOrg == null) {
                    throw new HikException("海康平台的根部门不存在");
                }
                person.put("orgIndexCode", hikOrg.orgIndexCode);
                person.put("phoneNo", user.getMobile());
                person.put("certificateType", "111");
                person.put("certificateNo", user.getIdnumber());
                if (Objects.equals("", user.getUserName())) {
                    person.put("jobNo", user.getUserName());
                } else {
                    person.put("jobNo", "");
                }
                List<Map> list = new ArrayList();
                Map face = new HashMap();
                String str = "";
                if (StringUtils.isNotEmpty(user.getPhoto())) {
                    str = FileUtils.readImgBase64Code(user.getPhoto());
                }
                face.put("faceData", str);
                list.add(face);
                person.put("faces", list);
                personId = ApiService.addPerson(person);
            } else {
                personId = hikperson.getString("personId");
            }
        }
        if (personId != null) {
            JSONObject olduser = new JSONObject();
            olduser.put("id", user.getId());
            olduser.put("personId", personId);
            userDao.updateInst((JSONObject) JSON.toJSON(olduser));
        }
        // 下发门禁权限
        List<JSONObject> gateways = faceDao.findGatewayByName("东二门");
        if (gateways == null) {
            return new ResponseBean(400, "FAILED", "找不到东二门的门禁设备", jsonObj);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        String startTime = simpleDateFormat.format(new Date(visitlog.getLong("starttime")));
        String endTime = simpleDateFormat.format(new Date(visitlog.getLong("endtime")));

        Set<JSONObject> personInfos = new HashSet<>();
        Set<String> personIds = new HashSet<>();
        //添加人员信息
        if (StringUtils.isNotEmpty(personId)) {
            JSONObject person = faceDao.findUserByPersonId(personId);
            JSONObject personInfo = new JSONObject();
            personInfo.put("personId", person.getString("personId"));
            personInfo.put("operatorType", 1);
            personInfo.put("startTime", startTime);
            personInfo.put("endTime", endTime);
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
                return new ResponseBean(200, "FAILED", "该人员无人脸信息", null);
            }
            String imgPath = localhostUri + "/face/img?imgPath=" + FileUtils.getRealFilePath(photo);
            faceData.put("f" + person.getString("id"), imgPath);
            face.put("data", faceData);
            personInfo.put("face", face);
            personInfos.add(personInfo);
        }
        personIds.add(personId);

        Set<JSONObject> resourceInfos = new HashSet<>();
        JSONObject policy = new JSONObject();
        for (int i = 0; i < gateways.size(); i++) {
            JSONObject gateway = gateways.get(i);
            //添加资源信息
            if (StringUtils.isNotEmpty(gateway.getString("acsDevIndexCode"))) {
                List<JSONObject> doors = faceDao.findGatewayByIndexCode(gateway.getString("acsDevIndexCode"));
                if (doors != null) {
                    for (int k = 0; k < doors.size(); k++) {
                        JSONObject resourceInfo = new JSONObject();
                        resourceInfo.put("resourceIndexCode", doors.get(k).getString("doorIndexCode"));
                        resourceInfo.put("resourceType", doors.get(k).getString("channelType"));
                        JSONArray channelNos = new JSONArray();
                        channelNos.add(doors.get(k).getInteger("channelNo"));
                        resourceInfo.put("channelNos", channelNos);
                        resourceInfos.add(resourceInfo);
                    }
                }
            } else {
                break;
            }
            policy.put("personId", personId);
            policy.put("acsDevIndexCode", gateway.getString("acsDevIndexCode"));
            policy.put("region_id", gateway.getLong("region_id"));
            policy.put("gateway_id", gateway.getLong("id"));
            policy.put("user_id", user.getId());
            policy.put("org_id", user.getParentId());
            policy.put("starttime", visitlog.getLong("starttime"));
            policy.put("endtime", visitlog.getLong("endtime"));
            policy.put("created", System.currentTimeMillis());
            policy.put("updated", System.currentTimeMillis());
            policy.put("state", "配置通过");
            faceDao.insertOrUpdatePolicy(policy);
            policy.clear();
        }
        if (hikEnable) {
            if (resourceInfos.size() > 0 || personIds.size() > 0) {
                ApiService.authDownload(resourceInfos, personInfos, true);
                ApiService.authDownloadSearchList(resourceInfos, personIds);
            }
        }

        String state = "允许";
        faceDao.updateVisitState(visitId, state);
        return new ResponseBean(200, "SUCCESS", "操作成功", jsonObj);
    }

    @Override
    public Object visitprohibit(JSONObject jsonObj) {
        Long visitId = jsonObj.getLong("id");
        if (visitId == null || visitId == 0) {
            return new ResponseBean(200, "FAILED", "操作失败", jsonObj);
        }
        String state = "禁止";
        faceDao.updateVisitState(visitId, state);
        return new ResponseBean(200, "SUCCESS", "操作成功", jsonObj);
    }

    @Override
    public Object cardadd(JSONObject jsonObj) {
        User user = userDao.queryById(jsonObj.getLong("user_id"));
        if (user == null) {
            return new ResponseBean(200, "FAILED", "该人员{" + jsonObj.getString("user_name") + "}在系统不存在，请先进行人员新增", null);
        } else if (user.getCardnumber() >= 5) {
            return new ResponseBean(200, "FAILED", "已到达卡片数量上限", null);
        }
        int count = faceDao.exitsCardByNo(jsonObj.getString("cardno"));
        if (count > 0) {
            return new ResponseBean(200, "FAILED", "该卡已发给其他人员", null);
        } else {
            jsonObj.put("state", "正常");
            faceDao.insertUserCard(jsonObj);
            faceDao.updateUserCardnumber(user.getId(), 1); //人员卡片数量加1
        }
        return new ResponseBean(200, "SUCCESS", "操作成功", jsonObj);
    }

    @Override
    public Object carddel(JSONArray jsonArray) {
        List<JSONObject> jsonObjects = faceDao.findCards(jsonArray);
        for (int i = 0; i < jsonObjects.size(); i++) {
            faceDao.updateUserCardnumber(jsonObjects.get(i).getLong("user_id"), -1); //人员卡片数量加1
        }
        faceDao.deleteCardByIds(jsonArray);
        return new ResponseBean(200, "SUCCESS", "操作成功", null);
    }

    @Override
    public Object cardstart(JSONArray jsonArray) {
        faceDao.updateCardState(jsonArray, "正常");
        return new ResponseBean(200, "SUCCESS", "操作成功", null);
    }

    @Override
    public Object cardstop(JSONArray jsonArray) {
        faceDao.updateCardState(jsonArray, "停用");
        return new ResponseBean(200, "SUCCESS", "操作成功", null);

    }

    @Override
    public Object cardlist(JSONObject jsonObj) {
        int totalRec = faceDao.countCard(jsonObj);
        jsonObj = ParaUtils.checkStartIndex(jsonObj, totalRec);
        List<Map> list = faceDao.listCard(jsonObj);
        jsonObj.put("pageData", list);
        jsonObj.put("totalRec", totalRec);

        return new ResponseBean(200, "SUCCESS", "操作成功", jsonObj);
    }


}