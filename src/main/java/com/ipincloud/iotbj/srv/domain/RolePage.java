package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(RolePage)
//generate by redcloud,2020-07-07 23:53:41
public class RolePage implements Serializable {
    private static final long serialVersionUID = 32L;
    // 角色ID
    @JSONField(name = "role_id")
    private Long roleId ;
    // 页面ID
    @JSONField(name = "page_id")
    private Long pageId ;

    public Long getRoleId() {
        return roleId ;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPageId() {
        return pageId ;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

}

