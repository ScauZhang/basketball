package com.example.administrator.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.administrator.myapplication.base.Communicate_ListView_Adapter;
import com.example.administrator.myapplication.base.NoTouchScrollView;
import com.example.administrator.myapplication.base.NoTouchSwipRefreshLayout;
import com.example.administrator.myapplication.base.PullToRefreshLayout;
import com.example.administrator.myapplication.base.SwipBackActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;


public class CourtDetailActivity extends SwipBackActivity {

    private static final int REFRESH_COMPLETE = 0X110;
    private ScrollView scrollView;
    private Handler mHandler;
    private SwipeRefreshLayout mSwipeLayout;
    private Button get_more_btn;
    private ListView lv_communicate;
    private ImageButton imageButton;
//    private ViewPager viewPager;
//    private List<View> viewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_court_detail);
        initView();
        initEvent();
        bindData();
//        setViewPager();
    }

    private void initView(){
        imageButton = (ImageButton)findViewById(R.id.back);
        lv_communicate = (ListView)findViewById(R.id.lv_communicate);
        get_more_btn = (Button)findViewById(R.id.get_more_btn);
        scrollView = (ScrollView)findViewById(R.id.scrollView);
        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.id_swipe_ly);
//        mSwipeLayout.se
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

    private void initEvent(){
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);
            }
        });
        get_more_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CourtDetailActivity.this, CommunicateActivity.class));
                overridePendingTransition(R.anim.activity_start, 0);
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continueMove(0,500);
//                finish();
            }
        });

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

        Communicate_ListView_Adapter communicate_listView_adapter = new Communicate_ListView_Adapter(getApplicationContext(),listMap);
        lv_communicate.setAdapter(communicate_listView_adapter);
        setListViewHeightBasedOnChildren(lv_communicate);
    }


    private void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);

    }



//    private void setViewPager(){
////        LayoutInflater inflater=getLayoutInflater();
//
//        List<String> urlList = new ArrayList<String>();
//        urlList.add("https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=2319614035,1459920758&fm=58");
//        urlList.add("http://hiphotos.baidu.com/baidu/pic/item/7d8aebfebf3f9e125c6008d8.jpg");
//
//        viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
//
//        LayoutParams params = new LayoutParams(dip2px(this, 320),LayoutParams.WRAP_CONTENT);
//        LinearLayout.LayoutParams btn_params = new LinearLayout.LayoutParams(dip2px(this, 320),dip2px(this, 160));
//        ImageView imageView;
//        LinearLayout lineLayout;
//
//        for(String url : urlList){
//            imageView = new ImageView(this);
//            lineLayout = new LinearLayout(this);
//            lineLayout.setLayoutParams(params);
//            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//            imageView.setLayoutParams(btn_params);
//            new DownImgAsyncTask(imageView).execute(url);
//            lineLayout.addView(imageView);
//            viewList.add(lineLayout);
//        }
////        imageView.setImageResource(R.mipmap.timg);
////        imageView.setImageBitmap(tool.returnBitMap("http://hiphotos.baidu.com/baidu/pic/item/7d8aebfebf3f9e125c6008d8.jpg"));
//
//        PagerAdapter pagerAdapter = new PagerAdapter() {
//
//            @Override
//            public boolean isViewFromObject(View arg0, Object arg1) {
//                // TODO Auto-generated method stub
//                return arg0 == arg1;
//            }
//
//            @Override
//            public int getCount() {
//                // TODO Auto-generated method stub
//                return viewList.size();
//            }
//
//            @Override
//            public void destroyItem(ViewGroup container, int position,
//                                    Object object) {
//                // TODO Auto-generated method stub
//                container.removeView(viewList.get(position));
//            }
//
//            @Override
//            public Object instantiateItem(ViewGroup container, int position) {
//                // TODO Auto-generated method stub
//                container.addView(viewList.get(position));
//                return viewList.get(position);
//            }
//        };
//
//
//        viewPager.setAdapter(pagerAdapter);
//    }
//
////    下载网络图片
//    class DownImgAsyncTask extends AsyncTask<String, Void, Bitmap> {
//        private ImageView showImageView;
//        public DownImgAsyncTask(ImageView showImageView){
//            this.showImageView = showImageView;
//        }
//
//        @Override
//        protected void onPreExecute() {
//            // TODO Auto-generated method stub
//            super.onPreExecute();
//            showImageView.setImageBitmap(null);
////            showProgressBar();//显示进度条提示框
//
//        }
//
//        @Override
//        protected Bitmap doInBackground(String... params) {
//            // TODO Auto-generated method stub
//            Bitmap b = getImageBitmap(params[0]);
//            return b;
//        }
//
//        @Override
//        protected void onPostExecute(Bitmap result) {
//            // TODO Auto-generated method stub
//            super.onPostExecute(result);
//            if(result!=null){
////            dismissProgressBar();
//                showImageView.setImageBitmap(result);
//            }
//        }
//
//        /**
//         * 从指定URL获取图片
//         * @param url
//         * @return
//         */
//        private Bitmap getImageBitmap(String url){
//            URL imgUrl = null;
//            Bitmap bitmap = null;
//            try {
//                imgUrl = new URL(url);
//                HttpURLConnection conn = (HttpURLConnection)imgUrl.openConnection();
//                conn.setDoInput(true);
//                conn.connect();
//                InputStream is = conn.getInputStream();
//                bitmap = BitmapFactory.decodeStream(is);
//                is.close();
//            } catch (MalformedURLException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }catch(IOException e){
//                e.printStackTrace();
//            }
//            return bitmap;
//        }
//
//    }
//
//    /**
//     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
//     */
//    public static int px2dip(Context context, float pxValue) {
//        final float scale = context.getResources().getDisplayMetrics().density;
//        return (int) (pxValue / scale + 0.5f);
//    }
//
//    /**
//     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
//     */
//    public static int dip2px(Context context, float dpValue) {
//        final float scale = context.getResources().getDisplayMetrics().density;
//        return (int) (dpValue * scale + 0.5f);
//    }
}
