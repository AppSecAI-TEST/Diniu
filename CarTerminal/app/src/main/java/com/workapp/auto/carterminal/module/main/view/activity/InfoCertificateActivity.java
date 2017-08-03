package com.workapp.auto.carterminal.module.main.view.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import com.workapp.auto.carterminal.R;
import com.workapp.auto.carterminal.base.BaseActivity;
import com.workapp.auto.carterminal.base.MyApplication;
import com.workapp.auto.carterminal.http.RetrofitUtil;
import com.workapp.auto.carterminal.module.main.bean.CarInfoCheckBean;
import com.workapp.auto.carterminal.module.main.bean.CarInfoCheckReturnBean;
import com.workapp.auto.carterminal.module.main.view.adapter.CarInfoCheckAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 信息-证书
 * Created by Administrator on 2017/8/3 0003.
 */

public class InfoCertificateActivity extends BaseActivity {
    @Bind(R.id.infoCertificate_recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.infoCertificate_btn_save)
    Button btnSave;

    private CarInfoCheckAdapter mCarInfoCheckAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_info_certificate;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        setTitle("证书");
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mCarInfoCheckAdapter = new CarInfoCheckAdapter();
        recyclerView.setAdapter(mCarInfoCheckAdapter);
    }

    @Override
    protected void initData() {
        getNetData();
    }

    @Override
    protected void initListener() {
        btnSave.setOnClickListener(v -> {
            List<CarInfoCheckBean> data = mCarInfoCheckAdapter.getData();
            Log.d("check", data.get(0).getStatus() + "" + data.get(1).getStatus() + "" + data.get(2).getStatus() + "" + data.get(3).getStatus() + "");
        });
    }

    private void getNetData() {
        RetrofitUtil.getInstance().api().checkCarLogs("001", "1")
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
                            List<CarInfoCheckBean> carInfoCheckBeanList = new ArrayList<>();
                            CarInfoCheckBean carInfoCheckBean1 = new CarInfoCheckBean("交强险凭证", carInfoCheckReturnBean.getData().getInsuranceCertificate());
                            CarInfoCheckBean carInfoCheckBean2 = new CarInfoCheckBean("保险卡", carInfoCheckReturnBean.getData().getInsuranceCard());
                            CarInfoCheckBean carInfoCheckBean3 = new CarInfoCheckBean("保修保养卡", carInfoCheckReturnBean.getData().getWarrantyCard());
                            CarInfoCheckBean carInfoCheckBean4 = new CarInfoCheckBean("车辆行驶证", carInfoCheckReturnBean.getData().getCarTravelLicense());
                            carInfoCheckBeanList.add(carInfoCheckBean1);
                            carInfoCheckBeanList.add(carInfoCheckBean2);
                            carInfoCheckBeanList.add(carInfoCheckBean3);
                            carInfoCheckBeanList.add(carInfoCheckBean4);
                            mCarInfoCheckAdapter.setNewData(carInfoCheckBeanList);
                        } else {
                            showMsg(carInfoCheckReturnBean.getMessage());
                        }
                    }
                });
    }

}
