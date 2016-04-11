package com.example.administrator.weibo.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.weibo.R;
import com.example.administrator.weibo.activity.StatusContentActivity;
import com.example.administrator.weibo.adapter.ItemDivider;
import com.example.administrator.weibo.adapter.MyBaseAdapter;
import com.example.administrator.weibo.adapter.StatusListAdpater;
import com.example.administrator.weibo.entity.Status;
import com.example.administrator.weibo.entity.StatusList;
import com.example.administrator.weibo.model.StatusListModel;
import com.example.administrator.weibo.model.callback.StatusListCallback;
import com.example.administrator.weibo.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/7.
 */
public class ShowUserHomeFragment extends BaseFragment  implements StatusListCallback.GetStatusListCallback {
    private Context mContext;
    private List<Status> mStatusList=new ArrayList<Status>() ;
    private StatusListAdpater mAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private StatusListModel mStatusListModel;
    private SharedPreferencesUtils mSharedPreferencesUtils;
    @SuppressLint("ValidFragment")
    public ShowUserHomeFragment(Context context) {
        super();
        mContext=context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View chatView = inflater.inflate(R.layout.fragment_show_user_home,container,false);
        initViews(chatView);//初始化View，做一些findViewById的操作
        getStatusList(mSharedPreferencesUtils.getToken().getToken(),5,"ShowUserHomeFragment");
        return chatView;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }
    private void initViews(View view) {
        mStatusListModel=new StatusListModel();
        mSharedPreferencesUtils=new SharedPreferencesUtils(mContext);
        mLayoutManager=new LinearLayoutManager(mContext);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_statuslist);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new ItemDivider(mContext,
                ItemDivider.VERTICAL_LIST));
        mAdapter = new StatusListAdpater(mContext,new MyBaseAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v) {
                onClickStatus(v,mStatusList,mContext);
             //   TextView info = (TextView) v.findViewById(R.id.tv_username);
                //   Toast.makeText(getApplicationContext(), "单击" + info.getText(), Toast.LENGTH_LONG).show();
            }
        },new MyBaseAdapter.OnItemLongClickListener() {
            @Override
            public void onLongClick(View v) {
             //   TextView info = (TextView) v.findViewById(R.id.tv_username);
                //     Toast.makeText(getApplicationContext(), "长按"+info.getText(), Toast.LENGTH_LONG).show();

            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }
    public void onClickStatus(View v, List<Status> mStatusList,Context context)
    {
        int position=(int)v.getTag();
        Status status=mStatusList.get(position);
        StatusContentActivity.launch(context, status);
    }
    private void getStatusList(String token,int count,String caller) {
        mStatusListModel.getStatusList(token,count,caller);
    }

    @Override
    public void onGetStatusListSuccess(StatusList statusList,String caller) {
        if(caller.equals("ShowUserHomeFragment")) {
            mStatusList=new ArrayList<Status>();
            mStatusList=statusList.getStatuses();
            mAdapter.setData(mStatusList);
            mAdapter.notifyDataSetChanged();
            Log.d("test", "chang" + mStatusList.size());
        }

    }

    @Override
    public void onGetStatusListFailed(String errorMsg) {

    }
}
