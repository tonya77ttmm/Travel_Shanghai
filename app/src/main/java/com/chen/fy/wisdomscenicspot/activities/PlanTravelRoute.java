package com.chen.fy.wisdomscenicspot.activities;


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.HeatmapTileProvider;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.maps.model.TileOverlayOptions;
import com.chen.fy.wisdomscenicspot.R;
import com.chen.fy.wisdomscenicspot.model.Plan;
import com.chen.fy.wisdomscenicspot.utils.UiUtils;


import com.amap.api.maps.MapView;
import android.view.View;

public class PlanTravelRoute extends AppCompatActivity {

    MapView mMapView = null;
    /**
     * 地图控制器
     */
    private AMap aMap;
    /**
     * 当前地图显示位置的经纬度和位置
     */
    private double nowLatitude;
    private double nowLongitude;

    private Marker marker1;   //普贤塔
    private Marker marker2;   //桂林抗战遗址
    private Marker marker3;   //象眼岩

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan_travel_route);

        //初始化view并设置接口
        initView(savedInstanceState);
        //申请权限
        requestLocation();
        Object[][] plan=line(1);
        //绘制Marker
        myMarker(plan);

    }
    public void initView(Bundle savedInstanceState) {

        //将状态栏字体变为黑色
        UiUtils.changeStatusBarTextImgColor(this, true);//将状态栏字体变为黑色

        //获取地图控件引用
        mMapView = findViewById(R.id.line_map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);

    }
    public Object[][] line(float day) {

    Object[][] plan = Plan.plan_travel(day);
        String tline = null;
    for(int i=0;i<plan.length;i++) {
        tline = String.valueOf(plan[i][0]);
        tline += " ";
        tline += String.valueOf(plan[i][1]);
        tline += " ";
        tline += String.valueOf(plan[i][2]);
        tline += "/n";
    }
        TextView textView2= (TextView) findViewById(R.id.textView2);
        textView2.setText(tline);


        return plan;
    }

    public void myMarker(Object plan[][]) {
        //Marker点击接口
        AMap.OnMarkerClickListener markerClickListener = new AMap.OnMarkerClickListener() {
            boolean flag = false;

            // marker 对象被点击时回调的接口
            // 返回true 表示接口已经相应事件，否则返回false
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (flag) {
                    marker.showInfoWindow();
                } else {
                    marker.hideInfoWindow();
                }
                flag = !flag;
                return true;
            }
        };
        //点击Marker后弹出的信息接口
        AMap.OnInfoWindowClickListener infoWindowClickListener = new AMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                marker.hideInfoWindow();
            }
        };
        for(int i=0;i<plan.length;i++) {
            String name=plan[i][0].toString();
            double lat=new Double(plan[i][1].toString());
            double lon=new Double(plan[i][2].toString());
            int num=(int)(1+Math.random()*(1000-1+1));

            LatLng latLng1 = new LatLng(lat, 110.296046);
            marker1 = aMap.addMarker(new MarkerOptions().
                    position(latLng1).title(name).snippet("人流量："+num).visible(true));

        }

        aMap.setOnMarkerClickListener(markerClickListener);
        aMap.setOnInfoWindowClickListener(infoWindowClickListener);
    }
    /**
     * 开始进行定位等功能
     */
    private void requestLocation() {
        //初始化地图控制器对象
        if (aMap == null) {
            aMap = mMapView.getMap();
        }

        initPositionDot();
        //设置地图的放缩级别
        // aMap.moveCamera(CameraUpdateFactory.zoomTo(20));
        if (getIntent() != null) {
            double latitude = getIntent().getDoubleExtra("Latitude", 40.0615841);
            double longitude = getIntent().getDoubleExtra("Longitude", 116.086286);
            navigateTo(latitude, longitude);
        }
        aMap.showIndoorMap(true);
    }

    /**
     * 根据经纬度移动到某一个位置并显示
     */
    private void navigateTo(double latitude, double longitude) {
        nowLatitude = latitude;
        nowLongitude = longitude;
        LatLng latLng = new LatLng(latitude, longitude);//构造一个位置
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 19));
    }

    /**
     * 实现定位蓝点
     */
    private void initPositionDot() {
        MyLocationStyle myLocationStyle;
        //1  初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);
        //   连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。
        //  （1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle = new MyLocationStyle();
        //2  设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.interval(3000);
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);
        //3  设置定位圆圈
        myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));// 设置圆形的边框颜色
        myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));// 设置圆形的填充颜色
        //4  设置定位蓝点的Style
        aMap.setMyLocationStyle(myLocationStyle);
        // aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
        //5  设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.setMyLocationEnabled(true);
    }

}
