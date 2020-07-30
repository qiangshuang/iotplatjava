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
import com.ipincloud.iotbj.srv.dao.*;
import com.ipincloud.iotbj.srv.service.PageService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(Page) 服务实现类
//generate by redcloud,2020-07-24 19:59:20
@Service("PageService")
public class PageServiceImpl implements PageService {
    @Resource
    private PageDao pageDao;

    //@param id 主键 
    //@return 实例对象Page 
    @Override 
    public Page queryById(Long id){
        return this.pageDao.queryById(id); 
    } 
    //@param userId 用户ID 
    //@return PageMap列表 
    @Override 
    public List<Map> queryAllByUser(long userId){
        return this.pageDao.queryAllByUser(userId); 
    } 

    //@param jsonObj 过滤条件等 
    //@return 实例对象Page 
    @Override 
    public Map pageRolePageMmlist(JSONObject jsonObj){
         int totalRec = this.pageDao.countPageRolePageMmlist(jsonObj); 
        jsonObj.put("totalRec",totalRec);
        jsonObj = ParaUtils.checkStartIndex(jsonObj,totalRec);
        List<Map> pageData = this.pageDao.pageRolePageMmlist(jsonObj);

        Map retMap = new HashMap();
        retMap.put("pageData",pageData);
        retMap.put("totalRec",totalRec);
        retMap.put("cp",jsonObj.get("cp"));
        retMap.put("rop",jsonObj.get("rop"));
        return retMap;
    }
    }
