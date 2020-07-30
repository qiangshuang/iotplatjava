package com.ipincloud.iotbj.api.utils.hik;

public class BaseResponse<T> {
    public static final String ERR_NO_DATA = "海康安防管理平台返回空数据";
    public String code;
    public String msg;
    public T data;
}