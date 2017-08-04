package com.workapp.auto.carterminal.module.main.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.workapp.auto.carterminal.R;
import com.workapp.auto.carterminal.base.BaseFragment;
import com.workapp.auto.carterminal.base.MyApplication;
import com.workapp.auto.carterminal.http.RetrofitUtil;
import com.workapp.auto.carterminal.module.main.bean.CurrentTaskReturnBean;
import com.workapp.auto.carterminal.utils.TabLayoutUtils;
import com.workapp.auto.carterminal.utils.ToastUtils;
import com.workapp.auto.carterminal.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 任务
 * Created by owner on 2017/7/5.
 */

public class MissionFragment extends BaseFragment {
    @Bind(R.id.missionFrg_tabLayout)
    TabLayout tabLayout;                //有任务不显示
    @Bind(R.id.missionFrg_viewPager)
    NoScrollViewPager viewPager;
    @Bind(R.id.missionFrg_iv_title)
    ImageView ivTitle;                  //有任务不显示

    private String[] mTitles = {"还车", "调度"};
    private List<Fragment> mFragments = new ArrayList<>();
    private AMapLocationClientOption mLocationOption = null;  //高德定位
    private AMapLocationClient mLocationClient;
    private double mLatitude;                                //当前纬度
    private double mLongitude;                               //当前经度
    private boolean firstGetLngLat = true;

    public static MissionFragment newInstance() {
        Bundle args = new Bundle();
        MissionFragment fragment = new MissionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View getLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_mission, null);
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
        initLocationListener();
    }

    @Override
    protected void initData() {
        mFragments.add(MissionReturnCarFragment.newInstance());
        mFragments.add(MissionDispatchFragment.newInstance());
        viewPager.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];
            }
        });
        tabLayout.setupWithViewPager(viewPager);
        TabLayoutUtils.setIndicator(getActivity(), tabLayout, 60, 60);

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric;
    }

    private void initLocationListener() {
        mLocationClient = new AMapLocationClient(getActivity());
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        // 注意设置合适的定位时间的间隔（最小间隔支持为1000ms），并且在合适时间调用stopLocation()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用onDestroy()方法
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
        //启动定位
        mLocationClient.startLocation();
        AMapLocationListener aMapLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation amapLocation) {
                if (amapLocation != null) {
                    if (amapLocation.getErrorCode() == 0) {
                        //定位成功回调信息，设置相关消息
                        amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                        mLatitude = amapLocation.getLatitude();//获取纬度
                        mLongitude = amapLocation.getLongitude();//获取经度
                        amapLocation.getAccuracy();//获取精度信息
                       /* SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = new Date(amapLocation.getTime());
                        df.format(date);//定位时间*/
                       if(firstGetLngLat){
                           getCurrentTask();
                           firstGetLngLat = false;
                       }
                    } else {
                        //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                        Log.e("AmapError", "location Error, ErrCode:"
                                + amapLocation.getErrorCode() + ", errInfo:"
                                + amapLocation.getErrorInfo());
                    }
                }
            }
        };
        //设置定位监听
        mLocationClient.setLocationListener(aMapLocationListener);
    }

    public void getCurrentTask() {
        RetrofitUtil.getInstance().api().getCurrentTask(String.valueOf(mLatitude), String.valueOf(mLatitude))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CurrentTaskReturnBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showShort(MyApplication.getInstance(), MyApplication.getInstance().getString(R.string.network_on_error) + e.toString());
                    }

                    @Override
                    public void onNext(CurrentTaskReturnBean currentTaskReturnBean) {
                        MissionReturnCarFragment missionReturnCarFragment = (MissionReturnCarFragment) mFragments.get(0);
                        MissionDispatchFragment missionDispatchAdapter = (MissionDispatchFragment) mFragments.get(1);
                        if (currentTaskReturnBean.isSuccess() && currentTaskReturnBean.getData() != null) {
                            tabLayout.setVisibility(View.GONE);
                            ivTitle.setVisibility(View.GONE);
                            if (currentTaskReturnBean.getData().getTaskType().equals("0")) {
                                missionReturnCarFragment.showMap(currentTaskReturnBean, mLatitude, mLongitude);
                                missionDispatchAdapter.hideMap();
                            } else {
                                missionReturnCarFragment.hideMap();
                                missionDispatchAdapter.showMap(currentTaskReturnBean, mLatitude, mLongitude);
                            }
                        } else {
                            tabLayout.setVisibility(View.VISIBLE);
                            ivTitle.setVisibility(View.VISIBLE);
//                            ToastUtils.showShort(MyApplication.getInstance(), currentTaskReturnBean.getMessage());
                            missionDispatchAdapter.hideMap();
                            missionReturnCarFragment.hideMap();
                        }
                    }
                });
    }
}
