package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(SensorSensorTrigger)触发器关联传感器
//generate by redcloud,2020-07-24 19:59:20
public class SensorSensorTrigger implements Serializable {
    private static final long serialVersionUID = 56L;
    // 触发器名称
    @JSONField(name = "trigger_id")
    private Long triggerId ;
    // 传感器名称
    @JSONField(name = "sensor_id")
    private Long sensorId ;

    public Long getTriggerId() {
        return triggerId ;
    }

    public void setTriggerId(Long triggerId) {
        this.triggerId = triggerId;
    }

    public Long getSensorId() {
        return sensorId ;
    }

    public void setSensorId(Long sensorId) {
        this.sensorId = sensorId;
    }

}

