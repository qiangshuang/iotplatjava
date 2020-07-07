package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(Region)区域
//generate by redcloud,2020-07-08 01:57:14
public class Region implements Serializable {
    private static final long serialVersionUID = 28L;
    // 自增ID
    private Long id ;
    // 区域名称：
    private String title ;
    // 上级ID：
    @JSONField(name = "parent_id")
    private Long parentId ;

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

    public Long getParentId() {
        return parentId ;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

}

