package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(SensorAlarm)传感器-预警记录
//generate by redcloud,2020-07-24 19:59:20
public class SensorAlarm implements Serializable {
    private static final long serialVersionUID = 51L;
    // 主键id
    private Long id ;
    // 传感器id
    @JSONField(name = "sensor_id")
    private Long sensorId ;
    // 传感器名称
    @JSONField(name = "sensor_title")
    private String sensorTitle ;
    // 区域id
    @JSONField(name = "region_id")
    private Long regionId ;
    // 区域名称
    @JSONField(name = "region_title")
    private String regionTitle ;
    // 传感器类型
    @JSONField(name = "sensor_categary")
    private String sensorCategary ;
    // 数据值
    private String dataval ;
    // 标准值
    private String standardval ;
    // 预警等级
    private String grade ;
    // 预警时间
    private Long alarmtiem ;
    // 状态（未确认，已确认）
    private String state ;
    // 创建时间
    private Long created ;
    // 修改时间
    private Long updated ;

    public Long getId() {
        return id ;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSensorId() {
        return sensorId ;
    }

    public void setSensorId(Long sensorId) {
        this.sensorId = sensorId;
    }

    public String getSensorTitle() {
        return sensorTitle ;
    }

    public void setSensorTitle(String sensorTitle) {
        this.sensorTitle = sensorTitle;
    }

    public Long getRegionId() {
        return regionId ;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getRegionTitle() {
        return regionTitle ;
    }

    public void setRegionTitle(String regionTitle) {
        this.regionTitle = regionTitle;
    }

    public String getSensorCategary() {
        return sensorCategary ;
    }

    public void setSensorCategary(String sensorCategary) {
        this.sensorCategary = sensorCategary;
    }

    public String getDataval() {
        return dataval ;
    }

    public void setDataval(String dataval) {
        this.dataval = dataval;
    }

    public String getStandardval() {
        return standardval ;
    }

    public void setStandardval(String standardval) {
        this.standardval = standardval;
    }

    public String getGrade() {
        return grade ;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Long getAlarmtiem() {
        return alarmtiem ;
    }

    public void setAlarmtiem(Long alarmtiem) {
        this.alarmtiem = alarmtiem;
    }

    public String getState() {
        return state ;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getCreated() {
        return created ;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Long getUpdated() {
        return updated ;
    }

    public void setUpdated(Long updated) {
        this.updated = updated;
    }

}

