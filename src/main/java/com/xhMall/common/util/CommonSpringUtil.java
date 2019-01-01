package com.xhMall.common.util;

import org.springframework.context.ApplicationContext;

/**
 * Created by sheting on Administrator
 * DateTime  2018/9/17,21:46
 */
public class CommonSpringUtil {

    private static ApplicationContext ctx = null;

    static {
        try {

        }catch (Exception e) {
            LogUtil.debug(e.toString());
            LogUtil.error("XML文件： ApplicationContext 初始化",e);
        }
    }

    private static Object getBean(String beanName){
        return ctx.getBean(beanName);
    }

}
