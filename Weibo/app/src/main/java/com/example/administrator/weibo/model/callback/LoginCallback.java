package com.example.administrator.weibo.model.callback;

import com.example.administrator.weibo.entity.AccessToken;

/**
 * Created by ZZB on 2016/3/29.
 */
public class LoginCallback {


    public interface GetAccessTokenCallback{
        void onGetTokenSuccess(AccessToken token);
        void onGetTokenFailed(String errorMsg);
    }
}
