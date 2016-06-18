package com.example.myapplication.base;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.MainActivity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/14 0014.
 */
public class NewsPager extends BasePager {
    public NewsPager(Activity activity) {
        super(activity);
    }
    int i = 0;
    @Override
    public void initData() {

        if (i == 0) {
            tvTitle.setText("新闻");
//        flLayout.removeAllViews();
            TextView text = new TextView(mActivity);
            text.setText("新闻");
//        flLayout.removeAllViews();
            text.setTextColor(Color.RED);
            text.setTextSize(30);
            text.setGravity(Gravity.CENTER);
            flLayout.addView(text);
        }
        setSlidingMenuEnable(true);
        i++;
    }


}
