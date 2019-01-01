package com.xhMall.common.util;

import net.sf.jett.transform.ExcelTransformer;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sheting on Administrator
 * DateTime  2018/10/6,13:24
 */
public class CommonJettUtil {

    /**
     * 根据文件路劲读取文件信息
     * @param filePath
     * @return
     */
    public static Workbook getWorkbook(String filePath) {
        Workbook workbook = null;
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            workbook = WorkbookFactory.create(inputStream);
        } catch (FileNotFoundException ex){
            ex.printStackTrace();
            LogUtil.error(ex);
        } catch (IOException ex) {
            ex.printStackTrace();
            LogUtil.error(ex);
        } catch (EncryptedDocumentException ex) {
            ex.printStackTrace();
            LogUtil.error(ex);
        } catch (InvalidFormatException ex ) {
            ex.printStackTrace();
            LogUtil.error(ex);
        }
        return workbook;
    }

    public static Workbook getWorkbook(InputStream inputStream) {
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(inputStream);
        } catch (IOException ex) {
            ex.printStackTrace();
            LogUtil.error(ex);
        } catch (EncryptedDocumentException ex) {
            ex.printStackTrace();
            LogUtil.error(ex);
        } catch (InvalidFormatException ex ) {
            ex.printStackTrace();
            LogUtil.error(ex);
        }
        return workbook;
    }

    /**
     * 获取工作簿中的所有工作表
     * @param workbook
     * @return
     */
    public static List<Sheet> getSheets(Workbook workbook) {
        List<Sheet> sheets = new ArrayList <>();
        if (null == workbook) {
            return null;
        }

        int sheetNum = workbook.getNumberOfSheets();
        for (int i = 0; i < sheetNum ; i++ ) {
            sheets.add(workbook.getSheetAt(i)) ;
        }
        return sheets;
    }

    /**
     * 从工作表中获取指定行信息
     * @param sheet
     * @param rowIndex
     * @return
     */
    public static Row getRowInfo(Sheet sheet,int rowIndex) {

        Row row = null;
        if (null == sheet) {
            return null;
        }
        row = sheet.getRow(rowIndex);
        return row;
    }

    /**
     * 从工作表中获取指定单元格信息
     * @param sheet
     * @param rowIndex
     * @param colIndex
     * @return
     */
    public static String getCellInfo(Sheet sheet,int rowIndex,int colIndex) {
        String cellInfo = null;
        if (sheet != null) {
            cellInfo = getCellInfo(sheet.getRow(rowIndex),colIndex);
        }

        return cellInfo;
    }

    /**
     * 从行内容中获取指定单元格信息
     * @param row
     * @param colIndex
     * @return
     */
    public static String getCellInfo(Row row,int colIndex) {
        String cellInfo = getCellFormatValue(row.getCell(colIndex));
        return cellInfo;
    }

    /**
     * 判断是否为空行
     * @param row
     * @return
     */
    public static boolean isBlankRow(Row row) {
        if (null == row) {
            return true;
        }
        boolean isBlank = true;
        int lastCellNum = row.getLastCellNum();
        for (int i = 0;i<lastCellNum;i++ ) {
            String cellInfo = getCellInfo(row,i);
            if (!CommonStringUtil.isNullOrEmpty(cellInfo)){
                isBlank = false;
                break;
            }
        }
        return isBlank;
    }

    /**
     * 填充数据
     */
    public static Workbook transForm(String tmpPath, HashMap<String,Object> beans) {
        Workbook workbook = null;
        ExcelTransformer transformer = new ExcelTransformer();
        //tmpPath = GlobalManager.getPropertyManager().getGlobalProperty("templatePath") + tmpPath;
        try {
            InputStream inputStream = CommonJettUtil.class.getClassLoader().getResourceAsStream(URLEncoder.encode("sample.xls","utf-8"));
            workbook = transformer.transform(inputStream,beans);
        }/* catch (FileNotFoundException ex){
            ex.printStackTrace();
            LogUtil.error(ex);
        } catch (IOException ex) {
            ex.printStackTrace();
            LogUtil.error(ex);
        } catch (InvalidFormatException ex ) {
            ex.printStackTrace();
            LogUtil.error(ex);
        }*/
        catch (Exception ex ) {
            ex.printStackTrace();
            LogUtil.error(ex);
        }
        return workbook;
    }

    /**
     * 获取下载用文件
     */
    public static ResponseEntity<byte[]> getResponseEntity(Workbook workbook,String fileName) {
        HttpHeaders headers = new HttpHeaders();
        //headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.set("Content-Type","application/vnd.ms-excel");
        headers.setContentDispositionFormData("attachment",fileName);
        byte[] body = asWorkbookBytes(workbook);
        ResponseEntity<byte[]> responseEntity = new ResponseEntity <>(body,headers, HttpStatus.CREATED);
        return responseEntity;
    }

    /**
     * 更加cell类型设置数据
     * @param cell
     * @return
     */
    private static String getCellFormatValue(Cell cell) {
        String cellValue = null;
        if (null != cell) {
            switch (cell.getCellType()){
                case Cell.CELL_TYPE_NUMERIC :
                case Cell.CELL_TYPE_FORMULA : {
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        Date date = cell.getDateCellValue();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        cellValue = sdf.format(date);
                    } else {
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                     break;
                }
                case Cell.CELL_TYPE_STRING :{
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }

        }else {
            cellValue = "";
        }

        return cellValue.trim();
    }

    /**
     * 将工作簿转换为byte数组
     * @param workbook
     * @return
     */
    public static byte[] asWorkbookBytes(Workbook workbook) {
        byte[] bytes = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            workbook.write(bos);
            bytes = bos.toByteArray();
        }catch (IOException ex){
            ex.printStackTrace();
            LogUtil.error(ex);
        }finally {
            try {
                if (null != workbook) {
                    workbook.close();
                    workbook = null;
                }
            }catch (IOException ex) {
                ex.printStackTrace();
                LogUtil.error(ex);
            }
        }
        return bytes;
    }
}
