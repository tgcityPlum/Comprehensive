package com.tgcity.base.network.bean.response;

/**
 * 
 * @author haiyang.liu@xietong110.com
 * 
 */
public class AccountTokenOutput {

    private String access_Token;
    private int expires_In;
    private String refresh_Token;
    private String scope;
    private String token_Type;

    public String getAccess_Token() {
        return access_Token;
    }

    public void setAccess_Token(String accessToken) {
        this.access_Token = accessToken;
    }

    public int getExpires_In() {
        return expires_In;
    }

    public void setExpires_In(int expiresIn) {
        this.expires_In = expiresIn;
    }

    public String getRefresh_Token() {
        return refresh_Token;
    }

    public void setRefresh_Token(String refreshToken) {
        this.refresh_Token = refreshToken;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getToken_Type() {
        return token_Type;
    }

    public void setToken_Type(String tokenType) {
        this.token_Type = tokenType;
    }

    @Override
    public String toString() {
        return "AccountTokenOutput{" +
                "access_Token='" + access_Token + '\'' +
                ", expires_In=" + expires_In +
                ", refresh_Token='" + refresh_Token + '\'' +
                ", scope='" + scope + '\'' +
                ", token_Type='" + token_Type + '\'' +
                '}';
    }

}
