package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(Tags)标签管理
//generate by redcloud,2020-07-07 23:53:41
public class Tags implements Serializable {
    private static final long serialVersionUID = 35L;
    // 自增ID
    private Long id ;
    // 姓名
    private String name ;
    // 类型
    private String type ;
    // 厂商
    private String factory ;

    public Long getId() {
        return id ;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name ;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type ;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFactory() {
        return factory ;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

}

