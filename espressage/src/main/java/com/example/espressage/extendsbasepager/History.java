package com.example.espressage.extendsbasepager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.espressage.base.BasePager;

/**
 * Created by Administrator on 2016/6/27 0027.
 */
public class History extends BasePager {
    public History(Activity activity) {
        super(activity);
         }
    public View initViews() {
        TextView text=new TextView(mActivity);
        text.setText("历史记录");
        text.setTextColor(Color.RED);
        text.setTextSize(30);
        text.setGravity(Gravity.CENTER);
        return text;
    }
}
