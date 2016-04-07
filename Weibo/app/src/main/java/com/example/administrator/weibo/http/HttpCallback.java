package com.example.administrator.weibo.http;

import java.util.List;

/**
 * Created by ZZB on 2016/3/26.
 */
public abstract class HttpCallback<T> {
    public Class clazz;
    public HttpCallback(Class cls){
        clazz = cls;
    }

    public abstract void onResponseSuccess(T result);
    public abstract void onResponseListSuccess(List<T> resultList);

    public abstract void onResponseFailed(int errorCode, String errorMsg);
}
