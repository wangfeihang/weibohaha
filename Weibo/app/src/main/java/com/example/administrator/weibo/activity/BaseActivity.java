package com.example.administrator.weibo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yy.androidlib.util.notification.NotificationCenter;

/**
 * Created by ZZB on 2016/3/29.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NotificationCenter.INSTANCE.addObserver(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NotificationCenter.INSTANCE.removeObserver(this);
    }
}
