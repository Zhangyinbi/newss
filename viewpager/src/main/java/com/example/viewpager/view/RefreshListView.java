package com.example.viewpager.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

import com.example.viewpager.R;

/**
 * Created by Administrator on 2016/6/21 0021.
 */
public class RefreshListView extends ListView {
    private View mHeaderView;
    public RefreshListView(Context context) {
        super(context);
        initHeaderView();
    }

    public RefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initHeaderView();

    }

    public RefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initHeaderView();

    }

    private void initHeaderView() {

        mHeaderView = View.inflate(getContext(), R.layout.refresh_header,null);
        this.addHeaderView(mHeaderView);
        mHeaderView.measure(0,0);

        int mHeaderViewheight=mHeaderView.getMeasuredHeight();
        mHeaderView.setPadding(0,-mHeaderViewheight,0,0);//隐藏头布局
    }
}
