package com.example.administrator.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

public class SplashActivity extends Activity {
    private RelativeLayout rlRoot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        rlRoot = (RelativeLayout) findViewById(R.id.rl_Root);
        startAnimation();
    }
    /**
     * 开启动画
     */
    private void startAnimation() {

        //RotateAnimation 旋转动画效果
        RotateAnimation ra = new RotateAnimation(0f, 360f, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        ra.setDuration(2000);//动画执行时间
        ra.setFillAfter(true);//动画执行完后保持最终状态


        //AlphaAnimation 透明度动画效果
        AlphaAnimation aa = new AlphaAnimation(0f, 1f);
        aa.setDuration(2000);
        aa.setFillAfter(true);

        //ScaleAnimation 缩放动画效果
        ScaleAnimation sa = new ScaleAnimation(0f, 1f, 0f, 1f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(2000);
        sa.setFillAfter(true);

        //动画集合
        AnimationSet set = new AnimationSet(false);
        set.addAnimation(ra);
        set.addAnimation(aa);
        set.addAnimation(sa);

        //设置动画的监听事件
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            /**
             * 动画结束
             * @param animation
             */
            @Override
            public void onAnimationEnd(Animation animation) {
                //跳转到引导界面
                startActivity(new Intent(SplashActivity.this, GuideActivity.class));
                finish();//结束当前页面
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        rlRoot.startAnimation(set);
    }


}
