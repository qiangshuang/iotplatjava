package com.ipincloud.iotbj.srv.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.srv.domain.RoleBtn;
import com.ipincloud.iotbj.srv.dao.RoleBtnDao;
import com.ipincloud.iotbj.srv.service.RoleBtnService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(RoleBtn) 服务实现类
//generate by redcloud,2020-07-07 10:18:16
@Service("RoleBtnService")
public class RoleBtnServiceImpl implements RoleBtnService {
    @Resource
    private RoleBtnDao roleBtnDao;
    //@param id 主键 
    //@return 实例对象RoleBtn 
    @Override 
    public RoleBtn queryById(Long id){
        this.roleBtnDao.queryById(id); 
    } 
    //@param jsonObj 过滤条件等 
    //@return JSONObject 
    public void roleBtnSetRelation(@Param("jsonObj") JSONObject jsonObj){
        return this.roleBtnDao.roleBtnSetRelation(jsonObj); 
    } 

}
