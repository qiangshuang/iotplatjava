package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(SensorTrigger)触发器管理
//generate by redcloud,2020-07-24 19:59:20
public class SensorTrigger implements Serializable {
    private static final long serialVersionUID = 58L;
    // 主键id
    private Long id ;
    // 触发器名称
    private String titel ;
    // 触发器数据流
    @JSONField(name = "stream_id")
    private Long streamId ;
    // 触发条件
    private String cond ;
    // 触发值
    private String dataval ;
    // 推送方式(系统预警，url)
    private String pushway ;
    // 创建时间
    private Long created ;
    // 修改时间
    private Long updated ;
    //  触发器数据流名称
    @JSONField(name = "stream_title")
    private String streamTitle ;

    public Long getId() {
        return id ;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitel() {
        return titel ;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public Long getStreamId() {
        return streamId ;
    }

    public void setStreamId(Long streamId) {
        this.streamId = streamId;
    }

    public String getCond() {
        return cond ;
    }

    public void setCond(String cond) {
        this.cond = cond;
    }

    public String getDataval() {
        return dataval ;
    }

    public void setDataval(String dataval) {
        this.dataval = dataval;
    }

    public String getPushway() {
        return pushway ;
    }

    public void setPushway(String pushway) {
        this.pushway = pushway;
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

    public String getStreamTitle() {
        return streamTitle ;
    }

    public void setStreamTitle(String streamTitle) {
        this.streamTitle = streamTitle;
    }

}

