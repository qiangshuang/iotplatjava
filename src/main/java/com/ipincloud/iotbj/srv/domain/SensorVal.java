 package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(SensorVal)数据流
//generate by redcloud,2020-07-24 19:59:20
public class SensorVal implements Serializable {
    private static final long serialVersionUID = 57L;
    // 自增ID
    private Long id ;
    // 数据流名称
    @JSONField(name = "dataflow_title")
    private String dataflowTitle ;
    // 数据流值
    private String dataval ;
    // 单位名称
    private String unitname ;
    // 单位符号
    private String unitcode ;

    @JSONField(name = "dataflow_id")
    private Long dataflowId;

    private Long deviceId ;
    
    @JSONField(name = "device_name")
    private String deviceName  ;

    private Long sendtime;

    public Long getId() {
        return id ;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDataflowId() {
        return dataflowId ;
    }

    public void setDataflowId(Long id) {
        this.dataflowId = id;
    }
    public Long getDeviceId() {
        return deviceId ;
    }

    public void setDeviceId(Long id) {
        this.deviceId = id;
    }

    public String getDataflowTitle() {
        return dataflowTitle ;
    }

    public void setDataFlowTitle(String title) {
        this.dataflowTitle = title;
    }

    public String getDataval() {
        return dataval ;
    }

    public void setDataval(String dataval) {
        this.dataval = dataval;
    }

    public String getUnitname() {
        return unitname ;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }

    public String getUnitcode() {
        return unitcode ;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }

    public String getDeviceName() {
        return deviceName ;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Long getSendtime() {
        return sendtime ;
    }

    public void setSendtime(Long sendtime) {
        this.sendtime = sendtime;
    }
}

