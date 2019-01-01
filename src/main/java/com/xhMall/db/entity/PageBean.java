package com.xhMall.db.entity;

import com.xhMall.db.entity.base.BaseBean;

/**
 * Created by sheting on Administrator
 * DateTime  2018/9/19,22:20
 */
public class PageBean extends BaseBean {


    private static final long serialVersionUID = 1528931213884994497L;

    private int currentPage;

    private int pageIndex;

    private int totalPages;

    private int pageSize;

    private int totalRecords;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }
}
