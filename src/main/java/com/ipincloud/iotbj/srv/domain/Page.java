package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(Page)
//generate by redcloud,2020-07-07 23:53:41
public class Page implements Serializable {
    private static final long serialVersionUID = 23L;
    // 自增ID
    private Long id ;
    // 网页名称
    private String name ;
    // 服务图标
    private String icon ;
    // 网页标题
    private String title ;
    // 父级ID
    @JSONField(name = "parent_id")
    private Long parentId ;
    // 父级标题
    @JSONField(name = "parent_title")
    private String parentTitle ;
    // 网页路径
    private String path ;
    // 菜单标志
    @JSONField(name = "is_menu")
    private String isMenu ;
    // 路由标志
    @JSONField(name = "client_route")
    private String clientRoute ;
    // 缓存标志
    @JSONField(name = "is_cache")
    private String isCache ;
    // 登录访问
    @JSONField(name = "need_login")
    private String needLogin ;
    // 登录访问
    private String encode ;
    // 排序号
    private Integer sort ;
    // 版本锁
    private Integer version ;

    public Long getId() {
        return id ;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name ;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon ;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title ;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getPath() {
        return path ;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getIsMenu() {
        return isMenu ;
    }

    public void setIsMenu(String isMenu) {
        this.isMenu = isMenu;
    }

    public String getClientRoute() {
        return clientRoute ;
    }

    public void setClientRoute(String clientRoute) {
        this.clientRoute = clientRoute;
    }

    public String getIsCache() {
        return isCache ;
    }

    public void setIsCache(String isCache) {
        this.isCache = isCache;
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

    public Integer getSort() {
        return sort ;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getVersion() {
        return version ;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}

