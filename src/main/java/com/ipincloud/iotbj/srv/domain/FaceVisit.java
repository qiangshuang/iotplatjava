package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(FaceVisit)来访人员管理
//generate by redcloud,2020-07-24 19:59:20
public class FaceVisit implements Serializable {
    private static final long serialVersionUID = 28L;
    // 主键id
    private Long id ;
    // 来访人员id
    @JSONField(name = "visit_id")
    private Long visitId ;
    // 被访人员id
    @JSONField(name = "interview_ids")
    private String interviewIds ;
    // 被访人员
    @JSONField(name = "interview_titles")
    private String interviewTitles ;
    // 门禁id
    @JSONField(name = "gateway_id")
    private Long gatewayId ;
    // 门禁
    @JSONField(name = "gateway_title")
    private String gatewayTitle ;
    // 申请时间
    private Long conftime ;
    // 通行时间-开始
    private Long starttime ;
    // 通行时间-结束
    private Long endtime ;
    // 状态（申请中，已通过，已拒绝）
    private String state ;
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

    public Long getVisitId() {
        return visitId ;
    }

    public void setVisitId(Long visitId) {
        this.visitId = visitId;
    }

    public String getInterviewIds() {
        return interviewIds ;
    }

    public void setInterviewIds(String interviewIds) {
        this.interviewIds = interviewIds;
    }

    public String getInterviewTitles() {
        return interviewTitles ;
    }

    public void setInterviewTitles(String interviewTitles) {
        this.interviewTitles = interviewTitles;
    }

    public Long getGatewayId() {
        return gatewayId ;
    }

    public void setGatewayId(Long gatewayId) {
        this.gatewayId = gatewayId;
    }

    public String getGatewayTitle() {
        return gatewayTitle ;
    }

    public void setGatewayTitle(String gatewayTitle) {
        this.gatewayTitle = gatewayTitle;
    }

    public Long getConftime() {
        return conftime ;
    }

    public void setConftime(Long conftime) {
        this.conftime = conftime;
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

}

