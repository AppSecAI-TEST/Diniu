package com.workapp.auto.carterminal.module.login.presenter;

import android.util.Log;

import com.workapp.auto.carterminal.R;
import com.workapp.auto.carterminal.base.BasePresenter;
import com.workapp.auto.carterminal.base.MyApplication;
import com.workapp.auto.carterminal.http.RetrofitUtil;
import com.workapp.auto.carterminal.module.login.bean.SignInReturnBean;
import com.workapp.auto.carterminal.module.login.view.function.ILoginView;
import com.workapp.auto.carterminal.utils.AppUtils;
import com.workapp.auto.carterminal.utils.SharedPreferencesUtils;
import com.workapp.auto.carterminal.utils.SystemUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/8/2 0002.
 */

public class LoginPresenter extends BasePresenter<ILoginView> {
    public void SignIn(String accountNo, String password) {
        mView.showLoading();
        RetrofitUtil.getInstance().api().signIn(accountNo, password,"1", AppUtils.getVersionName(MyApplication.getInstance()),SystemUtils.getSystemModel(),"1", SystemUtils.getSystemVersion(),SystemUtils.getIMEI(MyApplication.getInstance()),"jg")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SignInReturnBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.hideLoading();
                        mView.showMessage(MyApplication.getInstance().getString(R.string.network_on_error) + e.toString());
                    }

                    @Override
                    public void onNext(SignInReturnBean signInReturnBean) {
                        if (signInReturnBean.isSuccess()) {
                            mView.hideLoading();
                            mView.toMainAct();
                            SharedPreferencesUtils.put(MyApplication.getInstance(), "X-Auth-Token", signInReturnBean.getData().getToken());
                            Log.d("token", String.valueOf(SharedPreferencesUtils.get(MyApplication.getInstance(), "X-Auth-Token", "")));
                        } else {
                            mView.hideLoading();
                            mView.showMessage(signInReturnBean.getMessage());
                        }
                    }
                });
    }
}
