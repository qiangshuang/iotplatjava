package com.ipincloud.iotbj.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.io.IOException;

import com.alibaba.fastjson.JSONObject;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

/**
 * 参数检测工具包
 */
// @Component

public class ParaUtils {

	public static boolean isNullOrEmpty(Object obj) {
		return null == obj || "".equals(obj);
	}
    public static boolean isNullOrEmptyInt(Object obj) {
        return null == obj || "0".equals(obj.toString() );
    }

    public static String hasAllColValInt(final JSONObject jsonObject, String requiredColumnStr) {
        String[] requiredColumns = requiredColumnStr.split(",");
        if (requiredColumns != null) {
            //验证字段非空
            String missCol = "";
            for (String column : requiredColumns) {
                Object val = jsonObject.get(column.trim());
                if ( isNullOrEmptyInt(val) ) {
                    missCol += column + "  ";
                }
            }
            if (!isNullOrEmpty(missCol)) {
                return missCol;
            }
        }
        return "";
    }
    
	public static String notHaveColVal(final JSONObject jsonObject, String notNeedColumnStr){
		String[] notNeedColumns = notNeedColumnStr.split(",");
		if (notNeedColumns != null) {
			List<String> listCols = Arrays.asList(notNeedColumns);
			
			String notHaveCols = "";

            for(Map.Entry<String,Object> entry : jsonObject.entrySet()){
	            String key = entry.getKey();
	            if (listCols.contains(key) ){
	            	notHaveCols = notHaveCols +" "+ key;
	            }
	        }
            
            if (!isNullOrEmpty(notHaveCols)) {
                return notHaveCols;
            }
        }
        return "";
	}
	public static String hasAllColVal(final JSONObject jsonObject, String requiredColumnStr ) {
        String[] requiredColumns = requiredColumnStr.split(",");
        if (requiredColumns != null) {
            //验证字段非空
            String missCol = "";
            for (String column : requiredColumns) {
                Object val = jsonObject.get(column.trim());
                if (isNullOrEmpty(val)) {
                    missCol += column + "  ";
                }
            }
            if (!isNullOrEmpty(missCol)) {
                return missCol;
            }
        }
        return "";
    }
    public static Integer checkStartIndex(JSONObject jsonObject,int totalRec){
        Integer rop = jsonObject.getInteger("rop");
        Integer cp = jsonObject.getInteger("cp");
        if (rop < 1){
            return 0;
        }
        if (cp < 1) {
            cp = 0;
        }
        int startIndex = rop*(cp-1);
        while (startIndex > totalRec)  {
            cp = cp - 1;
            startIndex = rop * (cp-1);
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        jsonObject.put("totalRec",totalRec);
        jsonObject.put("cp",cp);
        jsonObject.put("startIndex",startIndex);
        return startIndex;
    }
    public static JSONObject copyJsonObjectCols(JSONObject jsonObject,String columnsStr){

        String[] tbColumns = columnsStr.split(",");
        JSONObject retJsonObject = new JSONObject();

        List<String> listCols = Arrays.asList(tbColumns);
            
        for(Map.Entry<String,Object> entry : jsonObject.entrySet()){
            String key = entry.getKey();
            if (listCols.contains(key) ){
                retJsonObject.put(key,entry.getValue());
            }
        }
        
    
        return retJsonObject;
    }
}	


