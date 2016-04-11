package com.example.administrator.weibo.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/5.
 */
public class FragmentAdapter extends FragmentPagerAdapter {
    List<Fragment> mFragmentList = new ArrayList<Fragment>();
    List<String> mFragmentTitles=new ArrayList<String>();



    public FragmentAdapter(FragmentManager supportFragmentManager, List<Fragment> fragmentList,List<String> fragmentTitles) {
        super(supportFragmentManager);
        this.mFragmentList = fragmentList;
        this.mFragmentTitles=fragmentTitles;
    }

    @Override
    public Fragment getItem(int position) {

        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitles.get(position);
    }
}
