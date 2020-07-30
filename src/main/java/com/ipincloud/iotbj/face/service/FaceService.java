package com.ipincloud.iotbj.face.service;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public interface FaceService {

    Object syncAcsdev(JSONObject jsonObj);

    Object gatewayadd(JSONArray jsonObj);

    Object gatewayup(JSONObject jsonObj);

    Object regionlist(JSONObject jsonObj);

    Object gatewaylist(JSONObject jsonObj);

    Object gatewaydel(JSONArray jsonObj);

    Object policyadd(JSONObject jsonObj);

    Object policylist(JSONObject jsonObj);

    Object gatewayopen(JSONObject jsonObj);

    Object policydel(JSONArray jsonObj);

    Object visithistorylist(JSONObject jsonObj);

    Object visitpersonadd(JSONObject jsonObj);

    Object visitpersoncheck(JSONObject jsonObj);

    Object userlist(JSONObject jsonObj);

    Object visitList(JSONObject jsonObj);

    Object visitpersonup(JSONObject jsonObj);

    Object visitpersondel(JSONArray jsonObj);

    Object visitresult(Long userId);

    Object visitallow(JSONObject jsonObj);

    Object visitprohibit(JSONObject jsonObj);

    Object cardadd(JSONObject jsonObj);

    Object carddel(JSONArray jsonArray);

    Object cardstart(JSONArray jsonArray);

    Object cardstop(JSONArray jsonArray);

    Object cardlist(JSONObject jsonObject);

}