package com.tgcity.base.network.bean.response;
/*
*
* 缓存配置模型
*
* */
public class CacheVersionDto {


    /**
     * College : 1
     * Major : 2
     * PCL : 3
     * Other : 4
     * LastModificationTime : 2018-05-10 09:33:11
     * LastModificationUserName : sample string 6
     * Id : 7
     */

    private int College;//院校缓存版本
    private int Major;//专业缓存版本
    private int PCL;//省控线缓存版本
    private int Other;//其它缓存版本
    private String LastModificationTime;//修改时间
    private String LastModificationUserName;//修改人姓名（清数据缓存的时候自动更新）
    private int Id;

    public int getCollege() {
        return College;
    }

    public void setCollege(int College) {
        this.College = College;
    }

    public int getMajor() {
        return Major;
    }

    public void setMajor(int Major) {
        this.Major = Major;
    }

    public int getPCL() {
        return PCL;
    }

    public void setPCL(int PCL) {
        this.PCL = PCL;
    }

    public int getOther() {
        return Other;
    }

    public void setOther(int Other) {
        this.Other = Other;
    }

    public String getLastModificationTime() {
        return LastModificationTime;
    }

    public void setLastModificationTime(String LastModificationTime) {
        this.LastModificationTime = LastModificationTime;
    }

    public String getLastModificationUserName() {
        return LastModificationUserName;
    }

    public void setLastModificationUserName(String LastModificationUserName) {
        this.LastModificationUserName = LastModificationUserName;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
}
