package com.tgcity.common.bean.response;

import java.util.List;

public class TestDataItemBean {
    private long appId;
    private String appIdentify;
    private String appName;
    private String icon;
    private Object link;
    private int sequence;
    private boolean isCloudApp;
    private long classification;
    private List<Long> classificationList;

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }

    public String getAppIdentify() {
        return appIdentify;
    }

    public void setAppIdentify(String appIdentify) {
        this.appIdentify = appIdentify;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Object getLink() {
        return link;
    }

    public void setLink(Object link) {
        this.link = link;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public boolean isIsCloudApp() {
        return isCloudApp;
    }

    public void setIsCloudApp(boolean isCloudApp) {
        this.isCloudApp = isCloudApp;
    }

    public long getClassification() {
        return classification;
    }

    public void setClassification(long classification) {
        this.classification = classification;
    }

    public List<Long> getClassificationList() {
        return classificationList;
    }

    public void setClassificationList(List<Long> classificationList) {
        this.classificationList = classificationList;
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "appId=" + appId +
                ", appIdentify='" + appIdentify + '\'' +
                ", appName='" + appName + '\'' +
                ", icon='" + icon + '\'' +
                ", link=" + link +
                ", sequence=" + sequence +
                ", isCloudApp=" + isCloudApp +
                ", classification=" + classification +
                ", classificationList=" + classificationList +
                '}';
    }
}
