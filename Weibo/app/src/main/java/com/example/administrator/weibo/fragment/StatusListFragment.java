package com.example.administrator.weibo.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.weibo.R;
import com.example.administrator.weibo.activity.StatusContentActivity;
import com.example.administrator.weibo.adapter.ItemDivider;
import com.example.administrator.weibo.adapter.MyBaseAdapter;
import com.example.administrator.weibo.adapter.StatusListAdpater;
import com.example.administrator.weibo.common.AppConstants;
import com.example.administrator.weibo.entity.Status;
import com.example.administrator.weibo.entity.StatusList;
import com.example.administrator.weibo.listener.ListInfiniteScrollListener;
import com.example.administrator.weibo.model.StatusListModel;
import com.example.administrator.weibo.model.callback.StatusListCallback.GetStatusListCallback;
import com.example.administrator.weibo.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/5.
 */

public class StatusListFragment extends BaseFragment  implements GetStatusListCallback ,SwipeRefreshLayout.OnRefreshListener {
    private Context mContext;
    private List<Status> mStatusList=new ArrayList<Status>();
    private ProgressBar progressBar;
    private StatusListAdpater mAdapter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private StatusListModel mStatusListModel;
    private SharedPreferencesUtils mSharedPreferencesUtils;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private int page=0;
    public StatusListFragment(Context context) {
        super();
        mContext=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_statuslist,container,false);
        initViews(view);//初始化View，做一些findViewById的操作

        page++;
        Log.d("page", "page" + page);
        getStatusList(mSharedPreferencesUtils.getToken().getToken(), AppConstants.WeiboConfig.MAX_ITEMS_PER_PAGE, "StatusListFragment", page);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    private void initViews(View view) {
        mStatusListModel=new StatusListModel();
        mSharedPreferencesUtils=new SharedPreferencesUtils(mContext);
        mLayoutManager=new LinearLayoutManager(mContext);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_statuslist);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new ItemDivider(mContext,
                ItemDivider.VERTICAL_LIST));
        mAdapter = new StatusListAdpater(mContext,new MyBaseAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v) {
                TextView info = (TextView) v.findViewById(R.id.tv_username);
                onClickStatus(v, mStatusList, mContext);
                //   Toast.makeText(getApplicationContext(), "单击" + info.getText(), Toast.LENGTH_LONG).show();
            }
        },new MyBaseAdapter.OnItemLongClickListener() {
            @Override
            public void onLongClick(View v) {
                TextView info = (TextView) v.findViewById(R.id.tv_username);
           //     Toast.makeText(getApplicationContext(), "长按"+info.getText(), Toast.LENGTH_LONG).show();

            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new ListInfiniteScrollListener(mLayoutManager) {
            @Override
            public void onScrolledToEnd() {
                simulateLoading();
                page++;
                getStatusList(mSharedPreferencesUtils.getToken().getToken(), AppConstants.WeiboConfig.MAX_ITEMS_PER_PAGE, "StatusListFragment", page);
            }
        });


        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        // 设置下拉圆圈上的颜色，蓝色、绿色、橙色、红色
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mSwipeRefreshLayout.setDistanceToTriggerSync(400);// 设置手指在屏幕下拉多少距离会触发下拉刷新
        mSwipeRefreshLayout.setProgressBackgroundColor(R.color.white); // 设定下拉圆圈的背景
        mSwipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE); // 设置圆圈的大小


    }
    public void onClickStatus(View v, List<Status> mStatusList,Context context)
    {
        int position=(int)v.getTag();
        Status status=mStatusList.get(position);
        StatusContentActivity.launch(context, status);
    }

    private void getStatusList(String token,int count,String caller,int page) {
        mStatusListModel.getStatusList(token, count, caller, page);
    }

    @Override
    public void onGetStatusListSuccess(StatusList statusList,String caller) {
        if(caller.equals("StatusListFragment")){
            if(page==1) {
                mStatusList = statusList.getStatuses();
                mAdapter.setData(mStatusList);
                mAdapter.notifyDataSetChanged();
            }
            else{
                mStatusList.addAll(statusList.getStatuses());
                mAdapter.addData(statusList.getStatuses());
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onGetStatusListFailed(String errorMsg) {

    }


    /**
     * WARNING! This method is only for demo purposes!
     * Don't do anything like that in your regular project!
     */
    private void simulateLoading() {
        new AsyncTask<Void, Void, Void>() {
            @Override protected void onPreExecute() {
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override protected Void doInBackground(Void... params) {
                try {
                   Thread.sleep(550);
                } catch (Exception e) {
                    Log.e("MainActivity", e.getMessage());
                }
                return null;
            }

            @Override protected void onPostExecute(Void param) {
                progressBar.setVisibility(View.GONE);
            }
        }.execute();
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 停止刷新
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 5000); // 5秒后发送消息，停止刷新

    }
}
