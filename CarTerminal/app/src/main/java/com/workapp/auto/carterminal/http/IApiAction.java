package com.workapp.auto.carterminal.http;

import com.workapp.auto.carterminal.base.BaseResponse;
import com.workapp.auto.carterminal.module.login.bean.SignInReturnBean;
import com.workapp.auto.carterminal.module.main.bean.CarInfoCheckReturnBean;
import com.workapp.auto.carterminal.module.main.bean.ReturnCarListReturnBean;

import retrofit2.http.GET;
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

    @GET("returncar/findReturnCarList")
    Observable<ReturnCarListReturnBean> findReturnCarList(@Query("lat") String lat, @Query("lng") String lng,
                                                          @Query("range") String range, @Query("page") String page,
                                                          @Query("size") String size, @Query("handleState") String handleState);

    @GET("returncar/CheckCarLogs")
    Observable<CarInfoCheckReturnBean> checkCarLogs(@Query("taskId") String taskId, @Query("type") String type);
}
