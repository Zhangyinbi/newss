package com.example.viewpager.Utils;

import android.content.Context;

/**
 * Created by Administrator on 2016/6/27 0027.
 */
public class CacheUtils {

    /**
     * 设置缓存
     */
    public static void setCache(String key, String json, Context ctx){
        PrefUtils.setString(ctx,key,json);
        //可以缓存在文件里面。文件名就是（Md5）url以整体为key，把参数也带上，文件内容json
    }

    /**
     * 获取缓存
     */
    public static String getCache(String key,Context ctx){
            return PrefUtils.getString(ctx,key,null);
    }
}
