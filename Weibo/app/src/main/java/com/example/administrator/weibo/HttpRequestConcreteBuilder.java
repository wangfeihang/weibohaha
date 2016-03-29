package com.example.administrator.weibo;

import android.os.Looper;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Administrator on 2016/3/24.
 */
public class HttpRequestConcreteBuilder implements HttpRequestBuilder {

    private HttpRequestProduct httpRequestProduct = new HttpRequestProduct();

    @Override
    public void buildClient() {
        OkHttpClient client = new OkHttpClient();
        httpRequestProduct.setClient(client);
    }

    @Override
    public void buildRequest(String url,RequestBody requestBody) {
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        httpRequestProduct.setRequest(request);
    }
    @Override
    public void bulidCallback(final HttpUtils.Callback mcallback)
    {


        com.squareup.okhttp.Callback callback=new com.squareup.okhttp.Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }
                final String result = response.body().string() + "";
                Looper.prepare();
                mcallback.onRequestSuccess(result);
                Looper.loop();

            }
        };
        httpRequestProduct.setCallback(callback);
    }
    @Override
    public HttpRequestProduct retrieveResult() {
        return httpRequestProduct;
    }
}
