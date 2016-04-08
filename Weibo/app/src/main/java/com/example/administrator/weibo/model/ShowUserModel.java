package com.example.administrator.weibo.model;

import android.content.Context;
import android.util.Log;

import com.example.administrator.weibo.common.AppConstants.WeiboConfig;
import com.example.administrator.weibo.entity.User;
import com.example.administrator.weibo.http.HttpCallback;
import com.example.administrator.weibo.http.HttpRequest;
import com.example.administrator.weibo.http.HttpRequest.Builder;
import com.example.administrator.weibo.http.HttpRequest.Method;
import com.example.administrator.weibo.http.WeiboHttpClient;
import com.example.administrator.weibo.model.callback.ShowUserCallback;
import com.example.administrator.weibo.utils.SharedPreferencesUtils;
import com.yy.androidlib.util.notification.NotificationCenter;

/**
 * Created by ZZB on 2016/3/29.
 */
public class ShowUserModel {

    private WeiboHttpClient mHttpClient = new WeiboHttpClient();

    public void getUser(Context context,Long uid){
        Log.d("test","有");
        SharedPreferencesUtils sharedPreferencesUtils=new SharedPreferencesUtils(context);
        Builder builder = new Builder();
        builder.url(WeiboConfig.END_POINT + "/2/users/show.json").method(Method.GET).
                addParams("access_token", sharedPreferencesUtils.getToken().getToken()).addParams("uid", uid);
        HttpRequest request = builder.build();
        mHttpClient.request(false,request, new HttpCallback<User>(User.class) {

            @Override
            public void onResponseSuccess(User result) {
                NotificationCenter.INSTANCE.getObserver(ShowUserCallback.GetUserCallback.class).onGetUserSuccess(result);
            }
            @Override
            public void onResponseFailed(int errorCode, String errorMsg) {
                Log.d("test","失败");
                NotificationCenter.INSTANCE.getObserver(ShowUserCallback.GetUserCallback.class).onGetUserFailed(errorMsg);
            }
        });
    }


}
