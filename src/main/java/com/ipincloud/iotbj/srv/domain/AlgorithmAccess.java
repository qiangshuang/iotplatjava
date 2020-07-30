package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(AlgorithmAccess)算法接入
//generate by redcloud,2020-07-24 19:59:20
public class AlgorithmAccess implements Serializable {
    private static final long serialVersionUID = 4L;
    // 自增ID
    private Long id ;
    // 算法名称
    private String title ;
    // 算法描述
    private String describion ;
    // 请求地址
    private String address ;
    // 算法接入
    private String accessmode ;
    // 预警等级
    private String grade ;
    // 是否关联人脸识别
    private String relationface ;
    // 算法标识
    private String ucode ;
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

    public String getDescribion() {
        return describion ;
    }

    public void setDescribion(String describion) {
        this.describion = describion;
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

    public String getGrade() {
        return grade ;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getRelationface() {
        return relationface ;
    }

    public void setRelationface(String relationface) {
        this.relationface = relationface;
    }

    public String getUcode() {
        return ucode ;
    }

    public void setUcode(String ucode) {
        this.ucode = ucode;
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

