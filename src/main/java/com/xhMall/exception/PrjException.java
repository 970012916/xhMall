package com.xhMall.exception;

/**
 * Created by sheting on Administrator
 * DateTime  2018/9/10,20:35
 */
public class PrjException extends Exception {
    private static final long serialVersionUID = 6988289622775659L;

    private Object errorObj = null;

    public PrjException(){
        super();
    }

    public PrjException(String message){
        super(message);
    }


    /**
     *
     * @param cause 原因
     */
    public PrjException(Throwable cause){
        super(cause);
    }

    public PrjException(String message,Throwable cause){
        super(message,cause);
    }


    public Object getErrorObj() {
        return errorObj;
    }

    public void setErrorObj(Object errorObj) {
        this.errorObj = errorObj;
    }
}
