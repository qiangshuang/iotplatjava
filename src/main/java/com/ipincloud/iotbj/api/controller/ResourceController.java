package com.ipincloud.iotbj.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.api.domain.DoorEvent;
import com.ipincloud.iotbj.api.domain.VehicleEvent;
import com.ipincloud.iotbj.api.service.ResourceService;
import com.ipincloud.iotbj.sys.domain.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Configuration
//@EnableScheduling
@RestController
public class ResourceController {
    @Autowired
    ResourceService resourceService;

    @GetMapping("/syncOrg")
    public Object syncOrg() {
        return resourceService.syncOrg();
    }

    @GetMapping("/syncResion")
    public Object syncResion() {
        return resourceService.syncResion();
    }

    @GetMapping("/syncUser")
    public Object syncUser() {
        return resourceService.syncUser();
    }

    @GetMapping("/syncGateway")
    public Object syncGateway() {
        return resourceService.syncGateway();
    }

    @Scheduled(cron = "0 0/2 * * * ?")
    private void configureTasks() {
        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
        //this.syncOrg();
        //this.syncResion();
        this.syncUser();
        //this.syncGateway();
        System.err.println("结束执行静态定时任务时间: " + LocalDateTime.now());
    }

    //接受门禁事件消息
    @PostMapping("eventRcvFace")
    public Object eventRcvFace(@RequestBody DoorEvent doorEvent) {
        if (doorEvent == null) {
            return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
        }
        return resourceService.saveEventFace(doorEvent);
    }

    //接受车辆订阅消息
    @PostMapping("eventRcvVehicle")
    public Object eventRcvVehicle(@RequestBody VehicleEvent vehicleEvent) {
        if (vehicleEvent == null) {
            return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
        }
        try {
            resourceService.saveEventVehicle(vehicleEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseBean(200, "SUCESS", "操作成功!", null);

    }

}
