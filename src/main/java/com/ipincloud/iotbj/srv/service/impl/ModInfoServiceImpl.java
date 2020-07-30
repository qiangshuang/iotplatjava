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
import com.ipincloud.iotbj.srv.domain.ModInfo;
import com.ipincloud.iotbj.srv.dao.*;
import com.ipincloud.iotbj.srv.service.ModInfoService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(ModInfo)模型表 服务实现类
//generate by redcloud,2020-07-24 19:59:20
@Service("ModInfoService")
public class ModInfoServiceImpl implements ModInfoService {
    @Resource
    private ModInfoDao modInfoDao;

    //@param id 主键 
    //@return 实例对象ModInfo 
    @Override 
    public ModInfo queryById(Long id){
        return this.modInfoDao.queryById(id); 
    } 

    //@param jsonObj 调用参数 
    //@return 实例对象ModInfo 
    @Override 
    public JSONObject addInst( JSONObject jsonObj){
        
        jsonObj = ParaUtils.removeSurplusCol(jsonObj,"mod_id,mod_name,mod_desc,mod_index,mod_index_type,mod_index_len,mod_key,user_name");
        this.modInfoDao.addInst(jsonObj);
        return jsonObj; 
                
    } 
}
