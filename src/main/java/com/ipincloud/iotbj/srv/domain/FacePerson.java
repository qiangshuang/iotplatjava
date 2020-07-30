package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(FacePerson)人员信息
//generate by redcloud,2020-07-24 19:59:20
public class FacePerson implements Serializable {
    private static final long serialVersionUID = 26L;
    // 主键id
    private Long id ;
    // 姓名
    private String title ;
    // 岗位
    private String post ;
    // 性别
    private String gender ;
    // 身份证号
    private String idnumber ;
    // 工号
    private String jobnumber ;
    // 卡片数量
    private Integer cardcount ;
    // 手机号
    private String mobile ;
    // 人员类型
    private String category ;
    // 照片
    @JSONField(name = "face_pic")
    private String facePic ;
    // 创建时间
    private Long created ;
    // 修改时间
    private Long updated ;
    // 部门id
    @JSONField(name = "org_id")
    private Long orgId ;
    // 部门
    @JSONField(name = "org_title")
    private String orgTitle ;

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

    public String getPost() {
        return post ;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getGender() {
        return gender ;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdnumber() {
        return idnumber ;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getJobnumber() {
        return jobnumber ;
    }

    public void setJobnumber(String jobnumber) {
        this.jobnumber = jobnumber;
    }

    public Integer getCardcount() {
        return cardcount ;
    }

    public void setCardcount(Integer cardcount) {
        this.cardcount = cardcount;
    }

    public String getMobile() {
        return mobile ;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCategory() {
        return category ;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFacePic() {
        return facePic ;
    }

    public void setFacePic(String facePic) {
        this.facePic = facePic;
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

    public Long getOrgId() {
        return orgId ;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgTitle() {
        return orgTitle ;
    }

    public void setOrgTitle(String orgTitle) {
        this.orgTitle = orgTitle;
    }

}

