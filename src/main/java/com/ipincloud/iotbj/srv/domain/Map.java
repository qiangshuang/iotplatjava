package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(Map)地图
//generate by redcloud,2020-07-24 19:59:20
public class Map implements Serializable {
    private static final long serialVersionUID = 33L;
    // 主键id
    private Long id ;
    // 名称
    private String titel ;
    // 类型
    private String category ;
    // 地图文件
    private String picurl ;
    // 是否首页展示
    private String showhome ;
    // 区域id
    @JSONField(name = "region_id")
    private Long regionId ;
    // 区域名称
    @JSONField(name = "region_title")
    private String regionTitle ;
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

    public String getCategory() {
        return category ;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPicurl() {
        return picurl ;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getShowhome() {
        return showhome ;
    }

    public void setShowhome(String showhome) {
        this.showhome = showhome;
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

