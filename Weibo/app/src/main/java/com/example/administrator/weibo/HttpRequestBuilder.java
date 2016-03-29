package com.example.administrator.weibo;

import com.squareup.okhttp.RequestBody;

/**
 * Created by Administrator on 2016/3/24.
 */
public interface HttpRequestBuilder {
    public void buildClient();
    public void buildRequest(String url,RequestBody requestBody);
    public void bulidCallback(HttpUtils.Callback mcallback);
    public HttpRequestProduct retrieveResult();
}
