package com.ipincloud.iotbj.srv.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.api.utils.hik.ApiService;
import com.ipincloud.iotbj.srv.dao.VehicleDao;
import com.ipincloud.iotbj.srv.domain.Vehicle;
import com.ipincloud.iotbj.srv.service.VehicleService;
import com.ipincloud.iotbj.utils.ParaUtils;
import com.ipincloud.iotbj.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

//(Vehicle)车辆白名单 服务实现
//generate by redcloud,2020-07-23 11:43:18
@Service("VehicleService")
@Transactional
public class VehicleServiceImpl implements VehicleService {
    @Resource
    private VehicleDao vehicleDao;

    @Value("${hikEnable}")
    private boolean hikEnable;

    //@param id 主键 
    //@return 实例对象Vehicle 
    @Override
    public Vehicle queryById(Long id) {
        return this.vehicleDao.queryById(id);
    }

    //@param jsonObj 调用参数 
    //@return 实例对象Vehicle 
    @Override
    public JSONObject addInst(JSONObject jsonObj) {
        jsonObj = ParaUtils.removeSurplusCol(jsonObj, "id,title,category,starttime,endtime,owner,idnumber,mobile,pic,created,updated");
        this.vehicleDao.addInst(jsonObj);

        if (hikEnable) {
            JSONObject vehicleSearch = new JSONObject();
            vehicleSearch.put("plateNo", jsonObj.getString("title"));
            vehicleSearch.put("pageNo", 1);
            vehicleSearch.put("pageSize", 1000);
            JSONObject vehicleResult = ApiService.searchVehicle(vehicleSearch);
            if (vehicleResult == null || vehicleResult.getLong("total") == 0) {
                List<JSONObject> vehicles = new ArrayList<>();
                JSONObject vehicle = new JSONObject();
                vehicle.put("clientId", jsonObj.getString("id"));
                vehicle.put("plateNo", jsonObj.getString("title"));
                vehicles.add(vehicle);
                JSONObject addResult = ApiService.addVehicle(vehicles);
                if (addResult != null && addResult.getJSONArray("successes").size() > 0) {
                    JSONObject successe = addResult.getJSONArray("successes").getJSONObject(0);
                    if (Objects.equals(successe.getString("clientId"), jsonObj.getString("id"))) {
                        jsonObj.put("vehicleId", successe.getString("vehicleId"));
                    }
                }
            } else {
                ApiService.fixed(jsonObj.getString("title"));
                String vehicleId = vehicleResult.getJSONArray("list").getJSONObject(0).getString("vehicleId");
                jsonObj.put("vehicleId", vehicleId);
            }
            vehicleDao.updateInst(jsonObj);
        }
        // jsonObj.put("id",genId);
        return jsonObj;

    }

    //@param jsonObj 调用参数 
    //@return 影响记录数Vehicle 
    @Override
    public void updateInst(JSONObject jsonObj) {
        jsonObj = ParaUtils.removeSurplusCol(jsonObj, "id,title,category,starttime,endtime,owner,idnumber,mobile,pic,created,updated");
        this.vehicleDao.updateInst(jsonObj);

        if (hikEnable) {
            Vehicle vehicle = vehicleDao.queryById(jsonObj.getLong("id"));
            if (vehicle != null && StringUtils.isNotEmpty(vehicle.getVehicleId())) {
                JSONObject updateVehicle = new JSONObject();
                updateVehicle.put("vehicleId", vehicle.getVehicleId());
                updateVehicle.put("plateNo", vehicle.getTitle());
                ApiService.updateVehicle(updateVehicle);
            }
        }
    }

    //@param jsonObj 过滤条件等 
    //@return 对象查询Vehicle 分页 
    @Override
    public Map vehicleList(JSONObject jsonObj) {
        int totalRec = this.vehicleDao.countVehicleList(jsonObj);
        jsonObj = ParaUtils.checkStartIndex(jsonObj, totalRec);
        List<Map> pageData = this.vehicleDao.vehicleList(jsonObj);
        Map retMap = new HashMap();
        retMap.put("pageData", pageData);
        retMap.put("totalRec", totalRec);
        retMap.put("cp", jsonObj.get("cp"));
        retMap.put("rop", jsonObj.get("rop"));
        return retMap;
    }


    //@param jsonObj 调用参数  
    //@return 影响记录数 
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer deletesVehicleInst(JSONObject jsonObj) {
        if (hikEnable) {
            if (jsonObj != null && jsonObj.getJSONArray("vehicle").size() > 0) {
                JSONArray deleteIds = jsonObj.getJSONArray("vehicle").getJSONObject(0).getJSONArray("val");
                if (deleteIds != null && deleteIds.size() > 0) {
                    List<String> vehicleIds = vehicleDao.queryByIds(deleteIds.toJavaList(Long.class));
                    JSONObject deleteVehicle = new JSONObject();
                    deleteVehicle.put("vehicleIds", vehicleIds);
                    ApiService.deleteVehicle(deleteVehicle);
                }
            }
        }
        Integer delNum1 = this.vehicleDao.deletesInst(jsonObj);
        return delNum1;
    }
}
