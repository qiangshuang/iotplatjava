package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(SiteStation)基站管理
//generate by redcloud,2020-07-24 19:59:20
public class SiteStation implements Serializable {
    private static final long serialVersionUID = 63L;
    // 主键id
    private Long id ;
    // 基站名称
    private String title ;
    // 基站类型
    private String category ;
    // 厂商
    private String firm ;
    // 区域id
    @JSONField(name = "region_id")
    private Long regionId ;
    // 区域名称
    @JSONField(name = "region_title")
    private String regionTitle ;
    // ip地址及端口号
    private String ipandport ;
    // 设备型号
    private String equipment ;
    // 接入协议
    private String accesscode ;
    // 位置信息
    private String site ;
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

    public String getTitle() {
        return title ;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category ;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFirm() {
        return firm ;
    }

    public void setFirm(String firm) {
        this.firm = firm;
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

    public String getIpandport() {
        return ipandport ;
    }

    public void setIpandport(String ipandport) {
        this.ipandport = ipandport;
    }

    public String getEquipment() {
        return equipment ;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getAccesscode() {
        return accesscode ;
    }

    public void setAccesscode(String accesscode) {
        this.accesscode = accesscode;
    }

    public String getSite() {
        return site ;
    }

    public void setSite(String site) {
        this.site = site;
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

