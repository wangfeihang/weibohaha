package com.example.administrator.weibo.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.administrator.weibo.R;
import com.example.administrator.weibo.common.AppConstants.WeiboConfig;
import com.example.administrator.weibo.entity.AccessToken;
import com.example.administrator.weibo.model.LoginModel;
import com.example.administrator.weibo.model.callback.LoginCallback.GetAccessTokenCallback;
import com.example.administrator.weibo.utils.SharedPreferencesUtils;
import com.example.administrator.weibo.utils.UrlUtils;

import java.util.Map;

/**
 * Created by ZZB on 2016/3/29.
 */
public class AuthActivity extends BaseActivity implements GetAccessTokenCallback{
    private WebView mWebView;
    private LoginModel mLoginModel = new LoginModel();
    private SharedPreferencesUtils sharedPreferencesUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        sharedPreferencesUtils=new SharedPreferencesUtils(AuthActivity.this);
        if(sharedPreferencesUtils.getToken().isValid()) {
            mLoginModel.gotoStatusListActivity(AuthActivity.this);
            finish();
        }
        else
        {
            initViews();//初始化View，做一些findViewById的操作
            loadData();
        }

    }

    private void loadData() {
        String url = "https://open.weibo.cn/oauth2/authorize?client_id=" + WeiboConfig.APP_KEY + "&response_type=code&redirect_uri=" + WeiboConfig.REDIRECT_URL + "&scope=all&display=mobile";
        mWebView.loadUrl(url);
    }

    private void initViews() {
        mWebView = (WebView) findViewById(R.id.wv_authorization);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Map<String, String> params = UrlUtils.getUrlParams(url);
                String code = params.get("code");
                getAccessToken(code);
                return true;
            }
        });
    }

    private void getAccessToken(String code) {
        mLoginModel.getAccessToken(code);
    }

    @Override
    public void onGetTokenSuccess(AccessToken token) {

        sharedPreferencesUtils.saveToken(token);
        mLoginModel.gotoStatusListActivity(AuthActivity.this);
        finish();
        Toast.makeText(this, "onGetTokenSuccess:" + token.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGetTokenFailed(String errorMsg) {
        Toast.makeText(this, "onGetTokenFailed:" + errorMsg, Toast.LENGTH_LONG).show();
    }
}
