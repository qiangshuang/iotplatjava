package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(Dability)设备能力
//generate by redcloud,2020-07-24 19:59:20
public class Dability implements Serializable {
    private static final long serialVersionUID = 18L;
    // 主键ID
    private Long id ;
    // 厂商
    private String corp ;
    // 协议名称
    private String protocolname ;
    // 发送格式
    private String sendformat ;
    // 设备名称
    private String title ;
    // 头参数
    private String header ;
    // 内容参数
    private String body ;
    // 实际参数
    private String actheader ;
    // 实际参数
    private String actbody ;
    // 动作名称
    private String actname ;
    // 备注
    private String memo ;

    public Long getId() {
        return id ;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCorp() {
        return corp ;
    }

    public void setCorp(String corp) {
        this.corp = corp;
    }

    public String getProtocolname() {
        return protocolname ;
    }

    public void setProtocolname(String protocolname) {
        this.protocolname = protocolname;
    }

    public String getSendformat() {
        return sendformat ;
    }

    public void setSendformat(String sendformat) {
        this.sendformat = sendformat;
    }

    public String getTitle() {
        return title ;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHeader() {
        return header ;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body ;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getActheader() {
        return actheader ;
    }

    public void setActheader(String actheader) {
        this.actheader = actheader;
    }

    public String getActbody() {
        return actbody ;
    }

    public void setActbody(String actbody) {
        this.actbody = actbody;
    }

    public String getActname() {
        return actname ;
    }

    public void setActname(String actname) {
        this.actname = actname;
    }

    public String getMemo() {
        return memo ;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

}

