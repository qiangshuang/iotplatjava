package com.ipincloud.iotbj.utils;


import java.io.*;
import java.util.Base64;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * 文件工具包
 */
public class FileUtils {

 
    public static void unZipFiles(File zipFile,String descDir)throws IOException{
        File pathFile = new File(descDir);
        if(!pathFile.exists()){
          pathFile.mkdirs();
        }
       
        ZipFile zip = new ZipFile(zipFile);
        for( Enumeration entries = zip.entries(); entries.hasMoreElements(); ){
          ZipEntry entry = (ZipEntry)entries.nextElement();
          String zipEntryName = entry.getName();
          
          if(zipEntryName.startsWith("__MACOSX/")){
              continue;
          }
          InputStream in = zip.getInputStream(entry);
          String outPath = (descDir+"/"+zipEntryName).replaceAll("\\*", "/");;
          //判断路径是否存在,不存在则创建文件路径
            
          File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
          if(!file.exists())
          {
            file.mkdirs();
          }
          //判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
          if(new File(outPath).isDirectory())
          {
            continue;
          }
          //输出文件路径信息
          System.out.println(outPath);
          OutputStream out = new FileOutputStream(outPath);
          byte[] buf1 = new byte[1024];
          int len;
          while((len=in.read(buf1))>0){
            out.write(buf1,0,len);
          }
          in.close();
          out.close();
        }
    }

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

    public static String getFullFilePath(String saveFileInfo) {
        String[] tArr = saveFileInfo.split(";");
        if (tArr.length < 1 || "".equals(tArr[1])) {
            return "";
        }
        String runPath = System.getProperty("user.dir") + "/classes/upload";
        String imgFullPath = runPath + tArr[1];
        return imgFullPath;
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