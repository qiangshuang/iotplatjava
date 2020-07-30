package com.ipincloud.iotbj.vehicle.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.api.utils.hik.ApiModel;
import com.ipincloud.iotbj.api.utils.hik.ApiService;
import com.ipincloud.iotbj.sys.domain.ResponseBean;
import com.ipincloud.iotbj.utils.ParaUtils;
import com.ipincloud.iotbj.vehicle.dao.ApiVehicleDao;
import com.ipincloud.iotbj.vehicle.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    ApiVehicleDao vehicleDao;

    @Value("${hikEnable}")
    private boolean hikEnable;

    @Override
    public Object syncVehicleDevice(JSONObject jsonObj) {
		
		if (hikEnable) {
            JSONObject para = new JSONObject();
            para.put("pageNo", 1);
            para.put("pageSize", 1000);
            para.put("roadwayName", jsonObj.getString("title"));
            JSONObject jsonObject = ApiService.ascDevSync(para);

            List<ApiModel.HikDoor> list1 = jsonObject.getJSONArray("list").toJavaList(ApiModel.HikDoor.class);
            List<Map> list2 = vehicleDao.listSyncDevice(new JSONObject());
            Set<String> set = new HashSet();
            for (int i = 0; i < list2.size(); i++) {
                set.add(list2.get(i).get("acsDevIndexCode").toString());
            }
            JSONArray list = new JSONArray();
            for (int i = 0; i < list1.size(); i++) {
                String acsDevIndexCode = list1.get(i).acsDevIndexCode;
                for (int j = 0; j < set.size(); j++) {
                    if (!set.contains(acsDevIndexCode)) {
                        list1.get(i).acsDevName = list1.get(i).doorName;
                        list1.get(i).acsDevIndexCode = list1.get(i).acsDevIndexCode;
                        list.add(list1.get(i));
                        break;
                    }
                }
            }

            jsonObj.put("pageData", list);
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
    public Object vehiclegateadd(JSONArray jsonObj) {
        for (int i = 0; i <jsonObj.size() ; i++) {
            vehicleDao.insertVehicleGate(jsonObj.getJSONObject(i));
        }
        return new ResponseBean(200, "SUCCESS", "操作成功", jsonObj);
    }

    @Override
    public Object vehiclegateopen(JSONObject jsonObj) {
        Long id = jsonObj.getLong("id");
//        Map gateway = vehicleDao.findGateById(id);
//        String acsDevIndexCode = String.valueOf(gateway.get("acsDevIndexCode"));
//        if (StringUtils.isEmpty(acsDevIndexCode)) {
//            return new ResponseBean(200, "FAILED", "操作失败", null);
//        }
//        //调试打开下面注释
//        //boolean bool = ApiService.gatewayOpen(acsDevIndexCode);

        if (hikEnable) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("roadwaySyscode", "3f2283401d0a4214ae7032c9117ba30c");
            jsonObject.put("command", 1);
            ApiService.deviceOpen(jsonObject);
        }

        boolean bool = true;

        if (!bool) {
            return new ResponseBean(200, "FAILED", "操作失败", null);
        }
        vehicleDao.updateGateState(id);
        return new ResponseBean(200, "SUCCESS", "操作成功", null);
    }
}
