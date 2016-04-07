package com.example.administrator.weibo.http;


import java.util.List;

/**
 * Created by ZZB on 2016/3/26.
 */
public interface HttpSerializer {

    Object toObject(Object clazz, int statusCode, byte[] body) throws RequestException;
    List<Object> toObjectList(Object clazz, int statusCode, byte[] body) throws RequestException;
}
