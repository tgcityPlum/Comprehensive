package com.tgcity.base.network.bean.response;

public class SettingsDto {

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

    @Override
    public String toString() {
        return "SettingsDto{" +
                "id='" + id + '\'' +
                ", numId=" + numId +
                ", name='" + name + '\'' +
                ", identification='" + identification + '\'' +
                ", description='" + description + '\'' +
                ", settingsJson='" + settingsJson + '\'' +
                ", info=" + info +
                '}';
    }
}
