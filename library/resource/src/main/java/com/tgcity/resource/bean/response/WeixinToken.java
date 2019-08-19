package com.tgcity.resource.bean.response;

/**
 * Created by Administrator on 2018/9/11.
 */

public class WeixinToken {
    /**
     * access_token : 13_-ddddddddddddd-ddddddddd-ddddddddd-dddddddddd-5p-dddddddddddddd-ZUqJ-ddddddddddd
     * expires_in : 7200
     */

    private String access_token;
    private int expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    @Override
    public String toString() {
        return "WeixinToken{" +
                "access_token='" + access_token + '\'' +
                ", expires_in=" + expires_in +
                '}';
    }
}
