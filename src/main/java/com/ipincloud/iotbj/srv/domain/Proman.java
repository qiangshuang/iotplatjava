package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(Proman)产品管理
//generate by redcloud,2020-07-07 10:18:15
public class Proman implements Serializable {
    // 自增ID
    private Long id ;
    // 名称
    private String title ;
    // 描述
    private String prodes ;

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

    public String getProdes() {
        return prodes ;
    }

    public void setProdes(String prodes) {
        this.prodes = prodes;
    }

}

