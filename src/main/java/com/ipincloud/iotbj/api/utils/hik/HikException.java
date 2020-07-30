package com.ipincloud.iotbj.api.utils.hik;

public class HikException extends RuntimeException {
    public String code;
    public String msg;

    public HikException(String code, String msg) {
        super("code:" + code + " msg:" + msg);
        this.code = code;
        this.msg = msg;
    }

    public HikException(String msg) {
        super(msg);
    }
}