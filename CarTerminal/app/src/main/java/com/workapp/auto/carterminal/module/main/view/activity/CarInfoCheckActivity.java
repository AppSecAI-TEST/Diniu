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
 * 还车信息检查
 * Created by Administrator on 2017/8/3 0003.
 */

public class CarInfoCheckActivity extends BaseActivity {
    @Bind(R.id.infoCertificate_recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.infoCertificate_btn_save)
    Button btnSave;

    private String mType;  //1证书 2车体部件 3车内部件 4工具部件 5车辆上传资料
    private CarInfoCheckAdapter mCarInfoCheckAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_info_certificate;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        mType = getIntent().getStringExtra("type");
        switch (mType) {
            case "1":
                setTitle("证书");
                break;
            case "2":
                setTitle("车体部件");
                break;
            case "3":
                setTitle("车内部件");
                break;
            case "4":
                setTitle("工具部件");
                break;
            default:
                setTitle("证书");
                break;
        }
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
        RetrofitUtil.getInstance().api().checkCarLogs("001", mType)
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
                            handleTypeData(carInfoCheckReturnBean);
                        } else {
                            showMsg(carInfoCheckReturnBean.getMessage());
                        }
                    }
                });
    }

    /**
     * 根据type不同配置不同的车辆检查信息
     */
    private void handleTypeData(CarInfoCheckReturnBean carInfoCheckReturnBean) {
        List<CarInfoCheckBean> carInfoCheckBeanList = new ArrayList<>();
        CarInfoCheckReturnBean.DataBean data = carInfoCheckReturnBean.getData();
        switch (mType) {
            case "1":
                CarInfoCheckBean certificate1 = new CarInfoCheckBean("交强险凭证", data.getInsuranceCertificate());
                CarInfoCheckBean certificate2 = new CarInfoCheckBean("保险卡", data.getInsuranceCard());
                CarInfoCheckBean certificate3 = new CarInfoCheckBean("保修保养卡", data.getWarrantyCard());
                CarInfoCheckBean certificate4 = new CarInfoCheckBean("车辆行驶证", data.getCarTravelLicense());
                carInfoCheckBeanList.add(certificate1);
                carInfoCheckBeanList.add(certificate2);
                carInfoCheckBeanList.add(certificate3);
                carInfoCheckBeanList.add(certificate4);
                mCarInfoCheckAdapter.setNewData(carInfoCheckBeanList);
                break;
            case "2":
                CarInfoCheckBean outCar1 = new CarInfoCheckBean("前号牌", data.getWindowLifterSwitch());
                CarInfoCheckBean outCar2 = new CarInfoCheckBean("前车轮", data.getTurnLightSwitch());
                CarInfoCheckBean outCar3 = new CarInfoCheckBean("后车轮", data.getBeforePlate());
                CarInfoCheckBean outCar4 = new CarInfoCheckBean("车辆行驶证", data.getCarTravelLicense());
                carInfoCheckBeanList.add(outCar1);
                carInfoCheckBeanList.add(outCar2);
                carInfoCheckBeanList.add(outCar3);
                carInfoCheckBeanList.add(outCar4);
                mCarInfoCheckAdapter.setNewData(carInfoCheckBeanList);
                break;
            case "3":
                CarInfoCheckBean innerCar1 = new CarInfoCheckBean("交强险凭证", data.getInsuranceCertificate());
                CarInfoCheckBean innerCar2 = new CarInfoCheckBean("保险卡", data.getInsuranceCard());
                CarInfoCheckBean innerCar3 = new CarInfoCheckBean("保修保养卡", data.getWarrantyCard());
                CarInfoCheckBean innerCar4 = new CarInfoCheckBean("车辆行驶证", data.getCarTravelLicense());
                carInfoCheckBeanList.add(innerCar1);
                carInfoCheckBeanList.add(innerCar2);
                carInfoCheckBeanList.add(innerCar3);
                carInfoCheckBeanList.add(innerCar4);
                mCarInfoCheckAdapter.setNewData(carInfoCheckBeanList);
                break;
            case "4":
                CarInfoCheckBean tool1 = new CarInfoCheckBean("交强险凭证", data.getInsuranceCertificate());
                CarInfoCheckBean tool2 = new CarInfoCheckBean("保险卡", data.getInsuranceCard());
                CarInfoCheckBean tool3 = new CarInfoCheckBean("保修保养卡", data.getWarrantyCard());
                CarInfoCheckBean tool4 = new CarInfoCheckBean("车辆行驶证", data.getCarTravelLicense());
                carInfoCheckBeanList.add(tool1);
                carInfoCheckBeanList.add(tool2);
                carInfoCheckBeanList.add(tool3);
                carInfoCheckBeanList.add(tool4);
                mCarInfoCheckAdapter.setNewData(carInfoCheckBeanList);
                break;
            default:
                break;
        }

    }

}
