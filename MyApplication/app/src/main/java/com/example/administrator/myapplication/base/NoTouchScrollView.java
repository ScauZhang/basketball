package com.example.administrator.myapplication.base;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * 解决与Activity的TouchEvent事件冲突问题
 * Created by yangle on 2016/5/6.
 */
public class NoTouchScrollView extends ScrollView {

    private float screenWidth;

    public NoTouchScrollView(Context context) {
        super(context);
        setMetrics();
    }

    public NoTouchScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setMetrics();
    }

    public NoTouchScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setMetrics();
    }
    private void setMetrics(){
        DisplayMetrics metrics = new DisplayMetrics();
        Activity app = (Activity)getContext();
        app.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenWidth = metrics.widthPixels;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        float x;

        x = ev.getX();
        if(x < screenWidth / 20){
            return false;
        }else{
            return super.onTouchEvent(ev);
        }
//        if (ev.getAction() == MotionEvent.ACTION_UP) {
//            return false;
//        } else {
//            return super.onTouchEvent(ev);
//        }
    }
}