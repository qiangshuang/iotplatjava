package com.ipincloud.iotbj.srv.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.oa.OAApi;
import com.ipincloud.iotbj.srv.dao.RoleDao;
import com.ipincloud.iotbj.srv.dao.RolePageDao;
import com.ipincloud.iotbj.srv.domain.Role;
import com.ipincloud.iotbj.srv.domain.RolePage;
import com.ipincloud.iotbj.srv.service.RolePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

//(RolePage) 服务实现类
//generate by redcloud,2020-07-24 19:59:20
@Service("RolePageService")
public class RolePageServiceImpl implements RolePageService {
    @Resource
    private RolePageDao rolePageDao;
    @Resource
    private RoleDao roleDao;
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
        int roleId = -1;
        String roleName = null;
        String thirdRoleUUID = null;
        JSONArray ja = jsonObj.getJSONArray("role_page");
        if (ja != null) {
            roleId = ja.getJSONObject(0).getIntValue("role_id");
        }
        Role role = roleDao.queryById(Long.parseLong(roleId+""));
        roleName = role.getTitle();
        thirdRoleUUID = role.getThirdUUID();
//        int roleId = jsonObj.getIntValue("roleId");
//        String roleName = jsonObj.getString("roleName");
//        String thirdRoleUUID = jsonObj.getString("thirdUUID");
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
