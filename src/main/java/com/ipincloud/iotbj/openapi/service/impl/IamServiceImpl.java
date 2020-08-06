package com.ipincloud.iotbj.openapi.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.api.utils.AlgorithmFaceUtils;
import com.ipincloud.iotbj.openapi.service.IamService;
import com.ipincloud.iotbj.api.utils.hik.ApiService;
import com.ipincloud.iotbj.api.utils.hik.ApiModel;
import com.ipincloud.iotbj.api.utils.hik.HikException;
import com.ipincloud.iotbj.srv.dao.OrgDao;
import com.ipincloud.iotbj.srv.dao.UserDao;
import com.ipincloud.iotbj.srv.dao.RoleDao;
import com.ipincloud.iotbj.srv.dao.RolePageDao;
import com.ipincloud.iotbj.srv.dao.UserRoleDao;
import com.ipincloud.iotbj.srv.domain.Org;
import com.ipincloud.iotbj.sys.domain.ResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ipincloud.iotbj.utils.FileUtils;
import com.ipincloud.iotbj.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.io.*;

import java.text.SimpleDateFormat;
import java.util.Base64;


@Service
@Transactional
public class IamServiceImpl implements IamService {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Value("${hikEnable}")
    private boolean hikEnable;

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

    public static String genUUNumber() {
        SimpleDateFormat sdf = new SimpleDateFormat("9yyMMddHHmmssSSS");
        String headStr = sdf.format(new Date());
        int num = (int) (Math.random() * 9000 + 1000);

        String tailStr = String.format("%d", num);
        return headStr + tailStr;
    }

