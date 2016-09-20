package com.example.administrator.myapplication;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.amap.api.maps.AMap;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private MapView mapView;
    private AMap aMap;
    private DrawerLayout id_drawerlayout;
    private ImageButton mune_btn;
    private ListView id_lv;
    private UiSettings mUiSettings;//定义一个UiSettings对象



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapView = (MapView) findViewById(R.id.map);
        mUiSettings = aMap.getUiSettings();//实例化UiSettings类
        mapView.onCreate(savedInstanceState);

        init();
        initView();
        initEvent();
        list_data_bind();
        mapDeal();
    }

    private void mapDeal(){

        aMap.setLocationSource(new LocationSource() {
            @Override
            public void activate(OnLocationChangedListener onLocationChangedListener) {

            }

            @Override
            public void deactivate() {

            }
        });// 设置定位监听
        mUiSettings.setMyLocationButtonEnabled(true); // 显示默认的定位按钮
        aMap.setMyLocationEnabled(true);// 可触发定位并显示定位层

        List<LatLng> latLngs = new ArrayList<LatLng>();
        latLngs.add(new LatLng(39.999391,116.135972));
        latLngs.add(new LatLng(39.898323,116.057694));
        latLngs.add(new LatLng(39.900430,116.265061));
        latLngs.add(new LatLng(39.955192,116.140092));
        Polyline polyline =aMap.addPolyline(new PolylineOptions().
                addAll(latLngs).width(10).color(Color.argb(255, 1, 1, 1)));
    }

    private void initView(){
        id_drawerlayout = (DrawerLayout)findViewById(R.id.id_drawerlayout);
        mune_btn = (ImageButton)findViewById(R.id.menu_btn);
        id_lv = (ListView)findViewById(R.id.id_lv);
    }
    private void initEvent(){
        mune_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id_drawerlayout.openDrawer(Gravity.START);
            }
        });
    }

    private void list_data_bind(){
        List<HashMap<String,String>> list_data = new ArrayList<HashMap<String,String>>();
        HashMap<String, String> item = new HashMap<String, String>();
        item.put("item","新建球场");
        list_data.add(item);
        item = new HashMap<String, String>();
        item.put("item", "管理球场");
        list_data.add(item);
        item = new HashMap<String, String>();
        item.put("item", "审核球场");
        list_data.add(item);
        SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(),list_data, R.layout.menu_item,new String[]{"item"},new int[]{R.id.title});
        id_lv.setAdapter(adapter);
        id_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(MainActivity.this, CourtDetailActivity.class));
                overridePendingTransition(R.anim.activity_start,0);
                id_drawerlayout.closeDrawer(Gravity.START);
            }
        });
    }

    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
//        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        mapView.onSaveInstanceState(outState);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mapView.onDestroy();
    }


}
