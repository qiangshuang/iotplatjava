package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(Algorithmalarm)算法报警记录
//generate by redcloud,2020-07-07 10:18:15
public class Algorithmalarm implements Serializable {
    // 主键id
    private Long id ;
    // 报警时间
    @JSONField(name = "alarm_time")
    private Long alarmTime ;
    // 算法名称
    @JSONField(name = "algorithm_name")
    private String algorithmName ;
    // 摄像机名称
    @JSONField(name = "camera_name")
    private String cameraName ;
    // 预警等级
    private String grade ;
    // 报警描述
    private String describion ;
    // 写入时间
    private Long indate ;
    // 状态
    private String state ;

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

    public String getAlgorithmName() {
        return algorithmName ;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public String getCameraName() {
        return cameraName ;
    }

    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }

    public String getGrade() {
        return grade ;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDescribion() {
        return describion ;
    }

    public void setDescribion(String describion) {
        this.describion = describion;
    }

    public Long getIndate() {
        return indate ;
    }

    public void setIndate(Long indate) {
        this.indate = indate;
    }

    public String getState() {
        return state ;
    }

    public void setState(String state) {
        this.state = state;
    }

}

