package com.example.administrator.weibo;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/3/29.
 */
public class SharedPreferencesUtils {
    private Context mcontext;

    SharedPreferencesUtils(Context mcontext)
    {
        this.mcontext=mcontext;
    }
    public  void saveStringData(String key,String vaule)
    {
        SharedPreferences settings = mcontext.getSharedPreferences("setting", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key,vaule);
        editor.commit();
    }
    public String getStringData(String key)
    {
        SharedPreferences settings = mcontext.getSharedPreferences("setting", 0);
        return settings.getString(key,"default");
    }
    public  void saveLongData(String key,Long vaule)
    {
        SharedPreferences settings = mcontext.getSharedPreferences("setting", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, vaule);
        editor.commit();
    }
    public Long getLongData(String key)
    {
        SharedPreferences settings = mcontext.getSharedPreferences("setting", 0);
        return settings.getLong(key,0);
    }

}
