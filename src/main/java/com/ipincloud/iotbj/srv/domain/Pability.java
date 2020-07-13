package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(Pability)平台能力
//generate by redcloud,2020-07-07 10:18:15
public class Pability implements Serializable {
    // 主键ID
    private Long id ;
    // 能力名称
    private String title ;
    // 三方平台
    @JSONField(name = "third_id")
    private Long thirdId ;
    // 三方名称
    @JSONField(name = "third_title")
    private String thirdTitle ;
    // 能力标识
    private String abcode ;
    // 服务地址
    private String srvurl ;
    // 请求方式
    private String srvstyle ;
    // 请求参数
    private String reqpara ;
    // 实际参数
    private String actpara ;
    // 备注
    private String memo ;
    // 已停用
    private String stop ;

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

    public Long getThirdId() {
        return thirdId ;
    }

    public void setThirdId(Long thirdId) {
        this.thirdId = thirdId;
    }

    public String getThirdTitle() {
        return thirdTitle ;
    }

    public void setThirdTitle(String thirdTitle) {
        this.thirdTitle = thirdTitle;
    }

    public String getAbcode() {
        return abcode ;
    }

    public void setAbcode(String abcode) {
        this.abcode = abcode;
    }

    public String getSrvurl() {
        return srvurl ;
    }

    public void setSrvurl(String srvurl) {
        this.srvurl = srvurl;
    }

    public String getSrvstyle() {
        return srvstyle ;
    }

    public void setSrvstyle(String srvstyle) {
        this.srvstyle = srvstyle;
    }

    public String getReqpara() {
        return reqpara ;
    }

    public void setReqpara(String reqpara) {
        this.reqpara = reqpara;
    }

    public String getActpara() {
        return actpara ;
    }

    public void setActpara(String actpara) {
        this.actpara = actpara;
    }

    public String getMemo() {
        return memo ;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getStop() {
        return stop ;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

}

