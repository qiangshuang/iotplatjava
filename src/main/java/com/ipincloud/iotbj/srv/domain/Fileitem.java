package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(Fileitem)
//generate by redcloud,2020-07-24 19:59:20
public class Fileitem implements Serializable {
    private static final long serialVersionUID = 29L;
    // 自增ID
    private Long id ;
    // 文件名称
    private String title ;
    // 文件路径
    private String url ;
    // 文件类型
    private String filetype ;
    // 用户ID
    @JSONField(name = "user_id")
    private Long userId ;
    // 用户姓名
    @JSONField(name = "user_title")
    private String userTitle ;
    // ID
    @JSONField(name = "relate_id")
    private Long relateId ;
    // 关联类别
    @JSONField(name = "relate_type")
    private String relateType ;
    // 文件路径
    @JSONField(name = "convert_url")
    private String convertUrl ;
    // 备注
    private String memo ;
    // 删除时间
    private Long deleted ;
    // 版本锁
    private Integer version ;
    // created
    private Long created ;

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

    public String getUrl() {
        return url ;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFiletype() {
        return filetype ;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
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

    public Long getRelateId() {
        return relateId ;
    }

    public void setRelateId(Long relateId) {
        this.relateId = relateId;
    }

    public String getRelateType() {
        return relateType ;
    }

    public void setRelateType(String relateType) {
        this.relateType = relateType;
    }

    public String getConvertUrl() {
        return convertUrl ;
    }

    public void setConvertUrl(String convertUrl) {
        this.convertUrl = convertUrl;
    }

    public String getMemo() {
        return memo ;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Long getDeleted() {
        return deleted ;
    }

    public void setDeleted(Long deleted) {
        this.deleted = deleted;
    }

    public Integer getVersion() {
        return version ;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Long getCreated() {
        return created ;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

}

