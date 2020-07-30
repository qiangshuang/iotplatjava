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
import java.util.ArrayList;

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
    public static JSONObject removeSurplusCol(JSONObject jsonObject,String requiredColumnStr){
        String[] tbColumns = requiredColumnStr.split(",");
        List<String> listTbColumns = Arrays.asList(tbColumns);
        List<String> listCols = new ArrayList();
            
        for(Map.Entry<String,Object> entry : jsonObject.entrySet()){
            String key = entry.getKey();
            if (!listTbColumns.contains(key) ){ 
                listCols.add(key);
            }
        }
        
        for(String surplusCol: listCols){
            jsonObject.remove(surplusCol);
        }
        
        return jsonObject;    
    }
    public static JSONObject checkStartIndex(JSONObject jsonObject,int totalRec){
        Integer rop = 0;
        String notHavekey = notHaveColVal(jsonObject,"rop");
        if (notHavekey != null && notHavekey.length() > 0){
            rop = jsonObject.getInteger("rop");
        }
        if (rop < 1) {
            rop = 1000;
        }
        System.out.println("rop:"+rop.toString() );
        notHavekey = notHaveColVal(jsonObject,"cp");
        Integer cp = 0;
        if (notHavekey != null && notHavekey.length() > 0){
            cp = jsonObject.getInteger("cp"); 
        }
    
        if (cp < 1) {
            cp = 1;
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
        jsonObject.put("rop",rop);
        jsonObject.put("startIndex",startIndex);
        return jsonObject;
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


