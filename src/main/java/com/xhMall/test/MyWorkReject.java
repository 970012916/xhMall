package com.xhMall.test;

import com.xhMall.db.entity.base.BaseResult;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.persistence.entity.HistoricActivityInstanceEntity;
import org.activiti.engine.impl.persistence.entity.HistoricActivityInstanceEntityManager;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sheting on Administrator
 * DateTime  2019/1/6,21:34
 */
public class MyWorkReject {


    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    /**
     * 驳回任务方封装
     *
     * @param destinationTaskID 驳回的任务ID 目标任务ID
     * @param messageContent  驳回的理由
     * @param currentTaskID  当前正要执行的任务ID
     * @return 驳回结果 携带下个任务编号
     */
    public BaseResult rejectTask(String destinationTaskID, String currentTaskID, String messageContent) {
        HistoryService historyService = processEngine.getHistoryService();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        TaskService processService = processEngine.getTaskService();
        /**
         * 目标任务实例 historicDestinationTaskInstance 带流程变量，任务变量
         */
        HistoricTaskInstance historicDestinationTaskInstance = historyService
                .createHistoricTaskInstanceQuery()
                .taskId(destinationTaskID)
                .includeProcessVariables()
                .includeTaskLocalVariables()
                .singleResult();

        /**
         * 正在执行的任务实例 historicCurrentTaskInstance 带流程变量，任务变量
         */
        HistoricTaskInstance historicCurrentTaskInstance = historyService
                .createHistoricTaskInstanceQuery()
                .taskId(currentTaskID)
                .includeProcessVariables()
                .includeTaskLocalVariables()
                .singleResult();

        /**
         * 流程定义ID
         */
        String processDefinitionId = historicCurrentTaskInstance.getProcessDefinitionId();

        /**
         * 流程实例ID
         */
        String processInstanceId = historicCurrentTaskInstance.getProcessInstanceId();

        /**
         * 流程定义实体
         */
        ProcessDefinitionEntity processDefinition =
                (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId);

        /**
         * 根据任务创建时间正序排序获取历史任务实例集合 historicTaskInstanceList 含流程变量，任务变量
         */
        List<HistoricTaskInstance> historicTaskInstanceList = historyService
                .createHistoricTaskInstanceQuery()
                .processInstanceId(processInstanceId)
                .includeProcessVariables()
                .includeTaskLocalVariables()
                .orderByTaskCreateTime()
                .asc()
                .list();

        /**
         * 历史活动节点实例集合 historicActivityInstanceList  包含一个活动(流程上的节点)的执行信息 。
         */
        List<HistoricActivityInstance> historicActivityInstanceList = historyService
                .createHistoricActivityInstanceQuery()
                .processInstanceId(processInstanceId)
                .orderByHistoricActivityInstanceStartTime()
                .asc()
                .list();

        /**
         * 获取目标任务的节点信息
         */

        ActivityImpl destinationActivity = processDefinition
                .findActivity(historicDestinationTaskInstance.getTaskDefinitionKey());

        /**
         * 定义一个历史任务集合，完成任务后任务删除此集合中的任务
         */
        List<HistoricTaskInstance> deleteHistoricTaskInstanceList = new ArrayList<>();

        /**
         * 定义一个历史活动节点集合，完成任务后要添加的历史活动节点集合
         */
        List<HistoricActivityInstanceEntity> insertHistoricTaskActivityInstanceList = new ArrayList<>();

        /**
         * 目标任务编号
         */
        Integer destinationTaskInstanceId = Integer.valueOf(destinationTaskID);

        /**
         * 把目标节点之后的节点Id加入deleteHistoricTaskInstanceList，准备删除
         */
        for (HistoricTaskInstance historicTaskInstance : historicTaskInstanceList) {
            Integer historicTaskInstanceId = Integer.valueOf(historicTaskInstance.getId());
            if (destinationTaskInstanceId <= historicTaskInstanceId) {
                deleteHistoricTaskInstanceList.add(historicTaskInstance);
            }
        }

        // 有序
        for (int i = 0; i < historicActivityInstanceList.size() - 1; i++) {
            HistoricActivityInstance historicActivityInstance = historicActivityInstanceList.get(i);
            // 历史活动节点的任务编号
            Integer historicActivityInstanceTaskId;
            String taskId = historicActivityInstance.getTaskId();
            if (taskId != null) {
                historicActivityInstanceTaskId = Integer.valueOf(taskId);
                if (historicActivityInstanceTaskId <= destinationTaskInstanceId) {
                    //将在目标任务节点之前的节点ID加入集合
                    insertHistoricTaskActivityInstanceList.add((HistoricActivityInstanceEntity) historicActivityInstance);
                }
            } else {
                if (historicActivityInstance.getActivityType().equals(ProcessConstant.START_EVENT.getName())) {
                    insertHistoricTaskActivityInstanceList.add((HistoricActivityInstanceEntity) historicActivityInstance);
                } else if (historicActivityInstance.getActivityType().equals(ProcessConstant.EXCLUSIVE_GATEWAY.getName())) {
                    insertHistoricTaskActivityInstanceList.add((HistoricActivityInstanceEntity) historicActivityInstance);
                }
            }
        }
        // 获取流程定义的节点信息
        List<ActivityImpl> processDefinitionActivities = processDefinition.getActivities();
        // 用于保存正在执行的任务节点信息
        ActivityImpl currentActivity = null;
        // 用于保存原来的任务节点的出口信息
        PvmTransition pvmTransition = null;
        // 保存原来的流程节点出口信息
        for (ActivityImpl activity : processDefinitionActivities) {
            if (historicCurrentTaskInstance.getTaskDefinitionKey().equals(activity.getId())) {
                currentActivity = activity;
                // 备份
                pvmTransition = activity.getOutgoingTransitions().get(0);
                // 清空当前任务节点的出口信息
                activity.getOutgoingTransitions().clear();
            }
        }
        // 执行流程转向
        processEngine.getManagementService().executeCommand(
                new RejectTaskCMD(historicDestinationTaskInstance, historicCurrentTaskInstance, destinationActivity));
        // 获取正在执行的任务的流程变量
        Map<String, Object> taskLocalVariables = historicCurrentTaskInstance.getTaskLocalVariables();
        // 获取目标任务的流程变量，修改任务不自动跳过，要求审批
        Map<String, Object> processVariables = historicDestinationTaskInstance.getProcessVariables();
        // 获取流程发起人编号
        Integer employeeId = (Integer) processVariables.get(ProcessConstant.PROCESS_START_PERSON.getName());
        processVariables.put(ProcessConstant.SKIP_EXPRESSION.getName(), false);
        taskLocalVariables.put(ProcessConstant.SKIP_EXPRESSION.getName(), false);
        // 设置驳回原因
        taskLocalVariables.put(ProcessConstant.REJECT_REASON.getName(), messageContent);
        // region 流程变量转换
        // 修改下个任务的任务办理人
        processVariables.put(ProcessConstant.DEAL_PERSON_ID.getName(), processVariables.get(ProcessConstant.CURRENT_PERSON_ID.getName()));
        // 修改下个任务的任务办理人姓名
        processVariables.put(ProcessConstant.DEAL_PERSON_NAME.getName(), processVariables.get(ProcessConstant.CURRENT_PERSON_NAME.getName()));
        // 修改下个任务的任务办理人
        taskLocalVariables.put(ProcessConstant.DEAL_PERSON_ID.getName(), processVariables.get(ProcessConstant.CURRENT_PERSON_ID.getName()));
        // 修改下个任务的任务办理人姓名
        taskLocalVariables.put(ProcessConstant.DEAL_PERSON_NAME.getName(), processVariables.get(ProcessConstant.CURRENT_PERSON_NAME.getName()));
        // endregion
        // 完成当前任务，任务走向目标任务
        //String nextTaskId = processService.completeTaskByTaskID(currentTaskID, processVariables, taskLocalVariables);
        if (currentActivity != null) {
            // 清空临时转向信息
            currentActivity.getOutgoingTransitions().clear();
        }
        if (currentActivity != null) {
            // 恢复原来的走向
            currentActivity.getOutgoingTransitions().add(pvmTransition);
        }
        // 删除历史任务
        for (HistoricTaskInstance historicTaskInstance : deleteHistoricTaskInstanceList) {
            historyService.deleteHistoricTaskInstance(historicTaskInstance.getId());
        }
        // 删除活动节点
        processEngine.getManagementService().executeCommand(
                (Command<List<HistoricActivityInstanceEntity>>) commandContext -> {
                    HistoricActivityInstanceEntityManager historicActivityInstanceEntityManager =
                            commandContext.getHistoricActivityInstanceEntityManager();
                    // 删除所有的历史活动节点
                    historicActivityInstanceEntityManager
                            .deleteHistoricActivityInstancesByProcessInstanceId(processInstanceId);
                    // 提交到数据库
                    commandContext.getDbSqlSession().flush();
                    // 添加历史活动节点的
                    for (HistoricActivityInstanceEntity historicActivityInstance : insertHistoricTaskActivityInstanceList) {
                        historicActivityInstanceEntityManager.insertHistoricActivityInstance(historicActivityInstance);
                    }
                    // 提交到数据库
                    commandContext.getDbSqlSession().flush();
                    return null;
                }
        );
        // 返回下个任务的任务ID
        return null;
    }
}
