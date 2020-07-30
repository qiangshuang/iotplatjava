package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(SensorGateway)网关管理
//generate by redcloud,2020-07-24 19:59:20
public class SensorGateway implements Serializable {
    private static final long serialVersionUID = 54L;
    // 主键id
    private Long id ;
    // 设备名称
    private String titel ;
    // 设备描述
    private String description ;
    // IP地址
    private String ipaddress ;
    // 厂商
    private String firm ;
    // 最后通信时间
    private Long starttime ;
    // 最后通信时间
    private Long endtime ;
    // 工作时长
    private String workhours ;
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

    public String getDescription() {
        return description ;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIpaddress() {
        return ipaddress ;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public String getFirm() {
        return firm ;
    }

    public void setFirm(String firm) {
        this.firm = firm;
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

    public String getWorkhours() {
        return workhours ;
    }

    public void setWorkhours(String workhours) {
        this.workhours = workhours;
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

