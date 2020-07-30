 package com.ipincloud.iotbj.mqtt;

import java.util.Base64;

public class ConvertSensorVal {

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

	public static byte[] base64DecodeStr(String sensorDataString){
        byte[] retBa = null;
        try{
            
            retBa = Base64.getDecoder().decode(sensorDataString);
               
            for (int i = 0; i < retBa.length; ++i) {
                if (retBa[i] < 0) {// 调整异常数据
                    retBa[i] += 256;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return retBa;
    }

	 /**
     * 数组BE转int
     *
     * @param bytes 数组大端BE
     * @return int
     */
    private static int byteBE2Int(byte[] bytes) {
 
        int addr;
        if(bytes.length==1){
            addr = bytes[0] & 0xFF;
        }else{
            addr = bytes[0] & 0xFF;
            addr = (addr << 8) | (bytes[1] & 0xff) ;
            addr = (addr << 8) | (bytes[2] & 0xff) ;
            addr = (addr << 8) | (bytes[3] & 0xff) ;
        }
        return addr;
    }
	/**
     * 数组BE转float
     *
     * @param bytes 数组大端BE
     * @return float
     */
    public static float byteBE2Float(byte[] bytes) {
        int l;
        l = bytes[0];
        l &= 0xff;
        l |= ((long) bytes[1] << 8);
        l &= 0xffff;
        l |= ((long) bytes[2] << 16);
        l &= 0xffffff;
        l |= ((long) bytes[3] << 24);
        return Float.intBitsToFloat(l);
    }
}