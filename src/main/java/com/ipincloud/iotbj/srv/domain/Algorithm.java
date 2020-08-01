package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(Algorithm)算法
//generate by redcloud,2020-07-24 19:59:20
public class Algorithm implements Serializable {
    private static final long serialVersionUID = 3L;
    // 自增ID
    private Long id ;
    // 摄像机ID
    @JSONField(name = "camera_id")
    private Long cameraId ;
    // 算法ID
    @JSONField(name = "algorithm_id")
    private Long algorithmId ;
    // 开始时间
    @JSONField(name = "pushtime_start")
    private Long pushtimeStart ;
    // 结束时间
    @JSONField(name = "pushtime_end")
    private Long pushtimeEnd ;
    // 算法名称
    @JSONField(name = "algorithm_title")
    private String algorithmTitle ;
    // 摄像机名称
    @JSONField(name = "camera_title")
    private String cameraTitle ;
    // 越界边界
    private String border ;
    // 越界方向
    private String direction ;
    // 人脸识别
    private Integer distinguish ;
    // 运行状态
    private String state ;
    // 帧率
    private String framerate ;
    // 分辨率
    private String resolution ;
    // 视频地址
    private String videoa ;
    // 摄像头编码
    private String codec ;
    // 算法地址
    @JSONField(name = "algorithm_url")
    private String algorithmUrl ;
    // 人员ids
    @JSONField(name = "user_ids")
    private String userIds ;
    // 人员姓名
    @JSONField(name = "user_titles")
    private String userTitles ;
    // 开启时间
    private Long opentime ;

    private String accesscode;
    private String pushaddress;
    private String region_title;

    public Long getId() {
        return id ;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCameraId() {
        return cameraId ;
    }

    public void setCameraId(Long cameraId) {
        this.cameraId = cameraId;
    }

    public Long getAlgorithmId() {
        return algorithmId ;
    }

    public void setAlgorithmId(Long algorithmId) {
        this.algorithmId = algorithmId;
    }

    public Long getPushtimeStart() {
        return pushtimeStart ;
    }

    public void setPushtimeStart(Long pushtimeStart) {
        this.pushtimeStart = pushtimeStart;
    }

    public Long getPushtimeEnd() {
        return pushtimeEnd ;
    }

    public void setPushtimeEnd(Long pushtimeEnd) {
        this.pushtimeEnd = pushtimeEnd;
    }

    public String getAlgorithmTitle() {
        return algorithmTitle ;
    }

    public void setAlgorithmTitle(String algorithmTitle) {
        this.algorithmTitle = algorithmTitle;
    }

    public String getCameraTitle() {
        return cameraTitle ;
    }

    public void setCameraTitle(String cameraTitle) {
        this.cameraTitle = cameraTitle;
    }

    public String getBorder() {
        return border ;
    }

    public void setBorder(String border) {
        this.border = border;
    }

    public String getDirection() {
        return direction ;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Integer getDistinguish() {
        return distinguish ;
    }

    public void setDistinguish(Integer distinguish) {
        this.distinguish = distinguish;
    }

    public String getState() {
        return state ;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFramerate() {
        return framerate ;
    }

    public void setFramerate(String framerate) {
        this.framerate = framerate;
    }

    public String getResolution() {
        return resolution ;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getVideoa() {
        return videoa ;
    }

    public void setVideoa(String videoa) {
        this.videoa = videoa;
    }

    public String getCodec() {
        return codec ;
    }

    public void setCodec(String codec) {
        this.codec = codec;
    }

    public String getAlgorithmUrl() {
        return algorithmUrl ;
    }

    public void setAlgorithmUrl(String algorithmUrl) {
        this.algorithmUrl = algorithmUrl;
    }

    public String getUserIds() {
        return userIds ;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public String getUserTitles() {
        return userTitles ;
    }

    public void setUserTitles(String userTitles) {
        this.userTitles = userTitles;
    }

    public Long getOpentime() {
        return opentime ;
    }

    public void setOpentime(Long opentime) {
        this.opentime = opentime;
    }

    public String getAccesscode() {
        return accesscode;
    }

    public void setAccesscode(String accesscode) {
        this.accesscode = accesscode;
    }

    public String getPushaddress() {
        return pushaddress;
    }

    public void setPushaddress(String pushaddress) {
        this.pushaddress = pushaddress;
    }

    public String getRegion_title() {
        return region_title;
    }

    public void setRegion_title(String region_title) {
        this.region_title = region_title;
    }
}

