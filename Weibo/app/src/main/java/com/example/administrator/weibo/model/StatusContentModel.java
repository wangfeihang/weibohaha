package com.example.administrator.weibo.model;

import android.content.Context;
import android.util.Log;

import com.example.administrator.weibo.common.AppConstants.WeiboConfig;
import com.example.administrator.weibo.entity.CommentsList;
import com.example.administrator.weibo.entity.Status;
import com.example.administrator.weibo.http.HttpCallback;
import com.example.administrator.weibo.http.HttpRequest;
import com.example.administrator.weibo.http.HttpRequest.Builder;
import com.example.administrator.weibo.http.HttpRequest.Method;
import com.example.administrator.weibo.http.WeiboHttpClient;
import com.example.administrator.weibo.model.callback.CommentsListCallback;
import com.example.administrator.weibo.utils.SharedPreferencesUtils;
import com.yy.androidlib.util.notification.NotificationCenter;

/**
 * Created by ZZB on 2016/3/29.
 */
public class StatusContentModel {

    private WeiboHttpClient mHttpClient = new WeiboHttpClient();

    public void getCommentsList(Context context,Status status){
        Log.d("test","有");
        SharedPreferencesUtils sharedPreferencesUtils=new SharedPreferencesUtils(context);
        Builder builder = new Builder();
        builder.url(WeiboConfig.END_POINT + "/2/comments/show.json").method(Method.GET).
                addParams("access_token", sharedPreferencesUtils.getToken().getToken()).addParams("id", status.getId());
        Log.d("test", String.valueOf(sharedPreferencesUtils.getToken()));
        HttpRequest request = builder.build();
        Log.d("test", String.valueOf(status.getId()));
        mHttpClient.request(false,request, new HttpCallback<CommentsList>(CommentsList.class) {

            @Override
            public void onResponseSuccess(CommentsList result) {
                Log.d("test","成功");
                Log.d("test", String.valueOf(result.getmCommentList().size()));
                NotificationCenter.INSTANCE.getObserver(CommentsListCallback.GetCommentsListCallback.class).onGetCommentsListSuccess(result);
            }
            @Override
            public void onResponseFailed(int errorCode, String errorMsg) {
                Log.d("test","失败");
                NotificationCenter.INSTANCE.getObserver(CommentsListCallback.GetCommentsListCallback.class).onGetCommentsListFailed(errorMsg);
            }
        });
    }


}
