package com.example.myapplication;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static final String FRAGMENT_LEFT = "fragment_left";
    private static final String FRAGMENT_RIGHT = "fragment_right";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        //开启事务
        FragmentTransaction ft = fm.beginTransaction();
        //替换帧布局
        ft.replace(R.id.rl_left, new LeftFragment(),FRAGMENT_LEFT);
        ft.replace(R.id.rl_right, new RightFragment(), FRAGMENT_RIGHT);
        //提交事务
        ft.commit();









       /* new Thread(){
            @Override
            public void run() {
                Log.e("哈哈哈哈哈哈啊哈哈哈哈哈哈哈","哈哈哈" );
                try {
                    URL url = new URL("http://route.showapi.com/109-34?showapi_appid=20576&showapi_sign=31d63c00b46841a8811483c04a4c7e56");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(3000);
                    int code=conn.getResponseCode();
                    byte [] by=new byte[1024];
                    Log.e("哈哈哈哈哈哈啊哈哈哈哈哈哈哈","+code ");


                    if (code==200){
                        InputStream is=conn.getInputStream();
                        ByteArrayOutputStream bos=new ByteArrayOutputStream();
                        int len=0;
                        while ((len=is.read(by))!=-1){
                            bos.write(by,0,len);
                        }
                        String str=new String(bos.toByteArray());
                        Log.e("哈哈哈哈哈哈啊哈哈哈哈哈哈哈",str );
                        is.close();
                        bos.close();
                    }

                }  catch (IOException e) {
                    Log.e("哈哈哈哈",e.getMessage() );
                    e.printStackTrace();
                }
            }
        }.start();*/
    }
    public RightFragment getRightFragmentg(){
        FragmentManager fm = getSupportFragmentManager();
        RightFragment fragment = (RightFragment) fm.findFragmentByTag(FRAGMENT_RIGHT);
        return fragment;
    }




}
