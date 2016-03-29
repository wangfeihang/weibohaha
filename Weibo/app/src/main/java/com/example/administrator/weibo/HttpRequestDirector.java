package com.example.administrator.weibo;

import com.squareup.okhttp.RequestBody;

/**
 * Created by Administrator on 2016/3/24.
 */
public class HttpRequestDirector {
    /* 持有当前需要使用的建造器对象
    */
    private HttpRequestBuilder builder;
    public HttpRequestDirector(HttpRequestBuilder builder){
        this.builder = builder;
    }
     public void construct(String url,RequestBody requestBody,HttpUtils.Callback mcallback){
         builder.buildClient();
         builder.buildRequest(url,requestBody);
         builder.bulidCallback(mcallback);
     }
}
