package com.ipincloud.iotbj.srv.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

//(User)
//generate by redcloud,2020-07-23 11:43:17
public class User implements Serializable {
    private static final long serialVersionUID = 72L;
    // 手机号
    private String mobile;
    // 账号
    @JSONField(name = "user_name")
    private String userName;
    // 密码
    private String pwd;
    // 主键ID
    private Long id;
    // 姓名
    private String title;
    // 部门ID
    @JSONField(name = "parent_id")
    private Long parentId;
    // 部门名称
    @JSONField(name = "parent_title")
    private String parentTitle;
    // 最后登录时间
    private Long lastlogin;
    // 岗位
    @JSONField(name = "job_title")
    private String jobTitle;
    // 岗位ID
    @JSONField(name = "job_id")
    private Long jobId;
    // 第三方
    private String thirdin;
    // 添加时间
    private Long created;
    // 最后修改时间
    private Long updated;
    private String photo;
    // 身份证号
    private String idnumber;

    private String jobno;
    // 卡片数量
    private Integer cardnumber;
    // 主卡号
    private String mcardno;

    // 性别
    private String gender;
    // 用户唯一标识
    private String personId;

    private String userGroup;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentTitle() {
        return parentTitle;
    }

    public void setParentTitle(String parentTitle) {
        this.parentTitle = parentTitle;
    }

    public Long getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(Long lastlogin) {
        this.lastlogin = lastlogin;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getThirdin() {
        return thirdin;
    }

    public void setThirdin(String thirdin) {
        this.thirdin = thirdin;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Long getUpdated() {
        return updated;
    }

    public void setUpdated(Long updated) {
        this.updated = updated;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }
    public String getJobno() {
        return jobno;
    }

    public void setJobno(String jobno) {
        this.jobno = jobno;
    }
    public Integer getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(Integer cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getMcardno() {
        return mcardno;
    }

    public void setMcardno(String mcardno) {
        this.mcardno = mcardno;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public boolean isGuest() {
        return "外来访客".equals(userGroup);
    }

}

