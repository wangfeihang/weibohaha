package com.example.administrator.weibo.utils;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZZB on 2016/3/29.
 */
public class UrlUtils {
    public static Map<String, String> getUrlParams(String url) {
        Map<String, String> params = new HashMap<>();
        if (!TextUtils.isEmpty(url)) {
            if (url.contains("?") && url.split("\\?").length == 2) {
                String paramsStr = url.split("\\?")[1];
                String[] paramsArr = paramsStr.split("&");
                for (String paramPair : paramsArr) {
                    String[] kv = paramPair.split("=");
                    params.put(kv[0], kv[1]);
                }
            }
        }
        return params;
    }
}
