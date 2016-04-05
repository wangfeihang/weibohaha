package com.example.administrator.weibo.model;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.example.administrator.weibo.activity.StatusContentActivity;
import com.example.administrator.weibo.common.AppConstants;
import com.example.administrator.weibo.entity.Status;
import com.example.administrator.weibo.entity.StatusList;
import com.example.administrator.weibo.http.HttpCallback;
import com.example.administrator.weibo.http.HttpRequest;
import com.example.administrator.weibo.http.WeiboHttpClient;
import com.example.administrator.weibo.model.callback.StatusListCallback;
import com.yy.androidlib.util.notification.NotificationCenter;

import java.util.List;

/**
 * Created by Administrator on 2016/3/30.
 */
public class StatusListModel {
    private WeiboHttpClient mHttpClient = new WeiboHttpClient();

    public void getStatusList(String token) {
        HttpRequest.Builder builder = new HttpRequest.Builder();
        builder.url(AppConstants.WeiboConfig.END_POINT + "/2/statuses/friends_timeline.json").method(HttpRequest.Method.GET).
                addParams("access_token", token).addParams("count",15);
        HttpRequest request = builder.build();
        mHttpClient.request(request, new HttpCallback<StatusList>(StatusList.class) {
            @Override
            public void onResponseSuccess(StatusList result) {
                Log.d("test1", String.format("%d",result.getStatuses().size()));
                NotificationCenter.INSTANCE.getObserver(StatusListCallback.GetStatusListCallback.class).onGetStatusListSuccess(result);
            }

            @Override
            public void onResponseFailed(int errorCode, String errorMsg) {
                NotificationCenter.INSTANCE.getObserver(StatusListCallback.GetStatusListCallback.class).onGetStatusListFailed(errorMsg);
            }
        });
    }
    public void onClick(View v, List<Status> mStatusList,Context context)
    {
        int position=(int)v.getTag();
        Status status=mStatusList.get(position);
        StatusContentActivity.launch(context, status);

    }

}
