package com.xhMall.test;


import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;

import org.activiti.engine.TaskService;

import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.List;

public class MyWorkQuery {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    //查询流程
    @Test
    public void queryTask(){
        TaskService taskService = processEngine.getTaskService();
        //根据assignee(节点接受人)查询任务
        String assignee = "张三";//
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(assignee).list();

        int size = tasks.size();
        for (int i = 0; i < size; i++) {
            Task task = tasks.get(i);

        }
        //首次运行的时候这个没有输出，因为第一次运行的时候扫描act_ru_task的表里面是空的，但第一次运行完成之后里面会添加一条记录，之后每次运行里面都会添加一条记录
        for (Task task : tasks) {
            System.out.println("taskId=" +"流程任务节点信息ID："+ task.getId() +
                    ",taskName:" +"流程任务节点名称ID：" +task.getName() +
                    ",assignee:" + "流程任务节点接受人："+task.getAssignee() +
                    ",createTime:" +"流程任务节点创建时间："+ task.getCreateTime());
        }
    }




}
