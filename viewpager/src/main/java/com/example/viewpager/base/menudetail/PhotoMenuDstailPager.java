package com.example.viewpager.base.menudetail;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.example.viewpager.R;
import com.example.viewpager.Utils.CacheUtils;
import com.example.viewpager.Utils.MyApplication;
import com.example.viewpager.base.BaseMenuDetailPager;
import com.example.viewpager.global.GlobalContants;
import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/15 0015.
 */
public class PhotoMenuDstailPager extends BaseMenuDetailPager {

    private GridView gvPhoto;
    private ListView lvPhoto;
    private ArrayList<Bitmap> photoList;
    private PhotoAdapter mPhotoAdapter;
    private ImageButton btnPhoto;
    private GridPhotoAdapter gridAdapter;

    public PhotoMenuDstailPager(Activity activity, ImageButton btnPhoto) {
        super(activity);
        this.btnPhoto = btnPhoto;
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changDisPlay();
            }
        });


    }
private boolean isListView=true;
    /**
     * 切换方式
     */
    private void changDisPlay() {
        if (isListView){
            isListView=false;
            lvPhoto.setVisibility(View.GONE);
            gvPhoto.setVisibility(View.VISIBLE);
            btnPhoto.setImageResource(R.drawable.listview);
        }else {
            isListView=true;
            lvPhoto.setVisibility(View.VISIBLE);
            gvPhoto.setVisibility(View.GONE);
            btnPhoto.setImageResource(R.drawable.gridview);
        }
    }

    @Override
    protected View initViews() {
        /*TextView text = new TextView(mActivity);
        text.setText("菜单详情页——组图");
        text.setTextColor(Color.RED);
        text.setTextSize(30);
        text.setGravity(Gravity.CENTER);*/
        View view = View.inflate(mActivity, R.layout.menu_photo_pager, null);
        lvPhoto = (ListView) view.findViewById(R.id.lv_photo);
        gvPhoto = (GridView) view.findViewById(R.id.gv_photo);
        return view;
    }

    @Override
    public void initData() {
        String cache = CacheUtils.getCache(GlobalContants.PHOTO_PIC, mActivity);
        if (cache != null) {

        }
        getDataFromServer();
    }

    private void getDataFromServer() {
        ImageRequest request = new ImageRequest(GlobalContants.PHOTO_PIC, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                parseData(bitmap);
                CacheUtils.setCache(GlobalContants.PHOTO_PIC, bitmap.toString(), mActivity);
            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(mActivity, "加载失败", Toast.LENGTH_LONG).show();
            }
        });
        request.setTag("abcGET");
        MyApplication.getHttpQueues().add(request);
    }

    private void parseData(Bitmap bitmap) {
        photoList = new ArrayList<Bitmap>();
        for (int i = 0; i < 20; i++) {

            photoList.add(bitmap);
        }

        if (photoList != null) {
            mPhotoAdapter = new PhotoAdapter();
            gridAdapter = new GridPhotoAdapter();
            lvPhoto.setAdapter(mPhotoAdapter);
            gvPhoto.setAdapter(gridAdapter);
        }

    }
        class  GridPhotoAdapter extends BaseAdapter{

            @Override
            public int getCount() {
                return photoList.size();
            }

            @Override
            public Bitmap getItem(int position) {
                return photoList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder holder;
                if (convertView == null) {
                    convertView = View.inflate(mActivity, R.layout.grid_photo_item, null);
                    holder = new ViewHolder();
                    holder.ivPic = (ImageView) convertView.findViewById(R.id.iv_pic);
                    holder.ivPic.setScaleType(ImageView.ScaleType.FIT_XY);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                Bitmap item = getItem(position);
                holder.ivPic.setImageBitmap(item);
                return convertView;
            }
        }
    class PhotoAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return photoList.size();
        }

        @Override
        public Bitmap getItem(int position) {
            return photoList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(mActivity, R.layout.list_photo_item, null);
                holder = new ViewHolder();
                holder.ivPic = (ImageView) convertView.findViewById(R.id.iv_pic);
                holder.ivPic.setScaleType(ImageView.ScaleType.FIT_XY);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            Bitmap item = getItem(position);
            holder.ivPic.setImageBitmap(item);

            return convertView;
        }
    }

    class ViewHolder {
        public ImageView ivPic;
    }
}
