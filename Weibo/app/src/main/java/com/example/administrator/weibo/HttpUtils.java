package com.example.administrator.weibo;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;

import java.util.List;

/**
 * Created by Administrator on 2016/3/22.
 */
public class HttpUtils {

    public static RequestBody getRequestBody(List<String> name,List<String> vaule)
    {
        FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
        for(int i=0;i<name.size();i++)
            formEncodingBuilder.add(name.get(i), vaule.get(i));
        RequestBody requestBody=formEncodingBuilder.build();
        return requestBody;
    }
    public static RequestBody getRequestBody(String name,String  vaule)
    {
        FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
        formEncodingBuilder.add(name, vaule);
        RequestBody requestBody=formEncodingBuilder.build();
        return requestBody;
    }


    public interface Callback{
        void onRequestSuccess(String json);
    }
    public static void request(String url, RequestBody requestBody,final Callback mcallback){

        HttpRequestBuilder httpRequestBuilder = new HttpRequestConcreteBuilder();
        HttpRequestDirector httpRequestDirector = new HttpRequestDirector(httpRequestBuilder);
        httpRequestDirector.construct(url, requestBody,mcallback);
        HttpRequestProduct httpRequestProduct = httpRequestBuilder.retrieveResult();

        httpRequestProduct.getClient().newCall(httpRequestProduct.getRequest()).enqueue(httpRequestProduct.getCallback());
    }
}

