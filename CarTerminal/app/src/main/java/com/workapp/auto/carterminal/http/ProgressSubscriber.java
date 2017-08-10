package com.workapp.auto.carterminal.http;

import com.workapp.auto.carterminal.R;
import com.workapp.auto.carterminal.base.BaseActivity;
import com.workapp.auto.carterminal.base.MyApplication;
import com.workapp.auto.carterminal.utils.ToastUtils;

import rx.Subscriber;

/**
 * 请求开始显示progress，请求结束关闭progress
 * Created by Administrator on 2017/8/10 0010.
 */

public abstract class ProgressSubscriber<T> extends Subscriber<T> {
    private BaseActivity mActivity;

    public ProgressSubscriber(BaseActivity activity) {
        this.mActivity = activity;
    }

    @Override
    public void onStart() {
        super.onStart();
        mActivity.showLoadingView();
    }

    @Override
    public void onCompleted() {
        mActivity.hideLoadingView();
    }

    @Override
    public void onError(Throwable e) {
        mActivity.hideLoadingView();
        ToastUtils.showShort(MyApplication.getInstance(), MyApplication.getInstance().getString(R.string.network_on_error) + e.toString());
    }

    @Override
    public abstract void onNext(T t);

}
