package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(Persone)
//generate by redcloud,2020-07-07 10:18:15
public class Persone implements Serializable {
    // 自增ID
    private Long id ;
    // 姓名
    private String name ;
    // 工号
    private String jobno ;
    // 身份证号
    private String idnumber ;

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

    public String getJobno() {
        return jobno ;
    }

    public void setJobno(String jobno) {
        this.jobno = jobno;
    }

    public String getIdnumber() {
        return idnumber ;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

}

