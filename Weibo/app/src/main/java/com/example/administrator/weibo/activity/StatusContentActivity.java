package com.example.administrator.weibo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.weibo.R;
import com.example.administrator.weibo.View.MyHoveringScrollView;
import com.example.administrator.weibo.adapter.CommentsListAdpater;
import com.example.administrator.weibo.adapter.ItemDivider;
import com.example.administrator.weibo.adapter.MyBaseAdapter;
import com.example.administrator.weibo.common.AppConstants;
import com.example.administrator.weibo.entity.Status;
import com.example.administrator.weibo.model.StatusContentModel;

/**
 * Created by Administrator on 2016/4/1.
 */
public class StatusContentActivity extends BaseActivity{
    private Status mStatus;
    private  MyHoveringScrollView mViewHover;
    private CommentsListAdpater mAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private StatusContentModel mStatusContentModel;
    private ImageButton imAvatar;
    private TextView tvUserName;
    private TextView tvSource;
    private TextView tvText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statuscontent);
        initViews();//初始化View，做一些findViewById的操作
    }
    private void initViews() {
        mStatus = (Status)getIntent().getSerializableExtra(AppConstants.WeiboConfig.STATUS_BUNDLE_KEY);
        imAvatar=(ImageButton)findViewById(R.id.im_avatar);
        tvUserName=(TextView)findViewById(R.id.tv_username);
        tvSource=(TextView)findViewById(R.id.tv_source);
        tvText=(TextView)findViewById(R.id.status_content_text);
       // tvUserName.setText(mStatus.getUser().getScreenName());
        tvSource.setText(mStatus.getSource());
        tvText.setText(mStatus.getText());

        mStatusContentModel=new StatusContentModel();
        mLayoutManager=new LinearLayoutManager(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_commentslist);
        mViewHover = (MyHoveringScrollView) findViewById(R.id.view_hover);
        mViewHover.setTopView(R.id.top);
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
    public static void launch(Context context,Status status) {
        Intent intent = new Intent();
        intent.setClass(context, StatusContentActivity.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable(AppConstants.WeiboConfig.STATUS_BUNDLE_KEY,status);
        intent.putExtras(bundle);
        Log.d("test",status.getText());
        context.startActivity(intent);
    }

}
