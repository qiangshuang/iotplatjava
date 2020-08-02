package com.ipincloud.iotbj.srv.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.ipincloud.iotbj.oa.OAApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.srv.domain.RolePage;
import com.ipincloud.iotbj.srv.dao.*;
import com.ipincloud.iotbj.srv.service.RolePageService;
import com.ipincloud.iotbj.utils.ParaUtils;

//(RolePage) 服务实现类
//generate by redcloud,2020-07-24 19:59:20
@Service("RolePageService")
public class RolePageServiceImpl implements RolePageService {
    @Resource
    private RolePageDao rolePageDao;

    @Autowired
    OAApi oaApi;

    //@param id 主键 
    //@return 实例对象RolePage 
    @Override
    public RolePage queryById(Long id) {
        return this.rolePageDao.queryById(id);
    }

    //@param jsonObj 新增数据等 
    //@return JSONObject 查询
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer rolePageMmjoin(JSONObject jsonObj) {
        int roleId = jsonObj.getIntValue("roleId");
        String roleName = jsonObj.getString("roleName");
        String thirdRoleUUID = jsonObj.getString("thirdUUID");
        JSONArray rmRolePage = jsonObj.getJSONArray("rm_role_page");
        oaApi.removeRoleMenu(thirdRoleUUID, roleName, rmRolePage);
        oaApi.saveOrUpdateRoleMenu(thirdRoleUUID, roleName, jsonObj.getJSONArray("role_page"));
        this.rolePageDao.deleteByRoleID(roleId + "");
        return this.rolePageDao.rolePageMmjoin(jsonObj);

    }


    //@param jsonObj 新增数据等 
    //@return JSONObject 查询
    @Override
    public Integer rolePageMmsub(JSONObject jsonObj) {

        return this.rolePageDao.rolePageMmsub(jsonObj);

    }

}
