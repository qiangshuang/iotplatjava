package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(RoleBtn)
//generate by redcloud,2020-07-07 10:18:15
public class RoleBtn implements Serializable {
    // 角色ID
    @JSONField(name = "role_id")
    private Long roleId ;
    // 功能ID
    @JSONField(name = "btn_id")
    private Long btnId ;

    public Long getRoleId() {
        return roleId ;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getBtnId() {
        return btnId ;
    }

    public void setBtnId(Long btnId) {
        this.btnId = btnId;
    }

}

