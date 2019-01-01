package com.xhMall.global;

import com.xhMall.FileSetting;
import com.xhMall.common.constant.Constant;
import com.xhMall.common.util.LogUtil;
import com.xhMall.exception.PrjException;
import com.xhMall.global.managerInterface.IGlobalManager;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by sheting on Administrator
 * DateTime  2018/9/24,16:14
 */
public class PropertyManager implements IGlobalManager{
    /**
     * 全局配置文件（GlobalSetting.xml）
     */
    private static Properties gloabalSettingProperties = null;

    public PropertyManager(){}


    @Override
    public void init() throws PrjException {
        LogUtil.debug(Constant.READ_PROPERTY_BEGIN);
        loadMessageInfo(FileSetting.MessageProperties.getPath(),false);
        loadGlobalInfo(FileSetting.GloabalSetting.getPath(),true);
        LogUtil.debug(Constant.READ_PROPERTY_END);

    }

    @Override
    public void destroy() {

        if (gloabalSettingProperties != null ) {
            gloabalSettingProperties.clear();
            gloabalSettingProperties = null;
        }

        LogUtil.debug(Constant.READ_COMMOX_INFO_DESTROY);
    }

    /**
     * 读取全局配置文件信息
     * @param path
     * @param isXml
     */
    private static void loadGlobalInfo(String path, boolean isXml) {
        gloabalSettingProperties = load(path,isXml);
    }


    /**
     * 读取message配置文件信息
     * @param path
     * @param isXml
     */
    private static void loadMessageInfo(String path, boolean isXml) {
        gloabalSettingProperties = load(path,isXml);
    }

    private static Properties load(String path, boolean isXml) {
        Properties prop = null;
        try {
            InputStream inputStream = PropertyManager.class.getClassLoader().getResourceAsStream(path);
            prop = new Properties();
            if (isXml){
                prop.loadFromXML(inputStream);
            } else {
                prop.load(inputStream);
            }
        }catch (Exception e) {
            prop = null;
            LogUtil.error(Constant.READ_PROPERTY_FAIL,e);
        }
        return prop;
    }


    /**
     * 根据键值获取配置信息(GlobalSetting.xml)
     * @param key
     * @return
     */
    public String getGlobalProperty(String key) {
        return gloabalSettingProperties.getProperty(key);
    }

    /**
     * 根据键值获取配置信息(GlobalSetting.xml)
     * @param key
     * @return
     */
    public String getGlobalProperty(String key,String defaultValue) {
        return gloabalSettingProperties.getProperty(key,defaultValue);
    }

    /**
     * 根据键值获取配置信息(GlobalSetting.xml)
     * @param key
     * @return
     */
    public int getGlobalPropertyForInt(String key) {
        return Integer.parseInt(gloabalSettingProperties.getProperty(key));
    }
}

