package com.workapp.auto.carterminal.module.main.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.workapp.auto.carterminal.R;
import com.workapp.auto.carterminal.base.BaseMapFragment;
import com.workapp.auto.carterminal.base.MyApplication;
import com.workapp.auto.carterminal.http.RetrofitUtil;
import com.workapp.auto.carterminal.module.main.bean.DispatchListReturnBean;
import com.workapp.auto.carterminal.module.main.view.activity.DispatchCompleteActivity;
import com.workapp.auto.carterminal.module.main.view.adapter.LogDispatchAdapter;
import com.workapp.auto.carterminal.utils.ToastUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 日志——调度
 * Created by Administrator on 2017/8/2 0002.
 */

public class LogDispatchFragment extends BaseMapFragment {

    @Bind(R.id.common_recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.common_refreshLayout)
    SmartRefreshLayout refreshLayout;

    private LogDispatchAdapter mMissionDispatchAdapter;
    private int mPage = 1;
    private int mSize = 10;


    public static LogDispatchFragment newInstance() {
        Bundle args = new Bundle();
        LogDispatchFragment fragment = new LogDispatchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View getLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.common_single_list, null);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        initRecyclerView();
    }


    @Override
    protected void initData() {
        getNetData();
    }

    @Override
    protected void initListener() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPage = 1;
                getNetData();
            }
        });

        mMissionDispatchAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPage++;
                getNetData();
            }
        }, recyclerView);

        mMissionDispatchAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                List<DispatchListReturnBean.DataBean.ContentBean> data = adapter.getData();
                Intent intent = new Intent(getActivity(), DispatchCompleteActivity.class);
                intent.putExtra("taskId", data.get(position).getTaskId() + "");
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);

    }

    private void getNetData() {
        //公司经纬度30.2765433873,119.9962377548
        RetrofitUtil.getInstance().api().dispatchList("0", "0", String.valueOf(mPage), String.valueOf(mSize), "2")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DispatchListReturnBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showShort(MyApplication.getInstance(), MyApplication.getInstance().getString(R.string.network_on_error) + e.toString());
                    }

                    @Override
                    public void onNext(DispatchListReturnBean dispatchListReturnBean) {
                        if (dispatchListReturnBean.isSuccess()) {
                            List<DispatchListReturnBean.DataBean.ContentBean> list = dispatchListReturnBean.getData().getContent();
                            if (list != null && list.size() > 0) {
                                if (mPage == 1) {
                                    refreshLayout.finishRefresh();
                                    mMissionDispatchAdapter.setNewData(list);
                                } else {
                                    mMissionDispatchAdapter.addData(list);
                                    mMissionDispatchAdapter.loadMoreComplete();
                                }
                            } else {
                                if (mPage == 1) {
                                    refreshLayout.finishRefresh();
                                } else {
                                    mMissionDispatchAdapter.loadMoreEnd();
                                }
                            }
                        } else {
                            ToastUtils.showShort(MyApplication.getInstance(), dispatchListReturnBean.getMessage());
                            if (mPage == 1) {
                                refreshLayout.finishRefresh();
                            } else {
                                mMissionDispatchAdapter.loadMoreFail();
                            }
                        }
                    }
                });
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mMissionDispatchAdapter = new LogDispatchAdapter(getActivity());
        recyclerView.setAdapter(mMissionDispatchAdapter);
        refreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
    }

}
