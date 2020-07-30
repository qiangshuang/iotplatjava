package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;

//(Vehicle)车辆白名单
//generate by redcloud,2020-07-23 11:43:17
public class Vehicle implements Serializable {
    private static final long serialVersionUID = 76L;
    // 主键id
    private Long id;
    // 车牌号码
    private String title;
    // 车辆类型
    private String category;
    // 允许通过-开始
    private Long starttime;
    // 允许通过-结束
    private Long endtime;
    // 车主
    private String owner;
    // 身份证号
    private String idnumber;
    // 手机号
    private String mobile;
    // 照片
    private String pic;
    // 创建时间
    private Long created;
    // 修改时间
    private Long updated;
    // 车辆唯一标识
    private String vehicleId;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getStarttime() {
        return starttime;
    }

    public void setStarttime(Long starttime) {
        this.starttime = starttime;
    }

    public Long getEndtime() {
        return endtime;
    }

    public void setEndtime(Long endtime) {
        this.endtime = endtime;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
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

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }
}

