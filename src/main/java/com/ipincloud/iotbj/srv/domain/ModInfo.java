package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(ModInfo)模型表
//generate by redcloud,2020-07-24 19:59:20
public class ModInfo implements Serializable {
    private static final long serialVersionUID = 35L;
    // 模型ID
    @JSONField(name = "mod_id")
    private String modId ;
    // 模型名称
    @JSONField(name = "mod_name")
    private String modName ;
    // 模型描述
    @JSONField(name = "mod_desc")
    private String modDesc ;
    // 模型基础标识
    @JSONField(name = "mod_index")
    private String modIndex ;
    // 基础标识数据模型
    @JSONField(name = "mod_index_type")
    private String modIndexType ;
    // 基础标识长度
    @JSONField(name = "mod_index_len")
    private BigDecimal modIndexLen ;
    // 模型密钥
    @JSONField(name = "mod_key")
    private String modKey ;
    // 开发者用户名
    @JSONField(name = "user_name")
    private String userName ;

    public String getModId() {
        return modId ;
    }

    public void setModId(String modId) {
        this.modId = modId;
    }

    public String getModName() {
        return modName ;
    }

    public void setModName(String modName) {
        this.modName = modName;
    }

    public String getModDesc() {
        return modDesc ;
    }

    public void setModDesc(String modDesc) {
        this.modDesc = modDesc;
    }

    public String getModIndex() {
        return modIndex ;
    }

    public void setModIndex(String modIndex) {
        this.modIndex = modIndex;
    }

    public String getModIndexType() {
        return modIndexType ;
    }

    public void setModIndexType(String modIndexType) {
        this.modIndexType = modIndexType;
    }

    public BigDecimal getModIndexLen() {
        return modIndexLen ;
    }

    public void setModIndexLen(BigDecimal modIndexLen) {
        this.modIndexLen = modIndexLen;
    }

    public String getModKey() {
        return modKey ;
    }

    public void setModKey(String modKey) {
        this.modKey = modKey;
    }

    public String getUserName() {
        return userName ;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}

