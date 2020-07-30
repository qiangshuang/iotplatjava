package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(FacePolicy)门禁权限管理
//generate by redcloud,2020-07-24 19:59:20
public class FacePolicy implements Serializable {
    private static final long serialVersionUID = 27L;
    // 主键id
    private Long id ;
    // 区域id
    @JSONField(name = "region_id")
    private Long regionId ;
    // 门禁id
    @JSONField(name = "gateway_id")
    private Long gatewayId ;
    // 部门id
    @JSONField(name = "org_id")
    private Long orgId ;
    // 人员id
    @JSONField(name = "user_id")
    private Long userId ;
    // 允许通行-开始
    private Long starttime ;
    // 允许通行-结束
    private Long endtime ;
    // 状态
    private String state ;
    // 创建时间
    private Long created ;
    // 修改时间
    private Long updated ;
    // 门禁设备唯一标识
    private String acsDevIndexCode ;
    // 人员唯一标识
    private String personId ;

    public Long getId() {
        return id ;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRegionId() {
        return regionId ;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public Long getGatewayId() {
        return gatewayId ;
    }

    public void setGatewayId(Long gatewayId) {
        this.gatewayId = gatewayId;
    }

    public Long getOrgId() {
        return orgId ;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getUserId() {
        return userId ;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getStarttime() {
        return starttime ;
    }

    public void setStarttime(Long starttime) {
        this.starttime = starttime;
    }

    public Long getEndtime() {
        return endtime ;
    }

    public void setEndtime(Long endtime) {
        this.endtime = endtime;
    }

    public String getState() {
        return state ;
    }

    public void setState(String state) {
        this.state = state;
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

    public String getAcsDevIndexCode() {
        return acsDevIndexCode ;
    }

    public void setAcsDevIndexCode(String acsDevIndexCode) {
        this.acsDevIndexCode = acsDevIndexCode;
    }

    public String getPersonId() {
        return personId ;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

}

