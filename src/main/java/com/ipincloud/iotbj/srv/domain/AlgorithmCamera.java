package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(AlgorithmCamera)摄像机
//generate by redcloud,2020-07-24 19:59:20
public class AlgorithmCamera implements Serializable {
    private static final long serialVersionUID = 6L;
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
    private String site ;
    // 分辨率
    private String resolution ;
    // 图片
    private String pic ;
    // 视频编码
    private String codec ;
    // 场景
    private String scene ;
    // 帧率
    private String framerate ;
    // 厂商
    private String firm ;
    // 视频地址
    private String videoa ;
    // 运行状态
    private String state ;
    // 算法
    private String algorithms ;
    // 平面视图
    private String planview ;
    // 推流地址
    private String pushaddress ;
    // 创建时间
    private Long created ;
    // 修改时间
    private Long updated ;

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

    public String getSite() {
        return site ;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getResolution() {
        return resolution ;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getPic() {
        return pic ;
    }

    public void setPic(String pic) {
        this.pic = pic;
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

    public String getFirm() {
        return firm ;
    }

    public void setFirm(String firm) {
        this.firm = firm;
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

    public String getPushaddress() {
        return pushaddress ;
    }

    public void setPushaddress(String pushaddress) {
        this.pushaddress = pushaddress;
    }

    public Long getCreated() {
        return created ;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Long getUpdated() {
        return updated ;
    }

    public void setUpdated(Long updated) {
        this.updated = updated;
    }

}

