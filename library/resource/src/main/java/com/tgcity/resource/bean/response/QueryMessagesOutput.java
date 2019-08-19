package com.tgcity.resource.bean.response;

/**
 * 个人中心--根据类型获取消息列表的数据模型
 */
public class QueryMessagesOutput {

    //系统通知， 资讯推送 ，社区消息返回字段
    private String id;//ObjectId
    private int numId;//NumId
    private String title;//消息标题
    private String content;//消息内容
    private String link;//链接（如需要跳转页面时使用）
    private String remark;//备注（特殊情况下使用，自定义参数列表）
    private int receiverId;//接收者Id
    private boolean isRead;//是否已读
    private String creationTime;//创建时间
    private String creationTimeFormat;//时间格式化
    private int foreignKeyId;//项目ID

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
}
