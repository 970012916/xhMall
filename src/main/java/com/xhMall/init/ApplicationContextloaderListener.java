package com.xhMall.init;

import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;

/**
 * Created by sheting on Administrator
 * DateTime  2018/9/26,22:19
 */
public class ApplicationContextloaderListener extends ContextLoaderListener{
    public void contextInitialized(ServletContextEvent event){
        super.contextInitialized(event);
    }
}
