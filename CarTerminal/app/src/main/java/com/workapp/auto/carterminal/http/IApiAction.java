package com.workapp.auto.carterminal.http;

import com.workapp.auto.carterminal.base.BaseResponse;
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

    @POST("sms/sendSmsCode")
    Observable<BaseResponse> sendSmsCode(@Query("phone") String phone);

    @POST("sms/checkSmsCode")
    Observable<BaseResponse> checkSmsCode(@Query("phone") String phone, @Query("smsCode") String smsCode);

    @POST("login/forgetPassword")
    Observable<BaseResponse> forgetPsw(@Query("phone") String phone, @Query("password") String password);
}
