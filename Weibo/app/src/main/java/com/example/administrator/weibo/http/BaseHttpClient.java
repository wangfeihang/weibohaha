package com.example.administrator.weibo.http;

import android.os.Handler;
import android.os.Looper;

import com.example.administrator.recyclerviewtest.exception.RequestException;
import com.example.administrator.recyclerviewtest.http.HttpRequest.Method;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Request.Builder;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Map;

/**
 * Created by ZZB on 2016/3/25.
 */
public abstract class BaseHttpClient {
    private OkHttpClient mHttpClient;
    private Handler mMainHandler = new Handler(Looper.getMainLooper());
    private OkHttpClient getClient(){
        if(mHttpClient == null){
            mHttpClient = new OkHttpClient();
        }
        return mHttpClient;
    }
    protected abstract HttpSerializer getHttpSerializer();
    private void post(HttpRequest request, HttpCallback callback){
        //TODO
    }
    private void get(HttpRequest request, final HttpCallback callback){
        if(request == null){
            return;
        }
        StringBuilder url = new StringBuilder(request.getUrl());
        Builder okBuilder = new Builder();
        Map<String, String> headers = request.getHeaders();
        if(headers != null){
            for(String key : headers.keySet()){
                okBuilder.header(key, headers.get(key));
            }
        }
        Map<String, Object> params = request.getParams();
        if(params != null){
            url.append("?");
            for(String key : params.keySet()){
                url.append(key).append("=").append(params.get(key)).append("&");
            }
        }
        okBuilder.url(url.toString());
        Request okRequest = okBuilder.build();
        getClient().newCall(okRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                //TODO
            }

            @Override
            public void onResponse(Response response) throws IOException {
                int statusCode = response.code();
                try {
                    byte[] responseBody = response.body().bytes();
                    final Object result = getHttpSerializer().toObject(callback.clazz, statusCode, responseBody);
                    onSuccess(callback, result);
                } catch (RequestException e) {
                    e.printStackTrace();
                    onError(callback, e);
                }
            }
        });
    }

    private void onError(final HttpCallback callback, final RequestException e) {
        if(callback == null){
            return;
        }
        mMainHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onResponseFailed(0, e.getMessage());
            }
        });
    }

    private void onSuccess(final HttpCallback callback, final Object result) {
        if(callback == null){
            return;
        }
        mMainHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onResponseSuccess(result);
            }
        });
    }

    public void request(HttpRequest request, HttpCallback callback){
        if(request.getMethod() == Method.GET){
            get(request, callback);
        }else if(request.getMethod() == Method.POST){
            post(request, callback);
        }
    }
}
