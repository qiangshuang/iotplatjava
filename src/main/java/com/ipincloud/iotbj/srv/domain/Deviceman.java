package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(Deviceman)设备管理
//generate by redcloud,2020-07-08 01:57:14
public class Deviceman implements Serializable {
    private static final long serialVersionUID = 16L;
    // 主键ID
    private Long id ;
    // 设备名称
    private String title ;
    // 区域
    private String area ;
    // 设备类型
    private String devicetype ;
    // 厂商
    private String manufacturer ;
    // 最近在线时间
    private String onlinetime ;
    // 状态
    private String state ;
    // IP地址
    private String ipaddress ;

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

    public String getArea() {
        return area ;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDevicetype() {
        return devicetype ;
    }

    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype;
    }

    public String getManufacturer() {
        return manufacturer ;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getOnlinetime() {
        return onlinetime ;
    }

    public void setOnlinetime(String onlinetime) {
        this.onlinetime = onlinetime;
    }

    public String getState() {
        return state ;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIpaddress() {
        return ipaddress ;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

}

