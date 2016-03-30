package com.example.administrator.weibo.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/3/30.
 */
public class User {
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
