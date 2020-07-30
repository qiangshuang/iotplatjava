package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(SensorProduct)传感器产品
//generate by redcloud,2020-07-24 19:59:20
public class SensorProduct implements Serializable {
    private static final long serialVersionUID = 55L;
    // 自增ID
    private Long id ;
    // 名称
    private String title ;
    // 描述
    private String description ;

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

    public String getDescription() {
        return description ;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

