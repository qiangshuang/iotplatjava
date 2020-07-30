package com.ipincloud.iotbj.vehicle.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.sys.domain.ResponseBean;
import com.ipincloud.iotbj.vehicle.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @PostMapping("syncVehicleDevice")
    public Object syncVehicleDevice(@RequestBody String jsonstr) {
        JSONObject jsonObj = JSONObject.parseObject(jsonstr);
        if (jsonObj == null) {
            return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
        }
        return vehicleService.syncVehicleDevice(jsonObj);
    }

    @PostMapping("vehiclegateadd")
    public Object vehiclegateadd(@RequestBody String jsonstr) {
        JSONArray jsonObj =  JSONArray.parseArray(jsonstr);
        if (jsonObj == null) {
            return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
        }
        return vehicleService.vehiclegateadd(jsonObj);
    }

    @PostMapping("vehiclegateopen")
    public Object vehiclegateopen(@RequestBody String jsonstr) {
        JSONObject jsonObj = JSONObject.parseObject(jsonstr);
        if (jsonObj == null) {
            return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
        }
        return vehicleService.vehiclegateopen(jsonObj);
    }

}
