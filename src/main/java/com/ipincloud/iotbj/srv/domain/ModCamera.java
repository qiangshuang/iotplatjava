package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(ModCamera)摄像机
//generate by redcloud,2020-07-24 19:59:20
public class ModCamera implements Serializable {
    private static final long serialVersionUID = 34L;
    // 摄像头标识
    @JSONField(name = "camera_index")
    private String cameraIndex ;
    // 厂商名
    @JSONField(name = "firm_type")
    private String firmType ;
    // 监控点ID
    @JSONField(name = "hik_camera_index")
    private String hikCameraIndex ;
    // 摄像机名称
    @JSONField(name = "camera_name")
    private String cameraName ;
    // 能力集，逗号分割
    @JSONField(name = "capability_set")
    private String capabilitySet ;
    // 云镜类型：1全方位云台（带转到和变焦），2只有变焦不带转动，3只带转动，不带变焦，4无云台，无变焦
    private String ptz ;
    // 录像存储位置：0中心存储，1设备存储
    @JSONField(name = "record_location")
    private Integer recordLocation ;
    // 传输协议：0-UDP,1-TCP
    @JSONField(name = "trans_type")
    private Integer transType ;
    // 状态：1在线，0离线
    private Integer status ;
    // 所在区域
    @JSONField(name = "region_path")
    private String regionPath ;
    // 通道号
    @JSONField(name = "channel_no")
    private String channelNo ;
    // 1无云台能力，0有云台能力
    @JSONField(name = "trans_ptz")
    private String transPtz ;
    // 海康视频流
    @JSONField(name = "hik_url")
    private String hikUrl ;

    public String getCameraIndex() {
        return cameraIndex ;
    }

    public void setCameraIndex(String cameraIndex) {
        this.cameraIndex = cameraIndex;
    }

    public String getFirmType() {
        return firmType ;
    }

    public void setFirmType(String firmType) {
        this.firmType = firmType;
    }

    public String getHikCameraIndex() {
        return hikCameraIndex ;
    }

    public void setHikCameraIndex(String hikCameraIndex) {
        this.hikCameraIndex = hikCameraIndex;
    }

    public String getCameraName() {
        return cameraName ;
    }

    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }

    public String getCapabilitySet() {
        return capabilitySet ;
    }

    public void setCapabilitySet(String capabilitySet) {
        this.capabilitySet = capabilitySet;
    }

    public String getPtz() {
        return ptz ;
    }

    public void setPtz(String ptz) {
        this.ptz = ptz;
    }

    public Integer getRecordLocation() {
        return recordLocation ;
    }

    public void setRecordLocation(Integer recordLocation) {
        this.recordLocation = recordLocation;
    }

    public Integer getTransType() {
        return transType ;
    }

    public void setTransType(Integer transType) {
        this.transType = transType;
    }

    public Integer getStatus() {
        return status ;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRegionPath() {
        return regionPath ;
    }

    public void setRegionPath(String regionPath) {
        this.regionPath = regionPath;
    }

    public String getChannelNo() {
        return channelNo ;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

    public String getTransPtz() {
        return transPtz ;
    }

    public void setTransPtz(String transPtz) {
        this.transPtz = transPtz;
    }

    public String getHikUrl() {
        return hikUrl ;
    }

    public void setHikUrl(String hikUrl) {
        this.hikUrl = hikUrl;
    }

}

