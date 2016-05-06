package com.example.administrator.weibo.model.callback;

import android.graphics.Bitmap;
import android.widget.ImageButton;

/**
 * Created by Administrator on 2016/4/19.
 */
public class ImageCallback {
    public interface GetImageCallback{
        void onGetImageSuccess(Bitmap bitmap,ImageButton imageButton);
        void onGetImageFailed(String errorMsg);
    }
}
