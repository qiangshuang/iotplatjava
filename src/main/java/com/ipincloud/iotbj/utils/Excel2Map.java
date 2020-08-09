 package com.ipincloud.iotbj.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

//import java.io.FileNotFoundException;
//import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.io.*;

public class Excel2Map {

    protected static final Logger logger = LoggerFactory.getLogger(Excel2Map.class);

    public static Map<Integer, Map<Integer, Object>> readExcelContentz(String excelFilePath) {

        try{
            Map<Integer, Map<Integer, Object>> content = new HashMap<Integer, Map<Integer, Object>>();
            // 上传文件名
            String ext = excelFilePath.substring(excelFilePath.lastIndexOf("."));

            InputStream is = new FileInputStream(excelFilePath);
            Workbook wb = null;

            if (".xls".equals(ext)) {
                wb = new HSSFWorkbook(is);
            } else if (".xlsx".equals(ext)) {
                wb = new XSSFWorkbook(is);
            } else {
                wb = null;
            }

            if (wb == null) {
                return content;
            }
            Sheet sheet = wb.getSheetAt(0);
            // 得到总行数
            int rowNum = sheet.getLastRowNum();
            Row row = sheet.getRow(0);
            int colNum = row.getPhysicalNumberOfCells();
            // 正文内容应该从第二行开始,第一行为表头的标题
            for (int i = 1; i <= rowNum; i++) {
                row = sheet.getRow(i);
                int j = 0;
                Map<Integer, Object> cellValue = new HashMap<Integer, Object>();
                while (j < colNum) {
                    Object obj = getCellFormatValue(row.getCell(j));
                    cellValue.put(j, obj);
                    j++;
                }
                content.put(i, cellValue);

            }
            is.close();
            return content;
        }catch (Exception e) {
            return null;
        }
        
    }

    //根据Cell类型设置数据
    private static Object getCellFormatValue(Cell cell) {
        Object cellvalue = "";
        if (cell != null) {
            switch (cell.getCellTypeEnum()) {
                case NUMERIC:
                    cellvalue = String.valueOf(cell.getNumericCellValue());
                    break;
                case FORMULA: {
                    cellvalue = cell.getDateCellValue();
                    break;
                }
                case STRING:
                    cellvalue = cell.getRichStringCellValue().getString();
                    break;
                default:
                    cellvalue = "";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;
    }
}