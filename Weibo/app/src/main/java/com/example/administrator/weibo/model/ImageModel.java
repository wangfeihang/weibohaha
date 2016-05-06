package com.example.administrator.weibo.model;

import android.graphics.Bitmap;
import android.widget.ImageButton;

import com.example.administrator.weibo.http.HttpCallback;
import com.example.administrator.weibo.http.HttpRequest;
import com.example.administrator.weibo.http.WeiboHttpClient;
import com.example.administrator.weibo.model.callback.ImageCallback;
import com.yy.androidlib.util.notification.NotificationCenter;

/**
 * Created by Administrator on 2016/4/19.
 */
public class ImageModel {
    private WeiboHttpClient mHttpClient = new WeiboHttpClient();

    public void getImage(String imageURL,final ImageButton imageButton) {
        HttpRequest.Builder builder = new HttpRequest.Builder();
        builder.url(imageURL).method(HttpRequest.Method.GET);
        HttpRequest request = builder.build();
        mHttpClient.request(false,request, new HttpCallback<Bitmap>(Bitmap.class) {

            @Override
            public void onResponseSuccess(Bitmap result) {
                NotificationCenter.INSTANCE.getObserver(ImageCallback.GetImageCallback.class).onGetImageSuccess(result,imageButton);
            }

            @Override
            public void onResponseFailed(int errorCode, String errorMsg) {
                NotificationCenter.INSTANCE.getObserver(ImageCallback.GetImageCallback.class).onGetImageFailed(errorMsg);
            }
        });
    }
}
