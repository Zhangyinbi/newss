package com.example.myapplication;

import android.app.NotificationManager;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class Main5Activity extends AppCompatActivity {
    AlertDialog.Builder dialog ;
    AlertDialog dia;

   /* @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(KeyEvent.KEYCODE_BACK==keyCode){
            if   ( dia!=null   &&  dia.isShowing ( ) )   {
                dia.dismiss();
                Log.e("哈哈哈哈", "11111 ——————");
            }
        }

        return super.onKeyDown(keyCode, event);
    }

    *//*@Override
    public void onKeyDown() {
        Log.e("test",dia.isShowing ( )+"");
        if   ( dia!=null   &&  dia.isShowing ( ) )   {
             dia.dismiss();
            Log.e("哈哈哈哈", "11111 ——————");
        }   else   {
            super . onBackPressed ( ) ;
        }
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new AlertDialog.Builder(Main5Activity.this);
                dialog.setTitle("NIHAO");
                dialog.setMessage("something");
                dialog.setCancelable(false);

                dialog.setPositiveButton("queding", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                dialog.setNegativeButton("quxiao", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                dia=dialog.create();
                dia.show();
                dia.setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                            if (null != dia && dia.isShowing()) {
                                dia.dismiss();
                            }
                        }
                        return false;
                    }
                });
            }
        });
    }
}
