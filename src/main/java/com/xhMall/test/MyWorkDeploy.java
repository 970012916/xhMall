package com.xhMall.test;

/**
 * Created by sheting on Administrator
 * DateTime  2018/12/25,22:06
 */
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.junit.Test;

public class MyWorkDeploy {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    //部署流程
    @Test
    public void deployProcess(){
        RepositoryService repositoryService = processEngine.getRepositoryService();
        DeploymentBuilder builder = repositoryService.createDeployment();
        Deployment deployment = builder.addClasspathResource("workFlowTest.bpmn")//bpmn文件的名称
                .addClasspathResource("workFlowTest.png")
                .name("请假流程")
                .deploy();
        System.out.println(deployment.getId());
        System.out.println(deployment.getName());
    }

}
