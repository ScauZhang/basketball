package com.example.administrator.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.administrator.myapplication.base.Communicate_ListView_Adapter;
import com.example.administrator.myapplication.base.SwipBackActivity;
import com.example.administrator.myapplication.base.tool;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;


public class CommunicateActivity extends SwipBackActivity {

    private static final int REFRESH_COMPLETE = 0X110;
    private Handler mHandler;
    private SwipeRefreshLayout mSwipeLayout;
    private ListView lv_communicate;
    private HorizontalScrollView horizontalScrollView;
    private ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communicate);
        initView();
        bindData();
        initEvent();
//        setViewPager();
    }

    private void initView(){
        imageButton = (ImageButton)findViewById(R.id.back);
        lv_communicate = (ListView)findViewById(R.id.lv_communicate);
        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.id_swipe_ly);
        horizontalScrollView = (HorizontalScrollView)findViewById(R.id.horizontalScrollView);

        mSwipeLayout.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mHandler = new Handler()
        {
            public void handleMessage(android.os.Message msg)
            {
                switch (msg.what)
                {
                    case REFRESH_COMPLETE:
                        ((TextView)findViewById(R.id.title)).setText("刷新成功");
                        mSwipeLayout.setRefreshing(false);
                        break;

                }
            };
        };
//        viewPager = (ViewPager)findViewById(R.id.viewpager);
    }

    private void bindData(){
        List<Map<String,Object>> listMap = new ArrayList<Map<String, Object>>();
        Map<String,Object> item = new Hashtable<String,Object>();
        item.put(Communicate_ListView_Adapter.MAP_NAME,"SINGLEZHANG");
        item.put(Communicate_ListView_Adapter.MAP_TEXT,"I Love CURRY");
        item.put(Communicate_ListView_Adapter.MAP_SORT, 0);
        listMap.add(item);
        item = new Hashtable<String,Object>();
        item.put(Communicate_ListView_Adapter.MAP_NAME,"SINGLEZHANG1");
        item.put(Communicate_ListView_Adapter.MAP_TEXT,"I Love CURRY");
        item.put(Communicate_ListView_Adapter.MAP_SORT, 1);
        listMap.add(item);
        item = new Hashtable<String,Object>();
        item.put(Communicate_ListView_Adapter.MAP_NAME,"SINGLEZHANG1");
        item.put(Communicate_ListView_Adapter.MAP_TEXT,"I Love CURRY");
        item.put(Communicate_ListView_Adapter.MAP_SORT, 1);
        listMap.add(item);
        item = new Hashtable<String,Object>();
        item.put(Communicate_ListView_Adapter.MAP_NAME,"SINGLEZHANG1");
        item.put(Communicate_ListView_Adapter.MAP_TEXT,"I Love CURRY");
        item.put(Communicate_ListView_Adapter.MAP_SORT, 1);
        listMap.add(item);
        item = new Hashtable<String,Object>();
        item.put(Communicate_ListView_Adapter.MAP_NAME,"SINGLEZHANG1");
        item.put(Communicate_ListView_Adapter.MAP_TEXT,"I Love CURRY");
        item.put(Communicate_ListView_Adapter.MAP_SORT, 1);
        listMap.add(item);item = new Hashtable<String,Object>();
        item.put(Communicate_ListView_Adapter.MAP_NAME,"SINGLEZHANG1");
        item.put(Communicate_ListView_Adapter.MAP_TEXT,"I Love CURRY");
        item.put(Communicate_ListView_Adapter.MAP_SORT, 1);
        listMap.add(item);
        item = new Hashtable<String,Object>();
        item.put(Communicate_ListView_Adapter.MAP_NAME,"SINGLEZHANG1");
        item.put(Communicate_ListView_Adapter.MAP_TEXT,"I Love CURRY");
        item.put(Communicate_ListView_Adapter.MAP_SORT, 1);
        listMap.add(item);
        item = new Hashtable<String,Object>();
        item.put(Communicate_ListView_Adapter.MAP_NAME,"SINGLEZHANG1");
        item.put(Communicate_ListView_Adapter.MAP_TEXT,"I Love CURRY");
        item.put(Communicate_ListView_Adapter.MAP_SORT, 1);
        listMap.add(item);
        item = new Hashtable<String,Object>();
        item.put(Communicate_ListView_Adapter.MAP_NAME,"SINGLEZHANG1");
        item.put(Communicate_ListView_Adapter.MAP_TEXT,"I Love CURRY");
        item.put(Communicate_ListView_Adapter.MAP_SORT, 1);
        listMap.add(item);

        Communicate_ListView_Adapter communicate_listView_adapter = new Communicate_ListView_Adapter(getApplicationContext(),listMap);
        lv_communicate.setAdapter(communicate_listView_adapter);
    }

    private void initEvent(){
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continueMove(0,500);
            }
        });

        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);
            }
        });

        lv_communicate.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                boolean enable = false;
                if (lv_communicate != null && lv_communicate.getChildCount() > 0) {
                    // check if the first item of the list is visible
                    boolean firstItemVisible = lv_communicate.getFirstVisiblePosition() == 0;
                    // check if the top of the first item is visible
                    boolean topOfFirstItemVisible = lv_communicate.getChildAt(0).getTop() == 0;
                    // enabling or disabling the refresh layout
                    enable = firstItemVisible && topOfFirstItemVisible;

//                    //判断
//                    if(Math.abs(lv_communicate.getChildAt(0).getTop()) > tool.dip2px(getApplicationContext(),55)&&horizontalScrollView.getVisibility() == View.VISIBLE){
//                        Animation fade_anim = AnimationUtils.loadAnimation(horizontalScrollView.getContext(), R.anim.abc_fade_out);
//                        horizontalScrollView.startAnimation(fade_anim);
//                        horizontalScrollView.setVisibility(View.GONE);
//                        Animation maring = AnimationUtils.loadAnimation(lv_communicate.getContext(), R.anim.margin);
//                        lv_communicate.startAnimation(maring);
//                    }

                }
                mSwipeLayout.setEnabled(enable);
            }
        });

    }
}
