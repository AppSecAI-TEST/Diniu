package com.maoyihan.www.kobe.http;

import com.maoyihan.www.kobe.config.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit工具类
 * Created by Administrator on 2016/9/15.
 */
public class RetrofitUtil {

    private final Retrofit retrofit;

    private RetrofitUtil() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(OkHttpManager.getInstance())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * 获取RetrofitUtil实例
     *
     * @return
     */
    public static RetrofitUtil getInstance() {
        return ApiHolder.retrofitUtil;
    }

    /**
     * ApiService
     *
     * @return
     */
    public <T> T getService(final Class<T> service) {

        return retrofit.create(service);
    }

    public IApiAction api() {
        return getInstance().getService(IApiAction.class);
    }

    private static class ApiHolder {
        private static final RetrofitUtil retrofitUtil = new RetrofitUtil();
    }

}
