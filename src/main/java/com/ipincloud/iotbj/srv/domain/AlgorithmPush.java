package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(AlgorithmPush)算法推送人员
//generate by redcloud,2020-07-24 19:59:20
public class AlgorithmPush implements Serializable {
    private static final long serialVersionUID = 7L;
    // 主键id
    private Long id ;
    // 算法id
    @JSONField(name = "algorithm_id")
    private Long algorithmId ;
    // 人员id
    @JSONField(name = "person_id")
    private Long personId ;
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

    public Long getAlgorithmId() {
        return algorithmId ;
    }

    public void setAlgorithmId(Long algorithmId) {
        this.algorithmId = algorithmId;
    }

    public Long getPersonId() {
        return personId ;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
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

