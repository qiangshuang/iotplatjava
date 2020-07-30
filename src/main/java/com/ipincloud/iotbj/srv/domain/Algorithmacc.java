package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(Algorithmacc)算法接入
//generate by redcloud,2020-07-24 19:59:20
public class Algorithmacc implements Serializable {
    private static final long serialVersionUID = 8L;
    // 自增ID
    private Long id ;
    // 请求地址：
    private String address ;
    // 算法接入：
    private String accessmode ;
    // 预警等级
    @JSONField(name = "warning_level")
    private String warningLevel ;
    // 关联人脸识别
    @JSONField(name = "relation_face")
    private String relationFace ;
    // 算法名称：
    private String title ;
    // 算法描述：
    private String describion ;
    // 算法标识
    private String accesscode ;

    public Long getId() {
        return id ;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address ;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAccessmode() {
        return accessmode ;
    }

    public void setAccessmode(String accessmode) {
        this.accessmode = accessmode;
    }

    public String getWarningLevel() {
        return warningLevel ;
    }

    public void setWarningLevel(String warningLevel) {
        this.warningLevel = warningLevel;
    }

    public String getRelationFace() {
        return relationFace ;
    }

    public void setRelationFace(String relationFace) {
        this.relationFace = relationFace;
    }

    public String getTitle() {
        return title ;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribion() {
        return describion ;
    }

    public void setDescribion(String describion) {
        this.describion = describion;
    }

    public String getAccesscode() {
        return accesscode ;
    }

    public void setAccesscode(String accesscode) {
        this.accesscode = accesscode;
    }

}

