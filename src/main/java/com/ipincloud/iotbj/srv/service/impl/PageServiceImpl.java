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
import com.ipincloud.iotbj.srv.domain.Page;
import com.ipincloud.iotbj.srv.dao.PageDao;
import com.ipincloud.iotbj.srv.service.PageService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(Page) 服务实现类
//generate by redcloud,2020-07-07 23:53:41
@Service("PageService")
public class PageServiceImpl implements PageService {
    @Resource
    private PageDao pageDao;
    //@param id 主键 
    //@return 实例对象Page 
    @Override 
    public Page queryById(Long id){
        this.pageDao.queryById(id); 
    } 
    //@param userId 用户ID 
    //@return PageMap列表 
    @Override 
    public List<Map> queryAllByUser(long userId){
        return this.pageDao.queryAllByUser(userId); 
    } 

    //@param jsonObj 过滤条件等 
    //@return 对象Page 查询
    @Override
    public List<Map> pageQuery(JSONObject jsonObj){

        return this.pageDao.pageQuery(jsonObj);
        
    }
    
}
