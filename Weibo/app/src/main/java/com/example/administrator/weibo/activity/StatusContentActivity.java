package com.example.administrator.weibo.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.weibo.R;
import com.example.administrator.weibo.View.MyHoveringScrollView;
import com.example.administrator.weibo.adapter.CommentsListAdpater;
import com.example.administrator.weibo.adapter.ItemDivider;
import com.example.administrator.weibo.adapter.MyBaseAdapter;
import com.example.administrator.weibo.model.StatusListModel;

/**
 * Created by Administrator on 2016/4/1.
 */
public class StatusContentActivity extends BaseActivity{
    private  MyHoveringScrollView view_hover;
    private CommentsListAdpater mAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private StatusListModel mStatusListModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statuscontent);
        initViews();//初始化View，做一些findViewById的操作
    }
    private void initViews() {
        view_hover = (MyHoveringScrollView) findViewById(R.id.view_hover);
        view_hover.setTopView(R.id.top);
        mStatusListModel=new StatusListModel();
        mLayoutManager=new LinearLayoutManager(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_commentslist);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new ItemDivider(this,
                ItemDivider.VERTICAL_LIST));
        mAdapter = new CommentsListAdpater(StatusContentActivity.this,new MyBaseAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v) {
                TextView info = (TextView) v.findViewById(R.id.tv_username);
                Toast.makeText(getApplicationContext(), "单击" + info.getText(), Toast.LENGTH_LONG).show();
            }
        },new MyBaseAdapter.OnItemLongClickListener() {
            @Override
            public void onLongClick(View v) {
                TextView info = (TextView) v.findViewById(R.id.tv_username);
                Toast.makeText(getApplicationContext(), "长按"+info.getText(), Toast.LENGTH_LONG).show();

            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }
}
