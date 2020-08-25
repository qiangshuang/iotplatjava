package com.ipincloud.iotbj.openapi.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.api.utils.hik.ApiModel;
import com.ipincloud.iotbj.api.utils.hik.ApiService;
import com.ipincloud.iotbj.api.utils.hik.HikException;
import com.ipincloud.iotbj.face.dao.FaceDao;
import com.ipincloud.iotbj.openapi.service.IamService;
import com.ipincloud.iotbj.srv.dao.*;
import com.ipincloud.iotbj.srv.domain.Org;
import com.ipincloud.iotbj.sys.domain.ResponseBean;
import com.ipincloud.iotbj.utils.FileUtils;
import com.ipincloud.iotbj.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
@Transactional
public class IamServiceImpl implements IamService {

    @Autowired
    UserDao userDao;
    @Autowired
    OrgDao orgDao;
    @Autowired
    RoleDao roleDao;
    @Autowired
    RolePageDao rolePageDao;
    @Autowired
    UserRoleDao userRoleDao;
    @Resource
    private FaceDao faceDao;
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Value("${hikEnable}")
    private boolean hikEnable;
    @Value("${localhostUri}")
    private String localhostUri;

    //1.增加或更新用户
    @Override
    public Object saveOrUpdateUser(JSONObject jsonObj) {
        if (jsonObj == null || jsonObj.isEmpty()) {
            return new ResponseBean(-1, "FAILED", "没有收到有效数据.", null);
        }
        JSONArray dArr = jsonObj.getJSONArray("saveOrUpdateUser");
        if (dArr == null || dArr.isEmpty() || dArr.size() < 1) {
            return new ResponseBean(-1, "FAILED", "没有收到有效数据.", null);
        }
        JSONObject data = new JSONObject();
        List<JSONObject> success = new ArrayList<>();
        List<JSONObject> error = new ArrayList<>();
        for (int i = 0; i < dArr.size(); i++) {
            JSONObject itemObject = dArr.getJSONObject(i);
            if (itemObject == null) {
                continue;
            }
            JSONObject userJsonObj = new JSONObject();
            //人员编号
            String personId = itemObject.getString("id");
            System.out.println("IAM ID=" + personId);
            if (StringUtils.isNotEmpty(personId)) {
                userJsonObj.put("personId", personId);
            }
            //手机号
            String mobile = itemObject.getString("mobile");
            if (StringUtils.isNotEmpty(mobile)) {
                userJsonObj.put("mobile", mobile);
            } else {
                error.add(itemObject);
                continue;
            }
            //性别
            String sex = itemObject.getString("gender");
            if (StringUtils.isNotEmpty(sex)) {
                String gender = "";
                if (Objects.equals("1", sex)) {
                    gender = "男";
                } else if (Objects.equals("2", sex)) {
                    gender = "女";
                } else {
                    gender = sex;
                }
                userJsonObj.put("gender", gender);
            }
            //工号 || 登录账号loginName
            String no = itemObject.getString("no");
            String loginName = itemObject.getString("loginName");
            if (StringUtils.isEmpty(no)) {
                userJsonObj.put("user_name", StringUtils.isEmpty(loginName) ? "" : loginName);
            } else {
                userJsonObj.put("user_name", StringUtils.isEmpty(no) ? "" : no);
            }

            //姓名
            String title = itemObject.getString("name");
            if (StringUtils.isNotEmpty(title)) {
                userJsonObj.put("title", itemObject.getString("name"));
            } else {
                error.add(itemObject);
                continue;
            }
            //头像
            String fileData = itemObject.getString("photo");
            if (StringUtils.isNotEmpty(fileData)) {
                String jpgBase64 = "data:image/jpg;base64,";
                fileData = fileData.contains(jpgBase64) ? fileData.replaceAll(jpgBase64, "") : fileData;
                String photo = saveBase64File(0L, fileData);
                userJsonObj.put("photo", photo);
            }
            //部门
            String orgId = itemObject.getString("orgId");
            JSONObject orgJson = new JSONObject();
            if (StringUtils.isNotEmpty(orgId)) {
                Org org = orgDao.queryByIndexCode(orgId);
                if (org != null) {
                    orgJson.put("type", "user");
                    orgJson.put("parent_id", org.getId());
                    orgJson.put("title", title);

                    userJsonObj.put("parent_id", org.getId());
                    userJsonObj.put("parent_title", org.getTitle());
                    userJsonObj.put("userGroup", "厂内人员");
                } else {
                    error.add(itemObject);
                    continue;
                }
            } else {
                orgJson.put("type", "user");
                orgJson.put("parent_id", 0);
                orgJson.put("title", title);

                Org org = orgDao.queryByName("外来访客");
                if (org != null) {
                    userJsonObj.put("parent_id", org.getId());
                    userJsonObj.put("parent_title", org.getTitle());
                    userJsonObj.put("userGroup", "外来访客");
                } else {
                    userJsonObj.put("parent_id", 0);
                    userJsonObj.put("parent_title", "");
                    userJsonObj.put("userGroup", "外来访客");
                }
            }

            //身份证
            String certificateNo = itemObject.getString("idCard");
            if (StringUtils.isNotEmpty(certificateNo)) {
                userJsonObj.put("idnumber", certificateNo);
            } else {
                userJsonObj.put("idnumber", genUUNumber());  //随机生成身份证号
            }

            Date date = new Date();//获取当前时间
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.YEAR, 5);
            Long starttime = date.getTime();
            Long endtime = calendar.getTime().getTime();

            JSONObject user = userDao.queryByPersonId(personId);
            JSONObject hikperson = null;
            // 存在人员进行更新
            if (user != null) {
                //更新海康
                if (hikEnable) {
                    //人员ID查询
                    if (hikperson == null && StringUtils.isNotEmpty(personId)) {
                        JSONObject personNo = new JSONObject();
                        personNo.put("personId", personId);
                        hikperson = ApiService.getPersonbyPersonNo(personNo);
                    }
                    //手机号查询
                    if (hikperson == null && StringUtils.isNotEmpty(mobile)) {
                        JSONObject phoneNo = new JSONObject();
                        phoneNo.put("phoneNo", mobile);
                        hikperson = ApiService.getPersonbyPhoneNo(phoneNo);
                    }
                    //身份证查询
                    if (hikperson == null && StringUtils.isNotEmpty(certificateNo)) {
                        JSONObject certificate = new JSONObject();
                        certificate.put("certificateType", "111");
                        certificate.put("certificateNo", certificateNo);
                        hikperson = ApiService.getPersonbycertificateno(certificate);
                    }
                    if (hikperson != null) {
                        JSONObject person = new JSONObject();
                        person.put("personId", hikperson.getString("personId"));
                        person.put("personName", userJsonObj.getString("title"));
                        if (Objects.equals("男", userJsonObj.getString("gender"))) {
                            person.put("gender", "1");
                        } else if (Objects.equals("女", userJsonObj.getString("gender"))) {
                            person.put("gender", "2");
                        } else {
                            person.put("gender", "0");
                        }
                        person.put("phoneNo", userJsonObj.getString("mobile"));
                        if (StringUtils.isNotEmpty(userJsonObj.getString("idnumber"))) {
                            person.put("certificateType", "111");
                            person.put("certificateNo", userJsonObj.getString("idnumber"));
                        } else if (StringUtils.isNotEmpty(userJsonObj.getString("mobile"))) {
                            person.put("certificateType", "111");
                            person.put("certificateNo", userJsonObj.getString("mobile"));
                        } else {
                            person.put("certificateType", "111");
                            person.put("certificateNo", certificateNo);
                        }
                        if (Objects.equals("", userJsonObj.getString("user_name"))) {
                            person.put("jobNo", userJsonObj.getString("user_name"));
                        } else {
                            person.put("jobNo", userJsonObj.getString("jobno"));
                        }
                        ApiService.updatePerson(person);
                    }
                }
                if (userJsonObj != null && StringUtils.isNotEmpty(user.getLong("id").toString())) {
                    genPolicy(personId, starttime, endtime, user.getLong("id"), user.getLong("parent_id")); //下发门禁权限

                    orgJson.put("id", user.getLong("id"));
                    orgDao.updateInst(orgJson);
                    userJsonObj.put("id", user.getLong("id"));
                    userJsonObj.put("updated", System.currentTimeMillis());
                    userDao.updateInst(userJsonObj);
                }
            } else {
                if (userJsonObj != null && orgJson != null) {
                    orgDao.addInst(orgJson);
                    userJsonObj.put("id", orgJson.getLong("id"));
                    userJsonObj.put("created", System.currentTimeMillis());
                    userDao.addInst(userJsonObj);
                }
                //添加到海康
                if (hikEnable) {
                    //人员ID查询
                    if (hikperson == null && StringUtils.isNotEmpty(personId)) {
                        JSONObject personNo = new JSONObject();
                        personNo.put("personId", personId);
                        hikperson = ApiService.getPersonbyPersonNo(personNo);
                    }
                    //手机号查询
                    if (hikperson == null && StringUtils.isNotEmpty(mobile)) {
                        JSONObject phoneNo = new JSONObject();
                        phoneNo.put("phoneNo", mobile);
                        hikperson = ApiService.getPersonbyPhoneNo(phoneNo);
                    }
                    //身份证查询
                    if (hikperson == null && StringUtils.isNotEmpty(certificateNo)) {
                        JSONObject certificate = new JSONObject();
                        certificate.put("certificateType", "111");
                        certificate.put("certificateNo", certificateNo);
                        hikperson = ApiService.getPersonbycertificateno(certificate);
                    }
                    if (hikperson == null || hikperson.isEmpty()) {
                        JSONObject person = new JSONObject();
                        person.put("personId", personId);
                        person.put("personName", userJsonObj.getString("title"));
                        if (Objects.equals("男", userJsonObj.getString("gender"))) {
                            person.put("gender", "1");
                        } else if (Objects.equals("女", userJsonObj.getString("gender"))) {
                            person.put("gender", "2");
                        } else {
                            person.put("gender", "0");
                        }
                        ApiModel.HikOrg hikOrg = ApiService.getOrgRoot();
                        if (hikOrg == null) {
                            throw new HikException("海康平台的根部门不存在");
                        }
                        person.put("orgIndexCode", hikOrg.orgIndexCode);
                        if (StringUtils.isNotEmpty(userJsonObj.getString("mobile"))) {
                            person.put("phoneNo", userJsonObj.getString("mobile"));
                        }
                        if (StringUtils.isNotEmpty(userJsonObj.getString("idnumber"))) {
                            person.put("certificateType", "111");
                            person.put("certificateNo", userJsonObj.getString("idnumber"));
                        } else if (StringUtils.isNotEmpty(userJsonObj.getString("mobile"))) {
                            person.put("certificateType", "111");
                            person.put("certificateNo", userJsonObj.getString("mobile"));
                        } else {
                            person.put("certificateType", "111");
                            person.put("certificateNo", certificateNo);
                        }
                        if (StringUtils.isNotEmpty(userJsonObj.getString("user_name"))) {
                            person.put("jobNo", userJsonObj.getString("user_name"));
                        }

                        if (StringUtils.isNotEmpty(userJsonObj.getString("photo"))) {
                            List<Map> list = new ArrayList();
                            Map face = new HashMap();
                            String str = FileUtils.readImgBase64Code(userJsonObj.getString("photo"));
                            face.put("faceData", str);
                            list.add(face);
                            person.put("faces", list);
                        }
                        personId = ApiService.addPerson(person);
                        if (StringUtils.isEmpty(personId)) {
                            error.add(itemObject);
                            continue;
                        } else {
                            System.out.println("HIK ID=" + personId);
                            genPolicy(personId, starttime, endtime, userJsonObj.getLong("id"), userJsonObj.getLong("parent_id")); //下发门禁权限

                            userJsonObj.put("personId", personId);
                            userJsonObj.put("updated", System.currentTimeMillis());
                            userDao.updateInst(userJsonObj);
                            success.add(itemObject);
                        }
                    }
                }
                data.put("success", success);
                data.put("fail", error);
            }
        }

