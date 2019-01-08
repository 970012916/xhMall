package com.xhMall.test;

import com.xhMall.db.entity.UserEntity;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskInfo;
import org.activiti.engine.task.TaskInfoQuery;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//审核过程
public class MyWorkProcessApproval {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    @Test
    public void startProcessApproval(){

        TaskService taskService = processEngine.getTaskService();

        //taskId 就是查询任务中的 ID
        /*String taskId = "17508";
        taskService.setVariable(taskId, "days", 2);
        taskService.setVariable(taskId, "date", new Date());
        taskService.setVariable(taskId, "reason", "发烧");*/

        //存序列化对象
        /*UserEntity student=new UserEntity();
        student.setId("21122474");
        student.setUsername("张三");
        taskService.setVariable(taskId, "student", student); */

        //将流程变量存储在map中
        /*String taskId="17508";
        UserEntity student=new UserEntity();
        student.setId("21122474");
        student.setUsername("张三");

        Map<String, Object> variables=new HashMap<String,Object>();
        variables.put("days", 2);
        variables.put("date", new Date());
        variables.put("reason", "发烧");
        variables.put("student", student);*/

        //RuntimeService绑定的是act_ru_execution表的executionId。任务ID随着任务节点的变化而变化，而executionId一般不会改变。
        //同样，用RuntimeService设置的值同样在接下来的流程都可以获取，知道流程结束
        RuntimeService runtimeService=processEngine.getRuntimeService(); // 任务Service
        String executionId="15001";
        runtimeService.setVariable(executionId, "days", 2);
        runtimeService.setVariable(executionId, "date", new Date());
        runtimeService.setVariable(executionId, "reason", "发烧");
        UserEntity student=new UserEntity();
        student.setId("21122474");
        student.setUsername("梁经理");
        runtimeService.setVariable(executionId, "student", student); // 存序列化对象
        //完成请假申请任务
        taskService.complete("20002");
        //完成请假任务时设置流程变量
        /*Map<String, Object> variables=new HashMap<String,Object>();
        variables.put("days", 2);
        variables.put("date", new Date());
        variables.put("reason", "发烧");
        variables.put("student", student);
        processEngine.getTaskService().complete("17508",variables);*/
    }


    @Test
    public void startProcessApprovalLianXian(){

        TaskService taskService = processEngine.getTaskService();
        Map<String, Object> variables=new HashMap<String,Object>();
        variables.put("msg", "重要情况");
        taskService.complete("35004");
    }

    @Test
    public void startProcessApprovalExclusiveGateway(){

        TaskService taskService = processEngine.getTaskService();
        Map<String, Object> variables=new HashMap<String,Object>();
        variables.put("days", "7");
        taskService.complete("45004",variables);
    }

    //并行网关 必须完成所有任务节点才到下一节点
    @Test
    public void startProcessApprovalParallelGateWay(){

        TaskService taskService = processEngine.getTaskService();

        taskService.complete("85010");
    }
    /**
     * taskID 是设置流程变量的下一级的任务的id
     */
    @Test
    public void getVariableValues(){

        TaskService taskService = processEngine.getTaskService();
        String taskId="17508";
        /*Integer days=(Integer) taskService.getVariable(taskId, "days");
        Date date=(Date) taskService.getVariable(taskId, "date");
        String reason=(String) taskService.getVariable(taskId, "reason");
        UserEntity student=(UserEntity) taskService.getVariable(taskId, "student");
        System.out.println("请假天数："+days);
        System.out.println("请假日期："+date);
        System.out.println("请假原因："+reason);
        System.out.println("请假对象："+student.getId()+","+student.getUsername());*/

        //从map中获取流程变量
       /* Map<String,Object> variableMap = taskService.getVariables(taskId);
        Integer days2=(Integer) variableMap.get("days");
        Date date2=(Date) variableMap.get("date");
        String reason2=(String) variableMap.get("reason");
        UserEntity student2=(UserEntity)variableMap.get("student");*/

        //RuntimeService绑定的是act_ru_execution表的executionId。任务ID随着任务节点的变化而变化，而executionId一般不会改变。
        //同样，用RuntimeService设置的值同样在接下来的流程都可以获取，知道流程结束
        RuntimeService runtimeService=processEngine.getRuntimeService(); // 任务Service
        String executionId="15001";
        Integer days=(Integer) runtimeService.getVariable(executionId, "days");
        Date date=(Date) runtimeService.getVariable(executionId, "date");
        String reason=(String) runtimeService.getVariable(executionId, "reason");
        UserEntity student=(UserEntity) runtimeService.getVariable(executionId, "student");
        System.out.println("请假天数："+days);
        System.out.println("请假日期："+date);
        System.out.println("请假原因："+reason);
        System.out.println("请假对象："+student.getId()+","+student.getUsername());
    }
}

