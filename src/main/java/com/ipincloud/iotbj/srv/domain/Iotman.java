package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(Iotman)网关管理
//generate by redcloud,2020-07-07 23:53:41
public class Iotman implements Serializable {
    private static final long serialVersionUID = 19L;
    // 自增ID
    private Long id ;
    // 设备名称
    private String title ;
    // 设备描述
    private String equdes ;
    // 设备IP
    private String equip ;
    // 厂商
    private String factory ;
    // 最后通信时间
    private Long lasttime ;
    // 工作时长
    private String workdur ;

    public Long getId() {
        return id ;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title ;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEqudes() {
        return equdes ;
    }

    public void setEqudes(String equdes) {
        this.equdes = equdes;
    }

    public String getEquip() {
        return equip ;
    }

    public void setEquip(String equip) {
        this.equip = equip;
    }

    public String getFactory() {
        return factory ;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public Long getLasttime() {
        return lasttime ;
    }

    public void setLasttime(Long lasttime) {
        this.lasttime = lasttime;
    }

    public String getWorkdur() {
        return workdur ;
    }

    public void setWorkdur(String workdur) {
        this.workdur = workdur;
    }

}

