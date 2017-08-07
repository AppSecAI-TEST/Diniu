package com.workapp.auto.carterminal.module.main.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.workapp.auto.carterminal.R;
import com.workapp.auto.carterminal.base.BaseActivity;
import com.workapp.auto.carterminal.base.MyApplication;
import com.workapp.auto.carterminal.http.RetrofitUtil;
import com.workapp.auto.carterminal.module.main.bean.CarInfoCheckReturnBean;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wanggibin on 2017/6/14.
 */

public class CarPictureDetailActivity extends BaseActivity {

    @Bind(R.id.iv_add1)
    ImageView ivAdd1;
    @Bind(R.id.iv_add2)
    ImageView ivAdd2;
    @Bind(R.id.iv_add3)
    ImageView ivAdd3;
    @Bind(R.id.iv_add4)
    ImageView ivAdd4;
    Intent intent;

    @Override
    protected int getLayout() {
        return R.layout.activity_carpicturedetail;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        setTitle("图片详情");
        intent=getIntent();
    }

    @Override
    protected void initData() {
        RetrofitUtil.getInstance().api().checkCarLogs(intent.getStringExtra("taskId"), "5")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CarInfoCheckReturnBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        showMsg(MyApplication.getInstance().getString(R.string.network_on_error) + e.toString());
                    }

                    @Override
                    public void onNext(CarInfoCheckReturnBean carInfoCheckReturnBean) {
                        if (carInfoCheckReturnBean.isSuccess()) {
                            Glide.with(CarPictureDetailActivity.this)
                                    .load(carInfoCheckReturnBean.getData().getCarHeadPhoto())
                                    .placeholder(R.mipmap.picture)
                                    .error(R.mipmap.picture)
                                    .crossFade(1000)
                                    .into(ivAdd1);
                            Glide.with(CarPictureDetailActivity.this)
                                    .load(carInfoCheckReturnBean.getData().getLeftCarBodyPhoto())
                                    .placeholder(R.mipmap.picture)
                                    .error(R.mipmap.picture)
                                    .crossFade(1000)
                                    .into(ivAdd2);
                            Glide.with(CarPictureDetailActivity.this)
                                    .load(carInfoCheckReturnBean.getData().getCarEndPhoto())
                                    .placeholder(R.mipmap.picture)
                                    .error(R.mipmap.picture)
                                    .crossFade(1000)
                                    .into(ivAdd3);
                            Glide.with(CarPictureDetailActivity.this)
                                    .load(carInfoCheckReturnBean.getData().getRightCarBodyPhoto())
                                    .placeholder(R.mipmap.picture)
                                    .error(R.mipmap.picture)
                                    .crossFade(1000)
                                    .into(ivAdd4);
                        } else {
                            showMsg(carInfoCheckReturnBean.getMessage());
                        }
                    }
                });
    }

    @Override
    protected void initListener() {

    }

}
