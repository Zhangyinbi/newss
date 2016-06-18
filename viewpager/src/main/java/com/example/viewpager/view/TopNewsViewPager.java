package com.example.viewpager.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * <--重写ViewPager中的dispatchTouchEven方法-->为也签页请求不要拦截往右的事件
 * Created by Administrator on 2016/6/17 0017.
 */
public class TopNewsViewPager extends ViewPager{

    public TopNewsViewPager(Context context) {
        super(context);
    }

    public TopNewsViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
            getParent().requestDisallowInterceptTouchEvent(true);

        return super.dispatchTouchEvent(ev);
    }

}
