package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(Dataflow)数据流
//generate by redcloud,2020-07-08 01:57:14
public class Dataflow implements Serializable {
    private static final long serialVersionUID = 14L;
    // 自增ID
    private Long id ;
    // 数据流值
    private String dataval ;
    // 数据流名称
    private String datatitle ;
    // 单位
    private String company ;
    // 单位符号
    private String comsym ;
    // 关联设备数
    private String equman ;

    public Long getId() {
        return id ;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataval() {
        return dataval ;
    }

    public void setDataval(String dataval) {
        this.dataval = dataval;
    }

    public String getDatatitle() {
        return datatitle ;
    }

    public void setDatatitle(String datatitle) {
        this.datatitle = datatitle;
    }

    public String getCompany() {
        return company ;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getComsym() {
        return comsym ;
    }

    public void setComsym(String comsym) {
        this.comsym = comsym;
    }

    public String getEquman() {
        return equman ;
    }

    public void setEquman(String equman) {
        this.equman = equman;
    }

}

