package com.example.administrator.weibo.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.weibo.R;
import com.example.administrator.weibo.adapter.FragmentAdapter;
import com.example.administrator.weibo.fragment.AccountFragment;
import com.example.administrator.weibo.fragment.FindFragment;
import com.example.administrator.weibo.fragment.MessageFragment;
import com.example.administrator.weibo.fragment.StatusListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/5.
 */
public class MainActivity extends FragmentActivity{



    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private FragmentAdapter mFragmentAdapter;
    private ViewPager mPageVp;
    /**
     * Tab显示内容TextView
     */
    private TextView tvHome,tvMessage,tvFind,tvAccount;
    private ImageView imHome,imMessage,imFind,imAccount;
    private LinearLayout llayoutHome,llayoutMessage,llayoutFind,llayoutAccount;
    /**
     * Fragment
     */
    private StatusListFragment mFragment1;
    private MessageFragment mFragment2;
    private FindFragment mFragment3;
    private AccountFragment mFragment4;
    /**
     * ViewPager的当前选中页
     */
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }


    private void init() {
        tvAccount = (TextView) this.findViewById(R.id.tv_account);
        tvFind = (TextView) this.findViewById(R.id.tv_find);
        tvHome = (TextView) this.findViewById(R.id.tv_home);
        tvMessage = (TextView) this.findViewById(R.id.tv_message);
        imAccount=(ImageView) this.findViewById(R.id.im_account);
        imFind = (ImageView) this.findViewById(R.id.im_find);
        imHome = (ImageView) this.findViewById(R.id.im_home);
        imMessage = (ImageView) this.findViewById(R.id.im_message);
        llayoutHome=(LinearLayout)this.findViewById(R.id.llayout_home);
        llayoutMessage=(LinearLayout)this.findViewById(R.id.llayout_message);
        llayoutFind=(LinearLayout)this.findViewById(R.id.llayout_find);
        llayoutAccount=(LinearLayout)this.findViewById(R.id.llayout_account);
        mPageVp = (ViewPager) this.findViewById(R.id.id_page_vp);

        mFragment1=new StatusListFragment(this);
        mFragment2=new MessageFragment(this);
        mFragment3=new FindFragment();
        mFragment4=new AccountFragment();

        mFragmentList.add(mFragment1);
        mFragmentList.add(mFragment2);
        mFragmentList.add(mFragment3);
        mFragmentList.add(mFragment4);

        mFragmentAdapter = new FragmentAdapter(this.getSupportFragmentManager(), mFragmentList);
        mPageVp.setAdapter(mFragmentAdapter);
        mPageVp.setCurrentItem(0);
        setPressed(0);
        setClickListener(llayoutHome, 0);
        setClickListener(llayoutMessage,1);
        setClickListener(llayoutFind,2);
        setClickListener(llayoutAccount,3);
    }

    private void setClickListener(LinearLayout linearlayout,final int i)
    {
        linearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPageVp.getCurrentItem() != i) {
                    reset();
                    setPressed(i);
                    mPageVp.setCurrentItem(i);
                }
            }
        });
    }

    /**
     * 重置颜色
     */

    private void reset() {
        tvHome.setTextColor(Color.BLACK);
        tvMessage.setTextColor(Color.BLACK);
        tvFind.setTextColor(Color.BLACK);
        tvAccount.setTextColor(Color.BLACK);
        imHome.setBackgroundResource(R.drawable.tabbar_home);
        imMessage.setBackgroundResource(R.drawable.tabbar_message_center);
        imFind.setBackgroundResource(R.drawable.tabbar_discover);
        imAccount.setBackgroundResource(R.drawable.tabbar_profile);
    }
    private void setPressed(int i)
    {
        switch (i) {
            case 0:
                tvHome.setTextColor(getResources().getColor(R.color.colorOrange));
                imHome.setBackgroundResource(R.drawable.tabbar_home_highlighted);
                break;
            case 1:
                tvMessage.setTextColor(getResources().getColor(R.color.colorOrange));
                imMessage.setBackgroundResource(R.drawable.tabbar_message_center_highlighted);
                break;
            case 2:
                tvFind.setTextColor(getResources().getColor(R.color.colorOrange));
                imFind.setBackgroundResource(R.drawable.tabbar_discover_highlighted);
                break;
            case 3:
                tvAccount.setTextColor(getResources().getColor(R.color.colorOrange));
                imAccount.setBackgroundResource(R.drawable.tabbar_profile_highlighted);
                break;

        }
    }
    public static void launch(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MainActivity.class);
        context.startActivity(intent);
    }
}
