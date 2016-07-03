package com.example.espressage.extendsbasepager;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.view.View;

import com.example.espressage.R;
import com.example.espressage.Utils.MyDbOpenHelper;
import com.example.espressage.base.BasePager;
import com.example.espressage.fragment.NoHistoryFragment;


/**
 * Created by Administrator on 2016/6/27 0027.
 */
public class History extends BasePager {



    public History(Activity activity) {
        super(activity);
         }
    public View initViews() {
        Cursor cursor = MyDbOpenHelper.dbHelper.rawQuery("select 1 from expressInfo", null);
        int count = cursor.getCount();
        View view;
        if (count==0){
            view = View.inflate(mActivity, R.layout.no_history_fragment, null);
            return view;

           /* FragmentManager fm = mActivity.getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.vp_viewpager,new NoHistoryFragment());
            ft.commit();*/
        }

        return null;
    }
}
