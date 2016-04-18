package com.example.administrator.weibo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.administrator.weibo.R;
import com.example.administrator.weibo.adapter.ItemDivider;
import com.example.administrator.weibo.adapter.MessageListAdpater;
import com.example.administrator.weibo.adapter.MyBaseAdapter;
import com.example.administrator.weibo.entity.Message;
import com.example.administrator.weibo.utils.SharedPreferencesUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/4/5.
 */
public class MessageFragment extends BaseFragment{
    private Context mContext;
    private List<Message> mMessageList;
    private MessageListAdpater mAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private SharedPreferencesUtils mSharedPreferencesUtils;
    private SearchView mSearchView;

    public MessageFragment(Context context) {
        super();
        mContext=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_message,container,false);
        initViews(view);//初始化View，做一些findViewById的操作
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    private void initViews(View view) {
        mSharedPreferencesUtils=new SharedPreferencesUtils(mContext);
        mLayoutManager=new LinearLayoutManager(mContext);
        mSearchView=(SearchView)view.findViewById(R.id.sv_search);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_messagelist);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new ItemDivider(mContext,
                ItemDivider.VERTICAL_LIST));
        mAdapter = new MessageListAdpater(mContext,new MyBaseAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v) {
                //   Toast.makeText(getApplicationContext(), "单击" + info.getText(), Toast.LENGTH_LONG).show();
            }
        },new MyBaseAdapter.OnItemLongClickListener() {
            @Override
            public void onLongClick(View v) {
                //     Toast.makeText(getApplicationContext(), "长按"+info.getText(), Toast.LENGTH_LONG).show();

            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

}
