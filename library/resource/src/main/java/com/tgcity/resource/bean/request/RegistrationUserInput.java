package com.tgcity.resource.bean.request;

public class RegistrationUserInput {
    /**
     * mobile : string
     * password : string
     * mobileAuthCode : string
     * sourceType : 1
     * accountType : 1
     * platform : 1
     * device : 1
     * appVersion : string
     * sourceSign : string
     * appChannel : string
     */

    private String mobile;//用户手机号
    private String password;//密码
    private String mobileAuthCode;//手机验证码
    private int sourceType;//注册来源 1=代理商 2=线上自营 3=控制台 4=运营活动 5=分销 6=公众号 7=门店 8=外部合作 9=其它
    private int accountType;//账户类型 1=手机 3=会员卡 4=微信 5=QQ 6=微博
    private int platform;//注册平台 1=xxxPC 2=xxxAPP 3=微信填报助手 4=百度小程序 5=xxxWAP
    private int device;//注册设备 1=PC; 2=安卓移动设备; 3=苹果移动设备; 4=其它;5=H5 ;6=小程序
    private String appVersion;//应用版本
    private String sourceSign;//UFS（用户来源）标记 ，这里填空
    private String appChannel;//应用渠道

    public RegistrationUserInput(String mobile, String password, String mobileAuthCode, int sourceType, int accountType, int platform, int device, String appVersion, String sourceSign, String appChannel) {
        this.mobile = mobile;
        this.password = password;
        this.mobileAuthCode = mobileAuthCode;
        this.sourceType = sourceType;
        this.accountType = accountType;
        this.platform = platform;
        this.device = device;
        this.appVersion = appVersion;
        this.sourceSign = sourceSign;
        this.appChannel = appChannel;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileAuthCode() {
        return mobileAuthCode;
    }

    public void setMobileAuthCode(String mobileAuthCode) {
        this.mobileAuthCode = mobileAuthCode;
    }

    public int getSourceType() {
        return sourceType;
    }

    public void setSourceType(int sourceType) {
        this.sourceType = sourceType;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }

    public int getDevice() {
        return device;
    }

    public void setDevice(int device) {
        this.device = device;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getSourceSign() {
        return sourceSign;
    }

    public void setSourceSign(String sourceSign) {
        this.sourceSign = sourceSign;
    }

    public String getAppChannel() {
        return appChannel;
    }

    public void setAppChannel(String appChannel) {
        this.appChannel = appChannel;
    }

    @Override
    public String toString() {
        return "RegistrationUserInput{" +
                "mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                ", mobileAuthCode='" + mobileAuthCode + '\'' +
                ", sourceType=" + sourceType +
                ", accountType=" + accountType +
                ", platform=" + platform +
                ", device=" + device +
                ", appVersion='" + appVersion + '\'' +
                ", sourceSign='" + sourceSign + '\'' +
                ", appChannel='" + appChannel + '\'' +
                '}';
    }
}
