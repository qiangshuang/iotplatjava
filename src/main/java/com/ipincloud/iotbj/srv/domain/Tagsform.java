package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(Tagsform)
//generate by redcloud,2020-07-07 10:18:15
public class Tagsform implements Serializable {
    // 自增ID
    private Long id ;

    public Long getId() {
        return id ;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

