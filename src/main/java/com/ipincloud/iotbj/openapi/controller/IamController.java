package com.ipincloud.iotbj.face.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.openapi.service.IamService;
import com.ipincloud.iotbj.sys.domain.ResponseBean;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
public class IamController {

    @Autowired
    IamService iamService;

    // @PostMapping("syncacsdev")
    // public Object syncacsdev(@RequestBody String jsonstr) {
    //     JSONObject jsonObj = JSONObject.parseObject(jsonstr);
    //     if (jsonObj == null) {
    //         return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
    //     }
    //     return faceService.syncAcsdev(jsonObj);
    // }

    // @PostMapping("gatewayadd")
    // public Object gatewayadd(@RequestBody String jsonstr) {
    //     JSONArray jsonObj = JSON.parseArray(jsonstr);
    //     if (jsonObj == null) {
    //         return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
    //     }
    //     return faceService.gatewayadd(jsonObj);
    // }

    // @PostMapping("gatewayup")
    // public Object gatewayup(@RequestBody String jsonstr) {
    //     JSONObject jsonObj = JSONObject.parseObject(jsonstr);
    //     if (jsonObj == null) {
    //         return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
    //     }
    //     return faceService.gatewayup(jsonObj);
    // }

    // @PostMapping("regionlist")
    // public Object regionlist(@RequestBody String jsonstr) {
    //     JSONObject jsonObj = JSONObject.parseObject(jsonstr);
    //     if (jsonObj == null) {
    //         return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
    //     }
    //     return faceService.regionlist(jsonObj);
    // }

    // @PostMapping("gatewaylist")
    // public Object gatewaylist(@RequestBody String jsonstr) {
    //     JSONObject jsonObj = JSONObject.parseObject(jsonstr);
    //     if (jsonObj == null) {
    //         return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
    //     }
    //     return faceService.gatewaylist(jsonObj);
    // }

    // @PostMapping("gatewaydel")
    // public Object gatewaydel(@RequestBody String jsonstr) {
    //     JSONArray jsonObj = JSONArray.parseArray(jsonstr);
    //     if (jsonObj == null) {
    //         return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
    //     }
    //     return faceService.gatewaydel(jsonObj);
    // }

    // @PostMapping("gatewayopen")
    // public Object gatewayopen(@RequestBody String jsonstr) {
    //     JSONObject jsonObj = JSONObject.parseObject(jsonstr);
    //     if (jsonObj == null) {
    //         return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
    //     }
    //     return faceService.gatewayopen(jsonObj);
    // }

    // @PostMapping("policyadd")
    // public Object policyadd(@RequestBody String jsonstr) {
    //     JSONObject jsonObj = JSONObject.parseObject(jsonstr);
    //     if (jsonObj == null) {
    //         return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
    //     }
    //     return faceService.policyadd(jsonObj);
    // }


    // @PostMapping("policylist")
    // public Object policylist(@RequestBody String jsonstr) {
    //     JSONObject jsonObj = JSONObject.parseObject(jsonstr);
    //     if (jsonObj == null) {
    //         return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
    //     }
    //     return faceService.policylist(jsonObj);
    // }

}
