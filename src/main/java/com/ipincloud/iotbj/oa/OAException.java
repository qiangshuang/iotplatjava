package com.ipincloud.iotbj.oa;

public class OAException extends RuntimeException {
    public String code;
    public String msg;

    public OAException(String code, String msg) {
        super("code:" + code + " msg:" + msg);
        this.code = code;
        this.msg = msg;
    }

    public OAException(String msg) {
        super(msg);
    }

    public OAException(Throwable throwable) {
        super(throwable);
    }
}
