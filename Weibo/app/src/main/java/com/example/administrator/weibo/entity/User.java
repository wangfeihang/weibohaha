package com.example.administrator.weibo.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/3/30.
 */
public class User implements Serializable {
    private static final long serialVersionUID = -6919461967497580385L;
    @SerializedName("screen_name")
    private String screenName;
    @SerializedName("profile_image_url")
    private String profileImageURL;

    public String getScreenName() {
        return screenName;
    }

    public String getProfileImageURL() {
        return profileImageURL;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public void setProfileImageURL(String profileImageURL) {
        this.profileImageURL = profileImageURL;
    }
}
