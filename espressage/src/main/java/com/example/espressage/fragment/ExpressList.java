package com.example.espressage.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.espressage.R;

/**
 * Created by Administrator on 2016/6/28 0028.
 */
public class ExpressList extends Fragment {
    public Activity mActivity;
    //fragment创建
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    //处理fragment的布局
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = initView();
        return view;
    }
    public View initView() {
        View view = View.inflate(mActivity, R.layout.express_list, null);
        return view;
    }

    /**
     * Fragment所依赖的Activity创建完成
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 初始化数据
     */
    public void initData() {
    }
}
