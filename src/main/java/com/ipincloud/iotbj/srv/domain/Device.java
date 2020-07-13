package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(Device)设备
//generate by redcloud,2020-07-07 10:18:15
public class Device implements Serializable {
    // 主键ID
    private Long id ;
    // 设备名称
    private String title ;
    // 厂商
    private String corp ;
    // 型号
    private String model ;
    // 区域ID
    @JSONField(name = "region_id")
    private Long regionId ;
    // 区域名称
    @JSONField(name = "region_title")
    private String regionTitle ;
    // 位置图片
    private String posurl ;
    // 设备状态
    private String state ;
    // 参数1
    private String para1 ;
    // 参数2
    private String para2 ;
    // 参数3
    private String para3 ;
    // 参数4
    private String para4 ;
    // 参数5
    private String para5 ;
    // 参数6
    private String para6 ;
    // 参数7
    private String para7 ;
    // 参数8
    private String para8 ;
    // 参数9
    private String para9 ;
    // 参数10
    private String 参数10 ;

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

    public String getCorp() {
        return corp ;
    }

    public void setCorp(String corp) {
        this.corp = corp;
    }

    public String getModel() {
        return model ;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getRegionId() {
        return regionId ;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getRegionTitle() {
        return regionTitle ;
    }

    public void setRegionTitle(String regionTitle) {
        this.regionTitle = regionTitle;
    }

    public String getPosurl() {
        return posurl ;
    }

    public void setPosurl(String posurl) {
        this.posurl = posurl;
    }

    public String getState() {
        return state ;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPara1() {
        return para1 ;
    }

    public void setPara1(String para1) {
        this.para1 = para1;
    }

    public String getPara2() {
        return para2 ;
    }

    public void setPara2(String para2) {
        this.para2 = para2;
    }

    public String getPara3() {
        return para3 ;
    }

    public void setPara3(String para3) {
        this.para3 = para3;
    }

    public String getPara4() {
        return para4 ;
    }

    public void setPara4(String para4) {
        this.para4 = para4;
    }

    public String getPara5() {
        return para5 ;
    }

    public void setPara5(String para5) {
        this.para5 = para5;
    }

    public String getPara6() {
        return para6 ;
    }

    public void setPara6(String para6) {
        this.para6 = para6;
    }

    public String getPara7() {
        return para7 ;
    }

    public void setPara7(String para7) {
        this.para7 = para7;
    }

    public String getPara8() {
        return para8 ;
    }

    public void setPara8(String para8) {
        this.para8 = para8;
    }

    public String getPara9() {
        return para9 ;
    }

    public void setPara9(String para9) {
        this.para9 = para9;
    }

    public String get参数10() {
        return 参数10 ;
    }

    public void set参数10(String 参数10) {
        this.参数10 = 参数10;
    }

}

