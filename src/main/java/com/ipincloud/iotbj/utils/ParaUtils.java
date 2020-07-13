package com.ipincloud.iotbj.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.io.IOException;

import com.alibaba.fastjson.JSONObject;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

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
    public static String hasAllColValInt(finally JSONObject,String[] requiredColumns){
        if (requiredColumns != null) {
            //验证字段非空
            String missCol = "";
            for (String column : requiredColumns) {
                Object val = jsonObject.get(column.trim());
                if (isNullOrEmptyInt(val)) {
                    missCol += column + "  ";
                }
            }
            if (!isNullOrEmpty(missCol)) {
                return missCol;
            }
        }
        return ""
    }
    
	public static String notHaveColVal(final JSONObject jsonObject, String[] notRequiredColumns){
		
		if (requiredColumns != null) {
			List<String> listCols = Arrays.asList(notRequiredColumns);
			
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
        return ""
	}
	public static String hasAllColVal(final JSONObject jsonObject, String[] requiredColumns) {
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
        return ""
    }
}	