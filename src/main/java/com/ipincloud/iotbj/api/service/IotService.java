package com.ipincloud.iotbj.api.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.srv.domain.Algorithmalarm;

import java.util.List;
import java.util.Map;

/**
 * 服务接口
 *
 * @author lcc
 * @since 2020-06-29 17:21:01
 */
public interface IotService {

    List<Map> deviceSync(JSONObject jo);

    List<Map> deviceOpen(List<Long> ids);

    List<Map> deviceClose(List<Long> ids);

    List<Map> deviceRestart(List<Long> ids);

    List<Map> algorithmClose(List<Long> ids);

    List<Map> algorithmRestart(List<Long> ids);

    List<Map> algorithmOpen(JSONArray algorithm);

    //List<Map> algorithmOpen1(JSONArray algorithm);

    void algorithmResult(JSONObject jo, String path);

    void alarmSave(Algorithmalarm algorithmalarm);

    void confirmState(JSONArray jsonArray);

    void missState(JSONArray jsonArray);

    JSONObject algorithmhome();

    List<JSONObject> cameraindexlist(JSONArray parm);

    List<JSONObject> alarmcount();

    List<JSONObject> alarmdtl(Long algorithm_id);

    List<JSONObject> deviceslist();

    List<JSONObject> otherCameralist(JSONArray jsonArray);
	
	JSONObject realcameraindexlist();
	
	Object realAlarmList(JSONObject jsonObject);
}