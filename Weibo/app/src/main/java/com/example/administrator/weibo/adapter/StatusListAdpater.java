package com.example.administrator.weibo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
public class StatusListAdpater extends RecyclerView.Adapter<StatusListAdpater.ViewHolder> {

    private List<Status> statusList;
    private Context mcontext;
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
    private OnItemClickListener onClickListener;
    private OnItemLongClickListener onLongClickListener;
    public interface OnItemClickListener {
        void onClick(View v);
    }
    public interface OnItemLongClickListener {
        void onLongClick(View v);
    }
    public StatusListAdpater(Context mcontext, OnItemClickListener onClickListener,
                             OnItemLongClickListener onLongClickListener)
    {
        this.statusList=new ArrayList<Status>();
        this.mcontext = mcontext;
        this.onClickListener = onClickListener;
        this.onLongClickListener = onLongClickListener;
    }


    @Override
    public StatusListAdpater.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_statuslist, parent, false);
        ViewHolder vh = new ViewHolder(v, onClickListener,
                onLongClickListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        View v=holder.v;
        initViews(v);
        initData(statusList,position);
    }

    @Override
    public int getItemCount() {
        return statusList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View v;
        public ViewHolder(View itemLayoutView,
                          final OnItemClickListener onClickListener,
                          final OnItemLongClickListener onLongClickListener) {
            super(itemLayoutView);
            v=itemLayoutView;
            itemLayoutView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (onClickListener != null) {
                        onClickListener.onClick(v);
                    }
                }
            });
            itemLayoutView.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    if (onLongClickListener != null) {
                        onLongClickListener.onLongClick(v);
                    }
                    return true;
                }
            });
        }
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
    private void initData(List<Status> statusList,int position)
    {
        tvUserName.setText(statusList.get(position).getUser().getScreenName());
        tvSource.setText(statusList.get(position).getSource());
        tvText.setText(statusList.get(position).getText());
        tvRepostsCount.setText(String.format("%d",statusList.get(position).getRepostsCount()));
        tvCommentsCount.setText(String.format("%d", statusList.get(position).getCommentsCount()));
        tvAttitudesCount.setText(String.format("%d", statusList.get(position).getAttitudesCount()));
    }
    public void setData(List<Status> statusList)
    {
        Log.d("statusList.size",String.format("%d",statusList.size()));
        this.statusList=statusList;
    }
}