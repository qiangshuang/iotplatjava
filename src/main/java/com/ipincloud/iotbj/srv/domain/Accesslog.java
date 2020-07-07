package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(Accesslog)访问记录
//generate by redcloud,2020-07-08 01:57:13
public class Accesslog implements Serializable {
    private static final long serialVersionUID = 2L;
    // 主键id
    private Long id ;
    // 访问时间
    private String accesstime ;
    // 设备名称
    private String equipment ;
    // 姓名
    private String title ;
    // 性别
    private String gender ;
    // 部门
    private String org ;
    // 工号
    private String cardno ;
    // 身份证
    private String idnumber ;
    // 手机号
    private String mobile ;
    // 状态
    private String state ;
    // 抓拍图片
    private String capturephoto ;
    // 底库照片
    private String basementphoto ;

    public Long getId() {
        return id ;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccesstime() {
        return accesstime ;
    }

    public void setAccesstime(String accesstime) {
        this.accesstime = accesstime;
    }

    public String getEquipment() {
        return equipment ;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getTitle() {
        return title ;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGender() {
        return gender ;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOrg() {
        return org ;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getCardno() {
        return cardno ;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getIdnumber() {
        return idnumber ;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getMobile() {
        return mobile ;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getState() {
        return state ;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCapturephoto() {
        return capturephoto ;
    }

    public void setCapturephoto(String capturephoto) {
        this.capturephoto = capturephoto;
    }

    public String getBasementphoto() {
        return basementphoto ;
    }

    public void setBasementphoto(String basementphoto) {
        this.basementphoto = basementphoto;
    }

}

