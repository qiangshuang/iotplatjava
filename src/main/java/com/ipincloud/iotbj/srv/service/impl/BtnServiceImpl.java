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
import com.ipincloud.iotbj.srv.domain.Btn;
import com.ipincloud.iotbj.srv.dao.*;
import com.ipincloud.iotbj.srv.service.BtnService;
import com.ipincloud.iotbj.utils.ParaUtils;
//(Btn) 服务实现类
//generate by redcloud,2020-07-24 19:59:20
@Service("BtnService")
public class BtnServiceImpl implements BtnService {
    @Resource
    private BtnDao btnDao;

    //@param id 主键 
    //@return 实例对象Btn 
    @Override 
    public Btn queryById(Long id){
        return this.btnDao.queryById(id); 
    } 
    //@param userId 用户ID 
    //@return 实例Btn列表 
    @Override 
    public String queryByUserId(long userId){

        List<Btn> listBtns = this.btnDao.queryByUserId(userId); 
        StringBuffer sbf = new StringBuffer();
        if (listBtns != null && listBtns.size() > 0){
            
            for(int i = 0 ; i < listBtns.size() ; i ++){

                Btn btn = listBtns.get(i);
                
                if (btn.getEncode().length() > 0 && btn.getNeedLogin() != "f" && btn.getNeedLogin() != "F" 
                        && btn.getNeedLogin() != "False" && btn.getNeedLogin() != "FALSE" 
                        && btn.getNeedLogin() != "false"){

                    sbf.append(";"+btn.getEncode());
                    
                }

            }
        }
        String retStr = sbf.toString();
        if (retStr != null && retStr.length() > 0){
            retStr = retStr.substring(1);
        }
        return retStr;
        
    } 

    //@param jsonObj 过滤条件等 
    //@return 对象查询Btn 分页 
    @Override
    public Map btnList(JSONObject jsonObj){

        int totalRec = this.btnDao.countBtnList(jsonObj);
        jsonObj = ParaUtils.checkStartIndex(jsonObj,totalRec);
        List<Map> pageData = this.btnDao.btnList(jsonObj);
        Map retMap = new HashMap();
        retMap.put("pageData",pageData);
        retMap.put("totalRec",totalRec);
        retMap.put("cp",jsonObj.get("cp"));
        retMap.put("rop",jsonObj.get("rop"));
        return retMap;
    }
            
    }
