package com.example.express.fragment;

import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/6/29 0029.
 */
public class MainFragment extends BaseFragment {
    @Override
    public View initView() {
        TextView text=new TextView(mActivity);
        text.setText("dasdasdasdsda");
        return text;
    }
}
