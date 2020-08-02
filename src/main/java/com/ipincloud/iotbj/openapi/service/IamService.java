package com.ipincloud.iotbj.openapi.service;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public interface IamService {

    Object saveOrUpdateUser(JSONObject jsonObj);
    Object deleteUsers(JSONObject jsonObj);
}