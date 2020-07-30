package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(FaceGateway)门禁信息
//generate by redcloud,2020-07-24 19:59:20
public class FaceGateway implements Serializable {
    private static final long serialVersionUID = 24L;
    // 主键id
    private Long id ;
    // 设备名称
    private String title ;
    // 厂商
    private String firm ;
    // 设备在线状态
    private String state ;
    // 创建时间
    private Long created ;
    // 修改时间
    private Long updated ;
    // 区域id
    @JSONField(name = "region_id")
    private Long regionId ;
    // 区域
    @JSONField(name = "region_title")
    private String regionTitle ;
    // 区域标识
    private String regionIndexCode ;
    // 门禁设备唯一标识
    private String acsDevIndexCode ;

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

    public String getRegionIndexCode() {
        return regionIndexCode ;
    }

    public void setRegionIndexCode(String regionIndexCode) {
        this.regionIndexCode = regionIndexCode;
    }

    public String getAcsDevIndexCode() {
        return acsDevIndexCode ;
    }

    public void setAcsDevIndexCode(String acsDevIndexCode) {
        this.acsDevIndexCode = acsDevIndexCode;
    }

}

