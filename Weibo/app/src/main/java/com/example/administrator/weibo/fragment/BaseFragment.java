package com.example.administrator.weibo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.yy.androidlib.util.notification.NotificationCenter;

/**
 * Created by Administrator on 2016/4/5.
 */
public class BaseFragment  extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NotificationCenter.INSTANCE.addObserver(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        NotificationCenter.INSTANCE.removeObserver(this);
    }
}
