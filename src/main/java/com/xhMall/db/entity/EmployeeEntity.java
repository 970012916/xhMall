package com.xhMall.db.entity;

import com.xhMall.db.entity.base.BaseEntity;

/**
 * Created by sheting on Administrator
 * DateTime  2018/10/7,9:38
 */
public class EmployeeEntity extends BaseEntity {
    private static final long serialVersionUID = 8194279330928491590L;

    private String name;
    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
