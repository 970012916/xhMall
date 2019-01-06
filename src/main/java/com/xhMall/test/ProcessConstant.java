package com.xhMall.test;

/**
 * Created by sheting on Administrator
 * DateTime  2019/1/6,22:06
 */
public enum ProcessConstant {


    START_EVENT("startEvent"),EXCLUSIVE_GATEWAY("exclusiveGateway"),PROCESS_START_PERSON("processStartPerson"),
    SKIP_EXPRESSION("skipExpression"),DEAL_PERSON_ID("skipExpression"),REJECT_REASON("rejectReason"),
    CURRENT_PERSON_ID("currentPersonId"),DEAL_PERSON_NAME("dealPersonName"),CURRENT_PERSON_NAME("currentPersonName");

    private final String name;

    ProcessConstant(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
