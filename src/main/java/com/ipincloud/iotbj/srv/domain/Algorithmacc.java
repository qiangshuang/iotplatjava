package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(Algorithmacc)算法接入
//generate by redcloud,2020-07-08 01:57:13
public class Algorithmacc implements Serializable {
    private static final long serialVersionUID = 4L;
    // 自增ID
    private Long id ;
    // 请求地址：
    private String address ;
    // 算法接入：
    private String accessmode ;
    // 算法名称：
    private String title ;
    // 算法描述：
    private String describion ;

    public Long getId() {
        return id ;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address ;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAccessmode() {
        return accessmode ;
    }

    public void setAccessmode(String accessmode) {
        this.accessmode = accessmode;
    }

    public String getTitle() {
        return title ;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribion() {
        return describion ;
    }

    public void setDescribion(String describion) {
        this.describion = describion;
    }

}

