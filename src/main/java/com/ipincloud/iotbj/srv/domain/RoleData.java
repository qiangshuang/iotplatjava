package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(RoleData)
//generate by redcloud,2020-07-08 01:57:14
public class RoleData implements Serializable {
    private static final long serialVersionUID = 31L;
    // 自增ID
    private Long id ;
    // 角色ID
    @JSONField(name = "role_id")
    private Long roleId ;
    // 数据表
    @JSONField(name = "scope_table")
    private String scopeTable ;
    // 数据权限
    @JSONField(name = "scope_ids")
    private String scopeIds ;
    // 排序
    private Integer sort ;
    // 备注
    private String memo ;
    // 字段配置
    private String cs1 ;
    // 字段配置2
    private String cs2 ;
    // 隐藏列名
    @JSONField(name = "cs1_title")
    private String cs1Title ;
    // 范围显示
    @JSONField(name = "ids_title")
    private String idsTitle ;
    // 操作名称
    @JSONField(name = "op_name")
    private String opName ;
    // 版本锁
    private Integer version ;

    public Long getId() {
        return id ;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId ;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getScopeTable() {
        return scopeTable ;
    }

    public void setScopeTable(String scopeTable) {
        this.scopeTable = scopeTable;
    }

    public String getScopeIds() {
        return scopeIds ;
    }

    public void setScopeIds(String scopeIds) {
        this.scopeIds = scopeIds;
    }

    public Integer getSort() {
        return sort ;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getMemo() {
        return memo ;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCs1() {
        return cs1 ;
    }

    public void setCs1(String cs1) {
        this.cs1 = cs1;
    }

    public String getCs2() {
        return cs2 ;
    }

    public void setCs2(String cs2) {
        this.cs2 = cs2;
    }

    public String getCs1Title() {
        return cs1Title ;
    }

    public void setCs1Title(String cs1Title) {
        this.cs1Title = cs1Title;
    }

    public String getIdsTitle() {
        return idsTitle ;
    }

    public void setIdsTitle(String idsTitle) {
        this.idsTitle = idsTitle;
    }

    public String getOpName() {
        return opName ;
    }

    public void setOpName(String opName) {
        this.opName = opName;
    }

    public Integer getVersion() {
        return version ;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}

