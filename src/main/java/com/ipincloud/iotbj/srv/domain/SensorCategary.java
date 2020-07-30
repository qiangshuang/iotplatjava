package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(SensorCategary)传感器类型
//generate by redcloud,2020-07-24 19:59:20
public class SensorCategary implements Serializable {
    private static final long serialVersionUID = 53L;
    // 自增ID
    private Long id ;
    // 名称
    private String title ;
    // 图标
    private String icon ;
    // 数量
    private String num ;

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

    public String getIcon() {
        return icon ;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getNum() {
        return num ;
    }

    public void setNum(String num) {
        this.num = num;
    }

}

