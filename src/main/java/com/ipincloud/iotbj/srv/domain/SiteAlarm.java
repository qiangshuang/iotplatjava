package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(SiteAlarm)人员定位-预警记录
//generate by redcloud,2020-07-24 19:59:20
public class SiteAlarm implements Serializable {
    private static final long serialVersionUID = 60L;
    // 主键id
    private Long id ;
    // 人员
    private String person ;
    // 卡号
    private String card ;
    // 类型
    private String category ;
    // 预警等级
    private String grade ;
    // 位置信息
    private String site ;
    // 预警时间
    private Long alarmtiem ;
    // 状态（未确认，已确认）
    private String state ;
    // 预警图片
    private String alarmpic ;
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

    public String getPerson() {
        return person ;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getCard() {
        return card ;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getCategory() {
        return category ;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGrade() {
        return grade ;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSite() {
        return site ;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Long getAlarmtiem() {
        return alarmtiem ;
    }

    public void setAlarmtiem(Long alarmtiem) {
        this.alarmtiem = alarmtiem;
    }

    public String getState() {
        return state ;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAlarmpic() {
        return alarmpic ;
    }

    public void setAlarmpic(String alarmpic) {
        this.alarmpic = alarmpic;
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

