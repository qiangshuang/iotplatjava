package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(Pusher)推送人员
//generate by redcloud,2020-07-07 23:53:41
public class Pusher implements Serializable {
    private static final long serialVersionUID = 27L;
    // 主键id
    private Long id ;
    // 算法ID
    @JSONField(name = "algorithm_id")
    private Long algorithmId ;
    // 用户ID
    @JSONField(name = "user_id")
    private Long userId ;
    // 用户姓名
    @JSONField(name = "user_title")
    private String userTitle ;
    // 部门ID
    @JSONField(name = "parent_id")
    private Long parentId ;
    // 部门名称
    @JSONField(name = "parent_title")
    private String parentTitle ;
    // 备注
    private String memo ;

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

    public Long getUserId() {
        return userId ;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserTitle() {
        return userTitle ;
    }

    public void setUserTitle(String userTitle) {
        this.userTitle = userTitle;
    }

    public Long getParentId() {
        return parentId ;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentTitle() {
        return parentTitle ;
    }

    public void setParentTitle(String parentTitle) {
        this.parentTitle = parentTitle;
    }

    public String getMemo() {
        return memo ;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

}

