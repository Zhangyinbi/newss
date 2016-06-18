package com.example.viewpager.base;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.viewpager.R;
import com.example.viewpager.demain.NewsData;
import com.example.viewpager.global.GlobalContants;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * Created by Administrator on 2016/6/16 0016.
 */
public class TabDetailPager extends BaseMenuDetailPager {
    NewsData.NewsMenuData.NewsTabData mTabData;
    TextView tvText;
private String mUrl;
    public TabDetailPager(Activity activity, NewsData.NewsMenuData.NewsTabData newsTabData) {
        super(activity);
        mTabData = newsTabData;
        mUrl=GlobalContants.NEWS_TAB_URL+"&"+mTabData.channelId;
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
        tvText.setText(mTabData.name);

        //得到网络图片
        getDataFromService();
    }

    private void getDataFromService() {
        //网络获取图片资源，本来是动态的  现在设置为静态的  因为网络格式限制
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
        });
    }

    protected void parseData(String result) {
        Gson gson=new Gson();

    }
}
