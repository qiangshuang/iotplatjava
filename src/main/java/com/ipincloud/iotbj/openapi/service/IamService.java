package com.ipincloud.iotbj.openapi.service;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public interface IamService {

    Object saveOrUpdateUser(JSONObject jsonObj);

    Object deleteUsers(JSONObject jsonObj);

    Object saveOrUpdatePos(JSONObject jsonObj);

    Object deletePoss(JSONObject jsonObj);

    Object saveOrUpdateUserPos(JSONObject jsonObj);

    Object deleteUserPoss(JSONObject jsonObj);

    Object saveOrUpdateUserFace(JSONArray jsonObj);

    Object issueAccessControlAuthority(JSONObject jsonObj);

}