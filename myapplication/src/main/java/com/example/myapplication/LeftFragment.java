package com.example.myapplication;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * Created by Administrator on 2016/6/18 0018.
 */
public class LeftFragment extends Fragment {
    private Activity mActivity;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity=getActivity();

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(mActivity, R.layout.left_layout,null);
        Button brnRed= (Button) view.findViewById(R.id.btn_red);
        Button brnYellow= (Button) view.findViewById(R.id.btn_yellow);
        brnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainUi= (MainActivity) mActivity;
                Log.e("哈哈哈哈", "1111");
                RightFragment rightFragment=mainUi.getRightFragmentg();
                Log.e("哈哈哈哈", ""+ rightFragment.ll);
                rightFragment.ll.setBackgroundResource(android.R.color.holo_red_light);
            }
        });
        brnYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainUi= (MainActivity) mActivity;
                Log.e("哈哈哈哈", "2222");
                RightFragment rightFragment=mainUi.getRightFragmentg();
                rightFragment.ll.setBackgroundResource(R.color.yellow);
            }
        });
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
