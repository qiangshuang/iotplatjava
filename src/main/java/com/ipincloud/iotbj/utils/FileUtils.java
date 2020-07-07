package com.ipincloud.iotbj.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.io.IOException;

/**
 * 文件工具包
 */
// @Component

public class FileUtils {
	public static String readFileStr(String priKeyPath){
		BufferedReader reader = null;
	    StringBuffer sbf = new StringBuffer();
	    try {
	    	File file = new File(priKeyPath);
	    	
	        reader = new BufferedReader(new FileReader(file));
	        String tempStr;
	        while ((tempStr = reader.readLine()) != null) {
	        	sbf.append(tempStr) ;
	        }
	        reader.close();
	        return sbf.toString();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if (reader != null) {
	            try {
	                reader.close();
	            } catch (IOException e1) {
	                e1.printStackTrace();
	            }
	        }
	    }
	    return sbf.toString();
	}

	public static String readKeyFile(String priKeyPath){
		BufferedReader reader = null;
	    StringBuffer sbf = new StringBuffer();
	    try {
	    	File file = new File(priKeyPath);
	    	
	        reader = new BufferedReader(new FileReader(file));
	        String tempStr;
	        while ((tempStr = reader.readLine()) != null) {
	        	if( tempStr.charAt(0) != '-'){
	        		sbf.append(tempStr+"\r") ;
	        	}
	            
	        }
	        reader.close();
	        return sbf.toString();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if (reader != null) {
	            try {
	                reader.close();
	            } catch (IOException e1) {
	                e1.printStackTrace();
	            }
	        }
	    }
	    return sbf.toString();
	}
}