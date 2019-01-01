package com.xhMall;

/**
 * Created by sheting on Administrator
 * DateTime  2018/9/24,12:41
 */
public enum TransType {

    /**
     * Master管理
     */
    SB_MM("mm"),

    /**
     * 系统共通管理
     */
    SB_CM("cm");

    private final String transTypeId;

    public String getTransTypeId() {
        return transTypeId;
    }

    private TransType(String transTypeId) {
        this.transTypeId = transTypeId;
    }
}
