package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(Btn)
//generate by redcloud,2020-07-08 01:57:13
public class Btn implements Serializable {
    private static final long serialVersionUID = 9L;
    // 自增ID
    private Long id ;
    // 功能名称
    private String title ;
    // 页面ID
    @JSONField(name = "page_id")
    private Long pageId ;
    // 页面标题
    @JSONField(name = "page_title")
    private String pageTitle ;
    // 服务路径
    private String path ;
    // 排序号
    private Integer sort ;
    // 备注
    private String memo ;
    // 页面路径
    @JSONField(name = "page_path")
    private String pagePath ;
    // 功能类型
    private String srvt ;
    // 数据表名
    private String tbns ;
    // 需要登录
    @JSONField(name = "need_login")
    private String needLogin ;
    // 调用编码
    private String encode ;
    // 功能权限
    private String rbac ;
    // 数据权限
    @JSONField(name = "data_range")
    private String dataRange ;
    // 版本锁
    private Integer version ;

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

    public Long getPageId() {
        return pageId ;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    public String getPageTitle() {
        return pageTitle ;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getPath() {
        return path ;
    }

    public void setPath(String path) {
        this.path = path;
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

    public String getPagePath() {
        return pagePath ;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    public String getSrvt() {
        return srvt ;
    }

    public void setSrvt(String srvt) {
        this.srvt = srvt;
    }

    public String getTbns() {
        return tbns ;
    }

    public void setTbns(String tbns) {
        this.tbns = tbns;
    }

    public String getNeedLogin() {
        return needLogin ;
    }

    public void setNeedLogin(String needLogin) {
        this.needLogin = needLogin;
    }

    public String getEncode() {
        return encode ;
    }

    public void setEncode(String encode) {
        this.encode = encode;
    }

    public String getRbac() {
        return rbac ;
    }

    public void setRbac(String rbac) {
        this.rbac = rbac;
    }

    public String getDataRange() {
        return dataRange ;
    }

    public void setDataRange(String dataRange) {
        this.dataRange = dataRange;
    }

    public Integer getVersion() {
        return version ;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}

