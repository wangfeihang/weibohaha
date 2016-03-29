package com.example.administrator.weibo;

import com.google.gson.Gson;

/**
 * Created by Administrator on 2016/3/28.
 */
public class JsonHelper {
    public static <T>T toObject(String jsonString,Class<T> mclass)
    {
        Gson gson=new Gson();
        return gson.fromJson(jsonString, mclass);
    }
}
