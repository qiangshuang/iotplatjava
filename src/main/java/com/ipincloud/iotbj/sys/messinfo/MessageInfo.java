package com.ipincloud.iotbj.sys.messinfo;

public class MessageInfo {
    public static final String MsgSuccess = "操作已成功";
    public static final String MsgParamsError = "获取的参数不正确!";
    public static final String MsgProgramError = "程序运行不正确!";

    public static String exceptionInfo(Exception e){
        if (e.getMessage() != null)
            return MsgProgramError + ":" + e.getMessage();
        else
            return MsgProgramError + ":" + e.toString();
    }
}