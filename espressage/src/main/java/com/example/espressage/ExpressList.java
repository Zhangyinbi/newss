package com.example.espressage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.example.espressage.Utils.BitmapCache;
import com.example.espressage.Utils.MyApplication;
import com.example.espressage.bean.Express;
import com.example.espressage.global.DataInterface;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ExpressList extends Activity {

    private ListView lvCompany;
    private Express express;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_express_list);
        initView();
        initData();
    }

    private void initView() {
        lvCompany = (ListView) findViewById(R.id.lv_company);
    }

    private void initData() {
        getDataFromServer();
    }

    private void getDataFromServer() {
        RequestQueue queues = MyApplication.getHttpQueues();
        StringRequest request = new StringRequest(Request.Method.GET, DataInterface.EXPRESS_COMPANY_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e("----", "onResponse: ");
                parseData(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), "加载数据失败", Toast.LENGTH_LONG).show();
            }
        });
        request.setTag(DataInterface.EXPRESS_COMPANY_URL);
        queues.add(request);
    }

    ArrayList<Express.Data.DataItem> data;

    private void parseData(String s) {
        Gson gson = new Gson();
        express = gson.fromJson(s, Express.class);
        data = express.showapi_res_body.expressList;
        lvCompany.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return data.size();
            }

            @Override
            public Express.Data.DataItem getItem(int position) {
                return data.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                HolderView holder;
                if (convertView == null) {
                    convertView = View.inflate(getApplicationContext(), R.layout.list_item, null);
                    holder = new HolderView();
                    holder.ivPhoto = (ImageView) convertView.findViewById(R.id.iv_photo);
                    holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
                    convertView.setTag(holder);
                } else {
                    holder = (HolderView) convertView.getTag();
                }
                ImageLoader loder1 = new ImageLoader(MyApplication.getHttpQueues(), new BitmapCache());
                ImageLoader.ImageListener listener = ImageLoader.getImageListener(holder.ivPhoto, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
                loder1.get(getItem(position).imgUrl, listener);
                holder.tvName.setText(getItem(position).expName);
                return convertView;
            }
        });
        lvCompany.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in=new Intent();
                in.putExtra("key",((TextView)view.findViewById(R.id.tv_name)).getText());
                setResult(RESULT_OK,in);
                finish();
            }
        });
    }

    public class HolderView {
        public ImageView ivPhoto;
        public TextView tvName;
    }

}
