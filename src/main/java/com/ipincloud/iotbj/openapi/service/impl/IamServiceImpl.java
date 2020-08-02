package com.ipincloud.iotbj.openapi.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.ipincloud.iotbj.openapi.service.IamService;

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
import com.ipincloud.iotbj.openapi.domain.ResponseOpenApi;
import java.text.SimpleDateFormat;

@Service
@Transactional
public class IamServiceImpl implements IamService {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

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

    //1.增加或更新用户
    @Override
    public Object saveOrUpdateUser(JSONObject jsonObj){

        if(jsonObj == null || jsonObj.isEmpty()){
            return new ResponseBean(200,"FAILED","没有收到有效数据.",null);
        }
        JSONArray dArr = jsonObj.getJSONArray("saveOrUpdateUser");
        if (dArr == null || dArr.isEmpty() || dArr.size() < 1){
            return new ResponseBean(200,"FAILED","没有收到有效数据.",null);
        }

        for (int i = 0; i < dArr.size(); i++) {
            JSONObject itemObject = dArr.getJSONObject(i);
            if(itemObject == null){
                continue;
            }
            String personId = itemObject.getString("personId");
            JSONObject userJsonObj = userDao.queryByPersonId(Long.parseLong(personId));

            if (userJsonObj != null ){

                userJsonObj.put("user_name",itemObject.getString("loginName"));
                userJsonObj.put("mobile",itemObject.getString("mobile"));
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

                userDao.updateInst(userJsonObj);

            }else{
                JSONObject orgJsonObj = new JSONObject();
                orgJsonObj.put("type","user");

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
                orgJsonObj.put("tile",itemObject.getString("name"));
                orgDao.addInst(orgJsonObj);

                userJsonObj = new JSONObject();
                userJsonObj.put("id",orgJsonObj.getLong("id"));
                userJsonObj.put("user_name",itemObject.getString("loginName"));
                userJsonObj.put("mobile",itemObject.getString("mobile"));
                userJsonObj.put("title",itemObject.getString("name"));
                //Long orgId

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
            }
        }

        return new ResponseBean(200,"SUCCESS","用户增加成功.",null);
    }

