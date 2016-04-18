package com.example.administrator.weibo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2016/3/31.
 */
public class MyViewHolder extends RecyclerView.ViewHolder {
    // each data item is just a string in this case
    public View v;
    public MyViewHolder(View itemLayoutView,
                        final MyBaseAdapter.OnItemClickListener onClickListener,
                        final MyBaseAdapter.OnItemLongClickListener onLongClickListener) {
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