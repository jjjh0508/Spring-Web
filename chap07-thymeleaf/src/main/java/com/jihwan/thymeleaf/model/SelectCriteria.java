package com.jihwan.thymeleaf.model;

import lombok.*;


public class SelectCriteria {

    private int StartPage;
    private int endPage;
    private int pageNo;

    public SelectCriteria(int startPage, int endPage, int pageNo) {
        StartPage = startPage;
        this.endPage = endPage;
        this.pageNo = pageNo;
    }

    public int getStartPage() {
        return StartPage;
    }

    public void setStartPage(int startPage) {
        StartPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    @Override
    public String toString() {
        return "SelectCriteria{" +
                "StartPage=" + StartPage +
                ", endPage=" + endPage +
                ", pageNo=" + pageNo +
                '}';
    }
}
