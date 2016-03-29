package com.example.administrator.weibo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/3/29.
 */
public class TokenInfoBean {


    private String uid;
    @SerializedName("appkey")
    private String appKey;
    private String scope;
    @SerializedName("create_at")
    private String createat;
    @SerializedName("expire_in")
    private String lifeCycle;

    public String getUid() {
        return uid;
    }

    public String getAppKey() {
        return appKey;
    }

    public String getScope() {
        return scope;
    }

    public String getCreateat() {
        return createat;
    }

    public String getLifeCycle() {
        return lifeCycle;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public void setCreateat(String createat) {
        this.createat = createat;
    }

    public void setLifeCycle(String lifeCycle) {
        this.lifeCycle = lifeCycle;
    }
}
