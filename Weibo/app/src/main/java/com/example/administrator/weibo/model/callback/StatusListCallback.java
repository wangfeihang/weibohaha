package com.example.administrator.weibo.model.callback;

import com.example.administrator.weibo.entity.StatusList;

/**
 * Created by ZZB on 2016/3/29.
 */
public class StatusListCallback {


    public interface GetStatusListCallback{
        void onGetStatusListSuccess(StatusList statusList);
        void onGetStatusListFailed(String errorMsg);
    }
}
