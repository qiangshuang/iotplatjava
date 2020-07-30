package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(FaceHistory)访问记录
//generate by redcloud,2020-07-24 19:59:20
public class FaceHistory implements Serializable {
    private static final long serialVersionUID = 25L;
    // 主键id
    private Long id ;
    // 来访人员id
    @JSONField(name = "visit_id")
    private Long visitId ;
    // 来访人员id
    @JSONField(name = "visit_title")
    private String visitTitle ;
    // 性别
    private String gender ;
    // 手机号
    private String mobile ;
    // 部门ID
    @JSONField(name = "parent_id")
    private Long parentId ;
    // 部门名称
    @JSONField(name = "parent_title")
    private String parentTitle ;
    // 工号
    private Integer cardnumber ;
    // 身份证号
    private String idnumber ;
    // 被访人员id
    @JSONField(name = "interview_id")
    private Long interviewId ;
    // 被访人员
    @JSONField(name = "interview_title")
    private String interviewTitle ;
    // 门禁id
    @JSONField(name = "gateway_id")
    private Long gatewayId ;
    // 设备名称
    @JSONField(name = "gateway_titlee")
    private String gatewayTitlee ;
    // 访问时间
    private Long visittime ;
    // 抓拍照片
    private String vistpic ;
    // 底库照片
    private String userpic ;
    // 状态（允许通过，禁止通过）
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

    public String getVisitTitle() {
        return visitTitle ;
    }

    public void setVisitTitle(String visitTitle) {
        this.visitTitle = visitTitle;
    }

    public String getGender() {
        return gender ;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile ;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getParentId() {
        return parentId ;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentTitle() {
        return parentTitle ;
    }

    public void setParentTitle(String parentTitle) {
        this.parentTitle = parentTitle;
    }

    public Integer getCardnumber() {
        return cardnumber ;
    }

    public void setCardnumber(Integer cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getIdnumber() {
        return idnumber ;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public Long getInterviewId() {
        return interviewId ;
    }

    public void setInterviewId(Long interviewId) {
        this.interviewId = interviewId;
    }

    public String getInterviewTitle() {
        return interviewTitle ;
    }

    public void setInterviewTitle(String interviewTitle) {
        this.interviewTitle = interviewTitle;
    }

    public Long getGatewayId() {
        return gatewayId ;
    }

    public void setGatewayId(Long gatewayId) {
        this.gatewayId = gatewayId;
    }

    public String getGatewayTitlee() {
        return gatewayTitlee ;
    }

    public void setGatewayTitlee(String gatewayTitlee) {
        this.gatewayTitlee = gatewayTitlee;
    }

    public Long getVisittime() {
        return visittime ;
    }

    public void setVisittime(Long visittime) {
        this.visittime = visittime;
    }

    public String getVistpic() {
        return vistpic ;
    }

    public void setVistpic(String vistpic) {
        this.vistpic = vistpic;
    }

    public String getUserpic() {
        return userpic ;
    }

    public void setUserpic(String userpic) {
        this.userpic = userpic;
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

