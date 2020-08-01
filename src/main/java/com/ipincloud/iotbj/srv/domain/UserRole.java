package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(UserRole)
//generate by redcloud,2020-07-24 19:59:20
public class UserRole implements Serializable {
    private static final long serialVersionUID = 75L;
    // 用户ID
    @JSONField(name = "user_id")
    private Long userId ;
    // 角色ID
    @JSONField(name = "role_id")
    private Long roleId ;

    private String thirdUUID;
    
    public Long getUserId() {
        return userId ;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId ;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public void getThirdUUID(){
        return thirdUUID;
    }
    public void setThirdUUID(String thirdUUId){
        this.thirdUUID = thirdUUID;
    }

}

