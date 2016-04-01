package com.example.administrator.weibo.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/4/1.
 */
public class Comment {
    @SerializedName("created_at")
    private String date;
    private Long id;
    private String text;
    private User user;

    public String getDate() {
        return date;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public User getUser() {
        return user;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
