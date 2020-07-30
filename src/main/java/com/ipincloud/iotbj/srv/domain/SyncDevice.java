package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(SyncDevice)
//generate by redcloud,2020-07-24 19:59:20
public class SyncDevice implements Serializable {
    private static final long serialVersionUID = 68L;
    // 资源类型
    private String resourceType ;
    // 资源唯一标志
    private String indexCode ;
    // 用户名
    private String userName ;
    // 资源名称
    private String name ;
    // 所属区域编号
    private String regionIndexCode ;
    // 所属区域路径
    private String regionPath ;
    // 区域路径名称
    private String regionPathName ;
    // 设备IP
    private String ip ;
    // 设备端口
    private String port ;
    // 设备接入协议
    private String treatyType ;
    // 所属网域ID
    private String netZoneId ;
    // 厂商
    private String manufacturer ;
    // 创建时间
    private String createTime ;
    // 更新时间
    private String updateTime ;

    public String getResourceType() {
        return resourceType ;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getIndexCode() {
        return indexCode ;
    }

    public void setIndexCode(String indexCode) {
        this.indexCode = indexCode;
    }

    public String getUserName() {
        return userName ;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name ;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegionIndexCode() {
        return regionIndexCode ;
    }

    public void setRegionIndexCode(String regionIndexCode) {
        this.regionIndexCode = regionIndexCode;
    }

    public String getRegionPath() {
        return regionPath ;
    }

    public void setRegionPath(String regionPath) {
        this.regionPath = regionPath;
    }

    public String getRegionPathName() {
        return regionPathName ;
    }

    public void setRegionPathName(String regionPathName) {
        this.regionPathName = regionPathName;
    }

    public String getIp() {
        return ip ;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port ;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getTreatyType() {
        return treatyType ;
    }

    public void setTreatyType(String treatyType) {
        this.treatyType = treatyType;
    }

    public String getNetZoneId() {
        return netZoneId ;
    }

    public void setNetZoneId(String netZoneId) {
        this.netZoneId = netZoneId;
    }

    public String getManufacturer() {
        return manufacturer ;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
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

