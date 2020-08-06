package com.ipincloud.iotbj.openapi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.openapi.service.IamService;
import com.ipincloud.iotbj.sys.domain.ResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class IamController {

    @Autowired
    IamService iamService;
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @PostMapping("/account/saveOrUpdateUser")
    public Object saveOrUpdateUser(@RequestParam Map<String, String> param) {
        logger.info("info:recv saveOrUpdateUser{}", JSON.toJSONString(param));
        JSONObject jsonObj = JSONObject.parseObject(JSON.toJSONString(param));
        if (jsonObj == null) {
            return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
        }
        return iamService.saveOrUpdateUser(jsonObj);
    }

    @PostMapping("/account/deleteUsers")
    public Object deleteUsers(@RequestParam Map<String, String> param) {
        logger.info("info:recv deleteUsers{}", JSON.toJSONString(param));
        JSONObject jsonObj = JSONObject.parseObject(JSON.toJSONString(param));
        if (jsonObj == null) {
            return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
        }
        return iamService.deleteUsers(jsonObj);
    }

    @PostMapping("/post/saveOrUpdatePos")
    public Object saveOrUpdatePos(@RequestParam Map<String, String> param) {
        logger.info("info:recv saveOrUpdatePos{}", JSON.toJSONString(param));
        JSONObject jsonObj = JSONObject.parseObject(JSON.toJSONString(param));
        if (jsonObj == null) {
            return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
        }
        return iamService.saveOrUpdatePos(jsonObj);
    }

    @PostMapping("/post/deletePoss")
    public Object deletePoss(@RequestParam Map<String, String> param) {
        logger.info("info:recv deletePoss{}", JSON.toJSONString(param));
        JSONObject jsonObj = JSONObject.parseObject(JSON.toJSONString(param));
        if (jsonObj == null) {
            return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
        }
        return iamService.deletePoss(jsonObj);
    }

    @PostMapping("/user_post/saveOrUpdateUserPos")
    public Object saveOrUpdateUserPos(@RequestParam Map<String, String> param) {
        logger.info("info:recv saveOrUpdateUserPos{}", JSON.toJSONString(param));
        JSONObject jsonObj = JSONObject.parseObject(JSON.toJSONString(param));
        if (jsonObj == null) {
            return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
        }
        return iamService.saveOrUpdateUserPos(jsonObj);
    }

    @PostMapping("/user_post/deleteUserPoss")
    public Object deleteUserPoss(@RequestParam Map<String, String> param) {
        logger.info("info:recv deleteUserPoss{}", JSON.toJSONString(param));
        JSONObject jsonObj = JSONObject.parseObject(JSON.toJSONString(param));
        if (jsonObj == null) {
            return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
        }
        return iamService.deleteUserPoss(jsonObj);
    }

    @PostMapping("/account/saveOrUpdateUserFace")
    public Object saveOrUpdateUserFace(@RequestBody String jsonstr) {
        logger.info("info:recv saveOrUpdateUserFace{}", jsonstr);
        JSONArray jsonObj = JSONArray.parseArray(jsonstr);
        if (jsonObj == null) {
            return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
        }

        return iamService.saveOrUpdateUserFace(jsonObj);
    }


}
