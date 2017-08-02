package com.workapp.auto.carterminal.http;

import com.workapp.auto.carterminal.module.login.bean.SignInReturnBean;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * API请求接口
 * Created by Administrator on 2016/9/15.
 */
public interface IApiAction {
    @POST("login/signin")
    Observable<SignInReturnBean> signIn(@Query("accountNo") String accountNo, @Query("password") String password);
}
