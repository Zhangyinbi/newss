package com.example.espressage;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.espressage.base.BasePager;
import com.example.espressage.extendsbasepager.CallPhone;
import com.example.espressage.extendsbasepager.History;
import com.example.espressage.extendsbasepager.Search;
import com.example.espressage.view.NoScrollViewPager;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity{

    private TextView tvTitle;
    private RadioGroup rGroup;
    private ArrayList<BasePager> mPagerlist;

    private NoScrollViewPager mViewPager;
    public FrameLayout main_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_content = (FrameLayout) findViewById(R.id.main_content);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        rGroup = (RadioGroup) findViewById(R.id.rGroup);
        mViewPager = (NoScrollViewPager) findViewById(R.id.vp_viewpager);
        initData();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void initData() {
        mPagerlist=new ArrayList<BasePager>();
        mPagerlist.add((new Search(this)));
        mPagerlist.add(new History(this));
        mPagerlist.add(new CallPhone(this));
        mViewPager.setAdapter(new ContentAdapter());

        rGroup.check(R.id.rb_search);
        tvTitle.setText(((RadioButton)rGroup.getChildAt(0)).getText());
        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_search:
                        RadioButton rb= (RadioButton) group.getChildAt(0);
                        mViewPager.setCurrentItem(0,false);
                        tvTitle.setText(rb.getText());
                        break;
                    case R.id.rb_history:
                        RadioButton rb1= (RadioButton) group.getChildAt(1);
                        mViewPager.setCurrentItem(1,false);
                        tvTitle.setText(rb1.getText());
                        break;
                    case R.id.rb_tell:
                        RadioButton rb2= (RadioButton) group.getChildAt(2);
                        mViewPager.setCurrentItem(2,false);
                        tvTitle.setText(rb2.getText());
                        break;
                }
            }
        });
    }

    class ContentAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mPagerlist.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
          container.addView(mPagerlist.get(position).mRootView);
            return mPagerlist.get(position).mRootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 0:
                if (resultCode==RESULT_OK){
                    String companyName = data.getStringExtra("key");
                    Search.setName(companyName);
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
