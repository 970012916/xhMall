package com.xhMall.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

/**
 * Created by sheting on Administrator
 * DateTime  2018/12/25,20:55
 */
public class MyWorkTable {
    @Test
    public void createTable(){
        ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml").buildProcessEngine();
    }
}
