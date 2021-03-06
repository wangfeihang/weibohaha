package com.example.administrator.weibo.model;

import com.example.administrator.weibo.common.AppConstants;
import com.example.administrator.weibo.entity.StatusList;
import com.example.administrator.weibo.http.HttpCallback;
import com.example.administrator.weibo.http.HttpRequest;
import com.example.administrator.weibo.http.WeiboHttpClient;
import com.example.administrator.weibo.model.callback.StatusListCallback;
import com.yy.androidlib.util.notification.NotificationCenter;

/**
 * Created by Administrator on 2016/3/30.
 */
public class StatusListModel {
    private WeiboHttpClient mHttpClient = new WeiboHttpClient();

    public void getStatusList(String token,int count, final String caller,int page) {
        HttpRequest.Builder builder = new HttpRequest.Builder();
        builder.url(AppConstants.WeiboConfig.END_POINT + "/2/statuses/friends_timeline.json").method(HttpRequest.Method.GET).
                addParams("access_token", token).addParams("count", count).addParams("page",page);
        HttpRequest request = builder.build();
        mHttpClient.request(false,request, new HttpCallback<StatusList>(StatusList.class) {
            @Override
            public void onResponseSuccess(StatusList result) {
                NotificationCenter.INSTANCE.getObserver(StatusListCallback.GetStatusListCallback.class).onGetStatusListSuccess(result,caller);
            }
            @Override
            public void onResponseFailed(int errorCode, String errorMsg) {
                NotificationCenter.INSTANCE.getObserver(StatusListCallback.GetStatusListCallback.class).onGetStatusListFailed(errorMsg);
            }
        });
    }


}
