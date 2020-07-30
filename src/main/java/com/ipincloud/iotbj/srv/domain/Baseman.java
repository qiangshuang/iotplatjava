package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(Baseman)基站管理
//generate by redcloud,2020-07-24 19:59:20
public class Baseman implements Serializable {
    private static final long serialVersionUID = 12L;
    // 自增ID
    private Long id ;
    // 名称
    private String title ;
    // 类型
    private String type ;
    // 区域
    private String area ;
    // 厂商
    private String factory ;
    // 最近在线时间
    private Long recenttime ;
    // 状态
    private String state ;
    // IP地址
    private String ipaddress ;
    // 位置图片
    private String url ;

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

    public String getType() {
        return type ;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArea() {
        return area ;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getFactory() {
        return factory ;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public Long getRecenttime() {
        return recenttime ;
    }

    public void setRecenttime(Long recenttime) {
        this.recenttime = recenttime;
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

    public String getUrl() {
        return url ;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}

