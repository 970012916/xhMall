package com.xhMall.test;

/**
 * Created by sheting on Administrator
 * DateTime  2018/12/25,22:13
 */

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

public class MyWorkStart {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    //启动流程
    @Test
    public void startProcess(){
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey("myProcess_1");//流程的名称，对应流程定义表的key字段，也可以使用ByID来启动流程
        System.out.println("************" + processInstance.getId());
    }
    @Test
    public void startProcessLianXian(){
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey("ExclusiveGateWayProcess");//流程的名称，对应流程定义表的key字段，也可以使用ByID来启动流程
        System.out.println("************" + processInstance.getId());
    }

    //parallelGateWay
    @Test
    public void startParallelGateWay(){
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey("parallelGateWay");//流程的名称，对应流程定义表的key字段，也可以使用ByID来启动流程
        System.out.println("************" + processInstance.getId());
    }
}
