package com.xhMall.dto;

import java.util.List;

/**
 * Created by sheting on Administrator
 * DateTime  2018/8/15,22:08
 */
public class GankResult {
    private Boolean error;
    private List<GankMeizi> results;

    public GankResult() { }

    public GankResult(Boolean error, List<GankMeizi> results) {
        this.error = error;
        this.results = results;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<GankMeizi> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "GankResult{" +
                "error=" + error +
                ", results=" + results.toString() +
                '}';
    }

    public void setResults(List<GankMeizi> results) {
        this.results = results;
    }
}
