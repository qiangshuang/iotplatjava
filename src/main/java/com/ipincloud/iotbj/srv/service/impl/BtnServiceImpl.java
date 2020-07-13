package com.ipincloud.iotbj.srv.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.srv.domain.Btn;
import com.ipincloud.iotbj.srv.dao.BtnDao;
import com.ipincloud.iotbj.srv.service.BtnService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(Btn) 服务实现类
//generate by redcloud,2020-07-07 10:18:15
@Service("BtnService")
public class BtnServiceImpl implements BtnService {
    @Resource
    private BtnDao btnDao;
    //@param id 主键 
    //@return 实例对象Btn 
    @Override 
    public Btn queryById(Long id){
        this.btnDao.queryById(id); 
    } 
    //@param userId 用户ID 
    //@return 实例Btn列表 
    @Override 
    public List<Btn> queryByUserId(long userId){
        return this.btnDao.queryByUserId(userId); 
    } 

    //@param jsonObj 过滤条件等 
    //@return 实例对象Btn 
    @Override 
    public List<Map> btnRoleBtnMmlist JSONObject jsonObj){
         int totalRec = this.btnDao.countBtnRoleBtnMmlist(jsonObj); 
        jsonObj.put("totalRec",totalRec);
        int startIndex = ParaUtils.checkStartIndex(jsonObj,totalRec);
        list<Map> pageData = this.btnDao.btnRoleBtnMmlist(jsonObj);

        list<Map> retMap = new HashMap();
        retMap.put("pageData",pageData);
        retMap.put("totalRec",totalRec);
        retMap.put("cp",jsonObj.get("cp"));
        retMap.put("rop",jsonObj.get("rop"));
        return retMap;
    }
    
}
