package com.example.administrator.myapplication.base;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import java.sql.Timestamp;


public class SwipBackActivity extends ActionBarActivity {
    /**
     * 整个Activity视图的根视图
     */
    public View decorView;
    /**
     * 手指按下时的坐标
     */
    float downX, downY;
    /**
     * 手机屏幕的宽度和高度
     */
    float screenWidth, screenHeight;

    boolean isSelf = false;

    /*
    * 是否运动着
    * */
    boolean isMoving = false;

    Timestamp now_down;
    Timestamp now_up;

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int touchWidth = 20;
        if(isMoving) return true;
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            downX = event.getX();
            now_down = new Timestamp(System.currentTimeMillis());
            if(downX > screenWidth / touchWidth) {
                isSelf = false;
                return super.dispatchTouchEvent(event);
            }else{
                isSelf = true;
            }

        }else if(event.getAction() == MotionEvent.ACTION_MOVE){
            if(!isSelf) return super.dispatchTouchEvent(event);
            float moveDistanceX = event.getX() - downX;
            if(moveDistanceX > 0){
                decorView.setX(moveDistanceX);
            }

        }else if(event.getAction() == MotionEvent.ACTION_UP){
            if(!isSelf) return super.dispatchTouchEvent(event);
            float moveDistanceX = event.getX() - downX;
            now_up = new Timestamp(System.currentTimeMillis());
            int time1 = (int)(now_up.getTime() - now_down.getTime());
            int time = (int)(time1*(screenWidth-moveDistanceX)/moveDistanceX);
            //滑动 > 屏幕1/3 或者 速度>300
            if((moveDistanceX > screenWidth / 3)||((moveDistanceX/time1)>(screenWidth / 3/300))){
                // 如果滑动的距离超过了手机屏幕的一半, 滑动处屏幕后再结束当前Activity
                continueMove(moveDistanceX,time);
                downX = 0;
                return true;
            }else{
                // 如果滑动距离没有超过一半, 往回滑动
                rebackToLeft(moveDistanceX);
                downX = 0;
                return true;
            }
        }
        return false;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_activity2);

        // 获得decorView
        decorView = getWindow().getDecorView();


        // 获得手机屏幕的宽度和高度，单位像素
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;
    }


    /**
     * 从当前位置一直往右滑动到消失。
     * 这里使用了属性动画。
     */
    public void continueMove(float moveDistanceX,int time){
        isMoving = true;
        // 从当前位置移动到右侧。
        ValueAnimator anim = ValueAnimator.ofFloat(moveDistanceX, screenWidth);
        time = time > 700?700:time;
        anim.setDuration(time/2); // 一秒的时间结束, 为了简单这里固定为1秒
        anim.start();

        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 位移
                float x = (float) (animation.getAnimatedValue());
                decorView.setX(x);
            }
        });

        // 动画结束时结束当前Activity
        anim.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                isMoving = false;
                finish();
            }

        });
    }

    /**
     * Activity被滑动到中途时，滑回去~
     */
    private void rebackToLeft(float moveDistanceX){
        ObjectAnimator.ofFloat(decorView, "X", moveDistanceX, 0).setDuration(300).start();
    }





}
