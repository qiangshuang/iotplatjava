package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(SensorStream)数据流
//generate by redcloud,2020-07-24 19:59:20
public class SensorStream implements Serializable {
    private static final long serialVersionUID = 57L;
    // 自增ID
    private Long id ;
    // 数据流名称
    private String title ;
    // 数据流值
    private String dataval ;
    // 单位名称
    private String unitname ;
    // 单位符号
    private String unitcode ;

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

    public String getDataval() {
        return dataval ;
    }

    public void setDataval(String dataval) {
        this.dataval = dataval;
    }

    public String getUnitname() {
        return unitname ;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }

    public String getUnitcode() {
        return unitcode ;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }

}

