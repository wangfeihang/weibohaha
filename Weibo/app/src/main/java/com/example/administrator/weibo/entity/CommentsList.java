package com.example.administrator.weibo.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016/4/1.
 */
public class CommentsList {
    @SerializedName("comments")
    List<Comment> mCommentList;

    public List<Comment> getmCommentList() {
        return mCommentList;
    }

    public void setmCommentList(List<Comment> mCommentList) {
        this.mCommentList = mCommentList;
    }
}
