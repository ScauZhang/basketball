package com.example.administrator.myapplication.base;

import android.app.Activity;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;

public class NoTouchSwipRefreshLayout extends SwipeRefreshLayout {
    private float screenWidth;

    public NoTouchSwipRefreshLayout(Context context) {
        super(context);
        setMetrics();
    }

    public NoTouchSwipRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setMetrics();
    }

//    public NoTouchSwipRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        setMetrics();
//    }
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
