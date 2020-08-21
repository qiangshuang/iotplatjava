package com.ipincloud.iotbj.vehicle.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.api.utils.hik.ApiService;
import com.ipincloud.iotbj.sys.domain.ResponseBean;
import com.ipincloud.iotbj.utils.ParaUtils;
import com.ipincloud.iotbj.vehicle.dao.ApiVehicleDao;
import com.ipincloud.iotbj.vehicle.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    ApiVehicleDao vehicleDao;

    @Value("${iotEnable}")
    private boolean iotEnable;
    @Value("${hikEnable}")
    private boolean hikEnable;

    @Override
    public Object syncVehicleDevice(JSONObject jsonObj) {
        String modName = "车辆道闸";
        if (iotEnable) {
            JSONObject jsonObject = ApiService.deviceSync(modName);

            List<JSONObject> jsons = jsonObject.getJSONObject("data").getJSONArray("list").toJavaList(JSONObject.class);
            List<JSONObject> gates = new ArrayList<>();
            for (int i = 0; i < jsons.size(); i++) {
                JSONObject gate = new JSONObject();
                gate.put("name", jsons.get(i).getString("roadwayName"));
                gate.put("indexCode", jsons.get(i).getString("roadwayIndex"));
                // gate.put("","");
                gates.add(gate);
            }

            jsonObj.put("pageData", gates);
            jsonObj.put("totalRec", jsonObject.getInteger("total"));
            return new ResponseBean(200, "SUCCESS", "操作成功", jsonObj);
        }

        int totalRec = vehicleDao.countSyncDevice(jsonObj);
        jsonObj = ParaUtils.checkStartIndex(jsonObj, totalRec);
        List<Map> list = vehicleDao.listSyncDevice(jsonObj);
        jsonObj.put("pageData", list);
        jsonObj.put("totalRec", totalRec);
        return new ResponseBean(200, "SUCCESS", "操作成功", jsonObj);

    }

    @Override
    public Object vehiclegateadd(JSONObject jsonObj) {
        vehicleDao.insertVehicleGate(jsonObj);
        return new ResponseBean(200, "SUCCESS", "操作成功", jsonObj);
    }

    @Override
    public Object vehiclegateopen(JSONObject jsonObj) {
        Long id = jsonObj.getLong("id");
        if (hikEnable) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("roadwaySyscode", "3f2283401d0a4214ae7032c9117ba30c");
            jsonObject.put("command", 1);
            ApiService.deviceOpen(jsonObject);
        }
        vehicleDao.updateGateState(id);
        return new ResponseBean(200, "SUCCESS", "操作成功", null);
    }
}
