package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(Algorithmalarm)报警记录
//generate by redcloud,2020-07-24 19:59:20
public class Algorithmalarm implements Serializable {
    private static final long serialVersionUID = 9L;
    // 主键id
    private Long id ;
    // 报警时间
    @JSONField(name = "alarm_time")
    private Long alarmTime ;
    // 算法id
    @JSONField(name = "algorithm_id")
    private Long algorithmId ;
    // 算法名称
    @JSONField(name = "algorithm_name")
    private String algorithmName ;
    // 摄像机id
    @JSONField(name = "camera_id")
    private Long cameraId ;
    // 摄像机名称
    @JSONField(name = "camera_name")
    private String cameraName ;
    // 状态
    private String state ;
    // 报警描述
    private String describion ;
    // 区域
    private String region ;
    // 报警图片
    @JSONField(name = "alarm_img")
    private String alarmImg ;
    // 预警等级
    private String grade ;
    // 创建时间
    private Long indate ;

    public Long getId() {
        return id ;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAlarmTime() {
        return alarmTime ;
    }

    public void setAlarmTime(Long alarmTime) {
        this.alarmTime = alarmTime;
    }

    public Long getAlgorithmId() {
        return algorithmId ;
    }

    public void setAlgorithmId(Long algorithmId) {
        this.algorithmId = algorithmId;
    }

    public String getAlgorithmName() {
        return algorithmName ;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public Long getCameraId() {
        return cameraId ;
    }

    public void setCameraId(Long cameraId) {
        this.cameraId = cameraId;
    }

    public String getCameraName() {
        return cameraName ;
    }

    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }

    public String getState() {
        return state ;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDescribion() {
        return describion ;
    }

    public void setDescribion(String describion) {
        this.describion = describion;
    }

    public String getRegion() {
        return region ;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAlarmImg() {
        return alarmImg ;
    }

    public void setAlarmImg(String alarmImg) {
        this.alarmImg = alarmImg;
    }

    public String getGrade() {
        return grade ;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Long getIndate() {
        return indate ;
    }

    public void setIndate(Long indate) {
        this.indate = indate;
    }

}

