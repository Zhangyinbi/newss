package com.example.myapplication;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.base.BasePager;
import com.example.myapplication.base.Government;
import com.example.myapplication.base.HomePager;
import com.example.myapplication.base.NewsPager;
import com.example.myapplication.base.Setting;
import com.example.myapplication.base.SmartService;
import com.example.myapplication.fragment.LeftMenuFragment;
import com.example.myapplication.view.NoScrollViewPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import java.util.ArrayList;

public class MainActivity extends SlidingFragmentActivity {
    private static final String FRAGMENT_LEFT_MENU = "fragment_left_menu";
    private NoScrollViewPager mViewPager;
    private TextView mTitle;
    private ArrayList<BasePager> mPagerlist;
    private RadioGroup rgGroup;
    private ArrayList<String> mMenuList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setBehindContentView(R.layout.left_menu);
        mViewPager = (NoScrollViewPager) findViewById(R.id.vp_content);
        mViewPager.setOverScrollMode(mViewPager.OVER_SCROLL_NEVER);//不让滑动
        rgGroup= (RadioGroup) findViewById(R.id.rgGroup);

        SlidingMenu slidingMenu = getSlidingMenu();//获取侧边栏对象
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//设置全屏触摸
        slidingMenu.setBehindOffset(260);//设置预留屏幕的宽度
        initFragment();
        rgGroup.check(R.id.rb_home);//默认勾选的首页
        mPagerlist = new ArrayList<BasePager>();
        mPagerlist.add(new HomePager(this));
        mPagerlist.add(new NewsPager(this));
        mPagerlist.add(new SmartService(this));
        mPagerlist.add(new Government(this));
        mPagerlist.add(new Setting(this));

        mViewPager.setAdapter(new ContentAdapter());
        rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                LeftMenuFragment fragment=new LeftMenuFragment();
                switch (checkedId) {
                    case R.id.rb_home:
//                        mViewPager.setCurrentItem(0);//设置当前页面，有切换效果
                        mViewPager.setCurrentItem(0, false);//设置当前页面，去掉切换效果
                        break;
                    case R.id.rb_news:
                      /*  setNewsTitleData();
                        fragment.getTitle(mMenuList);*/

                        mViewPager.setCurrentItem(1, false);
                        break;
                    case R.id.rb_smartService:
                     /*   setServiceTitleData();
                        fragment.getTitle(mMenuList);
*/
                        mViewPager.setCurrentItem(2, false);
                        break;
                    case R.id.rb_government:
                      /*  setGovernmentTitleData();
                        fragment.getTitle(mMenuList);
*/
                        mViewPager.setCurrentItem(3, false);
                        break;
                    case R.id.rb_setting:
                        mViewPager.setCurrentItem(4, false);
                        break;
                }
            }
        });
        mPagerlist.get(0).initData();//手动初始化第一个页面
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mPagerlist.get(position).initData();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }
 /*   public void setNewsTitleData(){
        mMenuList=new ArrayList<String>();
        mMenuList.add("新闻");
        mMenuList.add("专题");
        mMenuList.add("组图");
        mMenuList.add("互动");
    }
    public void setServiceTitleData(){
        mMenuList=new ArrayList<String>();
        mMenuList.add("服务");
    }
    public void setGovernmentTitleData(){
        mMenuList=new ArrayList<String>();
        mMenuList.add("政治");
    }*/
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
            BasePager pager = mPagerlist.get(position);
            container.addView(pager.mRootView);
//            pager.initData();
            return pager.mRootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    /**
     * 初始化Fragment
     */
    private void initFragment() {
        FragmentManager fm = getSupportFragmentManager();
        //开启事务
        FragmentTransaction ft = fm.beginTransaction();
        //替换帧布局
        ft.replace(R.id.left_menu, new LeftMenuFragment(), FRAGMENT_LEFT_MENU);
        //提交事务
        ft.commit();

    }
    public NewsPager getNewsCenterPager() {

            return (NewsPager) mPagerlist.get(1);

    }


    public LeftMenuFragment getLeftMenuFragment() {
        FragmentManager fm = getSupportFragmentManager();
        LeftMenuFragment fragment = (LeftMenuFragment) fm.findFragmentByTag(FRAGMENT_LEFT_MENU);
        return fragment;
    }

}