    // //2.批量删除用户
    @Override 
    public Object deleteUsers(JSONObject jsonObj){

        if(jsonObj == null || jsonObj.isEmpty()){
            return new ResponseBean(200,"FAILED","没有收到有效数据.",null);
        }
        JSONArray dArr = jsonObj.getJSONArray("deleteUsers");
        if (dArr == null || dArr.isEmpty() || dArr.size() < 1){
            return new ResponseBean(200,"FAILED","没有收到有效数据.",null);
        }

        JSONObject jsonQuery = new JSONObject();

        List<Map> qMapList = new ArrayList();
        Map qMap = new HashMap();
        qMap.put("name","personId");
        qMap.put("op","in");
        qMap.put("val",dArr);
        qMapList.add(qMap);

        jsonQuery.put("qps",qMapList);
        jsonQuery.put("cp",1);
        jsonQuery.put("rop",1000);

        List<Map> pageUsers = this.userDao.userList(jsonQuery);

        if (pageUsers == null || pageUsers.size() < 1) {
            return new ResponseBean(200,"FAILED","数据没有需要删除的数据.",null); 
        }
        List<Integer> delIds = new ArrayList();
        for(Map userMap : pageUsers){
            delIds.add( (Integer)userMap.get("id") );
        }

        JSONObject jsonDelUser = new JSONObject();

        Map userParaMap = new HashMap();
        userParaMap.put("name","id");
        userParaMap.put("op","in");
        userParaMap.put("val",delIds);

        List<Map> orgParaMaps = new ArrayList();
        orgParaMaps.add(userParaMap);

        jsonDelUser.put("user",orgParaMaps);

        JSONObject jsonDelOrg = new JSONObject();
        jsonDelOrg.put("org",orgParaMaps);


        Map userRoleParaMap = new HashMap();
        userRoleParaMap.put("name","user_id");
        userRoleParaMap.put("op","in");
        userRoleParaMap.put("val",delIds);

        JSONObject jsonDelUserRole = new JSONObject();
        jsonDelUserRole.put("user_role",userRoleParaMap);


        Integer delNum1 = this.orgDao.deletesInst(jsonDelOrg);
        Integer delNum2 = 0;
        delNum2 = this.userDao.deletesInst(jsonDelUser);
        delNum1 = delNum1 + delNum2;
        delNum2 = this.userRoleDao.deletesInst(jsonDelUserRole);
        delNum1 = delNum1 + delNum2;
        

        return new ResponseBean(200,"SUCCESS","删除用户成功.",delNum1);
    }
    /*
    //3.批量增加或更新岗位信息
    @Override
    public Object saveOrUpdatePos(JSONObject jsonObj){

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
            String personId = itemObject.getString("userId");
            if (StringUtils.isEmpty(personId)) {
                continue;
            }

            JSONObject roleJsonObj = roleDao.queryByThirdUUID(thirdUUID);
            if (roleJsonObj != null ){

                roleJsonObj.put("thirdUUID",thirdUUID);
                roleJsonObj.put("title",itemObject.getString("mainPos"));
                roleObj.updateInst(roleJsonObj);

                JSONObject userJsonObj = userDao.queryByPersonId(personId);
                if(userJsonObj != null){
                    userRoleJsonObj = new JSONObject();
                    userRoleJsonObj.put("user_id",userJsonObj.getLong("id"));
                    userRoleJsonObj.put("role_id",roleJsonObj.getLong("id"));

                    userRoleDao.addInst(userRoleJsonObj);
                }

            }else{
                
                roleJsonObj = new roleJsonObj();
                roleJsonObj.put("thirdUUID",thirdUUID);
                roleJsonObj.put("title",itemObject.getString("mainPos"));

                roleDao.addInst(roleJsonObj);

                JSONObject userJsonObj = userDao.queryByPersonId(personId);
                if(userJsonObj != null){
                    userRoleJsonObj = new JSONObject();
                    userRoleJsonObj.put("user_id",userJsonObj.getLong("id"));
                    userRoleJsonObj.put("role_id",roleJsonObj.getLong("id"));

                    userRoleDao.addInst(userRoleJsonObj);
                }

            }
        }

        return new ResponseBean(200,"SUCCESS","更新或新增角色成功.",null);
    }
    // //4.批量删除岗位信息
    @Override
    public Object deletePoss(JSONObject jsonObj){

        if(jsonObj == null || jsonObj.isEmpty()){
            return new ResponseBean(200,"FAILED","没有收到有效数据.",null);
        }
        Object[] dArr = jsonObj.getJSONArray("deletePoss");
        if (dArr == null || dArr.isEmpty() || dArr.size() < 1){
            return new ResponseBean(200,"FAILED","没有收到有效数据.",null);
        }

        JSONObject jsonDelRole = new JSONObject();

        Map paraMap = new HashMap();
        paraMap.put("name","id");
        paraMap.put("op","in");
        paraMap.put("val",delIds);

        List<Map> roleParaMaps = new List<HashMap>();
        roleParaMaps.add(paraMap);

        jsonDelRole.put("role",roleParaMaps);


        JSONObject jsonDelUserRole = new JSONObject();

        Map userRoleParaMap = new HashMap();
        userRoleParaMap.put("name","role_id");
        userRoleParaMap.put("op","in");
        userRoleParaMap.put("val",delIds);

        List<Map> userRoleParaMaps = new List<HashMap>();
        userRoleParaMaps.add(userRoleParaMap);

        jsonDelUserRole.put("user_role",userRoleParaMaps);

        roleDao.deletesInst(jsonDelRole);
        userRoleDao.deletesInst(jsonDelUserRole);

        return new ResponseBean(200,"SUCCESS","删除角色成功.",null);
    }
    // //5.批量增加或更新用户与岗位关系
    @Override
    public Object saveOrUpdateUserPos(JSONObject jsonObj){

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
                    logger.info("角色或者用户为空.");
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
                    logger.info("角色或者用户为空.");
                    continue;
                }

                userRoleJsonObj.put("user_id",userJsonObj.getLong("id"));
                userRoleJsonObj.put("role_id",roleJsonObj.getLong("id"));

                userRoleDao.addInst(userRoleJsonObj);

            }
            
        }

        return new ResponseBean(200,"SUCCESS","更新或新增用户角色成功.",null);
    }
    @Override
    public Object deleteUserPoss(JSONObject jsonObj){

        if(jsonObj == null || jsonObj.isEmpty()){
            return new ResponseBean(200,"FAILED","没有收到有效数据.",null);
        }
        Object[] dArr = jsonObj.getJSONArray("deleteUserPoss");
        if (dArr == null || dArr.isEmpty() || dArr.size() < 1){
            return new ResponseBean(200,"FAILED","没有收到有效数据.",null);
        }

        List<Integer> delIds = new ArrayList();
        for(Map userMap : pageUsers){
            delIds.add(userMap.get("id"));
        }

        JSONObject jsonDelUserRole = new JSONObject();

        Map paraMap = new HashMap();
        paraMap.put("name","id");
        paraMap.put("op","in");
        paraMap.put("val",delIds);


        List<Map> userRoleParaMaps = new List<HashMap>();
        userRoleParaMaps.add(paraMap);

        jsonDelUserRole.put("user_role",userRoleParaMaps);

        jsonDelUserRole.deletesInst(jsonDelRole);

        return new ResponseBean(200,"SUCCESS","删除角色成功.",null);
    }
    */
    


}