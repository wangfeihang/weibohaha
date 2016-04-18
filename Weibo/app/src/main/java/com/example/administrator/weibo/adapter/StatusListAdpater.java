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
public class StatusListAdpater extends MyBaseAdapter<Status> {

    private List<Status> mStatusList;
    private Context mContext;
    private ImageButton imAvatar;
    private TextView tvUserName;
    private TextView tvSource;
    private TextView tvText;
    private ImageButton imReposts;
    private TextView tvRepostsCount;
    private ImageButton imComments;
    private TextView tvCommentsCount;
    private ImageButton imAttitudes;
    private TextView tvAttitudesCount;

    public StatusListAdpater(Context context, OnItemClickListener onClickListener,
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
                .inflate(R.layout.item_statuslist, parent, false);
        MyViewHolder vh = new MyViewHolder(v, mOnClickListener,
                mOnLongClickListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        View v=holder.v;
        initViews(v);
        initData(mStatusList, position);
        v.setTag(position);
    }


    @Override
    public int getItemCount() {
        return mStatusList.size();
    }

    @Override
    public void addData(List<Status> datalist) {
        mStatusList.addAll(datalist);
    }


    private void initViews(View v) {
        imAvatar=(ImageButton)v.findViewById(R.id.im_avatar);
        tvUserName=(TextView)v.findViewById(R.id.tv_username);
        tvSource=(TextView)v.findViewById(R.id.tv_source);
        tvText=(TextView)v.findViewById(R.id.tv_text);
        imReposts=(ImageButton)v.findViewById(R.id.im_reposts);
        tvRepostsCount=(TextView)v.findViewById(R.id.tv_reposts_count);
        imComments=(ImageButton)v.findViewById(R.id.im_comments);
        tvCommentsCount=(TextView)v.findViewById(R.id.tv_comments_count);
        imAttitudes=(ImageButton)v.findViewById(R.id.im_attitudes);
        tvAttitudesCount=(TextView)v.findViewById(R.id.tv_attitudes_count);
    }
    private void initData(List<Status> statusList,int position) {
        tvUserName.setText(statusList.get(position).getUser().getScreenName());
        tvSource.setText(Html.fromHtml(statusList.get(position).getSource(), null,null));

        tvText.setText(statusList.get(position).getText());
        //        tvRepostsCount.setText(String.format("%d",statusList.get(position).getRepostsCount()));
        //        tvCommentsCount.setText(String.format("%d", statusList.get(position).getCommentsCount()));
        tvAttitudesCount.setText(String.format("%d", statusList.get(position).getAttitudesCount()));
    }
    public void setData(List<Status> statusList) {
        Log.d("statusList.size", String.format("%d", statusList.size()));
        mStatusList.addAll(statusList);
    }
}
