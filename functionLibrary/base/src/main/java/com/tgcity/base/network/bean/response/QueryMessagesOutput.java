package com.tgcity.base.network.bean.response;

/**
 * @author TGCity
 * 个人中心--根据类型获取消息列表的数据模型
 */
public class QueryMessagesOutput {

    private String id;
    private int numId;
    private String title;
    private String content;
    private String link;
    private String remark;
    private int receiverId;
    private boolean isRead;
    private String creationTime;
    private String creationTimeFormat;
    private int foreignKeyId;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public boolean isIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getCreationTimeFormat() {
        return creationTimeFormat;
    }

    public void setCreationTimeFormat(String creationTimeFormat) {
        this.creationTimeFormat = creationTimeFormat;
    }

    public int getForeignKeyId() {
        return foreignKeyId;
    }

    public void setForeignKeyId(int foreignKeyId) {
        this.foreignKeyId = foreignKeyId;
    }

    @Override
    public String toString() {
        return "QueryMessagesOutput{" +
                "id='" + id + '\'' +
                ", numId=" + numId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", link='" + link + '\'' +
                ", remark='" + remark + '\'' +
                ", receiverId=" + receiverId +
                ", isRead=" + isRead +
                ", creationTime='" + creationTime + '\'' +
                ", creationTimeFormat='" + creationTimeFormat + '\'' +
                ", foreignKeyId=" + foreignKeyId +
                '}';
    }
}
