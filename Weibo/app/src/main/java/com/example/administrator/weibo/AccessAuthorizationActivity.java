package com.example.administrator.weibo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.administrator.weibo.utils.JsonHelper;
import com.example.administrator.weibo.utils.SharedPreferencesUtils;
import com.squareup.okhttp.RequestBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/28.
 */
public class AccessAuthorizationActivity  extends AppCompatActivity {
    private SharedPreferencesUtils sharedPreferencesUtils;
    private WebView mWebView;
    private String appKeyName="client_id";
    private String appKeyVaule="1104955412";
    private String appSecretName="client_secret";
    private String appSecretVaule="03d740890a2c75b2962150df927358e5";
    private String grantTypeName="grant_type";
    private String grantTypeVaule="authorization_code";
    private String codeName="code";
    private String codeVaule;
    private String redirectUriName="redirect_uri";
    private String redirectUriVaule="https://www.baidu.com/";
    private List<String> requestNameList;
    private List<String> requestVauleList;
    private String requestURL="https://api.weibo.com/oauth2/access_token";
    private String authorizeURL="https://open.weibo.cn/oauth2/authorize?client_id=1104955412&display=mobile&redirect_uri=https://www.baidu.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.access_authorization);
        mWebView = (WebView) findViewById(R.id.wv_authorization);
        sharedPreferencesUtils=new SharedPreferencesUtils(AccessAuthorizationActivity.this);
        if(isAuthorized())
        {
            Intent mintent = new Intent();
            mintent.setClass(AccessAuthorizationActivity.this , MainActivity.class );
            startActivity(mintent);
        }
        else{
            accessAuthorization();
        }

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
    public List<String> getRequestNameList()
    {
        List<String> nameList=new ArrayList<String>();
        nameList.add(appKeyName);
        nameList.add(appSecretName);
        nameList.add(grantTypeName);
        nameList.add(codeName);
        nameList.add(redirectUriName);
        return  nameList;
    }
    public List<String> getRequestVauleList()
    {
        List<String> vauleList=new ArrayList<String>();
        vauleList.add(appKeyVaule);
        vauleList.add(appSecretVaule);
        vauleList.add(grantTypeVaule);
        vauleList.add(codeVaule);
        vauleList.add(redirectUriVaule);
        return  vauleList;
    }
    public String getCodeVaule(String url)
    {
        int firstindex=url.lastIndexOf("=");
        return url.substring(firstindex+1);
    }
    public boolean isAuthorized()
    {
        String accessToken=sharedPreferencesUtils.getStringData("accessToken");
        if(accessToken.equals("default"))
            return false;
        if( System.currentTimeMillis()>sharedPreferencesUtils.getLongData("currentMillis")+sharedPreferencesUtils.getLongData("lifeCycle"))
            return false;
        return true;
    }
    public void accessAuthorization()
    {
        //设置WebView识别javascript的函数
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(authorizeURL);
        //设置WebView拦截重定向的URL
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                codeVaule = getCodeVaule(url);
                requestNameList = getRequestNameList();
                requestVauleList = getRequestVauleList();
                RequestBody requestBody = HttpUtils.getRequestBody(requestNameList, requestVauleList);
                HttpUtils.request(requestURL, requestBody, new HttpUtils.Callback() {
                    @Override
                    public void onRequestSuccess(String json) {

                        AccessTokenBean accessTokenBean = new AccessTokenBean();
                        accessTokenBean = JsonHelper.toObject(json, accessTokenBean.getClass());

                        sharedPreferencesUtils.saveStringData("accessToken", accessTokenBean.getAccessToken());
                //        sharedPreferencesUtils.saveStringData("uid", accessTokenBean.getUid());
                        sharedPreferencesUtils.saveLongData("lifeCycle", Long.parseLong(accessTokenBean.getLifeCycle()));
                        sharedPreferencesUtils.saveLongData("currentMillis", System.currentTimeMillis());
                        Log.d("test", accessTokenBean.getLifeCycle());
                        Intent mintent = new Intent();
                        mintent.setClass(AccessAuthorizationActivity.this, MainActivity.class);
                        startActivity(mintent);
                        //       finish();
                    }
                });
                return true;
            }
        });

    }
}
