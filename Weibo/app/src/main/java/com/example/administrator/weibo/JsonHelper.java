package com.example.administrator.weibo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;

/**
 * Created by Administrator on 2016/3/28.
 */
public class JsonHelper {
    private static Gson sGson;
    private static Gson getGson() {
        if (sGson == null) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            sGson = gsonBuilder.create();
        }
        return sGson;
    }
    public static <T>T toObject(String jsonString,Class<T> mclass)
    {
        Gson gson=new Gson();
        return gson.fromJson(jsonString, mclass);
    }
    public static <T> T jsonToObject(String json, Type tokenType) {
        T t = null;
        try {
            t = getGson().fromJson(json, tokenType);
        }catch (JsonSyntaxException e){
            e.printStackTrace();
        }
        return t;
    }
}
