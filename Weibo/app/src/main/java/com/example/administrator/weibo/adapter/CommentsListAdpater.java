package com.example.administrator.weibo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.administrator.weibo.R;
import com.example.administrator.weibo.entity.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/30.
 */
public class CommentsListAdpater extends MyBaseAdapter<Comment> {

    private List<Comment> mCommentsList;
    private Context mContext;
    private ImageButton imAvatar;
    private TextView tvUserName;
    private TextView tvDate;
    private TextView tvText;
    private ImageButton imLike;
    private TextView tvLikeCount;


    public CommentsListAdpater(Context context, OnItemClickListener onClickListener,
                               OnItemLongClickListener onLongClickListener) {
        this.mCommentsList=new ArrayList<Comment>();
        this.mContext = context;
        this.mOnClickListener = onClickListener;
        this.mOnLongClickListener = onLongClickListener;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_commentslist, parent, false);
        MyViewHolder vh = new MyViewHolder(v, mOnClickListener,
                mOnLongClickListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        View v=holder.v;
        initViews(v);
        initData(mCommentsList, position);
    }

    @Override
    public int getItemCount() {
        return mCommentsList.size();
    }

    @Override
    public void addData(List<Comment> datalist) {
        mCommentsList.addAll(datalist);
    }


    private void initViews(View v) {
        imAvatar=(ImageButton)v.findViewById(R.id.im_avatar);
        tvUserName=(TextView)v.findViewById(R.id.tv_username);
        tvDate=(TextView)v.findViewById(R.id.tv_date);
        tvText=(TextView)v.findViewById(R.id.tv_text);
        imLike=(ImageButton)v.findViewById(R.id.im_like);
        tvLikeCount=(TextView)v.findViewById(R.id.tv_likecount);
    }
    private void initData(List<Comment> statusList,int position) {
        tvUserName.setText(statusList.get(position).getUser().getScreenName());
        tvDate.setText(statusList.get(position).getDate());
        tvText.setText(statusList.get(position).getText());
    }
    public void setData(List<Comment> commentsList) {
     //   Log.d("statusList.size",String.format("%d",statusList.size()));
        this.mCommentsList=commentsList;
    }
}