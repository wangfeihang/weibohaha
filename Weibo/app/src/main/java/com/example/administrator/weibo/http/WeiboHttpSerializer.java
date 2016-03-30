package com.example.administrator.weibo.http;

import com.example.administrator.weibo.utils.JsonHelper;

import java.lang.reflect.Type;

/**
 * Created by ZZB on 2016/3/29.
 */
public class WeiboHttpSerializer implements HttpSerializer {
    @Override
    public Object toObject(Object clazz, int statusCode, byte[] body) throws RequestException {
        try {
            String json = new String(body, "UTF-8");
            if (clazz instanceof Class) {
                return JsonHelper.toObject(json, (Class) clazz);
            } else {
                return JsonHelper.jsonToObject(json, (Type) clazz);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RequestException(e, statusCode, "json 解析异常");
        }
    }
  /*
    @Override
    public List<Object> toObjectList(Object clazz, int statusCode, byte[] body) throws RequestException {
        try {
            String json = new String(body, "UTF-8");
            if (clazz instanceof Class) {
                return JsonHelper.toObjectList(json, (Class) clazz);
            } else {
                return JsonHelper.jsonToObjectList(json, (Type) clazz);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RequestException(e, statusCode, "json 解析异常");
        }
    }
   */

}
