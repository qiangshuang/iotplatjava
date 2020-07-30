 package com.ipincloud.iotbj.mqtt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSON;
import java.util.Base64;

public class TriggerCond {
    private static Logger log = LoggerFactory.getLogger(TriggerCond.class);
	
    public static boolean isTrigger(String sensorValStr,String cond,String condVal,JSONObject valLast){
        
        switch(cond){
            case ">":{
                if (sensorValStr == null || "".equals(sensorValStr)){
                    return false;
                }
                Float sensorVal = Float.parseFloat(sensorValStr);
                return CompGreat(sensorVal,condVal);
            }
            case ">=":{
                if (sensorValStr == null || "".equals(sensorValStr)){
                    return false;
                }
                Float sensorVal = Float.parseFloat(sensorValStr);
                return CompGreatEqual(sensorVal,condVal);
            }
            case "<":{
                if (sensorValStr == null || "".equals(sensorValStr)){
                    return false;
                }
                Float sensorVal = Float.parseFloat(sensorValStr);
                return CompLess(sensorVal,condVal);
            }
            case "<=":{
                if (sensorValStr == null || "".equals(sensorValStr)){
                    return false;
                }
                Float sensorVal = Float.parseFloat(sensorValStr);
                return CompLessEqual(sensorVal,condVal);
            }
            case "==":{
                if (sensorValStr == null || "".equals(sensorValStr)){
                    return false;
                }
                Float sensorVal = Float.parseFloat(sensorValStr);
                return CompEqual(sensorVal,condVal);
            }
            case "inout":{
                if (sensorValStr == null || "".equals(sensorValStr)){
                    return false;
                }
                Float sensorVal = Float.parseFloat(sensorValStr);
                return CompInOut(sensorVal,condVal,valLast);
            }
            case "change":{
                if (sensorValStr == null || "".equals(sensorValStr)){
                    return false;
                }
                Float sensorVal = Float.parseFloat(sensorValStr);
                return CompChange(sensorVal,condVal,valLast);
            }
            case "frozen":{
                return CompFrozen(condVal,valLast);
            }
            case "live":{
                return CompLive(condVal,valLast);
            }

            default:{
                log.info("未处理的条件类型:"+ cond.toString());
            }
        }

        return false;
    }

    public static boolean CompGreat(float sensorVal,String condVal){
        Float floatCond = Float.parseFloat(condVal);
        if(sensorVal > floatCond){
            return true;
        }
        return false;
    }
    public static boolean CompGreatEqual(float sensorVal,String condVal){
        Float floatCond = Float.parseFloat(condVal);
        if(sensorVal >= floatCond){
            return true;
        }
        return false;
    }
    public static boolean CompLess(float sensorVal,String condVal){
        Float floatCond = Float.parseFloat(condVal);
        if(sensorVal < floatCond){
            return true;
        }
        return false;
    }
    public static boolean CompLessEqual(float sensorVal,String condVal){
        Float floatCond = Float.parseFloat(condVal);
        if(sensorVal <= floatCond){
            return true;
        }
        return false;
    }
    public static boolean CompEqual(float sensorVal,String condVal){
        Float floatCond = Float.parseFloat(condVal);
        if(sensorVal == floatCond){
            return true;
        }
        return false;
    }
    public static boolean CompInOut(float sensorVal,String condVal,JSONObject valLast){
        String[] condValArr = condVal.split(",");
        if (condValArr == null || condValArr.length < 2){
            log.info("InOut比较区间未设置正确.");
            return false;
        }
        Float floatCondStart = Float.parseFloat(condValArr[0]);
        Float floatCondEnd = Float.parseFloat(condValArr[1]);
        Float floatLast = 0.0f;
        if(valLast != null ){
            String datavalLast  = valLast.getString("dataval");
            floatLast = Float.parseFloat(datavalLast);
        }
        
        if (floatLast  == 0){
            return true;
        } 
        if (floatLast >=floatCondStart && floatCondStart<= floatCondEnd){
            if(sensorVal >=floatCondStart && sensorVal<= floatCondEnd){
                return false;
            }else{
                return true;
            }
        }else{
            if(sensorVal >=floatCondStart && sensorVal<= floatCondEnd){
                return true;
            }else{
                return false;
            }
        }
        // return false;
    }

    public static boolean CompChange(float sensorVal,String condVal,JSONObject valLast){
        
        Float floatLast = 0.0f;
        if(valLast != null ){
            String datavalLast  = valLast.getString("dataval");
            if(datavalLast == null || "".equals(datavalLast)){
                return false;
            }
            floatLast = Float.parseFloat(datavalLast);
        }else{
            return false;
        }
        

        if (floatLast  != sensorVal){
            return true;
        } 
        
        return false;
    }

    public static boolean CompFrozen(String condVal,JSONObject valLast){
        
        if (valLast == null) {
            return false;
        }
        Long sendTime = valLast.getLong("sendtime");
        Float floatCond = Float.parseFloat(condVal);

        Long curMs = System.currentTimeMillis();
        if (curMs - sendTime > floatCond*1000 ){
            return true;
        }
        return false;
    }
    public static boolean CompLive(String condVal,JSONObject valLast){
        
        if (valLast == null) {
            return false;
        }
        Long sendTime = valLast.getLong("sendtime");

        Float floatCond = Float.parseFloat(condVal);

        Long curMs = System.currentTimeMillis();
        if (curMs - sendTime > floatCond*1000 ){
            return true;
        }
        return false;
    }
}