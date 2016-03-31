package com.example.administrator.weibo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
public class StatusListAdpater extends RecyclerView.Adapter<ViewHolder> {

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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
        tvSource.setText(Html.fromHtml(statusList.get(position).getSource(), null,null));

        tvText.setText(statusList.get(position).getText());
//        tvRepostsCount.setText(String.format("%d",statusList.get(position).getRepostsCount()));
//        tvCommentsCount.setText(String.format("%d", statusList.get(position).getCommentsCount()));
        tvAttitudesCount.setText(String.format("%d", statusList.get(position).getAttitudesCount()));
    }
    public void setData(List<Status> statusList)
    {
        Log.d("statusList.size",String.format("%d",statusList.size()));
        this.statusList=statusList;
    }
}