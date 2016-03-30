package com.example.administrator.weibo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.weibo.utils.JsonHelper;
import com.example.administrator.weibo.utils.SharedPreferencesUtils;
import com.squareup.okhttp.RequestBody;

public class MainActivity extends AppCompatActivity {

    private Button btnLogout;
    private TextView mTokenView;
    private String logoutURL="https://api.weibo.com/oauth2/revokeoauth2";
    private String getTokenInfoURL="https://api.weibo.com/oauth2/get_token_info";
    private SharedPreferencesUtils sharedPreferencesUtils;
    /** 注销对应的listener */
    private OnClickListener mLogoutListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            String at=sharedPreferencesUtils.getStringData("accessToken");
            RequestBody requestBody=HttpUtils.getRequestBody("access_token",sharedPreferencesUtils.getStringData("accessToken"));
            HttpUtils.request(logoutURL, requestBody, new HttpUtils.Callback() {
                @Override
                public void onRequestSuccess(String json) {

                    RevokeBean revokeBean = new RevokeBean();
                    revokeBean = JsonHelper.toObject(json, revokeBean.getClass());
                    String result = revokeBean.getResult();
                    if (revokeBean.getResult().equals("true")) {
                        sharedPreferencesUtils.saveStringData("accessToken", "default");
                        Intent mintent = new Intent();
                        mintent.setClass(MainActivity.this, AccessAuthorizationActivity.class);
                        startActivity(mintent);
                        finish();
                    }

                }
            });
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        sharedPreferencesUtils=new SharedPreferencesUtils(MainActivity.this);
        setUid();
        mTokenView=(TextView)findViewById(R.id.tv_content);
        mTokenView.setText("登录成功");
        btnLogout=(Button)findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(mLogoutListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item_statuslist clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void setUid()
    {
        String at=sharedPreferencesUtils.getStringData("accessToken");
        RequestBody requestBody=HttpUtils.getRequestBody("access_token",sharedPreferencesUtils.getStringData("accessToken"));
        HttpUtils.request(getTokenInfoURL, requestBody, new HttpUtils.Callback() {
            @Override
            public void onRequestSuccess(String json) {

                TokenInfoBean tokenInfoBean = new TokenInfoBean();
                tokenInfoBean = JsonHelper.toObject(json, tokenInfoBean.getClass());
                sharedPreferencesUtils.saveStringData("uid",tokenInfoBean.getUid());

            }
        });

    }
}
