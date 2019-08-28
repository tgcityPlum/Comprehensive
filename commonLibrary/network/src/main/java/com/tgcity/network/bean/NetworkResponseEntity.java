package com.tgcity.network.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.tgcity.base.utils.LogUtils;

/**
 * @author TGCity
 * 接口请求返回值信息
 */

public class NetworkResponseEntity implements Parcelable {
    /**
     * 发送的信息（GET模式下将为NULL）
     */
    private String send;
    /**
     * 访问消息
     */
    private String message;
    /**
     * 完整地址
     */
    private String url;
    /**
     * 访问代码
     */
    private int code;
    /**
     * 访问地址是否被重定向
     */
    private boolean isRedirect;
    /**
     * 访问是否成功
     */
    private boolean isSuccessful;
    /**
     * 是否为https连接
     */
    private boolean isHttps;
    /**
     * 协议
     */
    private String protocol;
    /**
     * get or post
     */
    private String method;
    /**
     * 头部信息
     */
    private String headers;
    /**
     * 服务器返回内容
     */
    private String content;
    /**
     * 发送时间
     */
    private long sendTime = 0;
    /**
     * 接收时间
     */
    private long receiveTime = 0;

    public String getSend() {
        return send;
    }

    public void setSend(String send) {
        this.send = send;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isHttps() {
        return isHttps;
    }

    public void setHttps(boolean https) {
        isHttps = https;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public static Creator<NetworkResponseEntity> getCREATOR() {
        return CREATOR;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isRedirect() {
        return isRedirect;
    }

    public void setRedirect(boolean redirect) {
        isRedirect = redirect;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }

    public long getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(long receiveTime) {
        this.receiveTime = receiveTime;
    }


    public void print(String intent, boolean isJsonFormate) {
            LogUtils.printFormat(!isJsonFormate, "------------------------------网络请求[" + intent + "]执行完毕,执行日志如下------------------------------" +
                    "\n请求类型:" + method +
                    "\n请求地址:" + url +
                    "\n是否与服务器成功交互:" + isSuccessful +
                    "\n响应代码:" + code +
                    "\n发送的数据:" + this.send +
                    "\n请求耗时:" + (receiveTime - sendTime) +
                    "\n服务器返回的数据:" + (isJsonFormate ? LogUtils.formatJson(content) : content)
            );

    }

    public NetworkResponseEntity() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.send);
        dest.writeString(this.message);
        dest.writeString(this.url);
        dest.writeInt(this.code);
        dest.writeByte(this.isRedirect ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isSuccessful ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isHttps ? (byte) 1 : (byte) 0);
        dest.writeString(this.protocol);
        dest.writeString(this.method);
        dest.writeString(this.headers);
        dest.writeString(this.content);
        dest.writeLong(this.sendTime);
        dest.writeLong(this.receiveTime);
    }

    protected NetworkResponseEntity(Parcel in) {
        this.send = in.readString();
        this.message = in.readString();
        this.url = in.readString();
        this.code = in.readInt();
        this.isRedirect = in.readByte() != 0;
        this.isSuccessful = in.readByte() != 0;
        this.isHttps = in.readByte() != 0;
        this.protocol = in.readString();
        this.method = in.readString();
        this.headers = in.readString();
        this.content = in.readString();
        this.sendTime = in.readLong();
        this.receiveTime = in.readLong();
    }

    public static final Creator<NetworkResponseEntity> CREATOR = new Creator<NetworkResponseEntity>() {
        @Override
        public NetworkResponseEntity createFromParcel(Parcel source) {
            return new NetworkResponseEntity(source);
        }

        @Override
        public NetworkResponseEntity[] newArray(int size) {
            return new NetworkResponseEntity[size];
        }
    };
}
