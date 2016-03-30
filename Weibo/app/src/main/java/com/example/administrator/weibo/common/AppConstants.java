package com.example.administrator.weibo.common;

/**
 * Created by ZZB on 2016/3/29.
 */
public class AppConstants {


    public static class WeiboConfig{
        private static final boolean IS_WFH = true;
        public static final String END_POINT = "https://api.weibo.com";
        public static final String APP_KEY = IS_WFH ? "1104955412" : "2120439287";
        public static final String APP_SECRET = IS_WFH ? "03d740890a2c75b2962150df927358e5" : "74e7a4590efcaf4b4dde5f2caa70ea86";
        public static final String REDIRECT_URL = "https://www.baidu.com/";
    }
}
