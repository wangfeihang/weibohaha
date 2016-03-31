package com.example.administrator.weibo.model;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.administrator.weibo.activity.StatusListActivity;
import com.example.administrator.weibo.common.AppConstants.WeiboConfig;
import com.example.administrator.weibo.entity.AccessToken;
import com.example.administrator.weibo.http.HttpCallback;
import com.example.administrator.weibo.http.HttpRequest;
import com.example.administrator.weibo.http.HttpRequest.Builder;
import com.example.administrator.weibo.http.HttpRequest.Method;
import com.example.administrator.weibo.http.WeiboHttpClient;
import com.example.administrator.weibo.model.callback.LoginCallback;
import com.yy.androidlib.util.notification.NotificationCenter;

/**
 * Created by ZZB on 2016/3/29.
 */
public class LoginModel {

    private WeiboHttpClient mHttpClient = new WeiboHttpClient();

    public void getAccessToken(String code){
        HttpRequest.Builder builder = new Builder();
        builder.url(WeiboConfig.END_POINT + "/oauth2/access_token").method(Method.POST).
                addParams("client_id", WeiboConfig.APP_KEY).addParams("client_secret", WeiboConfig.APP_SECRET).
                addParams("grant_type", "authorization_code").addParams("code", code).addParams("redirect_uri", WeiboConfig.REDIRECT_URL);
        HttpRequest request = builder.build();
        mHttpClient.request(request, new HttpCallback<AccessToken>(AccessToken.class) {
            @Override
            public void onResponseSuccess(AccessToken result) {
                Log.d("test", result.toString());
                NotificationCenter.INSTANCE.getObserver(LoginCallback.GetAccessTokenCallback.class).onGetTokenSuccess(result);
            }

            @Override
            public void onResponseFailed(int errorCode, String errorMsg) {
                NotificationCenter.INSTANCE.getObserver(LoginCallback.GetAccessTokenCallback.class).onGetTokenFailed(errorMsg);
            }
        });
    }
    public void gotoStatusListActivity(Context mContext)
    {
        Intent mintent = new Intent();
        mintent.setClass(mContext , StatusListActivity.class );
        mContext.startActivity(mintent);

    }
}
