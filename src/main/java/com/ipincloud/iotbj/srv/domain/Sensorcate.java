package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(Sensorcate)传感器类型
//generate by redcloud,2020-07-07 23:53:41
public class Sensorcate implements Serializable {
    private static final long serialVersionUID = 33L;
    // 自增ID
    private Long id ;
    // 名称
    private String title ;
    // 图标
    private String url ;
    // 设备数量
    private String sensornum ;

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

    public String getUrl() {
        return url ;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSensornum() {
        return sensornum ;
    }

    public void setSensornum(String sensornum) {
        this.sensornum = sensornum;
    }

}

