package com.workapp.auto.carterminal.module.main.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.workapp.auto.carterminal.R;
import com.workapp.auto.carterminal.base.BaseMapFragment;
import com.workapp.auto.carterminal.base.BaseResponse;
import com.workapp.auto.carterminal.base.MyApplication;
import com.workapp.auto.carterminal.config.AppConstant;
import com.workapp.auto.carterminal.http.RetrofitUtil;
import com.workapp.auto.carterminal.module.main.bean.BooleanReturnBean;
import com.workapp.auto.carterminal.module.main.bean.CurrentTaskReturnBean;
import com.workapp.auto.carterminal.module.main.bean.ReturnCarListReturnBean;
import com.workapp.auto.carterminal.module.main.view.activity.MissionReturnCarInfoActivity;
import com.workapp.auto.carterminal.module.main.view.adapter.MissionReturnCarAdapter;
import com.workapp.auto.carterminal.utils.ToastUtils;
import com.workapp.auto.carterminal.widget.aMap.DrivingRouteOverLay;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 任务——还车
 * Created by Administrator on 2017/8/2 0002.
 */

public class MissionReturnCarFragment extends BaseMapFragment {

    @Bind(R.id.common_recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.common_refreshLayout)
    SmartRefreshLayout refreshLayout;  //有任务不显示
    @Bind(R.id.mapPage_rl_map)
    RelativeLayout rlMap;              //有任务显示
    @Bind(R.id.mapPage_map)
    MapView mapView;
    @Bind(R.id.mapPage_tv_plateNo)
    TextView tvPlateNo;
    @Bind(R.id.mapPage_tv_endName)
    TextView tvEndName;
    @Bind(R.id.mapPage_tv_open)
    TextView tvOpen;
    @Bind(R.id.mapPage_tv_close)
    TextView tvClose;
    @Bind(R.id.mapPage_btn_next)
    Button btnNext;
    @Bind(R.id.mapPage_btn_location)
    ImageView ivLocation;


    private MissionReturnCarAdapter mMissionReturnCarAdapter;
    private int mPage = 1;
    private int mSize = 10;
    private AMap aMap;
    private double mStartLat;        //起点纬度
    private double mStartLng;        //起点经度
    private double mEndLat;          //终点纬度
    private double mEndLng;          //终点经度
    private String mTaskId;
    private AMapLocationClientOption mLocationOption = null; //高德定位
    private AMapLocationClient mLocationClient;
    private double mLatitude;                                //当前纬度
    private double mLongitude;                               //当前经度
    private boolean firstGetLngLat = true;                   //是否是第一次定位
    private String mFrameNo;                                 //车架号,用来开门关门
    private Marker mLocationMarker;                          //当前位置marker
    private LatLng mCurrentLatLng;                           //当前位置经纬度

