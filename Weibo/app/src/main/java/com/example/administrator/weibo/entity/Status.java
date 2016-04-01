package com.example.administrator.weibo.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/3/30.
 */
public class Status implements Serializable {
    private static final long serialVersionUID = -6919461967497580385L;
    private String text;
    private String source;
    private User user;
    private Long id;



    @SerializedName("reposts_count")
    private int repostsCount;
    @SerializedName("comments_count")
    private int commentsCount;
    @SerializedName("attitudes_count")
    private int attitudesCount;

    public String getText() {
        return text;
    }

    public String getSource() {
        return source;
    }

    public User getUser() {
        return user;
    }

    public int getRepostsCount() {
        return repostsCount;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public int getAttitudesCount() {
        return attitudesCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRepostsCount(int repostsCount) {
        this.repostsCount = repostsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public void setAttitudesCount(int attitudesCount) {
        this.attitudesCount = attitudesCount;
    }
/*
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(text);
        dest.writeString(source);
        dest.writeValue(user);
        dest.writeInt(repostsCount);
        dest.writeInt(commentsCount);
        dest.writeInt(attitudesCount);
    }
    public static final Parcelable.Creator<Status> CREATOR = new Creator<Status>() {

        @Override
        public Status createFromParcel(Parcel source) {
            Status police = new Status();
            police.id = source.re();
            police.workTime = source.readInt();
            return police;
        }

        @Override
        public Status[] newArray(int size) {
            return new Status[size];
        }
    };
    */
}
