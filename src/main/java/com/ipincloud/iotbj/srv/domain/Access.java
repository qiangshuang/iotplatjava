package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(Access)门禁编辑
//generate by redcloud,2020-07-07 23:53:41
public class Access implements Serializable {
    private static final long serialVersionUID = 1L;
    // 自增ID
    private Long id ;

    public Long getId() {
        return id ;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

