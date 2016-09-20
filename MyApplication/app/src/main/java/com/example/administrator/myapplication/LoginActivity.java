package com.example.administrator.myapplication;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class LoginActivity extends ActionBarActivity {

    private Handler handler;
    private View bg_view;
    private int[] startColor = new int[]{0,102,102};
    private int[] endColor = new int[]{0,0,102};
    private int step = 2;
    private ImageView loading;


    private Button login_btn;
    private TextView weixin_login;
    private TextView message_login_switch;
    private LinearLayout password_login_switch;
    private RelativeLayout password_login;
    private LinearLayout message_login;
    private FrameLayout dialog_loading;
    private RelativeLayout create_user;
    private Button next;
    private ImageButton back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bg_view = (View) findViewById(R.id.login_bg);
        login_btn = (Button)findViewById(R.id.login_btn);
        weixin_login = (TextView)findViewById(R.id.weixin_login);
        message_login_switch = (TextView)findViewById(R.id.message_login_switch);
        password_login_switch = (LinearLayout)findViewById(R.id.login_switch);
        password_login = (RelativeLayout)findViewById(R.id.password_login);
        message_login = (LinearLayout)findViewById(R.id.message_login);
        create_user = (RelativeLayout)findViewById(R.id.create_user);
        next = (Button)findViewById(R.id.next);
        back = (ImageButton)findViewById(R.id.back);

        handler = new Handler() {
            public void handleMessage(Message msg) {
                setBg();
            }
        };

        eventBind();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true){
                        handler.sendMessage(handler.obtainMessage(100,""));
                        Thread.sleep(500);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


    private void set_create_user_visible(){
        Animation fade_anim = AnimationUtils.loadAnimation(create_user.getContext(), R.anim.abc_slide_in_bottom);
        fade_anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                create_user.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
//        create_user.setAnimation(fade_anim);
        create_user.startAnimation(fade_anim);

    }

    private void eventBind(){
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_create_user_visible();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_create_user_visible();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation fade_anim = AnimationUtils.loadAnimation(create_user.getContext(), R.anim.abc_slide_out_bottom);
                fade_anim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        create_user.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                create_user.startAnimation(fade_anim);

            }
        });



        weixin_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //      ���ض���
                dialogLoadingStart();
            }
        });

        message_login_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password_login.setAnimation(AnimationUtils.loadAnimation(password_login.getContext(),R.anim.abc_slide_out_top));
                password_login.setVisibility(View.GONE);
                message_login.setAnimation(AnimationUtils.loadAnimation(message_login.getContext(), R.anim.abc_slide_in_bottom));
                message_login.setVisibility(View.VISIBLE);
            }
        });
        password_login_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password_login.setAnimation(AnimationUtils.loadAnimation(password_login.getContext(),R.anim.abc_slide_in_bottom));
                password_login.setVisibility(View.VISIBLE);
                message_login.setAnimation(AnimationUtils.loadAnimation(message_login.getContext(), R.anim.abc_slide_out_top));
                message_login.setVisibility(View.GONE);
            }
        });






    }

    private void dialogLoadingStart(){
        loading = (ImageView)findViewById(R.id.loading);
        dialog_loading = (FrameLayout)findViewById(R.id.dialog_loading);
        dialog_loading.setVisibility(View.VISIBLE);

//        ��ת����
        Animation rIn = new RotateAnimation(0f, +360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rIn.setRepeatCount(Animation.INFINITE);
        rIn.setDuration(1000);
        rIn.setFillAfter(true);
        loading.startAnimation(rIn);
    }


    private void setBg() {

        int[] startColorArray = {1,2};
        int[] endColorArray = {1};
//        �仯����
        for(int i = 0;i<startColorArray.length;i++){
            startColor[startColorArray[1]] = startColor[startColorArray[1]]+step>150?-startColor[startColorArray[1]]:startColor[startColorArray[1]];
            startColor[startColorArray[1]] = (startColor[startColorArray[1]]+step>-80)&&(startColor[startColorArray[1]]+step<0)?-startColor[startColorArray[1]]:startColor[startColorArray[1]];
            startColor[startColorArray[1]] = startColor[startColorArray[1]] + step;
        }

        for(int i = 0;i<endColorArray.length;i++){
            endColor[endColorArray[i]] = endColor[endColorArray[i]]+step>153?-endColor[endColorArray[i]]:endColor[endColorArray[i]];
            endColor[endColorArray[i]] = (endColor[endColorArray[i]]+step>-0)&&(endColor[endColorArray[i]]+step<0)?-endColor[endColorArray[i]]:endColor[endColorArray[i]];
            endColor[endColorArray[i]] = endColor[endColorArray[i]] + step;
        }


        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TL_BR,
                new int[]{Color.rgb(Math.abs(startColor[0]),Math.abs(startColor[1]), Math.abs(startColor[2])),
                        Color.rgb(Math.abs(endColor[0]), Math.abs(endColor[1]),Math.abs(endColor[2]))});

        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        bg_view.setBackgroundDrawable(gradientDrawable);

    }
}