package com.xhMall.exception;

import java.io.Serializable;

public class MallException extends RuntimeException implements Serializable{


    public MallException() {

    }

    public MallException(String message) {
        this.message = message;
    }

    private static final long serialVersionUID = 101851125778574430L;

    private String message;

    private Integer code ;

    private String name;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
