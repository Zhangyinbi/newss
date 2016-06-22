package com.example.myapplication.Utils;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Administrator on 2016/6/22 0022.
 */
public class MyApplication extends Application{
    public static RequestQueue queues;
    @Override
    public void onCreate() {
        super.onCreate();
        queues= Volley.newRequestQueue(getApplicationContext());
    }
    public static RequestQueue getHttpQueues(){
        return queues;
    }
}
