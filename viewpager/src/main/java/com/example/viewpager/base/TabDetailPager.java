package com.example.viewpager.base;

import android.app.Activity;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.viewpager.R;
import com.example.viewpager.demain.NewsData;
import com.example.viewpager.demain.TabData;
import com.example.viewpager.global.GlobalContants;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/6/16 0016.
 */
public class TabDetailPager extends BaseMenuDetailPager {
    NewsData.NewsMenuData.NewsTabData mTabData;
    TabData mTabDetailData;

    private String mUrl;

    public TabDetailPager(Activity activity, NewsData.NewsMenuData.NewsTabData newsTabData) {
        super(activity);
        mTabData = newsTabData;
        mUrl = GlobalContants.NEWS_TAB_URL + "&" + mTabData.channelId;
    }

    @Override
    protected View initViews() {
      /*tvText=new TextView(mActivity);
        tvText.setGravity(Gravity.CENTER);
        tvText.setTextSize(29);
        tvText.setText("页签详情页");
        tvText.setTextColor(Color.RED);*/
        View view = View.inflate(mActivity, R.layout.tab_detail_pager, null);


        return view;
    }

    @Override
    public void initData() {
        //得到网络图片
        getDataFromService();
    }

    private void getDataFromService() {
       /* //网络获取图片资源，本来是动态的  现在设置为静态的  因为网络格式限制
        HttpUtils utiles= new HttpUtils();
        utiles.send(HttpRequest.HttpMethod.GET, mUrl, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
//                Log.e("哈哈哈哈哈哈啊哈哈哈哈哈哈哈",result );
                parseData(result);
            }
            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(mActivity, s, Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        });*/
        new Thread() {
            @Override
            public void run() {
                Message mes;
                try {
                    URL url = new URL(mUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(2000);
                    int code = conn.getResponseCode();
                    byte[] by = new byte[1024];
                    if (code == 200) {
//                        Log.e("哈哈哈哈哈哈啊哈哈哈哈哈哈哈", "2");
                        InputStream is = conn.getInputStream();
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        int len = 0;
                        while ((len = is.read(by)) != -1) {
                            bos.write(by, 0, len);
                        }
                        String result = new String(bos.toByteArray());
//                        Log.e("哈哈哈子线程", result);
//                        paseData(result);//解析网络返回的数据，json格式的
                        mes = Message.obtain();
                        mes.what = 0;
                        mes.obj = result;
                        mhander.sendMessage(mes);
                        is.close();
                        bos.close();
                    }
                } catch (IOException e) {
                    mes = Message.obtain();
                    mes.what = 1;
                    mhander.sendMessage(mes);
//                    Message  mes = Message.obtain();
//                    mes.what=1;
//                    mhander.sendMessage(mes);
//                    Toast.makeText(mActivity, mActivity.getString(R.string.CheckIntnet), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        }.start();

    }

    final Handler mhander = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                String result = (String) msg.obj;
                parseData(result);
//                setCurrentMenuDetailPager(0);
            } else if (msg.what == 1) {
                //传递缓存数据  这个实在不联网的情况下需要调用另一个方法，给界面填充数据内容，读取缓存
//                paseData("null");
//                setCurrentMenuDetailPager(0);
                //没有网络服务的时候走的是这个程序
//                Log.e("哈哈哈哈哈哈啊哈哈哈哈哈哈哈","111111" );
                Toast.makeText(mActivity, mActivity.getString(R.string.CheckIntnet), Toast.LENGTH_LONG).show();
            }
        }
    };

    protected void parseData(String result) {
        Gson gson = new Gson();

        mTabDetailData = gson.fromJson(result, TabData.class);
        System.out.println("详情111"+mTabDetailData);
    }
}
