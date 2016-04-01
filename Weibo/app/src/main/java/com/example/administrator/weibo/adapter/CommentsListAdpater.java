package com.example.administrator.weibo.adapter;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.administrator.weibo.R;
import com.example.administrator.weibo.entity.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/30.
 */
public class CommentsListAdpater extends MyBaseAdapter {

    private List<Status> mStatusList;
    private Context mContext;
    private ImageButton imAvatar;
    private TextView tvUserName;
    private TextView tvDate;
    private TextView tvText;
    private ImageButton imLike;
    private TextView tvLikeCount;


    public CommentsListAdpater(Context context, OnItemClickListener onClickListener,
                               OnItemLongClickListener onLongClickListener) {
        this.mStatusList=new ArrayList<Status>();
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
 //       initViews(v);
 //       initData(mStatusList,position);
    }


    @Override
    public int getItemCount() {
        return 16;
    }


    private void initViews(View v) {
        imAvatar=(ImageButton)v.findViewById(R.id.im_avatar);
        tvUserName=(TextView)v.findViewById(R.id.tv_username);
        tvDate=(TextView)v.findViewById(R.id.tv_date);
        tvText=(TextView)v.findViewById(R.id.tv_text);
        imLike=(ImageButton)v.findViewById(R.id.im_like);
        tvLikeCount=(TextView)v.findViewById(R.id.tv_likecount);
    }
    private void initData(List<Status> statusList,int position) {
        tvUserName.setText(statusList.get(position).getUser().getScreenName());
        tvDate.setText(Html.fromHtml(statusList.get(position).getSource(), null,null));

        tvText.setText(statusList.get(position).getText());
//        tvRepostsCount.setText(String.format("%d",statusList.get(position).getRepostsCount()));
//        tvCommentsCount.setText(String.format("%d", statusList.get(position).getCommentsCount()));
        tvLikeCount.setText(String.format("%d", statusList.get(position).getAttitudesCount()));
    }
    public void setData(List<Status> statusList) {
        Log.d("statusList.size",String.format("%d",statusList.size()));
        this.mStatusList=statusList;
    }
}