    public static MissionReturnCarFragment newInstance() {
        Bundle args = new Bundle();
        MissionReturnCarFragment fragment = new MissionReturnCarFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View getLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.common_map_list, null);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        initLocationListener();
        mapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mapView.getMap();
        }
        initRecyclerView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPage = 1;
                getCurrentTask();
                getReturnCarList();
            }
        });

        mMissionReturnCarAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPage++;
                getReturnCarList();
            }
        }, recyclerView);

        mMissionReturnCarAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                List<ReturnCarListReturnBean.DataBean.ContentBean> data = adapter.getData();
                getTask(data.get(position).getTaskId() + "");
            }
        });

        btnNext.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MissionReturnCarInfoActivity.class);
            intent.putExtra("taskId", mTaskId);
            getActivity().startActivity(intent);
        });

        tvOpen.setOnClickListener(v -> {
            openCarDoor();
        });

        tvClose.setOnClickListener(v -> {
            closeDoor();
        });

        ivLocation.setOnClickListener(v -> {
            //然后可以移动到定位点,使用animateCamera就有动画效果
            aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mCurrentLatLng, 15));
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getCurrentTask();
        if (mapView != null) {
            mapView.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mapView != null) {
            mapView.onPause();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        if (mapView != null) {
            mapView.onDestroy();
        }
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
                        if (firstGetLngLat) {
                            mStartLat = amapLocation.getLatitude();
                            mStartLng = amapLocation.getLongitude();
                            getCurrentTask();
                            getReturnCarList();
                            firstGetLngLat = false;
                        }
                        //当前点
                        mCurrentLatLng = new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude());
                        //添加Marker显示定位位置
                        if (mLocationMarker == null) {
                            //如果是空的添加一个新的,icon方法就是设置定位图标，可以自定义
                            mLocationMarker = aMap.addMarker(new MarkerOptions()
                                    .position(mCurrentLatLng)
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_xandian)));
                        } else {
                            //已经添加过了，修改位置即可
                            mLocationMarker.setPosition(mCurrentLatLng);
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
                        MissionFragment parentFragment = (MissionFragment) getParentFragment();
                        if (currentTaskReturnBean.isSuccess() && currentTaskReturnBean.getData() != null) {
                            if (currentTaskReturnBean.getData().getTaskType().equals("0")) {
                                parentFragment.hideTabView();
                                parentFragment.setCurrentViewPagerItem(0);
                                showMap(currentTaskReturnBean, mLatitude, mLongitude);
                            } else {
                                hideMap();
                            }
                        } else {
                            parentFragment.showTabView();
//                            ToastUtils.showShort(MyApplication.getInstance(), currentTaskReturnBean.getMessage());
                            hideMap();
                        }
                    }
                });
    }

    private void getReturnCarList() {
        //公司经纬度30.2765433873,119.9962377548
        RetrofitUtil.getInstance().api().findReturnCarList(String.valueOf(mLatitude), String.valueOf(mLongitude), AppConstant.RETURNCAR_LIST_RANGE, String.valueOf(mPage), String.valueOf(mSize), "0")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ReturnCarListReturnBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showShort(MyApplication.getInstance(), MyApplication.getInstance().getString(R.string.network_on_error) + e.toString());
                    }

                    @Override
                    public void onNext(ReturnCarListReturnBean returnCarListReturnBean) {
                        if (returnCarListReturnBean.isSuccess()) {
                            List<ReturnCarListReturnBean.DataBean.ContentBean> list = returnCarListReturnBean.getData().getContent();
                            if (list != null && list.size() > 0) {
                                if (mPage == 1) {
                                    refreshLayout.finishRefresh();
                                    mMissionReturnCarAdapter.setNewData(list);
                                } else {
                                    mMissionReturnCarAdapter.addData(list);
                                    mMissionReturnCarAdapter.loadMoreComplete();
                                }
                            } else {
                                if (mPage == 1) {
                                    refreshLayout.finishRefresh();
                                } else {
                                    mMissionReturnCarAdapter.loadMoreEnd();
                                }
                            }
                        } else {
                            ToastUtils.showShort(MyApplication.getInstance(), returnCarListReturnBean.getMessage());
                            if (mPage == 1) {
                                refreshLayout.finishRefresh();
                            } else {
                                mMissionReturnCarAdapter.loadMoreFail();
                            }
                        }
                    }
                });
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mMissionReturnCarAdapter = new MissionReturnCarAdapter(getActivity());
        recyclerView.setAdapter(mMissionReturnCarAdapter);
        refreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
    }

    public void showMap(CurrentTaskReturnBean currentTaskReturnBean, double currentLat, double currentLng) {
        CurrentTaskReturnBean.DataBean data = currentTaskReturnBean.getData();
        refreshLayout.setVisibility(View.GONE);
        rlMap.setVisibility(View.VISIBLE);
        tvPlateNo.setText(data.getPlateNo());
        mEndLat = data.getLat();
        mEndLng = data.getLng();
        mTaskId = String.valueOf(data.getTaskId());
        mFrameNo = data.getFrameNo();
        drawMapLine();
    }

    public void hideMap() {
        refreshLayout.setVisibility(View.VISIBLE);
        rlMap.setVisibility(View.GONE);
    }

    private void getTask(String taskId) {
        //任务类型（0-还车 1-调度）
        RetrofitUtil.getInstance().api().getTask(taskId, "0")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showShort(MyApplication.getInstance(), MyApplication.getInstance().getString(R.string.network_on_error) + e.toString());
                    }

                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if (baseResponse.isSuccess()) {
                            getCurrentTask();
                        } else {
                            ToastUtils.showShort(MyApplication.getInstance(), baseResponse.getMessage());
                        }
                    }
                });
    }

    private void drawMapLine() {
        RouteSearch routeSearch = new RouteSearch(getActivity());
        LatLonPoint latLonPointStart = new LatLonPoint(mStartLat, mStartLng);
        LatLonPoint latLonPointEnd = new LatLonPoint(mEndLat, mEndLng);
        RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(latLonPointStart, latLonPointEnd);
        RouteSearch.DriveRouteQuery query = new RouteSearch.DriveRouteQuery(fromAndTo, RouteSearch.DRIVING_SINGLE_DEFAULT, null, null, "");
        routeSearch.calculateDriveRouteAsyn(query);
        routeSearch.setRouteSearchListener(new RouteSearch.OnRouteSearchListener() {
            @Override
            public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {

            }

            @Override
            public void onDriveRouteSearched(DriveRouteResult result, int errorCode) {
                aMap.clear();// 清理地图上的所有覆盖物
                if (errorCode == AMapException.CODE_AMAP_SUCCESS) {
                    if (result != null && result.getPaths() != null) {
                        if (result.getPaths().size() > 0) {
                            DriveRouteResult mDriveRouteResult = result;
                            final DrivePath drivePath = mDriveRouteResult.getPaths()
                                    .get(0);
                            DrivingRouteOverLay drivingRouteOverlay = new DrivingRouteOverLay(
                                    getActivity(), aMap, drivePath,
                                    mDriveRouteResult.getStartPos(),
                                    mDriveRouteResult.getTargetPos(), null);
                            drivingRouteOverlay.setNodeIconVisibility(false);//设置节点marker是否显示
                            drivingRouteOverlay.setIsColorfulline(true);//是否用颜色展示交通拥堵情况，默认true
                            drivingRouteOverlay.removeFromMap();
                            drivingRouteOverlay.addToMap();
                            drivingRouteOverlay.zoomToSpan();
                          /*  mBottomLayout.setVisibility(View.VISIBLE);
                            int dis = (int) drivePath.getDistance();
                            int dur = (int) drivePath.getDuration();
                            String des = AMapUtil.getFriendlyTime(dur)+"("+AMapUtil.getFriendlyLength(dis)+")";
                            mRotueTimeDes.setText(des);
                            mRouteDetailDes.setVisibility(View.VISIBLE);
                            int taxiCost = (int) mDriveRouteResult.getTaxiCost();
                            mRouteDetailDes.setText("打车约"+taxiCost+"元");
                            mBottomLayout.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(mContext,
                                            DriveRouteDetailActivity.class);
                                    intent.putExtra("drive_path", drivePath);
                                    intent.putExtra("drive_result",
                                            mDriveRouteResult);
                                    startActivity(intent);
                                }
                            });*/
                        } else if (result != null && result.getPaths() == null) {
                            ToastUtils.showShort(MyApplication.getInstance(), R.string.no_result);
                        }

                    } else {
                        ToastUtils.showShort(MyApplication.getInstance(), R.string.no_result);
                    }
                } else {
                    ToastUtils.showShort(MyApplication.getInstance(), errorCode + "");
                }

            }

            @Override
            public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {

            }

            @Override
            public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {

            }
        });
    }

    private void openCarDoor() {
        RetrofitUtil.getInstance().api().openCarDoor(mFrameNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BooleanReturnBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showShort(MyApplication.getInstance(), MyApplication.getInstance().getString(R.string.network_on_error) + e.toString());
                    }

                    @Override
                    public void onNext(BooleanReturnBean booleanReturnBean) {
                        if (booleanReturnBean.isSuccess()) {
                            if (booleanReturnBean.isData()) {
                                ToastUtils.showShort(MyApplication.getInstance(), "开门成功");
                            }else{
                                ToastUtils.showShort(MyApplication.getInstance(), "开门失败");
                            }
                        } else {
                            ToastUtils.showShort(MyApplication.getInstance(), booleanReturnBean.getMessage());
                        }
                    }
                });
    }

    private void closeDoor() {
        RetrofitUtil.getInstance().api().closeCarDoor(mFrameNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BooleanReturnBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showShort(MyApplication.getInstance(), MyApplication.getInstance().getString(R.string.network_on_error) + e.toString());
                    }

                    @Override
                    public void onNext(BooleanReturnBean booleanReturnBean) {
                        if (booleanReturnBean.isSuccess()) {
                            if (booleanReturnBean.isData()) {
                                ToastUtils.showShort(MyApplication.getInstance(), "关门成功");
                            }else{
                                ToastUtils.showShort(MyApplication.getInstance(), "关门失败");
                            }
                        } else {
                            ToastUtils.showShort(MyApplication.getInstance(), booleanReturnBean.getMessage());
                        }
                    }
                });
    }

}
