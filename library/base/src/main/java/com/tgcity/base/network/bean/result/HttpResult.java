package com.tgcity.base.network.bean.result;

/**
 * 服务器返回的基础类，此处进行拆分
 * @param <T> 核心的类，支持List
 */
public class HttpResult<T> {

    private T data;
    private int errorCode;
    private String message;

    public T getResults() {
        return data;
    }

    public int getCode() {
        return errorCode;
    }

    public void setCode(int Code) {
        this.errorCode = Code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                " data=" + data +
                ", Code=" + errorCode +
                ", Message='" + message + '\'' +
                '}';
    }
}
