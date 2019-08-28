package com.tgcity.network.bean.result;

/**
 * @author TGCity
 */
public class HttpMessage {

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
