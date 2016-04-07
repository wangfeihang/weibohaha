package com.example.administrator.weibo.http;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Request.Builder;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;
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
    private void post(HttpRequest request, HttpCallback callback,boolean isList){
        if(request == null){
            return;
        }
        FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
        Map<String, Object> params = request.getParams();
        if(params != null){
            for(String key : params.keySet()){
                formEncodingBuilder.add(key, params.get(key).toString());
            }
        }
        RequestBody formBody = formEncodingBuilder.build();
        Request okRequest = new Request.Builder()
                .url(request.getUrl()).post(formBody).build();
        getClient().newCall(okRequest).enqueue(getCallback(callback,isList));
    }
    private void get(HttpRequest request, final HttpCallback callback,boolean isList){
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
        getClient().newCall(okRequest).enqueue(getCallback(callback,isList));
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
    private void onSuccessList(final HttpCallback callback, final List resultList) {
        if(callback == null){
            return;
        }
        mMainHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onResponseListSuccess(resultList);
            }
        });
    }
    public void request(boolean isList,HttpRequest request, HttpCallback callback){
        if(request.getMethod() == HttpRequest.Method.GET){
            get(request, callback,isList);
        }else if(request.getMethod() == HttpRequest.Method.POST){
            post(request, callback,isList);
        }
    }

    private Callback getCallback(final HttpCallback callback, final boolean isList){
        return new Callback(){
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                int statusCode = response.code();
                try {
                    byte[] responseBody = response.body().bytes();
                    if(isList){
                        Log.d("test4","responseBody:"+1);
                        final List<Object> resultList=getHttpSerializer().toObjectList(callback.clazz, statusCode, responseBody);

                        onSuccessList(callback,resultList);
                    }
                    else {
                        final Object result = getHttpSerializer().toObject(callback.clazz, statusCode, responseBody);
                        onSuccess(callback, result);
                    }

                } catch (RequestException e) {
                    e.printStackTrace();
                    onError(callback, e);
                }
            }
        };
    }

}
