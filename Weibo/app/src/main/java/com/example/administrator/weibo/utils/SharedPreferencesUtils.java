package com.example.administrator.weibo.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.administrator.weibo.entity.AccessToken;

/**
 * Created by Administrator on 2016/3/29.
 */
public class SharedPreferencesUtils {
    private Context mContext;

    public SharedPreferencesUtils(Context mcontext)
    {
        this.mContext=mcontext;
    }
    public  void saveStringData(String key,String vaule) {
        SharedPreferences settings = mContext.getSharedPreferences("setting", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key,vaule);
        editor.commit();
    }
    public String getStringData(String key) {
        SharedPreferences settings = mContext.getSharedPreferences("setting", 0);
        return settings.getString(key,"default");
    }
    public  void saveLongData(String key,Long vaule) {
        SharedPreferences settings = mContext.getSharedPreferences("setting", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, vaule);
        editor.commit();
    }
    public Long getLongData(String key) {
        SharedPreferences settings = mContext.getSharedPreferences("setting", 0);
        return settings.getLong(key,0);
    }
    public void saveToken(AccessToken accessToken) {
        if(accessToken!=null) {
            SharedPreferences settings = mContext.getSharedPreferences("setting", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("access_token", accessToken.getToken());
            editor.putLong("expires_in", accessToken.getExpiresIn());
            editor.putLong("expiresTime",System.currentTimeMillis()+accessToken.getExpiresIn());
            editor.commit();
        }

    }
    public AccessToken getToken() {
        AccessToken accessToken=new AccessToken();
        SharedPreferences settings = mContext.getSharedPreferences("setting", 0);
        accessToken.setToken(settings.getString("access_token","default"));
        accessToken.setExpiresTime(settings.getLong("expiresTime",0));
        return accessToken;
    }

}
