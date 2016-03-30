package com.example.administrator.weibo.http;

/**
 * Created by ZZB on 2016/3/26.
 */
public class RequestException extends Exception {
    
    private int statusCode;

    public RequestException(Throwable throwable, int statusCode, String errorMessage) {
        super(errorMessage, throwable);
        this.statusCode = statusCode;
    }

    public RequestException(String errorMsg) {
        super(errorMsg);
    }


    public int getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

}
