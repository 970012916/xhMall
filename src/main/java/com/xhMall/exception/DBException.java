package com.xhMall.exception;

/**
 * Created by sheting on Administrator
 * DateTime  2018/9/10,20:44
 */
public class DBException extends PrjException {
    private static final long serialVersionUID = -9012284156805358223L;

    public DBException(){
        super();
    }

    public DBException(String message){
        super(message);
    }

    public DBException(Throwable cause){
        super(cause);
    }

    public DBException(String message,Throwable cause){
        super(message,cause);
    }
}
