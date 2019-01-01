package com.xhMall.exception;

/**
 * Created by sheting on Administrator
 * DateTime  2018/9/10,20:39
 */
public class TimeOutException extends PrjException {


    private static final long serialVersionUID = 4077856786219820762L;

    public TimeOutException(){
        super();
    }

    public TimeOutException(String message){
        super(message);
    }

    public TimeOutException(Throwable cause){
        super(cause);
    }

    public TimeOutException(String message,Throwable cause){
        super(message,cause);
    }
}
