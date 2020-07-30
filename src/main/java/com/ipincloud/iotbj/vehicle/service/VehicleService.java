package com.ipincloud.iotbj.vehicle.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public interface VehicleService {
    Object syncVehicleDevice(JSONObject jsonObj);

    Object vehiclegateadd(JSONArray jsonObj);

    Object vehiclegateopen(JSONObject jsonObj);
}
