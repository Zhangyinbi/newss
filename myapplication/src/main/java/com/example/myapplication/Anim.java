package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ScrollView;

public class Anim extends AppCompatActivity {
private ScrollView scrollView;
    private Animation btnAnim;
    private Animation layoutAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);


        btnAnim= AnimationUtils.loadAnimation(this,R.anim.btn_anim);
        btnAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
            layoutAnim=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.layout_anim);
                scrollView.startAnimation(layoutAnim);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        scrollView= (ScrollView) findViewById(R.id.scrollView);
        final Button myButton= (Button) findViewById(R.id.btn);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myButton.startAnimation(btnAnim);
            }
        });
    }
}
