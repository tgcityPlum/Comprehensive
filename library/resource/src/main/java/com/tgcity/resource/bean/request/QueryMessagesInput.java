package com.tgcity.resource.bean.request;

public class QueryMessagesInput {

    private int userNumId;//用户NumId
    private int type;//消息类型（0=系统消息，1=资讯推送，2=社区消息）
    private int pageIndex;//当前页
    private int pageSize;//多少条

    public int getUserNumId() {
        return userNumId;
    }

    public void setUserNumId(int userNumId) {
        this.userNumId = userNumId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "QueryMessagesInput{" +
                "userNumId=" + userNumId +
                ", type=" + type +
                ", pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                '}';
    }
}
