package com.example.myapplication.base;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.example.myapplication.R;

/**
 * Created by Administrator on 2016/6/14 0014.
 */
public class Government extends BasePager {
    public Government(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        tvTitle.setText("人口管理");
        TextView text = new TextView(mActivity);

        text.setText("政务");
        text.setId(R.id.tvText);
        text.setTextColor(Color.RED);
        text.setTextSize(30);
        text.setGravity(Gravity.CENTER);

        flLayout.addView(text);

        setSlidingMenuEnable(true);
    }
}
