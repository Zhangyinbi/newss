package com.example.myapplication.base;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/6/14 0014.
 */
public class SmartService extends BasePager {
    public SmartService(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        tvTitle.setText("生活");
        TextView text=new TextView(mActivity);
        text.setText("智能服务");
        text.setTextColor(Color.RED);
        text.setTextSize(30);
        text.setGravity(Gravity.CENTER);

        flLayout.addView(text);

        setSlidingMenuEnable(true);
    }
}
