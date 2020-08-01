package com.ipincloud.iotbj.sys.domain;

import java.io.Serializable;
import com.alibaba.fastjson.annotation.JSONField;
// import java.util.HashMap;

public class ResponseOpenApi implements Serializable{
	/**
	 * http状态码
	 */
	private int code;
	private String status;

	/**
	 * 返回信息
	 */
	private String msg;
	/**
	 * 返回数据
	 */
	private Object data;

	public int getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
	public ResponseOpenApi() {
		
	}
	
	public ResponseOpenApi(Integer code,String status, String msg, Object data) {
		this.code = code;
		this.status = status;
		
		this.msg = msg;
		this.data = data;
	}
}
