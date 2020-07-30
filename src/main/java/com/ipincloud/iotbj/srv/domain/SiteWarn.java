package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(SiteWarn)预警类型
//generate by redcloud,2020-07-24 19:59:20
public class SiteWarn implements Serializable {
    private static final long serialVersionUID = 65L;
    // 主键id
    private Long id ;
    // 名称
    private String title ;
    // 描述
    private String border ;
    // 预警等级
    private String grade ;
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

    public String getGrade() {
        return grade ;
    }

    public void setGrade(String grade) {
        this.grade = grade;
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

