package com.xhMall.test;



import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.List;

public class MyWorkQueryProcess {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    //查询流程定义明细
    @Test
    public void queryProcdef(){
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //创建查询对象
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
        //添加查询条件
        query.processDefinitionKey("myProcess_1");//通过key获取
        // .processDefinitionName("My process")//通过name获取
        // .orderByProcessDefinitionId()//根据ID排序
        //执行查询获取流程定义明细
        List<ProcessDefinition> pds = query.list();
        for (ProcessDefinition pd : pds) {
            System.out.println("ID:"+pd.getId()+",NAME:"+pd.getName()+",KEY:"+pd.getKey()+",VERSION:"+pd.getVersion()+",RESOURCE_NAME:"+pd.getResourceName()+",DGRM_RESOURCE_NAME:"+pd.getDiagramResourceName());
        }
    }

    @Test
    public void findMyPersonTask(){

        String assignee = "梁经理";

        List<Task>taskList= processEngine.getTaskService()//获取任务service

                .createTaskQuery()//创建查询对象

                .taskAssignee(assignee)//指定查询人

                .list();

        if(taskList.size()>0){

            for (Task task : taskList){

                System.out.println("代办任务ID:"+task.getId());

                System.out.println("代办任务name:"+task.getName());

                System.out.println("代办任务创建时间:"+task.getCreateTime());

                System.out.println("代办任务办理人:"+task.getAssignee());

                System.out.println("流程实例ID:"+task.getProcessInstanceId());

                System.out.println("执行对象ID:"+task.getExecutionId());
            }
        }
    }

    @Test
    public void historyActInstanceList(){
        //获取的是act_hi_actinst的数据
        List<HistoricActivityInstance> list = processEngine.getHistoryService()
                                                .createHistoricActivityInstanceQuery()
                                                .processInstanceId("2501")
                                                .finished()
                                                .list();
        for(HistoricActivityInstance hai:list){
            System.out.println("活动ID:"+hai.getId());
            System.out.println("流程实例ID:"+hai.getProcessInstanceId());
            System.out.println("活动名称："+hai.getActivityName());
            System.out.println("办理人："+hai.getAssignee());
            System.out.println("开始时间："+hai.getStartTime());
            System.out.println("结束时间："+hai.getEndTime());
            System.out.println("=================================");
        }
    }

    @Test
    public void historyTaskList(){
        // 查询act_hi_taskinst的数据
        List<HistoricTaskInstance> list=processEngine.getHistoryService() // 历史相关Service
                .createHistoricTaskInstanceQuery() // 创建历史任务实例查询
                .processInstanceId("2501") // 用流程实例id查询
                .finished() // 查询已经完成的任务
                .list();
        for(HistoricTaskInstance hti:list){
            System.out.println("任务ID:"+hti.getId());
            System.out.println("流程实例ID:"+hti.getProcessInstanceId());
            System.out.println("任务名称："+hti.getName());
            System.out.println("办理人："+hti.getAssignee());
            System.out.println("开始时间："+hti.getStartTime());
            System.out.println("结束时间："+hti.getEndTime());
            System.out.println("=================================");
        }
    }

    /**
     * 查询流程状态（正在执行 or 已经执行结束）
     */
    @Test
    public void processState(){
        ProcessInstance pi=processEngine.getRuntimeService() // 获取运行时Service
                .createProcessInstanceQuery() // 创建流程实例查询
                .processInstanceId("2501") // 用流程实例id查询
                .singleResult();
        if(pi!=null){
            System.out.println("流程正在执行！");
        }else{
            System.out.println("流程已经执行结束！");
        }
    }
}
