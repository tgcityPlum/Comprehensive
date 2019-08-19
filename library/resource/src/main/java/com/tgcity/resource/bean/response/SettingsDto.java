package com.tgcity.resource.bean.response;

/**
 * @author TGCity
 * @create 2019/5/31 14：32
 * @Describe
 */
public class SettingsDto {


    /**
     * id : 5cb581727b4e0d7eec869b1a
     * numId : 2
     * name : Android版本配置信息
     * identification : AndroidVersion
     * description : 用于Android版本配置信息
     * settingsJson : {"NewVersionCode":"111","ForceVersionCode":"222","NewVersionName":"333","Summary":"444","ApkSize":"555","Body":"666","UpdateDate":"888","UpdateUrl":"777"}
     * info : null
     */

    private String id;
    private int numId;
    private String name;
    private String identification;
    private String description;
    private String settingsJson;
    private Object info;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumId() {
        return numId;
    }

    public void setNumId(int numId) {
        this.numId = numId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSettingsJson() {
        return settingsJson;
    }

    public void setSettingsJson(String settingsJson) {
        this.settingsJson = settingsJson;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }
}
