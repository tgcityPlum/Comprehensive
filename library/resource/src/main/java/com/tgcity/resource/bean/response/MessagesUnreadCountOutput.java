package com.tgcity.resource.bean.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class MessagesUnreadCountOutput implements Parcelable {

    private int totalCount;//未读消息总数
    private List<GroupMessageInfoBean> groupMessageInfo;//最近一条未读消息

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<GroupMessageInfoBean> getGroupMessageInfo() {
        return groupMessageInfo;
    }

    public void setGroupMessageInfo(List<GroupMessageInfoBean> groupMessageInfo) {
        this.groupMessageInfo = groupMessageInfo;
    }

    public static class GroupMessageInfoBean implements Parcelable {

        private int type;//消息类型（0=系统消息，1=资讯推送，2=社区消息）
        private int unreadCount;//未读条数
        private String description;//最新一条消息内容
        private String creationTime;//最新一条消息发布时间

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getUnreadCount() {
            return unreadCount;
        }

        public void setUnreadCount(int unreadCount) {
            this.unreadCount = unreadCount;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCreationTime() {
            return creationTime;
        }

        public void setCreationTime(String creationTime) {
            this.creationTime = creationTime;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.type);
            dest.writeInt(this.unreadCount);
            dest.writeString(this.description);
            dest.writeString(this.creationTime);
        }

        public GroupMessageInfoBean() {
        }

        protected GroupMessageInfoBean(Parcel in) {
            this.type = in.readInt();
            this.unreadCount = in.readInt();
            this.description = in.readString();
            this.creationTime = in.readString();
        }

        public static final Creator<GroupMessageInfoBean> CREATOR = new Creator<GroupMessageInfoBean>() {
            @Override
            public GroupMessageInfoBean createFromParcel(Parcel source) {
                return new GroupMessageInfoBean(source);
            }

            @Override
            public GroupMessageInfoBean[] newArray(int size) {
                return new GroupMessageInfoBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.totalCount);
        dest.writeList(this.groupMessageInfo);
    }

    public MessagesUnreadCountOutput() {
    }

    protected MessagesUnreadCountOutput(Parcel in) {
        this.totalCount = in.readInt();
        this.groupMessageInfo = new ArrayList<GroupMessageInfoBean>();
        in.readList(this.groupMessageInfo, GroupMessageInfoBean.class.getClassLoader());
    }

    public static final Creator<MessagesUnreadCountOutput> CREATOR = new Creator<MessagesUnreadCountOutput>() {
        @Override
        public MessagesUnreadCountOutput createFromParcel(Parcel source) {
            return new MessagesUnreadCountOutput(source);
        }

        @Override
        public MessagesUnreadCountOutput[] newArray(int size) {
            return new MessagesUnreadCountOutput[size];
        }
    };
}
