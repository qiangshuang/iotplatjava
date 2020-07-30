package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(SyncAcsdev)
//generate by redcloud,2020-07-24 19:59:20
public class SyncAcsdev implements Serializable {
    private static final long serialVersionUID = 67L;
    // 门禁设备唯一标识
    private String acsDevIndexCode ;
    // 门禁设备名称
    private String acsDevName ;
    // 门禁设备类型描述
    private String acsDevTypeDesc ;
    // 门禁设备类型编号
    private String acsDevTypeCode ;
    // 门禁设备类型名称
    private String acsDevTypeName ;
    // 门禁设备IP
    private String acsDevIp ;
    // 门禁设备port
    private String acsDevPort ;
    // 门禁设备编号
    private String acsDevCode ;
    // 设备所属区域唯一标识
    private String regionIndexCode ;
    // 接入协议
    private String treatyType ;
    // 创建时间
    private String createTime ;
    // 更新时间
    private String updateTime ;

    public String getAcsDevIndexCode() {
        return acsDevIndexCode ;
    }

    public void setAcsDevIndexCode(String acsDevIndexCode) {
        this.acsDevIndexCode = acsDevIndexCode;
    }

    public String getAcsDevName() {
        return acsDevName ;
    }

    public void setAcsDevName(String acsDevName) {
        this.acsDevName = acsDevName;
    }

    public String getAcsDevTypeDesc() {
        return acsDevTypeDesc ;
    }

    public void setAcsDevTypeDesc(String acsDevTypeDesc) {
        this.acsDevTypeDesc = acsDevTypeDesc;
    }

    public String getAcsDevTypeCode() {
        return acsDevTypeCode ;
    }

    public void setAcsDevTypeCode(String acsDevTypeCode) {
        this.acsDevTypeCode = acsDevTypeCode;
    }

    public String getAcsDevTypeName() {
        return acsDevTypeName ;
    }

    public void setAcsDevTypeName(String acsDevTypeName) {
        this.acsDevTypeName = acsDevTypeName;
    }

    public String getAcsDevIp() {
        return acsDevIp ;
    }

    public void setAcsDevIp(String acsDevIp) {
        this.acsDevIp = acsDevIp;
    }

    public String getAcsDevPort() {
        return acsDevPort ;
    }

    public void setAcsDevPort(String acsDevPort) {
        this.acsDevPort = acsDevPort;
    }

    public String getAcsDevCode() {
        return acsDevCode ;
    }

    public void setAcsDevCode(String acsDevCode) {
        this.acsDevCode = acsDevCode;
    }

    public String getRegionIndexCode() {
        return regionIndexCode ;
    }

    public void setRegionIndexCode(String regionIndexCode) {
        this.regionIndexCode = regionIndexCode;
    }

    public String getTreatyType() {
        return treatyType ;
    }

    public void setTreatyType(String treatyType) {
        this.treatyType = treatyType;
    }

    public String getCreateTime() {
        return createTime ;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime ;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

}

