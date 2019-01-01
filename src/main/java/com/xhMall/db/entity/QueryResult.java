package com.xhMall.db.entity;

import com.xhMall.db.entity.base.BaseBean;

import java.util.List;

/**
 * Created by sheting on Administrator
 * DateTime  2018/9/24,12:32
 */
public class QueryResult<T> extends BaseBean {

    private static final long serialVersionUID = 6290718555414578942L;

    private List<T> date = null;

    private int totalCount = 0;

    private int pageIndex = 0;

    /**
     * 每页记录数
     */
    private int pageRows = 0;

    private int totalPageCount = 0;

    public List <T> getDate() {
        return date;
    }

    public void setDate(List <T> date) {
        this.date = date;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageRows() {
        return pageRows;
    }

    public void setPageRows(int pageRows) {
        this.pageRows = pageRows;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }
}
