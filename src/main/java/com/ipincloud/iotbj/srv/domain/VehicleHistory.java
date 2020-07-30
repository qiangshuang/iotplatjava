package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(VehicleHistory)车辆进出记录
//generate by redcloud,2020-07-24 19:59:20
public class VehicleHistory implements Serializable {
    private static final long serialVersionUID = 78L;
    // 主键id
    private Long id ;
    // 车辆id
    @JSONField(name = "vehicle_id")
    private Long vehicleId ;
    // 入口
    @JSONField(name = "in_gate_id")
    private Long inGateId ;
    // 访问时间
    private Long intime ;
    // 进入照片
    private String inpic ;
    // 出口
    @JSONField(name = "out_gate_id")
    private Long outGateId ;
    // 离开时间
    private Long outtime ;
    // 离开照片
    private String outpic ;
    // 状态（允许通过，禁止通过）
    private String state ;
    // 创建时间
    private Long created ;
    // 修改时间
    private Long updated ;
    // 车牌号
    @JSONField(name = "vehicle_title")
    private String vehicleTitle ;
    // 车辆类型
    @JSONField(name = "vahicle_category")
    private String vahicleCategory ;

    public Long getId() {
        return id ;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVehicleId() {
        return vehicleId ;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getInGateId() {
        return inGateId ;
    }

    public void setInGateId(Long inGateId) {
        this.inGateId = inGateId;
    }

    public Long getIntime() {
        return intime ;
    }

    public void setIntime(Long intime) {
        this.intime = intime;
    }

    public String getInpic() {
        return inpic ;
    }

    public void setInpic(String inpic) {
        this.inpic = inpic;
    }

    public Long getOutGateId() {
        return outGateId ;
    }

    public void setOutGateId(Long outGateId) {
        this.outGateId = outGateId;
    }

    public Long getOuttime() {
        return outtime ;
    }

    public void setOuttime(Long outtime) {
        this.outtime = outtime;
    }

    public String getOutpic() {
        return outpic ;
    }

    public void setOutpic(String outpic) {
        this.outpic = outpic;
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

    public String getVehicleTitle() {
        return vehicleTitle ;
    }

    public void setVehicleTitle(String vehicleTitle) {
        this.vehicleTitle = vehicleTitle;
    }

    public String getVahicleCategory() {
        return vahicleCategory ;
    }

    public void setVahicleCategory(String vahicleCategory) {
        this.vahicleCategory = vahicleCategory;
    }

}