    public static Set<String> genUUNumbers(int size) {
        Set<String> retSet = new HashSet<String>();
        while (retSet.size() < size) {
            String genOne = genUUNumber();
            retSet.add(genOne);
        }
        return retSet;
    }


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
        Set<String> certificateNoGen = genUUNumbers(dArr.size());
        Map<String, JSONObject> errMap = new HashMap<>();
        for (int i = 0; i < dArr.size(); i++) {
            JSONObject itemObject = dArr.getJSONObject(i);
            if (itemObject == null) {
                continue;
            }
            JSONObject userJsonObj = new JSONObject();
            //手机号
            String mobile = itemObject.getString("mobile");
            if (StringUtils.isNotEmpty(mobile)) {
                userJsonObj.put("mobile", mobile);
            } else {
                errMap.put(i + "手机号为空", itemObject);
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
            //工号
            String loginName = itemObject.getString("loginName");
            userJsonObj.put("user_name", StringUtils.isEmpty(loginName) ? "" : loginName);
            //姓名
            String title = itemObject.getString("name");
            if (StringUtils.isNotEmpty(title)) {
                userJsonObj.put("title", itemObject.getString("name"));
            } else {
                errMap.put(i + "姓名为空", itemObject);
                continue;
            }
            //头像
            String photo = itemObject.getString("photo");
            if (StringUtils.isNotEmpty(itemObject.getString("photo"))) {
                photo = saveBase64File(0L, photo);
                userJsonObj.put("photo", photo);
            }
            //用户类型
            String userGroup = itemObject.getString("user_type");
            if (StringUtils.isNotEmpty(userGroup)) {
                userJsonObj.put("userGroup", userGroup);
            } else {
                userJsonObj.put("userGroup", "外来访客");
            }
            //部门
            String orgId = itemObject.getString("orgId");
            JSONObject orgJson = new JSONObject();
            if (StringUtils.isNotEmpty(orgJson)) {
                Org org = orgDao.queryByIndexCode(orgId);
                if (org != null) {
                    userJsonObj.put("parent_id", org.getId());
                    userJsonObj.put("parent_title", org.getParentTitle());

                    orgJson.put("type", "user");
                    orgJson.put("parent_id", org.getId());
                    orgJson.put("title", title);
                } else {
                    errMap.put(i + "部门不存在", itemObject);
                    continue;
                }
            } else {
                orgJson.put("type", "user");
                orgJson.put("parent_id", 0);
                orgJson.put("title", title);
            }

            String certificateNo = genUUNumber(); //随机生成身份证号

            String personId = itemObject.getString("id");
            JSONObject user = userDao.queryByPersonId(personId);
            // 存在人员进行更新
            if (user != null) {
                //更新海康
                if (hikEnable) {
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
                    person.put("phoneNo", userJsonObj.getString("mobile"));
                    person.put("certificateType", "111");
                    if (StringUtils.isNotEmpty(userJsonObj.getString("idnumber"))) {
                        person.put("certificateNo", userJsonObj.getString("idnumber"));
                    } else if (StringUtils.isNotEmpty(userJsonObj.getString("mobile"))) {
                        person.put("certificateNo", userJsonObj.getString("mobile"));
                    } else {
                        person.put("certificateNo", certificateNo);
                    }

                    if (Objects.equals("", userJsonObj.getString("user_name"))) {
                        person.put("jobNo", userJsonObj.getString("user_name"));
                    } else {
                        person.put("jobNo", userJsonObj.getString("jobno"));
                    }
                    if (StringUtils.isNotEmpty(userJsonObj.getString("photo"))) {
                        String str = FileUtils.readImgBase64Code(userJsonObj.getString("photo"));
                        person.put("faces", str);
                    }
                    ApiService.updatePerson(person);
                }
                if (userJsonObj != null && StringUtils.isNotEmpty(user.getLong("id").toString())) {
                    orgJson.put("id", user.getLong("id"));
                    orgDao.updateInst(orgJson);
                    userJsonObj.put("id", user.getLong("id"));
                    userJsonObj.put("updated", System.currentTimeMillis());
                    userDao.updateInst(userJsonObj);
                }
            } else {
                if (userJsonObj != null) {
                    orgJson.put("id", user.getLong("id"));
                    orgDao.addInst(orgJson);
                    userJsonObj.put("id", orgJson.getLong("id"));
                    userJsonObj.put("personId", personId);
                    userJsonObj.put("created", System.currentTimeMillis());
                    userDao.addInst(userJsonObj);
                }
                //添加到海康
                if (hikEnable) {
                    //通过身份证或手机号查询海康是否存在人员
                    JSONObject hikperson = null;
                    JSONObject certificate = new JSONObject();
                    certificate.put("certificateType", "111");
                    if (StringUtils.isNotEmpty(userJsonObj.getString("idnumber"))) {
                        certificate.put("certificateNo", userJsonObj.getString("idnumber"));
                    } else if (StringUtils.isNotEmpty(certificateNo)) {
                        certificate.put("certificateNo", certificateNo);
                    }
                    hikperson = ApiService.getPersonbycertificateno(certificate);
                    if (hikperson == null) {
                        JSONObject phoneNo = new JSONObject();
                        if (StringUtils.isNotEmpty(mobile)) {
                            phoneNo.put("phoneNo", mobile);
                            hikperson = ApiService.getPersonbyPhoneNo(phoneNo);
                        }
                    }
                    if (hikperson == null) {
                        JSONObject person = new JSONObject();
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
                        person.put("certificateType", "111");
                        if (StringUtils.isNotEmpty(userJsonObj.getString("idnumber"))) {
                            person.put("certificateNo", userJsonObj.getString("idnumber"));
                        } else if (StringUtils.isNotEmpty(userJsonObj.getString("mobile"))) {
                            person.put("certificateNo", userJsonObj.getString("mobile"));
                        } else {
                            person.put("certificateNo", certificateNo);
                        }

                        if (StringUtils.isNotEmpty(loginName)) {
                            person.put("jobNo", loginName);
                        } else {
                            person.put("jobNo", "");
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
                            throw new HikException("海康平台添加人员失败");
                        }
                    }
                    if (userJsonObj != null && StringUtils.isNotEmpty(personId)) {
                        userJsonObj.put("personId", personId);
                        userDao.updateInst(userJsonObj);
                    }
                }

            }
        }

        return new ResponseBean(0, "SUCCESS", "用户同步成功.", errMap);
    }

