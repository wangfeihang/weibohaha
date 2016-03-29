package com.example.administrator.weibo;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

/**
 * Created by Administrator on 2016/3/24.
 */
public class HttpRequestProduct {
    private OkHttpClient client;
    private Request request;
    private  com.squareup.okhttp.Callback Callback;

    public com.squareup.okhttp.Callback getCallback() {
        return Callback;
    }

    public void setCallback(com.squareup.okhttp.Callback callback) {
        Callback = callback;
    }

    public OkHttpClient getClient() {
        return client;
    }

    public Request getRequest() {
        return request;
    }

    public void setClient(OkHttpClient client) {
        this.client = client;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}
