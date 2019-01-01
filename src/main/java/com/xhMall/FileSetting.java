package com.xhMall;

/**
 * Created by sheting on Administrator
 * DateTime  2018/9/24,12:36
 */
public enum FileSetting {

    /**
     * 全局配置文件
     */
    GloabalSetting("../conf/globalSetting.xml"),
    MessageProperties("../conf/message.properties"),
    SelectOptionSettingXml("../conf/message.properties"),;
    private final String path;

    private FileSetting(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
