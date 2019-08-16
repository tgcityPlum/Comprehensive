package com.tgcity.common.bean.response;

public class UserIdDto {
    /**
     * id : string
     * numId : 0
     */

    private String id;//用户ObjectId
    private int numId;//用户NumId

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
}
