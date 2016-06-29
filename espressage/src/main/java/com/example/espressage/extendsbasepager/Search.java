package com.example.espressage.extendsbasepager;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.espressage.MainActivity;
import com.example.espressage.R;
import com.example.espressage.base.BasePager;
import com.example.espressage.fragment.ExpressList;

import javax.security.auth.login.LoginException;

/**
 * Created by Administrator on 2016/6/27 0027.
 */
public class Search extends BasePager {
    private static String str;
    //    private static final String FRAGMENT_MAIN_CONTENT = "fragment_main_content";
    private TextView etName;
    private ImageButton delete;
    private ImageButton del;
    private EditText goodsName;
    private TextView must;
    private ImageButton jump;
    private Handler handler;

    public Search(Activity activity) {
        super(activity);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public View initViews() {
        View view = View.inflate(mActivity, R.layout.search, null);
        etName = (TextView) view.findViewById(R.id.et_name);
        delete = (ImageButton) view.findViewById(R.id.btn_delete);
        del = (ImageButton) view.findViewById(R.id.btn_del);
        must = (TextView) view.findViewById(R.id.tv_nomust);
        jump = (ImageButton) view.findViewById(R.id.ib_jump);
        goodsName = (EditText) view.findViewById(R.id.et_goodsName);
        onClick();
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etName.getText()!=null){
                    etName.setText("");
                }
                delete.setVisibility(View.GONE);
            }
        });
        if (handler == null) {
            handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                   if (str!=null){
                       etName.setText(str);
                       delete.setVisibility(View.VISIBLE);
                       str = null;
                   }
                    handler.sendEmptyMessageDelayed(0, 1000);
                }
            };
            handler.sendEmptyMessageDelayed(0, 1000);
        }else {
            handler.sendEmptyMessageDelayed(0, 1000);
        }

        return view;
    }
    private void onClick() {
        goodsName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    del.setVisibility(View.VISIBLE);
                    must.setVisibility(View.GONE);
                    del.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if ((goodsName.getText()) != null) {
                                goodsName.setText("");
                                del.setVisibility(View.INVISIBLE);
                                must.setVisibility(View.VISIBLE);
                            }
                        }
                    });
                }
                if (TextUtils.isEmpty(s)) {
                    del.setVisibility(View.INVISIBLE);
                    must.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        View.OnClickListener click=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  MainActivity mainUi= (MainActivity) mActivity;
                mainUi.main_content.setVisibility(View.INVISIBLE);
                initFragment();*/
                Intent in=new Intent(mActivity, com.example.espressage.ExpressList.class);
                mActivity.startActivityForResult(in,0);
            }
        };
        jump.setOnClickListener(click);
        etName.setOnClickListener(click);



    }
        public static void  setName(String name){
            str = name;

        }


   /* private void initFragment() {
        //开启事务
        android.app.FragmentManager fm = mActivity.getFragmentManager();
        android.app.FragmentTransaction ft = fm.beginTransaction();
        //替换帧布局
        ft.replace(R.id.main_content, new ExpressList(), FRAGMENT_MAIN_CONTENT);
        //提交事务
        ft.addToBackStack(null);
        ft.commit();
    }*/


}
