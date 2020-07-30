package com.ipincloud.iotbj.api.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//(JobDao) 表数据库访问层
//generate by redcloud,2020-07-13 18:40:46
public interface IotDao {
    JSONObject selectModInfo(@Param("jsonObj") JSONObject jsonObj);

    int existByIndex(@Param("cameraIndex") String cameraIndex);

    void confirmState(@Param("jsonArr") JSONArray jsonArr);

    void misstate(@Param("jsonArr") JSONArray jsonArr);

    JSONObject base();

    JSONObject totality();

    List<JSONObject> by();

    List<JSONObject> sy();

    int alarmTotal();

    List<JSONObject> alarmGroup();

    List<JSONObject> bjsAndQrsGroup();

    List<JSONObject> cameraindexlist(String region_id, String algorithm_id, String title);

    List<JSONObject> alarmcount();

    List<JSONObject> alarmdtl(@Param("algorithm_id") Long algorithm_id);

    List<JSONObject> allCameraList();
	
	List<JSONObject> realCameraIndexlist();
	
	int countRealAlarmList(JSONObject jsonObj);

    List<JSONObject> realAlarmList(JSONObject jsonObj);
}