        return new ResponseBean(0, "SUCCESS", "用户同步成功.", data);
    }

    // //2.批量删除用户
    @Override
    public Object deleteUsers(JSONObject jsonObj) {
        if (jsonObj == null || jsonObj.isEmpty()) {
            return new ResponseBean(-1, "FAILED", "没有收到有效数据.", null);
        }
        String dArr = jsonObj.getString("deleteUsers");
        if (dArr == null || dArr.isEmpty() || dArr.length() < 1) {
            return new ResponseBean(-1, "FAILED", "没有收到有效数据.", null);
        }

        List<String> deleteIds = new ArrayList<>();
        if (StringUtils.isNotEmpty(dArr)) {
            String[] personIds = dArr.split(",");
            for (int j = 0; j < personIds.length; j++) {
                if (StringUtils.isNotEmpty(personIds[j])) {
                    deleteIds.add(personIds[j]);
                }
            }
        }
        if (deleteIds.size() > 0) {
            //1.删除权限配置
            deletePersonPolicy(deleteIds);
            //2.删除海康人员
            JSONObject deletePersonInfo = new JSONObject();
            deletePersonInfo.put("personIds", deleteIds);
            ApiService.deletePerson(deletePersonInfo);
            //3.删除本地权限库
            faceDao.deletePolicyByPersonId(deleteIds);
            //4.删除本地用户信息
            orgDao.deleteOrgPerson(deleteIds);
            orgDao.deleteUserPerson(deleteIds);
        }
        return new ResponseBean(0, "SUCCESS", "删除用户成功.", "");
    }

    //3.批量增加或更新岗位信息
    @Override
    public Object saveOrUpdatePos(JSONObject jsonObj) {
        if (jsonObj == null || jsonObj.isEmpty()) {
            return new ResponseBean(-1, "FAILED", "没有收到有效数据.", null);
        }
        JSONArray dArr = jsonObj.getJSONArray("saveOrUpdatePos");
        if (dArr == null || dArr.isEmpty() || dArr.size() < 1) {
            return new ResponseBean(-1, "FAILED", "没有收到有效数据.", null);
        }

        for (int i = 0; i < dArr.size(); i++) {

            JSONObject itemObject = dArr.getJSONObject(i);
            if (itemObject == null) {
                continue;
            }

            String thirdUUID = itemObject.getString("id");

            JSONObject roleJsonObj = roleDao.queryByThirdUUID(thirdUUID);
            if (roleJsonObj != null) {

                roleJsonObj.put("thirdUUID", thirdUUID);
                roleJsonObj.put("title", itemObject.getString("name"));
                roleDao.updateInst(roleJsonObj);

            } else {

                roleJsonObj = new JSONObject();
                roleJsonObj.put("thirdUUID", thirdUUID);
                roleJsonObj.put("title", itemObject.getString("name"));

                roleDao.addInst(roleJsonObj);

            }
        }

        return new ResponseBean(0, "SUCCESS", "更新或新增角色成功.", null);
    }

    // //4.批量删除岗位信息
    @Override
    public Object deletePoss(JSONObject jsonObj) {
        if (jsonObj == null || jsonObj.isEmpty()) {
            return new ResponseBean(-1, "FAILED", "没有收到有效数据.", null);
        }
        String dArr = jsonObj.getString("deletePoss");
        if (dArr == null || dArr.isEmpty() || dArr.length() < 1) {
            return new ResponseBean(-1, "FAILED", "没有收到有效数据.", null);
        }

        List<String> delThirdUUID = new ArrayList<>();
        if (StringUtils.isNotEmpty(dArr)) {
            String[] personIds = dArr.split(",");
            for (int j = 0; j < personIds.length; j++) {
                if (StringUtils.isNotEmpty(personIds[j])) {
                    delThirdUUID.add(personIds[j]);
                }
            }
        }

        JSONObject jsonQueryRole = new JSONObject();

        Map paraMap = new HashMap();
        paraMap.put("name", "thirdUUID");
        paraMap.put("op", "in");
        paraMap.put("val", delThirdUUID);

        List<Map> roleParaMaps = new ArrayList<>();
        roleParaMaps.add(paraMap);

        jsonQueryRole.put("qps", roleParaMaps);

        List<Map> listRoles = roleDao.roleQuery(jsonQueryRole);

        List<Long> roleIds = new ArrayList<>();
        for (Map roleMap : listRoles) {
            roleIds.add((Long) roleMap.get("id"));
        }


        JSONObject jsonDelUserRole = new JSONObject();

        Map userRoleParaMap = new HashMap();
        userRoleParaMap.put("name", "role_id");
        userRoleParaMap.put("op", "in");
        userRoleParaMap.put("val", roleIds);

        List<Map> userRoleParaMaps = new ArrayList<>();
        userRoleParaMaps.add(userRoleParaMap);

        jsonDelUserRole.put("user_role", userRoleParaMaps);
        userRoleDao.deletesInst(jsonDelUserRole);

        JSONObject jsonDelRole = new JSONObject();
        jsonDelRole.put("role", roleParaMaps);

        roleDao.deletesInst(jsonDelRole);


        return new ResponseBean(0, "SUCCESS", "删除岗位成功.", null);
    }

    // //5.批量增加或更新用户与岗位关系
    @Override
    public Object saveOrUpdateUserPos(JSONObject jsonObj) {
        if (jsonObj == null || jsonObj.isEmpty()) {
            return new ResponseBean(-1, "FAILED", "没有收到有效数据.", null);
        }
        JSONArray dArr = jsonObj.getJSONArray("saveOrUpdateUserPos");
        if (dArr == null || dArr.isEmpty() || dArr.size() < 1) {
            return new ResponseBean(-1, "FAILED", "没有收到有效数据.", null);
        }

        for (int i = 0; i < dArr.size(); i++) {

            JSONObject itemObject = dArr.getJSONObject(i);
            if (itemObject == null) {
                continue;
            }

            String userRoleThirdUUID = itemObject.getString("id");
            String userThirdUUID = itemObject.getString("userId");
            String roleThirdUUID = itemObject.getString("posId");

            if (StringUtils.isEmpty(userRoleThirdUUID) || StringUtils.isEmpty(userThirdUUID) ||
                    StringUtils.isEmpty(roleThirdUUID)) {
                continue;
            }
            JSONObject userRoleJsonObj = userRoleDao.queryByThirdUUID(userRoleThirdUUID);
            if (userRoleJsonObj != null) {
                //check user and role exist ...
                JSONObject roleJsonObj = roleDao.queryByThirdUUID(roleThirdUUID);
                JSONObject userJsonObj = userDao.queryByPersonId(userThirdUUID);
                if (roleJsonObj == null || userJsonObj == null) {
                    logger.info("同步角色或者用户为空." + userRoleThirdUUID);
                    continue;
                }

                userRoleJsonObj.put("user_id", userJsonObj.getLong("id"));
                userRoleJsonObj.put("role_id", roleJsonObj.getLong("id"));
                userRoleJsonObj.put("thirdUUID", userRoleThirdUUID);

                userRoleDao.updateInst(userRoleJsonObj);
            } else {
                //check user and role exist ...
                JSONObject roleJsonObj = roleDao.queryByThirdUUID(roleThirdUUID);
                JSONObject userJsonObj = userDao.queryByPersonId(userThirdUUID);
                if (roleJsonObj == null || userJsonObj == null) {
                    logger.info("同步角色或者用户为空." + userRoleThirdUUID);
                    continue;
                }
                userRoleJsonObj = new JSONObject();
                userRoleJsonObj.put("user_id", userJsonObj.getLong("id"));
                userRoleJsonObj.put("role_id", roleJsonObj.getLong("id"));
                userRoleJsonObj.put("thirdUUID", userRoleThirdUUID);

                userRoleDao.addInst(userRoleJsonObj);
            }

        }

        return new ResponseBean(0, "SUCCESS", "更新或新增用户角色成功.", null);
    }

    //6 删除用户与岗位关系...
    @Override
    public Object deleteUserPoss(JSONObject jsonObj) {
        if (jsonObj == null || jsonObj.isEmpty()) {
            return new ResponseBean(-1, "FAILED", "没有收到有效数据.", null);
        }
        String dArr = jsonObj.getString("deleteUserPoss");
        if (dArr == null || dArr.isEmpty() || dArr.length() < 1) {
            return new ResponseBean(-1, "FAILED", "没有收到有效数据.", null);
        }

        List<String> delThirdUUID = new ArrayList<>();
        if (StringUtils.isNotEmpty(dArr)) {
            String[] thirdUUIDs = dArr.split(",");
            for (int j = 0; j < thirdUUIDs.length; j++) {
                if (StringUtils.isNotEmpty(thirdUUIDs[j])) {
                    delThirdUUID.add(thirdUUIDs[j]);
                }
            }
        }
        if (delThirdUUID != null && delThirdUUID.size() > 0) {
            userRoleDao.deletesByThirdUUID(delThirdUUID);
        }

        return new ResponseBean(0, "SUCCESS", "删除用户岗位成功.", null);

    }

    // 7.更新用户人脸接口
    //[{"id":同步人员Id,filedata:base64编码的人脸文件}] //文件格式为jpg. 200k以下。
    @Override
    public Object saveOrUpdateUserFace(JSONArray jsonObj) {
        if (jsonObj == null || jsonObj.isEmpty()) {
            return new ResponseBean(-1, "FAILED", "没有收到有效数据.", null);
        }
        JSONArray dArr = jsonObj;
        if (dArr == null || dArr.isEmpty() || dArr.size() < 1) {
            return new ResponseBean(-1, "FAILED", "没有收到有效数据.", null);
        }

        List<String> noExists = new ArrayList<>();
        for (int i = 0; i < dArr.size(); i++) {
            JSONObject itemObject = dArr.getJSONObject(i);
            if (itemObject == null) {
                continue;
            }
            String personId = itemObject.getString("id");
            if (StringUtils.isNotEmpty(personId)) {
                JSONObject userJsonObj = userDao.queryByPersonId(personId);
                if (userJsonObj == null) {
                    noExists.add(personId);
                    continue;
                }
                String fileData = itemObject.getString("filedata");
                if (StringUtils.isNotEmpty(fileData)) {
                    String jpgBase64 = "data:image/jpg;base64,";
                    fileData = fileData.contains(jpgBase64) ? fileData.replaceAll(jpgBase64, "") : fileData;
                    Long userId = userJsonObj.getLong("id");
                    String fullfilPath = saveBase64File(userId, fileData);
                    if (fullfilPath == null || StringUtils.isEmpty(fullfilPath)) {
                        return new ResponseBean(-1, "FAILED", "图片无法解析或不是jpg格式", null);
                    }
                    //更新海康
                    if (hikEnable) {
                        JSONObject person = new JSONObject();
                        person.put("personId", personId);
                        /*person.put("personName", userJsonObj.getString("title"));
                        if (Objects.equals("男", userJsonObj.getString("gender"))) {
                            person.put("gender", "1");
                        } else if (Objects.equals("女", userJsonObj.getString("gender"))) {
                            person.put("gender", "2");
                        } else {
                            person.put("gender", "0");
                        }
                        person.put("phoneNo", userJsonObj.getString("mobile"));
                        if (StringUtils.isNotEmpty(userJsonObj.getString("idnumber"))) {
                            person.put("certificateType", "111");
                            person.put("certificateNo", userJsonObj.getString("idnumber"));
                        }
                        if (Objects.equals("", userJsonObj.getString("user_name"))) {
                            person.put("jobNo", userJsonObj.getString("user_name"));
                        }*/
                        person.put("faces", fileData);
                        ApiService.updateFace(person);
                    }
                    userJsonObj.put("photo", fullfilPath);
                    userJsonObj.put("updated", System.currentTimeMillis());
                    userDao.updateInst(userJsonObj);
                }
            }
        }

        return new ResponseBean(0, "SUCCESS", "更行用户人脸资料成功.", noExists);
    }


    //8. 工作票下发人员门禁权限
    @Override
    public Object issueAccessControlAuthority(JSONObject jsonObj) {
        if (jsonObj == null || jsonObj.isEmpty()) {
            return new ResponseBean(-1, "FAILED", "没有收到有效数据.", null);
        }
        List<JSONObject> errList = new ArrayList<>();
        JSONArray ja = jsonObj.getJSONArray("policies");
        for (int i = 0; i < ja.size(); i++) {
            JSONObject policy = ja.getJSONObject(i);
            String personId = policy.getString("userId");
            String starttime = policy.getString("startAt");
            String endtime = policy.getString("endAt");
            String doorIndexCode = policy.getString("gatewayId");
            String gzpId = policy.getString("id");

            if (StringUtils.isEmpty(personId) || StringUtils.isEmpty(starttime) || StringUtils.isEmpty(endtime)
                    || StringUtils.isEmpty(doorIndexCode) || StringUtils.isEmpty(gzpId)) {
                policy.put("errMsg", "没有收到有效数据");
                errList.add(policy);
                continue;
            }
            JSONObject user = faceDao.findUserByPersonId(personId);
            if (user == null) {
                policy.put("errMsg", "系统无此人");
                errList.add(policy);
                continue;
            }
            Long userId = user.getLong("id");
            Long orgId = user.getLong("parent_id");
            String photo = user.getString("photo");
            if (StringUtils.isEmpty(photo)) {
                policy.put("errMsg", "人员无头像");
                errList.add(policy);
                continue;
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date startTime_t = simpleDateFormat.parse(starttime);
                Date endTime_t = simpleDateFormat.parse(endtime);
                genPolicybyGzp(personId, startTime_t.getTime(), endTime_t.getTime(), userId, orgId, doorIndexCode, gzpId);
            } catch (Exception e) {
                policy.put("errMsg", "下发权限失败");
                errList.add(policy);
                continue;
            }
        }
        return new ResponseBean(0, "SUCCESS", "权限下发成功", errList);
    }

    //新增或更新组织机构
    @Override
    public Object saveOrUpdateOrg(JSONObject jsonObj) {
        if (jsonObj == null || jsonObj.isEmpty()) {
            return new ResponseBean(-1, "FAILED", "没有收到有效数据.", null);
        }
        JSONArray dArr = jsonObj.getJSONArray("saveOrUpdateOrg");
        if (dArr == null || dArr.isEmpty() || dArr.size() < 1) {
            return new ResponseBean(-1, "FAILED", "没有收到有效数据.", null);
        }
        List<JSONObject> errList = new ArrayList<>();
        for (int i = 0; i < dArr.size(); i++) {
            JSONObject newOrg = new JSONObject();
            JSONObject itemObject = dArr.getJSONObject(i);
            if (itemObject == null) {
                continue;
            }
            String parentOrgIndexCode = itemObject.getString("parentId");
            if (StringUtils.isEmpty(parentOrgIndexCode)) {
                logger.error("parentId为空");
                itemObject.put("errMsg", "parentId为空");
            } else {
                Org parentOrg = orgDao.queryByIndexCode(parentOrgIndexCode);
                if (parentOrg != null) {
                    newOrg.put("parentOrgIndexCode", parentOrgIndexCode);
                    newOrg.put("parent_id", parentOrg.getId());
                    newOrg.put("parent_title", parentOrg.getTitle());
                } else {
                    logger.error("parentId为无效值");
                    itemObject.put("errMsg", "parentId为无效值");
                }
            }
            String orgIndexCode = itemObject.getString("id");
            if (StringUtils.isEmpty(orgIndexCode)) {
                logger.error("id为空");
                itemObject.put("errMsg", "id为空");
            } else {
                newOrg.put("orgIndexCode", orgIndexCode);
            }
            String title = itemObject.getString("name");
            if (StringUtils.isEmpty(title)) {
                logger.error("name为空");
                itemObject.put("errMsg", "name为空");
            } else {
                newOrg.put("title", title);
            }
            if (StringUtils.isNotEmpty(itemObject.getString("errMsg"))) {
                errList.add(itemObject);
                continue;
            }
            orgDao.insertOrUpdateOrg(newOrg);
        }

        return new ResponseBean(0, "SUCCESS", "新增或更新组织机构成功.", errList);
    }

    //批量删除组织机构
    @Override
    public Object deleteOrgs(JSONObject jsonObj) {
        List<String> list = new ArrayList<>();
        if (jsonObj == null || jsonObj.isEmpty()) {
            return new ResponseBean(-1, "FAILED", "没有收到有效数据.", null);
        }
        String dArr = jsonObj.getString("deleteOrgs");
        if (dArr == null || dArr.isEmpty() || dArr.length() < 1) {
            return new ResponseBean(-1, "FAILED", "没有收到有效数据.", null);
        }
        if (StringUtils.isNotEmpty(dArr)) {
            String[] orgIndexCodes = dArr.split(",");
            for (int i = 0; i < orgIndexCodes.length; i++) {
                if (StringUtils.isNotEmpty(orgIndexCodes[i])) {
                    list.add(orgIndexCodes[i]);
                }
            }
        }
        orgDao.deletesByOrgIndexCode(list);
        return new ResponseBean(0, "SUCCESS", "批量删除组织机构成功", null);
    }

    //IAM下发权限
    private boolean genPolicy(String personId, Long starttime, Long endtime, Long userId, Long orgId) {
        // 下发门禁权限
        List<JSONObject> gateways = faceDao.findGatewayByName("东二门");
        if (gateways == null) {
            return false;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        String startTime = simpleDateFormat.format(new Date(starttime));
        String endTime = simpleDateFormat.format(new Date(endtime));

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
                return false;
            }
            String imgPath = localhostUri + "/face/img?imgPath=" + FileUtils.getRealFilePath(photo);
            faceData.put("f" + person.getString("id"), imgPath);
            face.put("data", faceData);
            personInfo.put("face", face);
            personInfos.add(personInfo);
        }
        personIds.add(personId);

        Set<JSONObject> resourceInfos = new HashSet<>();
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
            JSONObject policy = new JSONObject();
            policy.put("personId", personId);
            policy.put("acsDevIndexCode", gateway.getString("acsDevIndexCode"));
            policy.put("region_id", gateway.getLong("region_id"));
            policy.put("gateway_id", gateway.getLong("id"));
            policy.put("user_id", userId);
            policy.put("org_id", orgId);
            policy.put("starttime", starttime);
            policy.put("endtime", endtime);
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
        return true;
    }

    //工作票下发权限
    private boolean genPolicybyGzp(String personId, Long starttime, Long endtime,
                                   Long userId, Long orgId, String doorIndexCode, String gzpId)
                throws Exception{
        List<JSONObject> gateways = faceDao.findGatewayByDoorIndexCode(doorIndexCode);
        if (gateways == null) {
            return false;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        String startTime = simpleDateFormat.format(new Date(starttime));
        String endTime = simpleDateFormat.format(new Date(endtime));

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
                return false;
            }
            String imgPath = localhostUri + "/face/img?imgPath=" + FileUtils.getRealFilePath(photo);
            faceData.put("f" + person.getString("id"), imgPath);
            face.put("data", faceData);
            personInfo.put("face", face);
            personInfos.add(personInfo);
        }
        personIds.add(personId);

        Set<JSONObject> resourceInfos = new HashSet<>();
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
            JSONObject policy = new JSONObject();
            policy.put("personId", personId);
            policy.put("acsDevIndexCode", gateway.getString("acsDevIndexCode"));
            policy.put("region_id", gateway.getLong("region_id"));
            policy.put("gateway_id", gateway.getLong("id"));
            policy.put("user_id", userId);
            policy.put("org_id", orgId);
            policy.put("starttime", starttime);
            policy.put("endtime", endtime);
            policy.put("created", System.currentTimeMillis());
            policy.put("updated", System.currentTimeMillis());
            policy.put("state", "配置通过");
            policy.put("gzpId", gzpId);
            faceDao.insertOrUpdatePolicy(policy);
            policy.clear();
        }
        if (hikEnable) {
            if (resourceInfos.size() > 0 || personIds.size() > 0) {
                ApiService.authDownload(resourceInfos, personInfos, true);
                ApiService.authDownloadSearchList(resourceInfos, personIds);
            }
        }
        return true;
    }

    private static String genUUNumber() {
        SimpleDateFormat sdf = new SimpleDateFormat("9yyMMddHHmmssSSS");
        String headStr = sdf.format(new Date());
        int num = (int) (Math.random() * 9000 + 1000);

        String tailStr = String.format("%d", num);
        return headStr + tailStr;
    }

    private static Set<String> genUUNumbers(int size) {
        Set<String> retSet = new HashSet<String>();
        while (retSet.size() < size) {
            String genOne = genUUNumber();
            retSet.add(genOne);
        }
        return retSet;
    }

    private static String saveBase64File(Long userId, String base64FileData) {
        // 文件对象
        OutputStream out = null;
        try {

            byte[] byteFileData = Base64.getDecoder().decode(base64FileData);

            String relateType = "headimage";

            //单文件上传
            String uuId = UUID.randomUUID().toString();
            Date date = new Date();
            String dateStr = new SimpleDateFormat("yyyyMMdd").format(date);

            String runPath = System.getProperty("user.dir") + "/classes/upload";

            String retPath = String.format("/%s/%d/%s/%s%s", relateType, userId, dateStr, uuId, ".jpg");
            String fullfilPath = runPath + retPath;

            // 保存到服务器中
            File dest = new File(fullfilPath);
            // 判断路径是否存在，如果不存在则创建
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }

            out = new FileOutputStream(dest);
            out.write(byteFileData);

            return String.format("%s;%s;0;%d;;%d", " ", retPath, userId, System.currentTimeMillis());

        } catch (Exception e) {
            return "";
        } finally {
            try {
                out.flush();
                out.close();
            } catch (Exception e) {
                // do no thing
            }

        }
    }

    private void deletePersonPolicy(List<String> validPeronIds) {
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