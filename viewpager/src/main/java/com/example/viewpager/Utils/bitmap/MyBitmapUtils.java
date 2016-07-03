package com.example.viewpager.Utils.bitmap;

import android.widget.ImageView;

/**
 * Created by Administrator on 2016/6/29 0029.
 */
public class MyBitmapUtils {
    NetCacheUtils myNewCacheUtils;
    public MyBitmapUtils() {
        myNewCacheUtils=new NetCacheUtils();
    }

    public void disPlay(ImageView iv, String ImageUrl){
        //从内存读

        //从本地读取

        //从网络中读取
        myNewCacheUtils.getBitmapFromNet(iv,ImageUrl);
    }

}
