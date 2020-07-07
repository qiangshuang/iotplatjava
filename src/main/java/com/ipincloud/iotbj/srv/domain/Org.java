package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(Org)
//generate by redcloud,2020-07-08 01:57:14
public class Org implements Serializable {
    private static final long serialVersionUID = 21L;
    // 自增ID
    private Long id ;
    // 机构类型
    private String type ;
    // 父级ID
    @JSONField(name = "parent_id")
    private Long parentId ;
    // 排序
    private Integer sort ;
    // 数据权限
    @JSONField(name = "data_scope")
    private String dataScope ;
    // 停用
    private String stop ;
    // 备注
    private String memo ;
    // 上级机构
    @JSONField(name = "parent_title")
    private String parentTitle ;
    // 部门名称
    private String title ;

    public Long getId() {
        return id ;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type ;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getParentId() {
        return parentId ;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getSort() {
        return sort ;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getDataScope() {
        return dataScope ;
    }

    public void setDataScope(String dataScope) {
        this.dataScope = dataScope;
    }

    public String getStop() {
        return stop ;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

    public String getMemo() {
        return memo ;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getParentTitle() {
        return parentTitle ;
    }

    public void setParentTitle(String parentTitle) {
        this.parentTitle = parentTitle;
    }

    public String getTitle() {
        return title ;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}

