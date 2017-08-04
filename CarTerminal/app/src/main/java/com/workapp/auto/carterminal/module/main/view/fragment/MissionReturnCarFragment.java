package com.workapp.auto.carterminal.module.main.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.workapp.auto.carterminal.R;
import com.workapp.auto.carterminal.base.BaseMapFragment;
import com.workapp.auto.carterminal.base.BaseResponse;
import com.workapp.auto.carterminal.base.MyApplication;
import com.workapp.auto.carterminal.http.RetrofitUtil;
import com.workapp.auto.carterminal.module.main.bean.CurrentTaskReturnBean;
import com.workapp.auto.carterminal.module.main.bean.ReturnCarListReturnBean;
import com.workapp.auto.carterminal.module.main.view.activity.MissionReturnCarInfoActivity;
import com.workapp.auto.carterminal.module.main.view.adapter.MissionReturnCarAdapter;
import com.workapp.auto.carterminal.utils.ToastUtils;

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


    private MissionReturnCarAdapter mMissionReturnCarAdapter;
    private int mPage = 1;
    private int mSize = 10;
    private AMap aMap;
    private double mEndLat;          //终点纬度
    private double mEndLng;          //终点经度
    private String mTaskId;


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
        getReturnCarList();
    }

    @Override
    protected void initListener() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPage = 1;
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
    }

    @Override
    public void onResume() {
        super.onResume();
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

    private void getReturnCarList() {
        //公司经纬度30.2765433873,119.9962377548
        RetrofitUtil.getInstance().api().findReturnCarList(String.valueOf(30.2765433873), String.valueOf(119.9962377548), String.valueOf(100000), String.valueOf(mPage), String.valueOf(mSize), "0")
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

    public void showMap(CurrentTaskReturnBean currentTaskReturnBean) {
        CurrentTaskReturnBean.DataBean data = currentTaskReturnBean.getData();
        refreshLayout.setVisibility(View.GONE);
        rlMap.setVisibility(View.VISIBLE);
        tvPlateNo.setText(data.getPlateNo());
        mEndLat = data.getLat();
        mEndLng = data.getLng();
        mTaskId = String.valueOf(data.getTaskId());
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
                            MissionFragment parentFragment = (MissionFragment) getParentFragment();
                            parentFragment.getCurrentTask();
                        } else {
                            ToastUtils.showShort(MyApplication.getInstance(), baseResponse.getMessage());
                        }
                    }
                });
    }
}
