package com.tgcity.common.bean.response.result;

public class HttpMessage {

    /**
     * Code : 0
     * Message : sample string 1
     * Timestamp : 2016-10-10T15:52:45.9089248+08:00
     */

    private int Code;
    private String Message;
    private String Timestamp;

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public String getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(String Timestamp) {
        this.Timestamp = Timestamp;
    }

    @Override
    public String toString() {
        return "HttpMessage{" +
                "Code=" + Code +
                ", Message='" + Message + '\'' +
                ", Timestamp='" + Timestamp + '\'' +
                '}';
    }
}
