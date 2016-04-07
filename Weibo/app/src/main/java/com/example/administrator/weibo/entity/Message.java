package com.example.administrator.weibo.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/4/6.
 */
public class Message {
    @SerializedName("sender_screen_name")
    private String senderScreenName;
    @SerializedName("recipient_screen_name")
    private String recipientScreenName;
    @SerializedName("created_at")
    private String time;
    private String text;

    public String getSenderScreenName() {
        return senderScreenName;
    }

    public String getRecipientScreenName() {
        return recipientScreenName;
    }

    public String getTime() {
        return time;
    }

    public String getText() {
        return text;
    }

    public void setSenderScreenName(String senderScreenName) {
        this.senderScreenName = senderScreenName;
    }

    public void setRecipientScreenName(String recipientScreenName) {
        this.recipientScreenName = recipientScreenName;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setText(String text) {
        this.text = text;
    }
}
