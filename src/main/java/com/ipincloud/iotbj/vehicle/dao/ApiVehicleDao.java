package com.ipincloud.iotbj.vehicle.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ApiVehicleDao {

    int countSyncDevice(JSONObject jsonObj);

    List<Map> listSyncDevice(JSONObject jsonObj);

    int insertVehicleGate(@Param("jsonObj") JSONObject jsonObj);

    int updateGateState(Long id);
}
