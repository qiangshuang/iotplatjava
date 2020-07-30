package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(Firm)厂商
//generate by redcloud,2020-07-24 19:59:20
public class Firm implements Serializable {
    private static final long serialVersionUID = 30L;
    // 主键id
    private Long id ;
    // 名称
    private String titel ;
    // 描述
    private String description ;
    // 厂商标识
    private String ucode ;
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

    public String getDescription() {
        return description ;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUcode() {
        return ucode ;
    }

    public void setUcode(String ucode) {
        this.ucode = ucode;
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