    // //2.批量删除用户
    @Override
    public Object deleteUsers(JSONObject jsonObj) {

        if (jsonObj == null || jsonObj.isEmpty()) {
            return new ResponseBean(200, "FAILED", "没有收到有效数据.", null);
        }
        JSONArray dArr = jsonObj.getJSONArray("deleteUsers");
        if (dArr == null || dArr.isEmpty() || dArr.size() < 1) {
            return new ResponseBean(200, "FAILED", "没有收到有效数据.", null);
        }

        List<String> deleteIds = new ArrayList<>();
        for (int i = 0; i < dArr.size(); i++) {
            JSONObject itemObject = dArr.getJSONObject(i);
            if (itemObject == null) {
                continue;
            }
            String thirdUUID = itemObject.getString("id");
            if (StringUtils.isNotEmpty(thirdUUID)) {
                deleteIds.add(thirdUUID);

            }
        }
        JSONObject jsonQueryUser = new JSONObject();

        List<Map> qMapList = new ArrayList();
        Map qMap = new HashMap();
        qMap.put("name", "thirdUUID");
        qMap.put("op", "in");
        qMap.put("val", deleteIds);
        qMapList.add(qMap);

        jsonQueryUser.put("qps", qMapList);

        List<Map> userDelList = userDao.userList(jsonQueryUser);

        List<String> personIds = new ArrayList<>();
        List<Long> delIds = new ArrayList<>();
        for (Map userMap : userDelList) {
            if (StringUtils.isNotEmpty(userMap.get("person_id").toString())) {
                personIds.add(userMap.get("person_id").toString());
                delIds.add(Long.parseLong(userMap.get("id").toString()));
            }
        }
        if (hikEnable) {

            if (personIds != null && personIds.size() > 0) {

                if (personIds.size() > 0) {
                    JSONObject deleteVehicle = new JSONObject();
                    deleteVehicle.put("personIds", personIds);
                    ApiService.deletePerson(deleteVehicle);
                }

            }

        }

        JSONObject jsonDelUser = new JSONObject();
        jsonDelUser.put("user", qMapList);

        this.userDao.deletesInst(jsonDelUser);

        qMapList = new ArrayList();
        qMap = new HashMap();
        qMap.put("name", "user_id");
        qMap.put("op", "in");
        qMap.put("val", delIds);
        qMapList.add(qMap);

        JSONObject jsonDelUserRole = new JSONObject();
        jsonDelUserRole.put("user_role", qMapList);
        this.userRoleDao.deletesInst(jsonDelUserRole);

        qMapList = new ArrayList();
        qMap = new HashMap();
        qMap.put("name", "id");
        qMap.put("op", "in");
        qMap.put("val", delIds);
        qMapList.add(qMap);
        JSONObject orgJsonDel = new JSONObject();
        orgJsonDel.put("org", qMapList);
        this.orgDao.deletesInst(orgJsonDel);

        return new ResponseBean(0, "SUCCESS", "删除用户成功.", "");
    }

