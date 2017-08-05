package com.workapp.auto.carterminal.http;

import com.workapp.auto.carterminal.base.BaseResponse;
import com.workapp.auto.carterminal.module.login.bean.SignInReturnBean;
import com.workapp.auto.carterminal.module.main.bean.CarInfoCheckReturnBean;
import com.workapp.auto.carterminal.module.main.bean.CurrentTaskReturnBean;
import com.workapp.auto.carterminal.module.main.bean.DispatchCompleteReturnBean;
import com.workapp.auto.carterminal.module.main.bean.DispatchListReturnBean;
import com.workapp.auto.carterminal.module.main.bean.ReturnCarDetailReturnBean;
import com.workapp.auto.carterminal.module.main.bean.ReturnCarListReturnBean;

import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
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

    @GET("returncar/findReturnCarList")
    Observable<ReturnCarListReturnBean> findReturnCarList( @Query("range") String range,@Query("page") String page, @Query("size") String size, @Query("handleState") String handleState);

    @GET("returncar/CheckCarLogs")
    Observable<CarInfoCheckReturnBean> checkCarLogs(@Query("taskId") String taskId, @Query("type") String type);

    @POST("returncar/saveOrUpdateValidCarStatus")
    Observable<BaseResponse> saveOrUpdateValidCarStatus(@QueryMap Map<String, String> map);

    @GET("returncar/returnCarReceiveDetail")
    Observable<ReturnCarDetailReturnBean> returnCarReceiveDetail(@Query("taskId") String taskId);

//    @POST("returncar/updateCarStatus")
//    Observable<BaseResponse> updateCarStatus(@Query("taskId") String taskId, @Query("state") String state,@Query("remarks") String remarks);

    @POST("returncar/updateCarStatus")
    @FormUrlEncoded
    Observable<BaseResponse> updateCarStatus(@Field("taskId") String taskId, @Field("state") String state,@Field("remarks") String remarks);

    @GET("dispatch/dispatchList")
    Observable<DispatchListReturnBean> dispatchList(@Query("lat") String lat, @Query("lng") String lng,
                                                    @Query("page") String page, @Query("size") String size,
                                                    @Query("state") String state);

    @GET("dispatch/dispatchReceive")
    Observable<DispatchCompleteReturnBean> dispatchReceive(@Query("taskId") String taskId);

    @GET("task/getCurrentTask")
    Observable<CurrentTaskReturnBean> getCurrentTask(@Query("lat") String lat, @Query("lng") String lng);

    //领取任务
    @POST("task/getTask")
    Observable<BaseResponse> getTask(@Query("taskId") String taskId, @Query("taskType") String taskType);

}
