package com.xhMall.exception;

/**
 * Created by sheting on Administrator
 * DateTime  2018/9/10,20:42
 */
public class AuthException extends PrjException {


    private static final long serialVersionUID = 6577404026869336836L;

    public AuthException(){
        super();
    }

    public AuthException(String message){
        super(message);
    }

    public AuthException(Throwable cause){
        super(cause);
    }

    public AuthException(String message,Throwable cause){
        super(message,cause);
    }
}
