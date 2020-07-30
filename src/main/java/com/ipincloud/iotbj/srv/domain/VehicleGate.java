package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(VehicleGate)车辆闸机
//generate by redcloud,2020-07-24 19:59:20
public class VehicleGate implements Serializable {
    private static final long serialVersionUID = 77L;
    // 主键id
    private Long id ;
    // 设备名称
    private String title ;
    // 厂商
    private String firm ;
    // 创建时间
    private Long created ;
    // 修改时间
    private Long updated ;
    // 设备唯一标识
    private String indexCode ;
    private String state ;

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

    public String getIndexCode() {
        return indexCode ;
    }

    public void setIndexCode(String indexCode) {
        this.indexCode = indexCode;
    }

    public String getState() {
        return state ;
    }

    public void setState(String state) {
        this.state = state;
    }

}

