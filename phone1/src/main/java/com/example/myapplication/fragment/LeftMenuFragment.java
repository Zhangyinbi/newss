package com.example.myapplication.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.base.BasePager;
import com.example.myapplication.base.NewsPager;
import com.example.myapplication.view.NoScrollViewPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/16 0016.
 */
public class LeftMenuFragment extends BaseFragment {
    private ListView lvList;
    private int mCurrentPos;
    private MenuAdapter mMenuAdapter;
    public ArrayList<String> mMenuList;

    public void getContext(Activity content) {
        mActivity = content;
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_left_menu, null);
        lvList = (ListView) view.findViewById(R.id.lv_list);
        return view;
    }

    public void getTitle(ArrayList<String> list) {
        mMenuList = new ArrayList<String>();
//        Log.e("哈哈哈哈121213123", " "+list.size());
        mMenuList = list;
//        Log.e("哈哈哈哈121213123", " "+mMenuList.size());
//        lvList.removeAllViews();

//        Log.e("哈哈哈哈121213123", " "+mMenuList.size());

    }

    @Override
    public void initData() {
        //给mMenuList设置数据和下面的setMenuData方法作用是一样的，setMenuData是给mMenuList设置网络数据
        mMenuList = new ArrayList<String>();
        mMenuList.add("新闻");
        mMenuList.add("专题");
        mMenuList.add("组图");
        mMenuList.add("互动");

        mMenuAdapter = new MenuAdapter();
        lvList.setAdapter(mMenuAdapter);
        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCurrentPos = position;
                mMenuAdapter.notifyDataSetChanged();
//                Log.e("哈哈哈哈哈", " "+position );
//                setCurrentMenuDetailPager(position);
                String str = mMenuList.get(position);
                MainActivity mainUi = (MainActivity) mActivity;
//                NoScrollViewPager pager= (NoScrollViewPager) mainUi.findViewById(R.id.vp_content);
//                Log.e("哈哈哈负的", pager.toString() );
                NewsPager pager = mainUi.getNewsCenterPager();
                pager.tvTitle.setText(str);
                NoScrollViewPager viewPager= (NoScrollViewPager) mainUi.findViewById(R.id.vp_content);
                pager.flLayout.removeAllViews();

                TextView text=new TextView(mActivity);
                text.setText(str+"页签");
                text.setTextColor(Color.RED);
                text.setTextSize(30);
                text.setGravity(Gravity.CENTER);
                pager.flLayout.addView(text);
                toggleSlidingMenu();

            }
        });

    }

    /**
     * 切换SlidingMenu的状态
     */
    private void toggleSlidingMenu() {
        MainActivity mainUi = (MainActivity) mActivity;
        SlidingMenu slidingMenu = mainUi.getSlidingMenu();
        slidingMenu.toggle();//切换状态，显示时隐藏，隐藏时显示
    }

    private void setCurrentMenuDetailPager(int position) {

//        MainActivity mainUi = (MainActivity) mActivity;
     /*   ContentFragment fragment= mainUi.getM();//获取主页面fragment
        NewsPager pager=fragment.getNewsCenterPager();//获取主页面*/
//        setCurrentMenuDetailPager(position);//设置当前菜单详情页

    }


    class MenuAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mMenuList.size();
        }

        @Override
        public String getItem(int position) {
            return mMenuList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(mActivity, R.layout.list_menu_item, null);
            TextView tvTitle = (TextView) view.findViewById(R.id.tv_NewsMenu);
            String str = getItem(position);
            tvTitle.setText(str);
            if (mCurrentPos == position) {
                tvTitle.setEnabled(true);
            } else {
                tvTitle.setEnabled(false);
            }
            return view;
        }
    }
}
