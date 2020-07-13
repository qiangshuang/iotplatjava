package com.ipincloud.iotbj.srv.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.srv.domain.Tags;
import com.ipincloud.iotbj.srv.dao.TagsDao;
import com.ipincloud.iotbj.srv.service.TagsService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(Tags)标签管理 服务实现类
//generate by redcloud,2020-07-07 10:18:16
@Service("TagsService")
public class TagsServiceImpl implements TagsService {
    @Resource
    private TagsDao tagsDao;
    //@param id 主键 
    //@return 实例对象Tags 
    @Override 
    public Tags queryById(Long id){
        this.tagsDao.queryById(id); 
    } 

    //@param jsonObj 过滤条件等 
    //@return 对象查询Tags 分页 
    @Override
    public List<Map> tagsList(JSONObject jsonObj){

        int totalRec = this.countTagsList(jsonObj);
        int startIndex = ParaUtils.checkStartIndex(jsonObj,totalRec)
        list<Map> pageData = this.tagsDao.tagsList(jsonObj)
        list<Map> retMap = new HashMap();
        retMap.put("pageData",pageData);
        retMap.put("totalRec",totalRec);
        retMap.put("cp",jsonObj.get("cp"));
        retMap.put("rop",jsonObj.get("rop"));
        return retMap;
    }
            
    
    //@param jsonObj 调用参数  
    //@return 影响记录数 
    @Override 
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Integer deletesInst(JSONObject jsonObj){
        Integer delNum1 = this.tagsDao.deletesInst(jsonObj); 
        Integer delNum2 = new com.ipincloud.iotbj.srv.dao.Dao().deletesInst(jsonObj); 
        return delNum1+delNum2;
    } 

    //@param jsonObj 调用参数 
    //@return 实例对象Tags 
    @Override 
    public JSONObject addInst( JSONObject jsonObj){
        
        Long genId = this.tagsDao.addInst(jsonObj);
        jsonObj.put("id",genId);
        return jsonObj;
            
    } 

    //@param jsonObj 调用参数 
    //@return 影响记录数Tags 
    @Override 
    public void updateInst(JSONObject jsonObj){
        return this.tagsDao.updateInst(jsonObj); 
    } 

}
