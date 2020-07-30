package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(RinseSolenoid)电磁阀管理
//generate by redcloud,2020-07-24 19:59:20
public class RinseSolenoid implements Serializable {
    private static final long serialVersionUID = 45L;
    // 主键id
    private Long id ;
    // 设备名称
    private String titel ;
    // 关联传感器
    @JSONField(name = "sensor_id")
    private Long sensorId ;
    // 位置信息
    private String site ;
    // 自动冲洗（开启，关闭）
    private String automatic ;
    // 定时冲洗（开启，关闭）
    private String timing ;
    // 定时冲洗-开始
    private Long starttime ;
    // 定时冲洗-结束
    private Long endtime ;
    // 状态（开启，关闭）
    private String state ;
    // 类型
    private String category ;
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

    public String getTitel() {
        return titel ;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public Long getSensorId() {
        return sensorId ;
    }

    public void setSensorId(Long sensorId) {
        this.sensorId = sensorId;
    }

    public String getSite() {
        return site ;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getAutomatic() {
        return automatic ;
    }

    public void setAutomatic(String automatic) {
        this.automatic = automatic;
    }

    public String getTiming() {
        return timing ;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public Long getStarttime() {
        return starttime ;
    }

    public void setStarttime(Long starttime) {
        this.starttime = starttime;
    }

    public Long getEndtime() {
        return endtime ;
    }

    public void setEndtime(Long endtime) {
        this.endtime = endtime;
    }

    public String getState() {
        return state ;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCategory() {
        return category ;
    }

    public void setCategory(String category) {
        this.category = category;
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

