package com.workapp.auto.carterminal.module.main.view.fragment;

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
import com.workapp.auto.carterminal.base.BaseFragment;
import com.workapp.auto.carterminal.base.MyApplication;
import com.workapp.auto.carterminal.http.RetrofitUtil;
import com.workapp.auto.carterminal.module.main.bean.ReturnCarListReturnBean;
import com.workapp.auto.carterminal.module.main.view.adapter.ReturnCarAdapter;
import com.workapp.auto.carterminal.utils.ToastUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 还车
 * Created by Administrator on 2017/8/2 0002.
 */

public class ReturnCarFragment extends BaseFragment {

    @Bind(R.id.common_recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.common_refreshLayout)
    SmartRefreshLayout refreshLayout;

    private ReturnCarAdapter mReturnCarAdapter;
    private int mPage = 1;
    private int mSize = 10;

    public static ReturnCarFragment newInstance() {
        Bundle args = new Bundle();
        ReturnCarFragment fragment = new ReturnCarFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View getLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.common_single_list, null);
    }

    @Override
    protected void initView(View view) {
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

        mReturnCarAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPage++;
                getNetData();
            }
        }, recyclerView);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void getNetData() {
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
                                    refreshLayout.finishLoadmore();
                                    mReturnCarAdapter.setNewData(list);
                                } else {
                                    mReturnCarAdapter.addData(list);
                                    mReturnCarAdapter.loadMoreComplete();
                                }
                            } else {
                                if (mPage == 1) {
                                    refreshLayout.finishLoadmore();
                                } else {
                                    mReturnCarAdapter.loadMoreEnd();
                                }
                            }
                        } else {
                            ToastUtils.showShort(MyApplication.getInstance(), returnCarListReturnBean.getMessage());
                            if (mPage == 1) {
                                refreshLayout.finishRefresh();
                            } else {
                                mReturnCarAdapter.loadMoreFail();
                            }
                        }
                    }
                });
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mReturnCarAdapter = new ReturnCarAdapter(getActivity());
        recyclerView.setAdapter(mReturnCarAdapter);
        refreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
    }
}
