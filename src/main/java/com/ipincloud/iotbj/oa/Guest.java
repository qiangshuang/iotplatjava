package com.ipincloud.iotbj.oa;

public class Guest {
    public String id;
    public String userId;
    public String name;
    public String certificateNum;
    public String mobile;
    public String gender;
    public String targetUserId;
    public String targetUserName;
    public String startTime;
    public String endTime;
    public String facePic;
    public String createTime;
    public String status;

    public Guest() {
    }

    public Guest(String id, String userId, String name, String certificateNum, String mobile,
                 String gender, String targetUserId, String targetUserName, String startTime,
                 String endTime, String facePic, String createTime, String status) {
        this.id = id;
        this.userId = userId;
        this.name = userId;
        this.certificateNum = certificateNum;
        this.mobile = mobile;
        this.gender = gender;
        this.targetUserId = targetUserId;
        this.targetUserName = targetUserName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.facePic = facePic;
        this.createTime = createTime;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCertificateNum() {
        return certificateNum;
    }

    public void setCertificateNum(String certificateNum) {
        this.certificateNum = certificateNum;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(String targetUserId) {
        this.targetUserId = targetUserId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTargetUserName() {
        return targetUserName;
    }

    public void setTargetUserName(String targetUserName) {
        this.targetUserName = targetUserName;
    }

    public String getFacePic() {
        return facePic;
    }

    public void setFacePic(String facePic) {
        this.facePic = facePic;
    }
}