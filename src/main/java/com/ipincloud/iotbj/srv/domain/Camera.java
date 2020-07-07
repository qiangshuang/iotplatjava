package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(Camera)摄像机
//generate by redcloud,2020-07-08 01:57:14
public class Camera implements Serializable {
    private static final long serialVersionUID = 11L;
    // 主键id
    private Long id ;
    // 设备名称
    private String title ;
    // 区域ID
    @JSONField(name = "region_id")
    private Long regionId ;
    // 区域名称
    @JSONField(name = "region_title")
    private String regionTitle ;
    // 摄像机位置
    private String pos ;
    // 分辨率
    private String resolution ;
    // 图片
    private String url ;
    // 视频编码
    private String codec ;
    // 场景
    private String scene ;
    // 帧率
    private String framerate ;
    // 厂商
    private String corp ;
    // 视频地址
    private String videoa ;
    // 运行状态
    private String state ;
    // 算法
    private String algorithms ;
    // 平面视图
    private String planview ;
    // 算法id
    @JSONField(name = "algorithms_id")
    private Long algorithmsId ;

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

    public String getPos() {
        return pos ;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getResolution() {
        return resolution ;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getUrl() {
        return url ;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCodec() {
        return codec ;
    }

    public void setCodec(String codec) {
        this.codec = codec;
    }

    public String getScene() {
        return scene ;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getFramerate() {
        return framerate ;
    }

    public void setFramerate(String framerate) {
        this.framerate = framerate;
    }

    public String getCorp() {
        return corp ;
    }

    public void setCorp(String corp) {
        this.corp = corp;
    }

    public String getVideoa() {
        return videoa ;
    }

    public void setVideoa(String videoa) {
        this.videoa = videoa;
    }

    public String getState() {
        return state ;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAlgorithms() {
        return algorithms ;
    }

    public void setAlgorithms(String algorithms) {
        this.algorithms = algorithms;
    }

    public String getPlanview() {
        return planview ;
    }

    public void setPlanview(String planview) {
        this.planview = planview;
    }

    public Long getAlgorithmsId() {
        return algorithmsId ;
    }

    public void setAlgorithmsId(Long algorithmsId) {
        this.algorithmsId = algorithmsId;
    }

}

