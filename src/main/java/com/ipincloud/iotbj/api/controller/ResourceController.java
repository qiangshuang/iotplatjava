package com.ipincloud.iotbj.api.controller;

import com.ipincloud.iotbj.api.domain.DoorEvent;
import com.ipincloud.iotbj.api.domain.VehicleEvent;
import com.ipincloud.iotbj.api.service.ResourceService;
import com.ipincloud.iotbj.api.service.impl.ResourceServiceImpl;
import com.ipincloud.iotbj.sys.domain.ResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@Configuration
//@EnableScheduling
@RestController
public class ResourceController {
    private static Logger logger = LoggerFactory.getLogger(ResourceServiceImpl.class);
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

//    @Scheduled(cron = "0 0/1 * * * ?")
//    private void configureTasks() {
//        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
//        //this.syncOrg();
//        //this.syncResion();
//        this.syncUser();
//        //this.syncGateway();
//        System.err.println("结束执行静态定时任务时间: " + LocalDateTime.now());
//    }

    //接受门禁事件消息
    @PostMapping("eventRcvFace")
    public Object eventRcvFace(@RequestBody DoorEvent doorEvent) {
        if (doorEvent == null) {
            return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
        }
        try {
            return resourceService.saveEventFace(doorEvent);
        } catch (Exception parseException) {
            logger.error("车辆订阅消息解析出错{}",parseException);
            return new ResponseBean(200, "FAILED", "日期解析出错!", null);
        }
    }

    //接受车辆订阅消息
    @PostMapping("eventRcvVehicle")
    public Object eventRcvVehicle(@RequestBody VehicleEvent vehicleEvent) {
        if (vehicleEvent == null) {
            return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
        }
        try {
            resourceService.saveEventVehicle(vehicleEvent);
        } catch (ParseException parseException) {
            logger.error("车辆订阅消息解析出错{}",parseException);
            return new ResponseBean(200, "FAILED", "日期解析出错!", null);
        }

        return new ResponseBean(200, "SUCESS", "操作成功", null);
    }

}
