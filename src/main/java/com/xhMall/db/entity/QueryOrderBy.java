package com.xhMall.db.entity;

import com.xhMall.common.constant.Constant;
import com.xhMall.db.entity.base.BaseBean;

/**
 * Created by sheting on Administrator
 * DateTime  2018/9/19,22:23
 */
public class QueryOrderBy extends BaseBean {

    private static final long serialVersionUID = 5884903972990881510L;

    /**
     * 排序字段
     */
    private String orderBy = null;

    private Constant.Order order = Constant.Order.ASC;


    public QueryOrderBy(String orderBy, Constant.Order order) {
        this.order = order;
        this.orderBy = orderBy;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Constant.Order getOrder() {
        return order;
    }

    public void setOrder(Constant.Order order) {
        this.order = order;
    }
}
