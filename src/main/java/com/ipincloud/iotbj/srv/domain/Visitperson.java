package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(Visitperson)
//generate by redcloud,2020-07-08 01:57:14
public class Visitperson implements Serializable {
    private static final long serialVersionUID = 42L;
    // 自增ID
    private Long id ;
    // 身份证号：
    private String idno ;
    // 姓名：
    private String name ;
    // 性别：
    private String gender ;
    // 人脸照片：
    private String image ;
    // 手机号码：
    private String phonenumber ;

    public Long getId() {
        return id ;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdno() {
        return idno ;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getName() {
        return name ;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender ;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image ;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhonenumber() {
        return phonenumber ;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

}

