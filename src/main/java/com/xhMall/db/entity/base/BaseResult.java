package com.xhMall.db.entity.base;

import com.xhMall.common.constant.Constant;
import com.xhMall.db.entity.PageBean;

/**
 * Created by sheting on Administrator
 * DateTime  2018/9/24,12:47
 */
public class BaseResult<T> extends BaseBean {


    private static final long serialVersionUID = -3537736947350766537L;

    private String message;

    private T data;

    private Constant.RESULT_TYPE result;

    private PageBean pageInfo;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Constant.RESULT_TYPE getResult() {
        return result;
    }

    public void setResult(Constant.RESULT_TYPE result) {
        this.result = result;
    }

    public PageBean getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageBean pageInfo) {
        this.pageInfo = pageInfo;
    }
}
