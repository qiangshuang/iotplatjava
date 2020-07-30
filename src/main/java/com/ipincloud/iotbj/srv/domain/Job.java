package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(Job)
//generate by redcloud,2020-07-24 19:59:20
public class Job implements Serializable {
    private static final long serialVersionUID = 32L;
    // 岗位名称
    private String title ;
    // 排序号
    private Integer sort ;
    // 备注
    private String memo ;
    // 自增ID
    private Long id ;

    public String getTitle() {
        return title ;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Long getId() {
        return id ;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

