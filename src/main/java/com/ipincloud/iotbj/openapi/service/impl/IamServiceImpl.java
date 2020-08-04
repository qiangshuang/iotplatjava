package com.ipincloud.iotbj.openapi.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.ipincloud.iotbj.openapi.service.IamService;
import com.ipincloud.iotbj.api.utils.hik.ApiService;
import com.ipincloud.iotbj.srv.dao.OrgDao;
import com.ipincloud.iotbj.srv.dao.UserDao;
import com.ipincloud.iotbj.srv.dao.RoleDao;
import com.ipincloud.iotbj.srv.dao.RolePageDao;
import com.ipincloud.iotbj.srv.dao.UserRoleDao;

import com.ipincloud.iotbj.srv.domain.User;
import com.ipincloud.iotbj.srv.domain.Org;
import com.ipincloud.iotbj.srv.domain.Role;
import com.ipincloud.iotbj.srv.domain.RolePage;
import com.ipincloud.iotbj.srv.domain.UserRole;
import com.ipincloud.iotbj.srv.domain.Page;

import com.ipincloud.iotbj.sys.domain.ResponseBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ipincloud.iotbj.utils.FileUtils;
import com.ipincloud.iotbj.utils.ParaUtils;
import com.ipincloud.iotbj.utils.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.io.*;
import com.ipincloud.iotbj.openapi.domain.ResponseOpenApi;
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

    public static String genUUNumber(){
        SimpleDateFormat sdf = new SimpleDateFormat("9yyMMddHHmmssSSS");
        String headStr = sdf.format(new Date());
        int num = (int)(Math.random()*9000+1000);
        
        String tailStr = String.format("%d",num);
        return headStr + tailStr;
    }
    public static Set<String> genUUNumbers(int size) {
        Set<String> retSet = new HashSet<String>();
        while(retSet.size() < size ){
            String genOne = genUUNumber();
            retSet.add(genOne);
        }
        return retSet;
    }
    //1.增加或更新用户
    @Override
    public Object saveOrUpdateUser(JSONObject jsonObj){

        /*
        if(jsonObj == null || jsonObj.isEmpty()){
            return new ResponseBean(200,"FAILED","没有收到有效数据.",null);
        }
        JSONArray dArr = jsonObj.getJSONArray("saveOrUpdateUser");
        if (dArr == null || dArr.isEmpty() || dArr.size() < 1){
            return new ResponseBean(200,"FAILED","没有收到有效数据.",null);
        }
        Set<String> certificateNoGen = genUUNumbers(dArr.size());

        for (int i = 0; i < dArr.size(); i++) {
            JSONObject itemObject = dArr.getJSONObject(i);
            if(itemObject == null){
                continue;
            }
            String personId = itemObject.getString("personId");
            JSONObject userJsonObj = userDao.queryByPersonId(Long.parseLong(personId));

            if (userJsonObj != null ){

                if(StringUtils.isEmpty( itemObject.getString("mobile") ) ){
                    String genOne ="";
                    Iterator<String> it = certificateNoGen.iterator();
                    if(it.hasNext()) {
                        genOne = it.next();
                        certificateNoGen.remove(genOne);
                    }
                    userJsonObj.put("mobile",genOne);
                }else{
                    userJsonObj.put("mobile",itemObject.getString("mobile"));
                }
                
                userJsonObj.put("gender",itemObject.getString("gender"));
                userJsonObj.put("user_name",itemObject.getString("loginName"));
                userJsonObj.put("title",itemObject.getString("name"));
                

                Long orgId = itemObject.getLong("orgId");
                if (orgId > 0 ){
                    userJsonObj.put("parent_id",orgId);
                    //查用户部门title
                    Org org = orgDao.queryById(itemObject.getLong("orgId"));
                    if(org != null ){
                        userJsonObj.put("parent_title",org.getParentTitle());
                    }
                }else{
                    userJsonObj.put("parent_id",0);
                }
                
                
                String photo = itemObject.getString("photo");
                if( StringUtils.isNotEmpty( itemObject.getString("photo") ) ){
                    userJsonObj.put("photo"," ;/upload/headimage/"+photo);
                }

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");

                if (StringUtils.isNotEmpty(itemObject.getString("createDate"))){
                    try{
                        Date date = simpleDateFormat.parse(itemObject.getString("createDate"));
                        userJsonObj.put("created",date.getTime());
                    }catch(Exception ex){
                        logger.error("error",ex.toString());
                    }
                    
                }
                if (StringUtils.isNotEmpty(itemObject.getString("updateDate"))){
                    try{
                        Date date = simpleDateFormat.parse(itemObject.getString("updateDate"));
                        userJsonObj.put("updated",date.getTime());
                    }catch(Exception ex){
                        logger.error("error",ex.toString());
                    }
                }

                //更新海康。
                if (hikEnable) {

                    String personId = userJsonObj.getString("personId");

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
                    if(StringUtils.isNotEmpty(userJsonObj.getString("idnumber"))){
                        person.put("certificateNo", userJsonObj.getString("idnumber"));
                    }else{
                        person.put("certificateNo", userJsonObj.getString("mobile"));
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
                    
                    ApiService.updatePerson(userJsonObj);
                }

                userDao.updateInst(userJsonObj);

            }else{
                JSONObject orgJsonObj = new JSONObject();
                orgJsonObj.put("type","user");
                orgJsonObj.put("orgIndexCode", UUID.randomUUID().toString());

                Long orgId = itemObject.getLong("orgId");
                if (orgId > 0 ){
                    orgJsonObj.put("parent_id",orgId);
                    //查用户部门title
                    Org org = orgDao.queryById(itemObject.getLong("orgId"));
                    if(org != null ){
                        orgJsonObj.put("parent_title",org.getParentTitle());
                    }
                }else{
                    orgJsonObj.put("parent_id",0);
                }

                orgJsonObj.put("stop","f");
                orgJsonObj.put("title",itemObject.getString("name"));

                orgDao.addInst(orgJsonObj);

                userJsonObj = new JSONObject();
                userJsonObj.put("id",orgJsonObj.getLong("id"));
                userJsonObj.put("user_name",itemObject.getString("loginName"));
                userJsonObj.put("jobno",itemObject.getString("no"));

                if(StringUtils.isEmpty( itemObject.getString("mobile") ) ){
                    String genOne = certificateNoGen.get(0);
                    certificateNoGen.remove(0);
                    userJsonObj.put("mobile",genOne);
                }else{
                    userJsonObj.put("mobile",itemObject.getString("mobile"));
                }

                userJsonObj.put("title",itemObject.getString("name"));
                
                userJsonObj.put("userGroup", "场内人员");
                

                if (orgId > 0 ){
                    userJsonObj.put("parent_id",orgId);
                    //查用户部门title
                    Org org = orgDao.queryById(itemObject.getLong("orgId"));
                    if(org != null ){
                        userJsonObj.put("parent_title",org.getParentTitle());
                    }
                }else{
                    userJsonObj.put("parent_id",0);
                }

                String photo = itemObject.getString("photo");
                if( StringUtils.isNotEmpty( itemObject.getString("photo") ) ){
                    userJsonObj.put("photo"," ;/upload/headimage/"+photo);
                }

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");

                if (StringUtils.isNotEmpty(itemObject.getString("createDate"))){
                    try{
                        Date date = simpleDateFormat.parse(itemObject.getString("createDate"));
                        userJsonObj.put("created",date.getTime());
                    
                    }catch(Exception ex){
                        logger.error("error",ex.toString());
                    }
                }
                if (StringUtils.isNotEmpty(itemObject.getString("updateDate"))){
                    try{
                        Date date = simpleDateFormat.parse(itemObject.getString("updateDate"));
                        userJsonObj.put("updated",date.getTime());
                    }catch(Exception ex){
                        logger.error("error",ex.toString());
                    }
                }

                userDao.addInst(userJsonObj);


                String personId = null;
                if (hikEnable) {
                    //通过身份证查询海康是否存在人员
                    JSONObject certifi = new JSONObject();
                    certifi.put("certificateType", "111");
                    certifi.put("certificateNo", userJsonObj.getString("mobile"));
                    JSONObject hikperson = ApiService.getPersonbycertificateno(certifi);
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
                        if (userJsonObj.getString("mobile").length() < 16){
                            person.put("phoneNo", userJsonObj.getString("mobile"));
                        }
                        person.put("certificateType", "111");
                        person.put("certificateNo", userJsonObj.getString("mobile"));

                        if (Objects.equals("", userJsonObj.getString("jobNo"))) {
                            person.put("jobNo", userJsonObj.getString("user_name"));
                        } else {
                            person.put("jobNo", userJsonObj.getString("jobno"));
                        }
                        //List<Map> list = new ArrayList();
                        //Map face = new HashMap();
                        //String str = "";
                        //if (StringUtils.isNotEmpty(jsonObjSecond.getString("photo"))) {
                        //    str = FileUtils.readImgBase64Code(jsonObjSecond.getString("photo"));
                        //}
                        //face.put("faceData", str);
                        //list.add(face);
                        //person.put("faces", list);
                        personId = ApiService.addPerson(person);
                        if (StringUtils.isEmpty(personId)) {
                            throw new HikException("海康平台添加人员失败");
                        }
                    }else{
                        throw new HikException("海康平台上已经部分人员资料");
                    }

                }
                if (personId != null) {
                    userJsonObj.put("personId", personId);
                    userDao.updateInst(userJsonObj);
                    //AlgorithmFaceUtils.registerFace(algorithmFaceRegisterUrl, personId, userJsonObj.getString("photo"));
                }

                //return jsonObj;


            }
        }
        */
        return new ResponseBean(-1,"SUCCESS","用户同步成功.",null);
    }

    // //2.批量删除用户
    @Override 
    public Object deleteUsers(JSONObject jsonObj){
        /*
        if(jsonObj == null || jsonObj.isEmpty()){
            return new ResponseBean(200,"FAILED","没有收到有效数据.",null);
        }
        JSONArray dArr = jsonObj.getJSONArray("deleteUsers");
        if (dArr == null || dArr.isEmpty() || dArr.size() < 1){
            return new ResponseBean(200,"FAILED","没有收到有效数据.",null);
        }

        List<String> deleteIds = new List<>();
        for (int i = 0; i < dArr.size(); i++) {
            JSONObject itemObject = dArr.getJSONObject(i);
            if(itemObject == null){
                continue;
            }
            String thirdUUID = itemObject.getString("id");
            if(StringUtils.isNotEmpty(thirdUUID)){
                deleteIds.add(thirdUUID);
                
            }
        }
        JSONObject jsonQueryUser = new JSONObject();

        List<Map> qMapList = new ArrayList();
        Map qMap = new HashMap();
        qMap.put("name","thirdUUID");
        qMap.put("op","in");
        qMap.put("val",deleteIds);
        qMapList.add(qMap);

        jsonQueryUser.put("qps",qMapList);

        List<JSONObject> userDelList = userDao.userList(jsonQueryUser);

        List<String> personIds = new List<>();
        List<Long> delIds = new List<>();
        for(JSONObject userJson:userDelList){
            if (StringUtils.isNotEmpty(userJson.getString("person_id"))){
                personIds.add(userJson.getString("person_id") );
                delIds.add(userJson.getLong("id"));
            }
        }
        if (hikEnable) {
            
            if (personIds != null && personIds.size() > 0) {
               
                if(personIds.size()>0) {
                    JSONObject deleteVehicle = new JSONObject();
                    deleteVehicle.put("personIds", personIds);
                    ApiService.deletePerson(deleteVehicle);
                }
                
            }
            
        }

        JSONObject jsonDelUser = new JSONObject();
        jsonDelUser.put("user",qMapList);

        this.userDao.deletesInst(jsonDelUser);

        qMapList = new ArrayList();
        qMap = new HashMap();
        qMap.put("name","user_id");
        qMap.put("op","in");
        qMap.put("val",delIds);
        qMapList.add(qMap);

        JSONObject jsonDelUserRole = new JSONObject();
        jsonDelUserRole.put("user_role",qMapList);
        this.userRoleDao.deletesInst(jsonDelUserRole);

        MapList = new ArrayList();
        qMap = new HashMap();
        qMap.put("name","id");
        qMap.put("op","in");
        qMap.put("val",delIds);
        qMapList.add(qMap);
        JSONObject orgJsonDel = new JSONObject();
        orgJsonDel.put("org",qMapList);
        this.orgDao.deletesInst(orgJsonDel);
        */
        return new ResponseBean(-1,"SUCCESS","删除用户成功.","");
    }
    
    //3.批量增加或更新岗位信息
    @Override
    public Object saveOrUpdatePos(JSONObject jsonObj){
        /*
        if(jsonObj == null || jsonObj.isEmpty()){
            return new ResponseBean(200,"FAILED","没有收到有效数据.",null);
        }
        Object[] dArr = jsonObj.getJSONArray("saveOrUpdatePos");
        if (dArr == null || dArr.isEmpty() || dArr.size() < 1){
            return new ResponseBean(200,"FAILED","没有收到有效数据.",null);
        }

        for (int i = 0; i < dArr.size(); i++) {

            JSONObject itemObject = dArr.getJSONObject(i);
            if(itemObject == null){
                continue;
            }

            String thirdUUID = itemObject.getString("id");

            JSONObject roleJsonObj = roleDao.queryByThirdUUID(thirdUUID);
            if (roleJsonObj != null ){

                roleJsonObj.put("thirdUUID",thirdUUID);
                roleJsonObj.put("title",itemObject.getString("name"));
                roleObj.updateInst(roleJsonObj);

            }else{
                
                roleJsonObj = new roleJsonObj();
                roleJsonObj.put("thirdUUID",thirdUUID);
                roleJsonObj.put("title",itemObject.getString("name"));

                roleDao.addInst(roleJsonObj);

            }
        }
        */
        return new ResponseBean(-1,"SUCCESS","更新或新增角色成功.",null);
    }
    // //4.批量删除岗位信息
    @Override
    public Object deletePoss(JSONObject jsonObj){
        /*
        if(jsonObj == null || jsonObj.isEmpty()){
            return new ResponseBean(200,"FAILED","没有收到有效数据.",null);
        }
        Object[] dArr = jsonObj.getJSONArray("deletePoss");
        if (dArr == null || dArr.isEmpty() || dArr.size() < 1){
            return new ResponseBean(200,"FAILED","没有收到有效数据.",null);
        }

        List<String> delThirdUUID = new List<>();
        for (int i = 0; i < dArr.size(); i++) {
            JSONObject itemObject = dArr.getJSONObject(i);
            if(itemObject == null){
                continue;
            }
            if(StringUtils.isNotEmpty( itemObject.getString("id") )){
                delThirdUUID.add(itemObject.getString("id"));
            }
            
        }
        JSONObject jsonQueryRole = new JSONObject();

        Map paraMap = new HashMap();
        paraMap.put("name","thirdUUID");
        paraMap.put("op","in");
        paraMap.put("val",delIds);

        List<Map> roleParaMaps = new List<HashMap>();
        roleParaMaps.add(paraMap);

        jsonQueryRole.put("qps",roleParaMaps);

        List<Map> listRoles = roleDao.roleQuery(jsonQueryRole);

        List<Long> roleIds = new List<>();
        for(Map roleMap : listRoles){
            roleIds.add((Long)roleMap.get("id"));
        }


        JSONObject jsonDelUserRole = new JSONObject();

        Map userRoleParaMap = new HashMap();
        userRoleParaMap.put("name","role_id");
        userRoleParaMap.put("op","in");
        userRoleParaMap.put("val",roleIds);

        List<Map> userRoleParaMaps = new List<HashMap>();
        userRoleParaMaps.add(userRoleParaMap);

        jsonDelUserRole.put("user_role",userRoleParaMaps);
        userRoleDao.deletesInst(jsonDelUserRole);

        jsonDelRole = new JSONObject();
        jsonDelRole.put("role",roleParaMaps);

        roleDao.deletesInst(jsonDelRole);
        
        */
        return new ResponseBean(-1,"SUCCESS","删除岗位成功.",null);
    }
    // //5.批量增加或更新用户与岗位关系
    @Override
    public Object saveOrUpdateUserPos(JSONObject jsonObj){
        /*
        if(jsonObj == null || jsonObj.isEmpty()){
            return new ResponseBean(200,"FAILED","没有收到有效数据.",null);
        }
        Object[] dArr = jsonObj.getJSONArray("saveOrUpdatePos");
        if (dArr == null || dArr.isEmpty() || dArr.size() < 1){
            return new ResponseBean(200,"FAILED","没有收到有效数据.",null);
        }

        for (int i = 0; i < dArr.size(); i++) {

            JSONObject itemObject = dArr.getJSONObject(i);
            if(itemObject == null){
                continue;
            }

            String userRoleThirdUUID = itemObject.getString("id");
            String userThirdUUID = itemObject.getString("userId");
            String roleThirdUUID = itemObject.getString("posId");
            if (StringUtils.isEmpty(userRoleThirdUUID) || StringUtils.isEmpty(userThirdUUID)||
                StringUtils.isEmpty(roleThirdUUID) ) {
                continue;
            }
            JSONObject userRoleJsonObj =  userRoleDao.queryByThirdUUID(userRoleThirdUUID);
            if (userRoleJsonObj != null ){
                //check user and role exist ...
                JSONObject roleJsonObj = roleDao.queryByThirdUUID(roleThirdUUID);
                JSONObject userJsonObj = userDao.queryByThirdUUID(userThirdUUID);
                if(roleJsonObj ==null || userJsonObj == null){
                    logger.info("同步角色或者用户为空."+userRoleThirdUUID);
                    continue;
                }

                userRoleJsonObj.put("user_id",userJsonObj.getLong("id"));
                userRoleJsonObj.put("role_id",roleJsonObj.getLong("id"));

                userRoleDao.updateInst(userRoleJsonObj);
            }else{
                //check user and role exist ...
                JSONObject roleJsonObj = roleDao.queryByThirdUUID(roleThirdUUID);
                JSONObject userJsonObj = userDao.queryByThirdUUID(userThirdUUID);
                if(roleJsonObj ==null || userJsonObj == null){
                    logger.info("同步角色或者用户为空."+userRoleThirdUUID);
                    continue;
                }
                userRoleJsonObj = new JSONObject();
                userRoleJsonObj.put("user_id",userJsonObj.getLong("id"));
                userRoleJsonObj.put("role_id",roleJsonObj.getLong("id"));
                userRoleJsonObj.put("thirdUUID",Long.parseLong(userRoleThirdUUID));

                userRoleDao.addInst(userRoleJsonObj);

            }
            
        }
        */
        return new ResponseBean(-1,"SUCCESS","更新或新增用户角色成功.",null);
    }
    //6 删除用户与岗位关系...
    @Override
    public Object deleteUserPoss(JSONObject jsonObj){
        /*
        if(jsonObj == null || jsonObj.isEmpty()){
            return new ResponseBean(200,"FAILED","没有收到有效数据.",null);
        }
        JSONArray[] dArr = jsonObj.getJSONArray("deleteUserPoss");
        if (dArr == null || dArr.isEmpty() || dArr.size() < 1){
            return new ResponseBean(200,"FAILED","没有收到有效数据.",null);
        }

        for (int i = 0; i < dArr.size(); i++) {
            JSONObject itemObject = dArr.getJSONObject(i);
            if(itemObject == null){
                continue;
            }
            String thirdUUIDRole = itemObject.getString("posId");
            String thirdUUIDUser = itemObject.getString("userId");

            JSONObject roleJsonObj = roleDao.queryByThirdUUID(thirdUUIDRole);
            JSONObject userJsonObj = userDao.queryByThirdUUID(thirdUUIDUser);

            if (roleJsonObj == null || userJsonObj == null){
                continue;
            }

            JSONObject jsonDelUserRole = new JSONObject();

            List<Map> userRoleParaMaps = new ArrayList<>();

            Map paraMap = new HashMap();
            paraMap.put("name","role_id");
            paraMap.put("op","=");
            paraMap.put("val",roleJsonObj.getLong("id"));

            userRoleParaMaps.add(paraMap);

            paraMap = new HashMap();
            paraMap.put("name","user_id");
            paraMap.put("op","=");
            paraMap.put("val",userJsonObj.getLong("id"));
            userRoleParaMaps.add(paraMap);

            jsonDelUserRole.put("user_role",userRoleParaMaps);

            userRoleDao.deletesInst(jsonDelUserRole);
        }
        */
        return new ResponseBean(-1,"SUCCESS","删除用户岗位成功.",null);
    }
    // 7.更新用户人脸接口
    //[{"id":同步人员Id,filedata:base64编码的人脸文件}] //文件格式为jpg. 200k以下。
    @Override 
    public Object saveOrUpdateUserFace(JSONObject jsonObj){
        
        if(jsonObj == null || jsonObj.isEmpty()){
            return new ResponseBean(200,"FAILED","没有收到有效数据.",null);
        }
        JSONArray dArr = jsonObj.getJSONArray("saveOrUpdateUserFace");
        if (dArr == null || dArr.isEmpty() || dArr.size() < 1){
            return new ResponseBean(200,"FAILED","没有收到有效数据.",null);
        }

        List<String> noExists = new ArrayList<>();
        List<Long> personIds = new ArrayList<>();
        List<JSONObject> userJsonList = new ArrayList<>();
        for (int i = 0; i < dArr.size(); i++) {
            JSONObject itemObject = dArr.getJSONObject(i);
            if(itemObject == null){
                continue;
            }
            String personId = itemObject.getString("id");
            if (StringUtils.isNotEmpty(personId)){
                JSONObject userJsonObj = userDao.queryByThirdUUID(personId);
                if(userJsonObj == null){
                    noExists.add(personId);
                    continue;
                }
                String fileData = itemObject.getString("filedata");

                if (StringUtils.isNotEmpty(fileData) ){

                    Long userId = userJsonObj.getLong("id");
                    String fullfilPath = SaveBase64File(userId,fileData);
                               
                    //更新海康。
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
                        if(StringUtils.isNotEmpty(userJsonObj.getString("idnumber"))){
                            person.put("certificateNo", userJsonObj.getString("idnumber"));
                        }else{
                            person.put("certificateNo", userJsonObj.getString("mobile"));
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
                        
                        ApiService.updatePerson(userJsonObj);
                    }

                    userDao.updateInst(userJsonObj);
                        
                    
                }

            }
        }
        
        return new ResponseBean(-1,"SUCCESS","更行用户人脸资料成功.",null);
    }

    public static String SaveBase64File(Long userId,String base64FileData)  {

        byte[] byteFileData = Base64.getDecoder().decode(base64FileData);

        String relateType = "headimage";

        //单文件上传
        String uuId =  UUID.randomUUID().toString();
        Date date = new Date();
        String dateStr = new SimpleDateFormat("yyyyMMdd").format(date);
        
        String runPath = System.getProperty("user.dir")+"/classes/upload";
        
        String retPath = String.format("/%s/%d/%s/%s%s",relateType,userId,dateStr,uuId,".jpg");
        String fullfilPath = runPath+retPath;

        // 文件对象
        
        OutputStream out =null;
        try {
            // 保存到服务器中
            File dest = new File( fullfilPath );
            // 判断路径是否存在，如果不存在则创建
            if(!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            
            out = new FileOutputStream(dest);
            out.write(byteFileData);
            out.flush();

            out.close();
            

            return String.format("%s;%s;0;%d;;%d"," ",retPath,userId,System.currentTimeMillis());
            
        } catch (Exception e) {
            try{
                out.close();
            }catch(Exception ex){

            }
            return "";
        }

    }
}