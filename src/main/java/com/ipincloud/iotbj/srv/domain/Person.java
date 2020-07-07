package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(Person)
//generate by redcloud,2020-07-08 01:57:14
public class Person implements Serializable {
    private static final long serialVersionUID = 24L;
    // 自增ID
    private Long id ;
    // 姓名：
    private String name ;
    // 岗位：
    private String post ;
    // 选择部门：
    @JSONField(name = "org_id")
    private Long orgId ;
    // 身份证号：
    private String idnumber ;
    // 工号：
    private String jobno ;
    // 手机号：
    private String phone ;
    // 性别：
    private String gender ;
    // 照片
    private String image ;
    // 卡片数量：
    private Integer cardnum ;
    // 最后修改时间：
    private Long uptime ;

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

    public String getPost() {
        return post ;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Long getOrgId() {
        return orgId ;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getIdnumber() {
        return idnumber ;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getJobno() {
        return jobno ;
    }

    public void setJobno(String jobno) {
        this.jobno = jobno;
    }

    public String getPhone() {
        return phone ;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Integer getCardnum() {
        return cardnum ;
    }

    public void setCardnum(Integer cardnum) {
        this.cardnum = cardnum;
    }

    public Long getUptime() {
        return uptime ;
    }

    public void setUptime(Long uptime) {
        this.uptime = uptime;
    }

}

