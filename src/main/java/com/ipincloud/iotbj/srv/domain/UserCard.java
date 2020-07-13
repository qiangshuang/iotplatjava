package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(UserCard)用户卡片
//generate by redcloud,2020-07-07 10:18:15
public class UserCard implements Serializable {
    // 自增ID
    private Long id ;
    // 卡号
    private String cardno ;
    // 姓名
    @JSONField(name = "user_name")
    private String userName ;
    // 卡片状态
    private String state ;
    // 用户id
    @JSONField(name = "user_id")
    private Long userId ;

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

    public String getUserName() {
        return userName ;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getState() {
        return state ;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getUserId() {
        return userId ;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}

