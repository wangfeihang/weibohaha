package com.example.administrator.weibo.model.callback;

import com.example.administrator.weibo.entity.User;

/**
 * Created by ZZB on 2016/3/29.
 */
public class ShowUserCallback {


    public interface GetUserCallback{
        void onGetUserSuccess(User user);
        void onGetUserFailed(String errorMsg);
    }
}
