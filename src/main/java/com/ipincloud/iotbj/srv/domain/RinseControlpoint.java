package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(RinseControlpoint)控制点位
//generate by redcloud,2020-07-24 19:59:20
public class RinseControlpoint implements Serializable {
    private static final long serialVersionUID = 44L;
    // 主键id
    private Long id ;
    // 控制点位
    private String titel ;
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

