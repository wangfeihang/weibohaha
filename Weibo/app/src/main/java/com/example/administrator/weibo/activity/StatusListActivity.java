package com.example.administrator.weibo.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.weibo.R;
import com.example.administrator.weibo.adapter.ItemDivider;
import com.example.administrator.weibo.adapter.StatusListAdpater;
import com.example.administrator.weibo.entity.StatusList;
import com.example.administrator.weibo.model.StatusListModel;
import com.example.administrator.weibo.model.callback.StatusListCallback.GetStatusListCallback;
import com.example.administrator.weibo.utils.SharedPreferencesUtils;

/**
 * Created by Administrator on 2016/3/30.
 */
public class StatusListActivity  extends BaseActivity implements GetStatusListCallback {


    private StatusListAdpater mAdapter;
    private RecyclerView mRecyclerView;
    private LayoutManager mLayoutManager;
    private StatusListModel mStatusListModel;
    private SharedPreferencesUtils mSharedPreferencesUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statuslist);
        initViews();//初始化View，做一些findViewById的操作
        getStatusList(mSharedPreferencesUtils.getToken().getToken());
    }


    @Override
    public void onGetStatusListSuccess(StatusList statusList) {
        mAdapter.setData(statusList.getStatuses());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetStatusListFailed(String errorMsg) {

    }
    private void initViews() {
        mStatusListModel=new StatusListModel();
        mSharedPreferencesUtils=new SharedPreferencesUtils(StatusListActivity.this);
        mLayoutManager=new LinearLayoutManager(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_statuslist);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new ItemDivider(this,
                ItemDivider.VERTICAL_LIST));
        mAdapter = new StatusListAdpater(StatusListActivity.this,new StatusListAdpater.OnItemClickListener() {
            @Override
            public void onClick(View v) {
                TextView info = (TextView) v.findViewById(R.id.tv_username);
                Toast.makeText(getApplicationContext(), "单击" + info.getText(), Toast.LENGTH_LONG).show();
            }
        },new StatusListAdpater.OnItemLongClickListener() {
            @Override
            public void onLongClick(View v) {
                TextView info = (TextView) v.findViewById(R.id.tv_username);
                Toast.makeText(getApplicationContext(), "长按"+info.getText(), Toast.LENGTH_LONG).show();

            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }
    private void getStatusList(String token)
    {
        mStatusListModel.getStatusList(token);
    }
}
