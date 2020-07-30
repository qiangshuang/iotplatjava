package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(Region)区域
//generate by redcloud,2020-07-24 19:59:20
public class Region implements Serializable {
    private static final long serialVersionUID = 43L;
    // 自增ID
    private Long id ;
    // 区域名称
    private String title ;
    // 上级ID
    @JSONField(name = "parent_id")
    private Long parentId ;
    // 唯一标识
    private String indexCode ;
    // 父标识
    private String parentIndexCode ;

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

    public String getIndexCode() {
        return indexCode ;
    }

    public void setIndexCode(String indexCode) {
        this.indexCode = indexCode;
    }

    public String getParentIndexCode() {
        return parentIndexCode ;
    }

    public void setParentIndexCode(String parentIndexCode) {
        this.parentIndexCode = parentIndexCode;
    }

}

