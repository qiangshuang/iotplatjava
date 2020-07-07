package com.ipincloud.iotbj.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import com.alibaba.fastjson.annotation.JSONField;

//(CronTask)
//generate by redcloud,2020-07-07 23:53:41
public class CronTask implements Serializable {
    private static final long serialVersionUID = 12L;
    // 任务ID
    private Long id ;
    // 任务名称
    private String name ;
    // crontab
    private String spec ;
    // 功能编码
    @JSONField(name = "pack_struct_name")
    private String packStructName ;
    // 其他参数
    private String para ;
    // 备注
    private String remark ;
    // 状态 1:正常 0:停止
    private Integer stop ;

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

    public String getSpec() {
        return spec ;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getPackStructName() {
        return packStructName ;
    }

    public void setPackStructName(String packStructName) {
        this.packStructName = packStructName;
    }

    public String getPara() {
        return para ;
    }

    public void setPara(String para) {
        this.para = para;
    }

    public String getRemark() {
        return remark ;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStop() {
        return stop ;
    }

    public void setStop(Integer stop) {
        this.stop = stop;
    }

}

