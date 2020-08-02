package com.ipincloud.iotbj.srv.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.api.utils.hik.ApiService;
import com.ipincloud.iotbj.srv.dao.RegionDao;
import com.ipincloud.iotbj.srv.domain.Region;
import com.ipincloud.iotbj.srv.service.RegionService;
import com.ipincloud.iotbj.utils.ParaUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

//(Region)区域 服务实现类
//generate by redcloud,2020-07-23 11:43:18
@Service("RegionService")
public class RegionServiceImpl implements RegionService {
    @Resource
    private RegionDao regionDao;

    @Value("${hikEnable}")
    private boolean hikEnable;

    //@param id 主键 
    //@return 实例对象Region 
    @Override
    public Region queryById(Long id) {
        return this.regionDao.queryById(id);
    }

    //@param jsonObj 调用参数  
    //@return 影响记录数 
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer deletesRegionInst(JSONObject jsonObj) {
        Integer delNum1 = this.regionDao.deletesInst(jsonObj);
        return delNum1;
    }

    //@param jsonObj 过滤条件等 
    //@return 对象Region 树形查询
    @Override
    public List<Map> regionTree(JSONObject jsonObj) {

        if (ParaUtils.notHaveColVal(jsonObj, "parent_id") != null && ParaUtils.notHaveColVal(jsonObj, "parent_id").length() > 0) {
            return this.regionDao.queryTreeHp(jsonObj);
        } else {
            return this.regionDao.queryTreeNp(jsonObj);
        }
    }


    //@param jsonObj 调用参数 
    //@return 实例对象Region 
    @Override
    public JSONObject addInst(JSONObject jsonObj) {

        jsonObj = ParaUtils.removeSurplusCol(jsonObj, "id,title,parent_id,regionIndexCode,indexCode,parentIndexCode");
        Region region = regionDao.queryById(jsonObj.getLong("parent_id"));
        String parentIndexCode = region.getIndexCode();
        jsonObj.put("indexCode", UUID.randomUUID().toString());
        jsonObj.put("parentIndexCode", parentIndexCode);

        this.regionDao.addInst(jsonObj);
        if (hikEnable) {
            List<JSONObject> list = new ArrayList();
            JSONObject regionObj = new JSONObject();
            regionObj.put("clientId", jsonObj.getLong("id"));
            regionObj.put("regionCode", jsonObj.getString("indexCode"));
            regionObj.put("regionName", jsonObj.getString("title"));
            regionObj.put("parentIndexCode", parentIndexCode);
            regionObj.put("regionType", 10);
            regionObj.put("description", null);

            list.add(regionObj);
            ApiService.addBatchRegion(list);
        }

        return jsonObj;

    }


    //@param jsonObj 调用参数 
    //@return 影响记录数Region 
    @Override
    public void updateInst(JSONObject jsonObj) {
        jsonObj = ParaUtils.removeSurplusCol(jsonObj, "id,title,parent_id,regionIndexCode,indexCode,parentIndexCode");
        this.regionDao.updateInst(jsonObj);
        if (hikEnable) {
            JSONObject region = new JSONObject();
            region.put("regionIndexCode", jsonObj.getString("indexCode"));
            region.put("regionName", jsonObj.getString("title"));
            ApiService.updateRegion(region);
        }
    }
}
