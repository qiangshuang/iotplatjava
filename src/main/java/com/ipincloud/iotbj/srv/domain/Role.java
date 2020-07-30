package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(Role)角色
//generate by redcloud,2020-07-24 19:59:20
public class Role implements Serializable {
    private static final long serialVersionUID = 46L;
    // 自增ID
    private Long id ;
    // 角色名称
    private String title ;
    // 是否编辑
    @JSONField(name = "is_edit")
    private String isEdit ;
    // 排序
    private Integer sort ;
    // 角色描述
    private String memo ;
    // 版本锁
    private Integer version ;
    // 添加时间
    private Long addtime ;
    // 是否启用
    private String enable ;

    public Long getId() {
        return id ;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title ;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsEdit() {
        return isEdit ;
    }

    public void setIsEdit(String isEdit) {
        this.isEdit = isEdit;
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

    public Integer getVersion() {
        return version ;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Long getAddtime() {
        return addtime ;
    }

    public void setAddtime(Long addtime) {
        this.addtime = addtime;
    }

    public String getEnable() {
        return enable ;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

}

