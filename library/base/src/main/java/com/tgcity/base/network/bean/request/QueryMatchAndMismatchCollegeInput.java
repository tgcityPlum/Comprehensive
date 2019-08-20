package com.tgcity.base.network.bean.request;

import java.io.Serializable;

public class QueryMatchAndMismatchCollegeInput implements Serializable {

    private int provinceId;//省份
    private int year;//选科年份
    private String subject;//选择科目
    private String majorCode;//专业码
    private int matchPageIndex;//匹配的查询页码
    private int matchPageSize;//匹配的单页显示条数
    private int mismatchPageIndex;//不匹配的查询页码
    private int mismatchPageSize;//不匹配的单页显示条数

    public QueryMatchAndMismatchCollegeInput() {
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMajorCode() {
        return majorCode;
    }

    public void setMajorCode(String majorCode) {
        this.majorCode = majorCode;
    }

    public int getMatchPageIndex() {
        return matchPageIndex;
    }

    public void setMatchPageIndex(int matchPageIndex) {
        this.matchPageIndex = matchPageIndex;
    }

    public int getMatchPageSize() {
        return matchPageSize;
    }

    public void setMatchPageSize(int matchPageSize) {
        this.matchPageSize = matchPageSize;
    }

    public int getMismatchPageIndex() {
        return mismatchPageIndex;
    }

    public void setMismatchPageIndex(int mismatchPageIndex) {
        this.mismatchPageIndex = mismatchPageIndex;
    }

    public int getMismatchPageSize() {
        return mismatchPageSize;
    }

    public void setMismatchPageSize(int mismatchPageSize) {
        this.mismatchPageSize = mismatchPageSize;
    }

    @Override
    public String toString() {
        return "QueryMatchAndMismatchCollegeInput{" +
                "provinceId=" + provinceId +
                ", year=" + year +
                ", subject='" + subject + '\'' +
                ", majorCode='" + majorCode + '\'' +
                ", matchPageIndex=" + matchPageIndex +
                ", matchPageSize=" + matchPageSize +
                ", mismatchPageIndex=" + mismatchPageIndex +
                ", mismatchPageSize=" + mismatchPageSize +
                '}';
    }
}
