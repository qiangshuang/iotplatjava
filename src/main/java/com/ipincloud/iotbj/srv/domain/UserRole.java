package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(UserRole)
//generate by redcloud,2020-07-07 10:18:15
public class UserRole implements Serializable {
    // 用户ID
    @JSONField(name = "user_id")
    private Long userId ;
    // 角色ID
    @JSONField(name = "role_id")
    private Long roleId ;

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

}

