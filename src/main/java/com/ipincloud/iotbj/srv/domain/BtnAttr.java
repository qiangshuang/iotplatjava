package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(BtnAttr)
//generate by redcloud,2020-07-08 01:57:13
public class BtnAttr implements Serializable {
    private static final long serialVersionUID = 10L;
    // 键值ID
    private Long id ;
    // 调用编码
    private String encode ;
    // 功能配置1
    private String cs1 ;
    // 功能配置2
    private String cs2 ;
    // 功能配置3
    private String cs3 ;
    // 功能配置4
    private String cs4 ;
    // 功能配置5
    private String cs5 ;
    // 功能配置5
    private String cs6 ;
    // 备注
    private String memo ;
    // 主sql
    private String cs7 ;
    // 版本锁
    private Integer version ;

    public Long getId() {
        return id ;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEncode() {
        return encode ;
    }

    public void setEncode(String encode) {
        this.encode = encode;
    }

    public String getCs1() {
        return cs1 ;
    }

    public void setCs1(String cs1) {
        this.cs1 = cs1;
    }

    public String getCs2() {
        return cs2 ;
    }

    public void setCs2(String cs2) {
        this.cs2 = cs2;
    }

    public String getCs3() {
        return cs3 ;
    }

    public void setCs3(String cs3) {
        this.cs3 = cs3;
    }

    public String getCs4() {
        return cs4 ;
    }

    public void setCs4(String cs4) {
        this.cs4 = cs4;
    }

    public String getCs5() {
        return cs5 ;
    }

    public void setCs5(String cs5) {
        this.cs5 = cs5;
    }

    public String getCs6() {
        return cs6 ;
    }

    public void setCs6(String cs6) {
        this.cs6 = cs6;
    }

    public String getMemo() {
        return memo ;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCs7() {
        return cs7 ;
    }

    public void setCs7(String cs7) {
        this.cs7 = cs7;
    }

    public Integer getVersion() {
        return version ;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}

