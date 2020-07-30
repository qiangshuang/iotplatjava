package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(SiteCheck)巡检管理
//generate by redcloud,2020-07-24 19:59:20
public class SiteCheck implements Serializable {
    private static final long serialVersionUID = 61L;
    // 主键id
    private Long id ;
    // 巡检名称
    private String title ;
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
    // 负责人
    private String person ;
    // 状态（未进行，进行中，已完成）
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

    public String getPerson() {
        return person ;
    }

    public void setPerson(String person) {
        this.person = person;
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

