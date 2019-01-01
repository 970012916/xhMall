package com.xhMall.baseEnum;

/**
 * Created by sheting on Administrator
 * DateTime  2018/10/6,14:29
 */
public enum TemplateSetting {
    TMP_SIMPLE("fileTempPath1/SAMPLE.xlsx");

    private final String fileName;

    private TemplateSetting(String fileName){
        this.fileName = fileName;
    }

    public String getFileName(){
        return fileName;
    }
}
