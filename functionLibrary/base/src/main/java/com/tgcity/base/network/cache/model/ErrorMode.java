package com.tgcity.base.network.cache.model;

/**
 * 作者：TGCity by Administrator on 2018/7/23 09：49
 * 所有请求错误
 */
public enum ErrorMode {

    OVERLOAD("请求过于频繁", "请稍后再试"),
    NO_CACHE("未找到缓存", "请连接网络后重试"),
    NO_NETWORK("没有找到网络", "请检查网络后重试"),
    NO_AUTHORITY("您没有权限查看该数据",null ),

    SINGNATURE_FAILURE_TIME("当前系统时间不正确", "请设置成标准北京时间后重试"),
    SINGNATURE_FAILURE_SSL("证书签名失败", null),

    CONNECT_TIME_OUT("连接超时", "请检查网络后重试"),
    UNKNOWN_HOST("无法连接主机地址", null),

    SERVER_NULL("服务器返回为空", null, 666),
    /*
     * 这个里的message 请勿使用
     * */
    API_VISUALIZATION_MESSAGE("服务器返回错误消息", null),
    API_NO_VISUALIZATION_MESSAGE("接口非可视化消息", null),
    API_OTHER_ERROR("API其他错误", null),
    HTTP_OTHER_ERROR("HTTP其他错误", null),
    DATA_FORMATE_ERROR("数据解析出错", null),
    TYPE_CAST_ERROR("类型转换出错", null);

    private int errorCode;
    private String errorTitle;
    private String errorContent;

    ErrorMode(String errorTitle, String errorContent) {
        this.errorTitle = errorTitle;
        this.errorContent = errorContent;
    }

    public ErrorMode setErrorTitle(String errorTitle) {
        this.errorTitle = errorTitle;
        return this;
    }

    public ErrorMode setErrorContent(String errorContent) {
        this.errorContent = errorContent;
        return this;
    }

    ErrorMode(String errorTitle, String errorContent, int errorCode) {
        this.errorTitle = errorTitle;
        this.errorContent = errorContent;
        this.errorCode = errorCode;
    }

    public String getErrorTitle() {
        return errorTitle;
    }

    public String getErrorContent() {
        return errorContent;
    }

    public String getErrorMessage() {
        return errorTitle + "\n" + getFormateContent();
    }

    protected String getFormateContent() {
        return errorContent == null || "".equals(errorContent) ? "" : "," + errorContent;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
