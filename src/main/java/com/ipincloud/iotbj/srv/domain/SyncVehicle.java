package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(SyncVehicle)
//generate by redcloud,2020-07-24 19:59:20
public class SyncVehicle implements Serializable {
    private static final long serialVersionUID = 69L;
    // 车辆ID（最大长度64）
    private String vehicleId ;
    // 车牌号码（最大长度64）
    private String plateNo ;
    // 是否关联人员
    private String isBandPerson ;
    // 人员ID（最大长度64）
    private String personId ;
    // 车主姓名（最大长度64） 车辆是否和人员绑定，带有人员信息
    private String personName ;
    // 车主联系电话（最大长度64）
    private String phoneNo ;
    // 车牌类型
    private String plateType ;
    // 车牌颜色
    private String plateColor ;
    // 车辆类型
    private String vehicleType ;
    // 车辆颜色
    private String vehicleColor ;
    // 车辆描述
    private String mark ;

    public String getVehicleId() {
        return vehicleId ;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getPlateNo() {
        return plateNo ;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public String getIsBandPerson() {
        return isBandPerson ;
    }

    public void setIsBandPerson(String isBandPerson) {
        this.isBandPerson = isBandPerson;
    }

    public String getPersonId() {
        return personId ;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName ;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPhoneNo() {
        return phoneNo ;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPlateType() {
        return plateType ;
    }

    public void setPlateType(String plateType) {
        this.plateType = plateType;
    }

    public String getPlateColor() {
        return plateColor ;
    }

    public void setPlateColor(String plateColor) {
        this.plateColor = plateColor;
    }

    public String getVehicleType() {
        return vehicleType ;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleColor() {
        return vehicleColor ;
    }

    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    public String getMark() {
        return mark ;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

}

