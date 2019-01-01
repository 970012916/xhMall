package com.xhMall.dto;

import com.xhMall.db.entity.base.BaseBean;

import java.io.Serializable;

public class BaseResult<T> extends BaseBean {

    private static final long serialVersionUID = -8178216882385805510L;
    private String message;

    private String status;

    private T data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
