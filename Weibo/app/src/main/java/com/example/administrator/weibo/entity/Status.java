package com.example.administrator.weibo.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/3/30.
 */
public class Status {
    private String text;
    private String source;
    private User user;

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
}
