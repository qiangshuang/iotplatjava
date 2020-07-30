package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(Algorithmresult)算法结果
//generate by redcloud,2020-07-24 19:59:20
public class Algorithmresult implements Serializable {
    private static final long serialVersionUID = 10L;
    // 主键id
    private Long id ;
    // 摄像机id
    @JSONField(name = "camera_id")
    private String cameraId ;
    // 返回结果
    private String result ;
    // 返回消息
    private String message ;
    // 预警图片路径
    private String imgpath ;

    public Long getId() {
        return id ;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCameraId() {
        return cameraId ;
    }

    public void setCameraId(String cameraId) {
        this.cameraId = cameraId;
    }

    public String getResult() {
        return result ;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message ;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImgpath() {
        return imgpath ;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

}

