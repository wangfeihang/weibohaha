package com.example.administrator.weibo.entity;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2016/4/20.
 */
public class StatuswithBitmap {
    private Status mStatus;
    private Bitmap mBitmap;
    private UserwithBitmap mUserwithBitmap;

    public UserwithBitmap getmUserwithBitmap() {
        return mUserwithBitmap;
    }

    public Status getmStatus() {
        return mStatus;
    }

    public Bitmap getmBitmap() {
        return mBitmap;
    }

    public void setmUserwithBitmap(UserwithBitmap mUserwithBitmap) {
        this.mUserwithBitmap = mUserwithBitmap;
    }

    public void setmStatus(Status mStatus) {
        this.mStatus = mStatus;
    }

    public void setmBitmap(Bitmap mBitmap) {
        this.mBitmap = mBitmap;
    }
}