    //3.批量增加或更新岗位信息
    @Override
    public Object saveOrUpdatePos(JSONObject jsonObj) {

        if (jsonObj == null || jsonObj.isEmpty()) {
            return new ResponseBean(200, "FAILED", "没有收到有效数据.", null);
        }
        JSONArray dArr = jsonObj.getJSONArray("saveOrUpdatePos");
        if (dArr == null || dArr.isEmpty() || dArr.size() < 1) {
            return new ResponseBean(200, "FAILED", "没有收到有效数据.", null);
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

        return new ResponseBean(-1, "SUCCESS", "更新或新增角色成功.", null);
    }

    // //4.批量删除岗位信息
    @Override
    public Object deletePoss(JSONObject jsonObj) {

        if (jsonObj == null || jsonObj.isEmpty()) {
            return new ResponseBean(200, "FAILED", "没有收到有效数据.", null);
        }
        JSONArray dArr = jsonObj.getJSONArray("deletePoss");
        if (dArr == null || dArr.isEmpty() || dArr.size() < 1) {
            return new ResponseBean(200, "FAILED", "没有收到有效数据.", null);
        }

        List<String> delThirdUUID = new ArrayList<>();
        for (int i = 0; i < dArr.size(); i++) {
            JSONObject itemObject = dArr.getJSONObject(i);
            if (itemObject == null) {
                continue;
            }
            if (StringUtils.isNotEmpty(itemObject.getString("id"))) {
                delThirdUUID.add(itemObject.getString("id"));
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


        return new ResponseBean(-1, "SUCCESS", "删除岗位成功.", null);
    }

    // //5.批量增加或更新用户与岗位关系
    @Override
    public Object saveOrUpdateUserPos(JSONObject jsonObj) {

        if (jsonObj == null || jsonObj.isEmpty()) {
            return new ResponseBean(200, "FAILED", "没有收到有效数据.", null);
        }
        JSONArray dArr = jsonObj.getJSONArray("saveOrUpdatePos");
        if (dArr == null || dArr.isEmpty() || dArr.size() < 1) {
            return new ResponseBean(200, "FAILED", "没有收到有效数据.", null);
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
                JSONObject userJsonObj = userDao.queryByThirdUUID(userThirdUUID);
                if (roleJsonObj == null || userJsonObj == null) {
                    logger.info("同步角色或者用户为空." + userRoleThirdUUID);
                    continue;
                }

                userRoleJsonObj.put("user_id", userJsonObj.getLong("id"));
                userRoleJsonObj.put("role_id", roleJsonObj.getLong("id"));

                userRoleDao.updateInst(userRoleJsonObj);
            } else {
                //check user and role exist ...
                JSONObject roleJsonObj = roleDao.queryByThirdUUID(roleThirdUUID);
                JSONObject userJsonObj = userDao.queryByThirdUUID(userThirdUUID);
                if (roleJsonObj == null || userJsonObj == null) {
                    logger.info("同步角色或者用户为空." + userRoleThirdUUID);
                    continue;
                }
                userRoleJsonObj = new JSONObject();
                userRoleJsonObj.put("user_id", userJsonObj.getLong("id"));
                userRoleJsonObj.put("role_id", roleJsonObj.getLong("id"));
                userRoleJsonObj.put("thirdUUID", Long.parseLong(userRoleThirdUUID));

                userRoleDao.addInst(userRoleJsonObj);

            }

        }

        return new ResponseBean(-1, "SUCCESS", "更新或新增用户角色成功.", null);
    }

    //6 删除用户与岗位关系...
    @Override
    public Object deleteUserPoss(JSONObject jsonObj) {

        if (jsonObj == null || jsonObj.isEmpty()) {
            return new ResponseBean(200, "FAILED", "没有收到有效数据.", null);
        }
        JSONArray dArr = jsonObj.getJSONArray("deleteUserPoss");
        if (dArr == null || dArr.isEmpty() || dArr.size() < 1) {
            return new ResponseBean(200, "FAILED", "没有收到有效数据.", null);
        }

        for (int i = 0; i < dArr.size(); i++) {
            JSONObject itemObject = dArr.getJSONObject(i);
            if (itemObject == null) {
                continue;
            }
            String thirdUUIDRole = itemObject.getString("posId");
            String thirdUUIDUser = itemObject.getString("userId");

            JSONObject roleJsonObj = roleDao.queryByThirdUUID(thirdUUIDRole);
            JSONObject userJsonObj = userDao.queryByThirdUUID(thirdUUIDUser);

            if (roleJsonObj == null || userJsonObj == null) {
                continue;
            }

            JSONObject jsonDelUserRole = new JSONObject();

            List<Map> userRoleParaMaps = new ArrayList<>();

            Map paraMap = new HashMap();
            paraMap.put("name", "role_id");
            paraMap.put("op", "=");
            paraMap.put("val", roleJsonObj.getLong("id"));

            userRoleParaMaps.add(paraMap);

            paraMap = new HashMap();
            paraMap.put("name", "user_id");
            paraMap.put("op", "=");
            paraMap.put("val", userJsonObj.getLong("id"));
            userRoleParaMaps.add(paraMap);

            jsonDelUserRole.put("user_role", userRoleParaMaps);

            userRoleDao.deletesInst(jsonDelUserRole);
        }

        return new ResponseBean(-1, "SUCCESS", "删除用户岗位成功.", null);
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
                    Long userId = userJsonObj.getLong("id");
                    String fullfilPath = saveBase64File(userId, fileData);
                    if (fullfilPath == null || StringUtils.isEmpty(fullfilPath)) {
                        return new ResponseBean(-1, "FAILED", "图片无法解析或不是jpg格式", null);
                    }
                    //更新海康
                    if (hikEnable) {
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
                        person.put("phoneNo", userJsonObj.getString("mobile"));
                        person.put("certificateType", "111");
                        if (StringUtils.isNotEmpty(userJsonObj.getString("idnumber"))) {
                            person.put("certificateNo", userJsonObj.getString("idnumber"));
                        } else {
                            person.put("certificateNo", userJsonObj.getString("mobile"));
                        }

                        if (Objects.equals("", userJsonObj.getString("user_name"))) {
                            person.put("jobNo", userJsonObj.getString("user_name"));
                        } else {
                            person.put("jobNo", userJsonObj.getString("jobno"));
                        }
                        person.put("faces", fileData);
                        ApiService.updatePerson(person);
                    }
                    userJsonObj.put("photo", fullfilPath);
                    userJsonObj.put("updated", System.currentTimeMillis());
                    userDao.updateInst(userJsonObj);
                }
            }
        }

        return new ResponseBean(0, "SUCCESS", "更行用户人脸资料成功.", noExists);
    }

    public static String saveBase64File(Long userId, String base64FileData) {
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
}