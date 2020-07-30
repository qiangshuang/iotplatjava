package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(Sensor)传感器管理
//generate by redcloud,2020-07-24 19:59:20
public class Sensor implements Serializable {
    private static final long serialVersionUID = 50L;
    // 主键id
    private Long id ;
    // 设备名称
    private String titel ;
    // 产品类型
    @JSONField(name = "product_ids")
    private Long productIds ;
    // 设备类型
    @JSONField(name = "category_id")
    private String categoryId ;
    // 厂商
    @JSONField(name = "firm_id")
    private String firmId ;
    // 区域id
    @JSONField(name = "region_id")
    private String regionId ;
    // 区域名称
    @JSONField(name = "region_title")
    private String regionTitle ;
    // 关联数据流
    @JSONField(name = "stream_id")
    private String streamId ;
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
    // 设备ID
    @JSONField(name = "equ_id")
    private String equId ;

    @JSONField(name = "stream_title")
    private String streamTitle ;
    private String devcode;

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

    public Long getProductIds() {
        return productIds ;
    }

    public void setProductIds(Long productIds) {
        this.productIds = productIds;
    }

    public String getCategoryId() {
        return categoryId ;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getFirmId() {
        return firmId ;
    }

    public void setFirmId(String firmId) {
        this.firmId = firmId;
    }

    public String getRegionId() {
        return regionId ;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getRegionTitle() {
        return regionTitle ;
    }

    public void setRegionTitle(String regionTitle) {
        this.regionTitle = regionTitle;
    }

    public String getStreamId() {
        return streamId ;
    }

    public void setStreamId(String streamId) {
        this.streamId = streamId;
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

    public String getEquId() {
        return equId ;
    }

    public void setEquId(String equId) {
        this.equId = equId;
    }

    public String getStreamTitle() {
        return streamTitle ;
    }

    public void setStreamTitle(String streamTitle) {
        this.streamTitle = streamTitle;
    }

    public String getDevcode() {
        return devcode ;
    }

    public void setDevcode(String devcode) {
        this.devcode = devcode;
    }
}

