package com.example.administrator.weibo.http;

/**
 * Created by ZZB on 2016/3/29.
 */
public class WeiboHttpClient extends BaseHttpClient {

    @Override
    protected HttpSerializer getHttpSerializer() {
        return new WeiboHttpSerializer();
    }
}
