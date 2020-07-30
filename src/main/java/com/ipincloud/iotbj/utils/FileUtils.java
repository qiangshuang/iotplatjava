package com.ipincloud.iotbj.utils;


import java.io.*;
import java.util.Base64;

/**
 * 文件工具包
 */
public class FileUtils {
    public static String encodeBase64File(String path) throws Exception {
        File file = new File(path);
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return Base64.getEncoder().encodeToString(buffer);
    }

    public static String readImgBase64Code(String imgUrl) {

        if (imgUrl == null || "".equals(imgUrl)) {
            return "";
        }

        String[] tArr = imgUrl.split(";");
        if (tArr.length < 1 || "".equals(tArr[1])) {
            return "";
        }
        String runPath = System.getProperty("user.dir") + "/classes/upload";
        String imgFullPath = runPath + tArr[1];

        try {
            return encodeBase64File(imgFullPath);
        } catch (Exception ex) {
            return "";
        }
    }

    public static String getRealFilePath(String saveFileInfo) {
        String[] tArr = saveFileInfo.split(";");
        if (tArr.length < 1 || "".equals(tArr[1])) {
            return "";
        }
        return tArr[1];
    }


    public static String readFileStr(String priKeyPath) {
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            File file = new File(priKeyPath);

            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
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

    public static String inputStreamToString(InputStream is) {
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(is));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                if (tempStr.charAt(0) != '-') {
                    sbf.append(tempStr + "\r");
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

    public static String readKeyFile(String priKeyPath) {
        //文件名
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            File file = new File(priKeyPath);

            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                if (tempStr.charAt(0) != '-') {
                    sbf.append(tempStr + "\r");
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

    public static String getRootPath() {
        String tPath = FileUtils.class.getClassLoader().getResource("").getPath();
        File tFile = new File(tPath);
        String retPath = tFile.getAbsolutePath();
        if (retPath.endsWith("/")) {
            return retPath.substring(0, retPath.length() - 1);
        }
        return retPath;
    }
}