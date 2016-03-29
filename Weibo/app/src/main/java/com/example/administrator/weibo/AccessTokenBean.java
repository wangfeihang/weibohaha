package com.example.administrator.weibo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/3/28.
 */
public class AccessTokenBean {
    /*用户授权的唯一票据，用于调用微博的开放接口，
    同时也是第三方应用验证微博用户登录的唯一票据，
    第三方应用应该用该票据和自己应用内的用户建立
    唯一影射关系，来识别登录状态，不能使用本返回
    值里的UID字段来做登录识别。*/
    @SerializedName("access_token")
    private String accessToken;
    //access_token的生命周期，单位是秒数。
    @SerializedName("expires_in")
    private String lifeCycle;
    //access_token的生命周期（该参数即将废弃，开发者请使用expires_in）。
    @SerializedName("remind_in")
    private String lifeCycle2;
    /*授权用户的UID，本字段只是为了方便开发者，
     减少一次user/show接口调用而返回的，第
     三方应用不能用此字段作为用户登录状态的
     识别，只有access_token才是用户授权的唯
     一票据。*/
    private String uid;

    public String getAccessToken() {
        return accessToken;
    }

    public String getUid() {
        return uid;
    }

    public String getLifeCycle() {
        return lifeCycle;
    }

    public String getLifeCycle2() {
        return lifeCycle2;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setLifeCycle2(String lifeCycle2) {
        this.lifeCycle2 = lifeCycle2;
    }

    public void setLifeCycle(String lifeCycle) {
        this.lifeCycle = lifeCycle;
    }
}
