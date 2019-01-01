package com.xhMall.global;

import com.xhMall.common.constant.Constant;
import com.xhMall.common.util.LogUtil;
import com.xhMall.exception.PrjException;

/**
 * Created by sheting on Administrator
 * DateTime  2018/9/24,15:06
 */
public final class GlobalManager {

    private GlobalManager(){

    }

    private static XMLManager xmlManager = null;

    private static PropertyManager propertyManager = null;

    public static void init() throws PrjException{
        xmlManager = new XMLManager();
        propertyManager = new PropertyManager();
        propertyManager.init();
        xmlManager.init();
    }


    public static void destroy(){
        propertyManager.destroy();
        xmlManager.destroy();
        propertyManager = null;
        xmlManager = null;
        LogUtil.debug(Constant.SYSTEM_DESTROY);
    }

    public static PropertyManager getPropertyManager(){
        return propertyManager;
    }

    public static XMLManager getXMLManager(){
        return xmlManager;
    }
}
