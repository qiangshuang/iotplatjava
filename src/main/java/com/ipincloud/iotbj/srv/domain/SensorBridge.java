package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(SensorBridge)网桥管理
//generate by redcloud,2020-07-24 19:59:20
public class SensorBridge implements Serializable {
    private static final long serialVersionUID = 52L;
    // 主键id
    private Long id ;
    // 设备名称
    private String titel ;
    // IP地址
    private String ip ;
    // 厂商
    private String firm ;
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

    public String getIp() {
        return ip ;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getFirm() {
        return firm ;
    }

    public void setFirm(String firm) {
        this.firm = firm;
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

