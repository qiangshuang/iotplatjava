package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(FaceCard)卡片信息
//generate by redcloud,2020-07-24 19:59:20
public class FaceCard implements Serializable {
    private static final long serialVersionUID = 23L;
    // 主键id
    private Long id ;
    // 卡号
    private String cardno ;
    // 类型
    private String category ;
    // 创建时间
    private Long created ;
    // 修改时间
    private Long updated ;
    // 人员id
    @JSONField(name = "person_id")
    private Long personId ;
    // 人员姓名
    @JSONField(name = "person_title")
    private String personTitle ;

    public Long getId() {
        return id ;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardno() {
        return cardno ;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getCategory() {
        return category ;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public Long getPersonId() {
        return personId ;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getPersonTitle() {
        return personTitle ;
    }

    public void setPersonTitle(String personTitle) {
        this.personTitle = personTitle;
    }

}

