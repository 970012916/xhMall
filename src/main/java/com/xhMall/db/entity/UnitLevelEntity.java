package com.xhMall.db.entity;

/**
 * Created by sheting on Administrator
 * DateTime  2018/9/17,21:37
 */
//table = MVW_UNIT_LEVEL
public class UnitLevelEntity extends UnitEntity {

    private static final long serialVersionUID = 8472144493553531878L;

    private String belong;

    private String pathCode;

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    public String getPathCode() {
        return pathCode;
    }

    public void setPathCode(String pathCode) {
        this.pathCode = pathCode;
    }
}
