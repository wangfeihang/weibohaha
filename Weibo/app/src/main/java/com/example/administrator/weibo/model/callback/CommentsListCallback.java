package com.example.administrator.weibo.model.callback;

import com.example.administrator.weibo.entity.CommentsList;

/**
 * Created by ZZB on 2016/3/29.
 */
public class CommentsListCallback {


    public interface GetCommentsListCallback{
        void onGetCommentsListSuccess(CommentsList commentsList);
        void onGetCommentsListFailed(String errorMsg);
    }
}
