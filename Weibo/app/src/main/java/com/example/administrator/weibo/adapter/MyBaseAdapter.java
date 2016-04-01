package com.example.administrator.weibo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/4/1.
 */
public  class MyBaseAdapter extends RecyclerView.Adapter<MyViewHolder> {
    public OnItemClickListener mOnClickListener;
    public OnItemLongClickListener mOnLongClickListener;

    public interface OnItemClickListener {
        void onClick(View v);
    }
    public interface OnItemLongClickListener {
        void onLongClick(View v);
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }


    //  abstract void onBindViewHolder(MyViewHolder holder, int position);

    @Override
    public int getItemCount() {
        return 0;
    }
}
