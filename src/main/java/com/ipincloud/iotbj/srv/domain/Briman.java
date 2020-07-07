package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(Briman)网桥管理
//generate by redcloud,2020-07-07 23:53:41
public class Briman implements Serializable {
    private static final long serialVersionUID = 8L;
    // 自增ID
    private Long id ;
    // 设备名称
    private String title ;
    // IP地址
    private String ipaddress ;
    // 设备状态
    private String state ;
    // 厂商
    private String factory ;
    // 信号强度
    private String signales ;

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

    public String getIpaddress() {
        return ipaddress ;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public String getState() {
        return state ;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFactory() {
        return factory ;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getSignales() {
        return signales ;
    }

    public void setSignales(String signales) {
        this.signales = signales;
    }

}

