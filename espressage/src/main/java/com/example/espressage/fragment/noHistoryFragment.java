package com.example.espressage.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.espressage.R;

/**
 * Created by Administrator on 2016/7/3 0003.
 */
public class NoHistoryFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("--=-==-======", "onCreateView: ");
        View view=inflater.inflate(R.layout.no_history_fragment,null);
        return view;
    }
}
