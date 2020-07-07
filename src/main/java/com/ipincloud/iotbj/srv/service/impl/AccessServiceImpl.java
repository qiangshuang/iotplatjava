package com.ipincloud.iotbj.srv.service.impl;

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
import com.ipincloud.iotbj.srv.domain.Access;
import com.ipincloud.iotbj.srv.dao.AccessDao;
import com.ipincloud.iotbj.srv.service.AccessService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(Access)门禁编辑 服务实现类
//generate by redcloud,2020-07-07 23:53:41
@Service("AccessService")
public class AccessServiceImpl implements AccessService {
    @Resource
    private AccessDao accessDao;
    //@param id 主键 
    //@return 实例对象Access 
    @Override 
    public Access queryById(Long id){
        this.accessDao.queryById(id); 
    } 
    //@param jsonObj 过滤条件等 
    //@return 实例对象Access 
    Map accessGetAttr(JSONObject jsonObj){
        return this.accessDao.accessGetAttr(jsonObj); 
    } 

}
