package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(SiteFence)电子围栏
//generate by redcloud,2020-07-24 19:59:20
public class SiteFence implements Serializable {
    private static final long serialVersionUID = 62L;
    // 主键id
    private Long id ;
    // 名称
    private String title ;
    // 越界边界
    private String border ;
    // 属性
    private String attribute ;
    // 预警类型id
    @JSONField(name = "warning_id")
    private Long warningId ;
    // 预警类型
    @JSONField(name = "warning_title")
    private String warningTitle ;
    // 区域id
    @JSONField(name = "region_id")
    private Long regionId ;
    // 区域名称
    @JSONField(name = "region_title")
    private String regionTitle ;
    // 开始时间
    private Long starttime ;
    // 结束时间
    private Long endtime ;
    // 状态（禁用中，启用中）
    private String state ;
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

    public String getBorder() {
        return border ;
    }

    public void setBorder(String border) {
        this.border = border;
    }

    public String getAttribute() {
        return attribute ;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public Long getWarningId() {
        return warningId ;
    }

    public void setWarningId(Long warningId) {
        this.warningId = warningId;
    }

    public String getWarningTitle() {
        return warningTitle ;
    }

    public void setWarningTitle(String warningTitle) {
        this.warningTitle = warningTitle;
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

    public String getState() {
        return state ;
    }

    public void setState(String state) {
        this.state = state;
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

