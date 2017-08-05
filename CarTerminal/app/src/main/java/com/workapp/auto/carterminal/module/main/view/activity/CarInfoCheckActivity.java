package com.workapp.auto.carterminal.module.main.view.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import com.workapp.auto.carterminal.R;
import com.workapp.auto.carterminal.base.BaseActivity;
import com.workapp.auto.carterminal.base.BaseResponse;
import com.workapp.auto.carterminal.base.MyApplication;
import com.workapp.auto.carterminal.http.RetrofitUtil;
import com.workapp.auto.carterminal.module.main.bean.CarInfoCheckBean;
import com.workapp.auto.carterminal.module.main.bean.CarInfoCheckReturnBean;
import com.workapp.auto.carterminal.module.main.view.adapter.CarInfoCheckAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private String mTaskId;//任务id
    private CarInfoCheckAdapter mCarInfoCheckAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_info_certificate;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        mType = getIntent().getStringExtra("type");
        mTaskId = getIntent().getStringExtra("taskId");
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
            saveChange(data);
            Log.d("check", data.get(0).getStatus() + "" + data.get(1).getStatus() + "" + data.get(2).getStatus() + "" + data.get(3).getStatus() + "");
        });
    }

    private void getNetData() {
        RetrofitUtil.getInstance().api().checkCarLogs(mTaskId, mType)
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
                CarInfoCheckBean certificate1;
                CarInfoCheckBean certificate2;
                CarInfoCheckBean certificate3;
                CarInfoCheckBean certificate4;
                if (data != null && data.getInsuranceCertificate() != null) {
                    certificate1 = new CarInfoCheckBean("交强险凭证", data.getInsuranceCertificate());
                    certificate2 = new CarInfoCheckBean("保险卡", data.getInsuranceCard());
                    certificate3 = new CarInfoCheckBean("保修保养卡", data.getWarrantyCard());
                    certificate4 = new CarInfoCheckBean("车辆行驶证", data.getCarTravelLicense());
                } else {
                    certificate1 = new CarInfoCheckBean("交强险凭证", "1");
                    certificate2 = new CarInfoCheckBean("保险卡", "1");
                    certificate3 = new CarInfoCheckBean("保修保养卡", "1");
                    certificate4 = new CarInfoCheckBean("车辆行驶证", "1");
                }
                carInfoCheckBeanList.add(certificate1);
                carInfoCheckBeanList.add(certificate2);
                carInfoCheckBeanList.add(certificate3);
                carInfoCheckBeanList.add(certificate4);
                mCarInfoCheckAdapter.setNewData(carInfoCheckBeanList);
                break;
            case "2":
                CarInfoCheckBean outCar1;
                CarInfoCheckBean outCar2;
                CarInfoCheckBean outCar3;
                CarInfoCheckBean outCar4;
                CarInfoCheckBean outCar5;
                CarInfoCheckBean outCar6;
                CarInfoCheckBean outCar7;
                CarInfoCheckBean outCar8;
                CarInfoCheckBean outCar9;
                CarInfoCheckBean outCar10;
                CarInfoCheckBean outCar11;
                CarInfoCheckBean outCar12;
                CarInfoCheckBean outCar13;
                if (data != null && data.getBeforePlate() != null) {
                    outCar1 = new CarInfoCheckBean("前号牌", data.getBeforePlate());
                    outCar2 = new CarInfoCheckBean("后车牌", data.getEndPlate());
                    outCar3 = new CarInfoCheckBean("前车轮", data.getFrontWheel());
                    outCar4 = new CarInfoCheckBean("后车轮", data.getRearWheel());
                    outCar5 = new CarInfoCheckBean("挡风玻璃", data.getWindshield());
                    outCar6 = new CarInfoCheckBean("车窗玻璃", data.getWindowGlass());
                    outCar7 = new CarInfoCheckBean("雨刮器", data.getWindscreenWiper());
                    outCar8 = new CarInfoCheckBean("反光镜", data.getRetroreflector());
                    outCar9 = new CarInfoCheckBean("车门把手", data.getDoorHandle());
                    outCar10 = new CarInfoCheckBean("车灯", data.getCarLights());
                    outCar11 = new CarInfoCheckBean("转向灯", data.getTurnLight());
                    outCar12 = new CarInfoCheckBean("倒车灯", data.getBackupLight());
                    outCar13 = new CarInfoCheckBean("充电接口", data.getChargeJack());
                } else {
                    outCar1 = new CarInfoCheckBean("前号牌", "1");
                    outCar2 = new CarInfoCheckBean("后车牌", "1");
                    outCar3 = new CarInfoCheckBean("前车轮", "1");
                    outCar4 = new CarInfoCheckBean("后车轮", "1");
                    outCar5 = new CarInfoCheckBean("挡风玻璃", "1");
                    outCar6 = new CarInfoCheckBean("车窗玻璃", "1");
                    outCar7 = new CarInfoCheckBean("雨刮器", "1");
                    outCar8 = new CarInfoCheckBean("反光镜", "1");
                    outCar9 = new CarInfoCheckBean("车门把手", "1");
                    outCar10 = new CarInfoCheckBean("车灯", "1");
                    outCar11 = new CarInfoCheckBean("转向灯", "1");
                    outCar12 = new CarInfoCheckBean("倒车灯", "1");
                    outCar13 = new CarInfoCheckBean("充电接口", "1");
                }
                carInfoCheckBeanList.add(outCar1);
                carInfoCheckBeanList.add(outCar2);
                carInfoCheckBeanList.add(outCar3);
                carInfoCheckBeanList.add(outCar4);
                carInfoCheckBeanList.add(outCar5);
                carInfoCheckBeanList.add(outCar6);
                carInfoCheckBeanList.add(outCar7);
                carInfoCheckBeanList.add(outCar8);
                carInfoCheckBeanList.add(outCar9);
                carInfoCheckBeanList.add(outCar10);
                carInfoCheckBeanList.add(outCar11);
                carInfoCheckBeanList.add(outCar12);
                carInfoCheckBeanList.add(outCar13);
                mCarInfoCheckAdapter.setNewData(carInfoCheckBeanList);
                break;
            case "3":
                CarInfoCheckBean innerCar1;
                CarInfoCheckBean innerCar2;
                CarInfoCheckBean innerCar3;
                CarInfoCheckBean innerCar4;
                CarInfoCheckBean innerCar5;
                CarInfoCheckBean innerCar6;
                CarInfoCheckBean innerCar7;
                CarInfoCheckBean innerCar8;
                CarInfoCheckBean innerCar9;
                CarInfoCheckBean innerCar10;
                CarInfoCheckBean innerCar11;
                CarInfoCheckBean innerCar12;
                CarInfoCheckBean innerCar13;
                CarInfoCheckBean innerCar14;
                CarInfoCheckBean innerCar15;
                CarInfoCheckBean innerCar16;
                CarInfoCheckBean innerCar17;
                CarInfoCheckBean innerCar18;
                CarInfoCheckBean innerCar19;
                if (data != null && data.getInsuranceCertificate() != null) {
                    innerCar1 = new CarInfoCheckBean("车窗升降开关", data.getWindowLifterSwitch());
                    innerCar2 = new CarInfoCheckBean("车灯开关", data.getLightsSwitch());
                    innerCar3 = new CarInfoCheckBean("转向灯开关", data.getTurnLightSwitch());
                    innerCar4 = new CarInfoCheckBean("雨刮器开关", data.getWindscreenWiperSwitch());
                    innerCar5 = new CarInfoCheckBean("手刹", data.getHandBrake());
                    innerCar6 = new CarInfoCheckBean("脚刹", data.getFootBrake());
                    innerCar7 = new CarInfoCheckBean("油门", data.getAccelerator());
                    innerCar8 = new CarInfoCheckBean("换挡器", data.getShifter());
                    innerCar9 = new CarInfoCheckBean("音响", data.getSound());
                    innerCar10 = new CarInfoCheckBean("喇叭", data.getHorn());
                    innerCar11 = new CarInfoCheckBean("方向盘", data.getSteeringWheel());
                    innerCar12 = new CarInfoCheckBean("车内灯", data.getBodyLight());
                    innerCar13 = new CarInfoCheckBean("仪表盘", data.getDashBoard());
                    innerCar14 = new CarInfoCheckBean("车载媒体", data.getAutomotiveMedia());
                    innerCar15 = new CarInfoCheckBean("空调", data.getAirCondition());
                    innerCar16 = new CarInfoCheckBean("后视镜", data.getRearviewMirror());
                    innerCar17 = new CarInfoCheckBean("遮阳板", data.getSunvisor());
                    innerCar18 = new CarInfoCheckBean("安全带", data.getSafetyBelt());
                    innerCar19 = new CarInfoCheckBean("座椅", data.getSeat());
                } else {
                    innerCar1 = new CarInfoCheckBean("车窗升降开关", "1");
                    innerCar2 = new CarInfoCheckBean("车灯开关", "1");
                    innerCar3 = new CarInfoCheckBean("转向灯开关", "1");
                    innerCar4 = new CarInfoCheckBean("雨刮器开关", "1");
                    innerCar5 = new CarInfoCheckBean("手刹", "1");
                    innerCar6 = new CarInfoCheckBean("脚刹", "1");
                    innerCar7 = new CarInfoCheckBean("油门", "1");
                    innerCar8 = new CarInfoCheckBean("换挡器", "1");
                    innerCar9 = new CarInfoCheckBean("音响", "1");
                    innerCar10 = new CarInfoCheckBean("喇叭", "1");
                    innerCar11 = new CarInfoCheckBean("方向盘", "1");
                    innerCar12 = new CarInfoCheckBean("车内灯", "1");
                    innerCar13 = new CarInfoCheckBean("仪表盘", "1");
                    innerCar14 = new CarInfoCheckBean("车载媒体", "1");
                    innerCar15 = new CarInfoCheckBean("空调", "1");
                    innerCar16 = new CarInfoCheckBean("后视镜", "1");
                    innerCar17 = new CarInfoCheckBean("遮阳板", "1");
                    innerCar18 = new CarInfoCheckBean("安全带", "1");
                    innerCar19 = new CarInfoCheckBean("座椅", "1");
                }
                carInfoCheckBeanList.add(innerCar1);
                carInfoCheckBeanList.add(innerCar2);
                carInfoCheckBeanList.add(innerCar3);
                carInfoCheckBeanList.add(innerCar4);
                carInfoCheckBeanList.add(innerCar5);
                carInfoCheckBeanList.add(innerCar6);
                carInfoCheckBeanList.add(innerCar7);
                carInfoCheckBeanList.add(innerCar8);
                carInfoCheckBeanList.add(innerCar9);
                carInfoCheckBeanList.add(innerCar10);
                carInfoCheckBeanList.add(innerCar11);
                carInfoCheckBeanList.add(innerCar12);
                carInfoCheckBeanList.add(innerCar13);
                carInfoCheckBeanList.add(innerCar14);
                carInfoCheckBeanList.add(innerCar15);
                carInfoCheckBeanList.add(innerCar16);
                carInfoCheckBeanList.add(innerCar17);
                carInfoCheckBeanList.add(innerCar18);
                carInfoCheckBeanList.add(innerCar19);
                mCarInfoCheckAdapter.setNewData(carInfoCheckBeanList);
                break;
            case "4":
                CarInfoCheckBean tool1;
                CarInfoCheckBean tool2;
                CarInfoCheckBean tool3;
                CarInfoCheckBean tool4;
                CarInfoCheckBean tool5;
                CarInfoCheckBean tool6;
                CarInfoCheckBean tool7;
                if (data != null && data.getJack() != null) {
                    tool1 = new CarInfoCheckBean("千斤顶", data.getJack());
                    tool2 = new CarInfoCheckBean("工具包", data.getKit());
                    tool3 = new CarInfoCheckBean("故障警示牌", data.getFaultWarningBoard());
                    tool4 = new CarInfoCheckBean("备胎", data.getSpareWheel());
                    tool5 = new CarInfoCheckBean("灭火器", data.getFireExtinguisher());
                    tool6 = new CarInfoCheckBean("车钥匙", data.getCarKey());
                    tool7 = new CarInfoCheckBean("随车手册", data.getAccessoryManual());
                } else {
                    tool1 = new CarInfoCheckBean("千斤顶", "1");
                    tool2 = new CarInfoCheckBean("工具包", "1");
                    tool3 = new CarInfoCheckBean("故障警示牌", "1");
                    tool4 = new CarInfoCheckBean("备胎", "1");
                    tool5 = new CarInfoCheckBean("灭火器", "1");
                    tool6 = new CarInfoCheckBean("车钥匙", "1");
                    tool7 = new CarInfoCheckBean("随车手册", "1");
                }
                carInfoCheckBeanList.add(tool1);
                carInfoCheckBeanList.add(tool2);
                carInfoCheckBeanList.add(tool3);
                carInfoCheckBeanList.add(tool4);
                carInfoCheckBeanList.add(tool5);
                carInfoCheckBeanList.add(tool6);
                carInfoCheckBeanList.add(tool7);
                mCarInfoCheckAdapter.setNewData(carInfoCheckBeanList);
                break;
            default:
                break;
        }

    }

    private void saveChange(List<CarInfoCheckBean> data) {
        //1证书 2车体部件 3车内部件 4工具部件 5车辆上传资料
        Map<String, String> map = new HashMap<>();
        map.put("taskId", mTaskId);
        switch (mType) {
            case "1":
                map.put("type", "1");
                map.put("insuranceCertificate", data.get(0).getStatus());
                map.put("insuranceCard", data.get(1).getStatus());
                map.put("warrantyCard", data.get(2).getStatus());
                map.put("carTravelLicense", data.get(3).getStatus());
                break;
            case "2":
                map.put("type", "2");
                map.put("beforePlate", data.get(0).getStatus());
                map.put("endPlate", data.get(1).getStatus());
                map.put("frontWheel", data.get(2).getStatus());
                map.put("rearWheel", data.get(3).getStatus());
                map.put("windshield", data.get(4).getStatus());
                map.put("windowGlass", data.get(5).getStatus());
                map.put("windscreenWiper", data.get(6).getStatus());
                map.put("retroreflector", data.get(7).getStatus());
                map.put("doorHandle", data.get(8).getStatus());
                map.put("carLights", data.get(9).getStatus());
                map.put("turnLight", data.get(10).getStatus());
                map.put("backupLight", data.get(11).getStatus());
                map.put("chargeJack", data.get(12).getStatus());
                break;
            case "3":
                map.put("type", "3");
                map.put("windowLifterSwitch", data.get(0).getStatus());
                map.put("lightsSwitch", data.get(1).getStatus());
                map.put("turnLightSwitch", data.get(2).getStatus());
                map.put("windscreenWiperSwitch", data.get(3).getStatus());
                map.put("handBrake", data.get(4).getStatus());
                map.put("footBrake", data.get(5).getStatus());
                map.put("accelerator", data.get(6).getStatus());
                map.put("shifter", data.get(7).getStatus());
                map.put("sound", data.get(8).getStatus());
                map.put("horn", data.get(9).getStatus());
                map.put("steeringWheel", data.get(10).getStatus());
                map.put("bodyLight", data.get(11).getStatus());
                map.put("dashBoard", data.get(12).getStatus());
                map.put("automotiveMedia", data.get(13).getStatus());
                map.put("airCondition", data.get(14).getStatus());
                map.put("rearviewMirror", data.get(15).getStatus());
                map.put("sunvisor", data.get(16).getStatus());
                map.put("safetyBelt", data.get(17).getStatus());
                map.put("seat", data.get(18).getStatus());
                break;
            case "4":
                map.put("type", "4");
                map.put("jack", data.get(0).getStatus());
                map.put("kit", data.get(1).getStatus());
                map.put("faultWarningBoard", data.get(2).getStatus());
                map.put("spareWheel", data.get(3).getStatus());
                map.put("fireExtinguisher", data.get(4).getStatus());
                map.put("carKey", data.get(5).getStatus());
                map.put("accessoryManual", data.get(6).getStatus());
                break;
            default:
                break;
        }
        RetrofitUtil.getInstance().api().saveOrUpdateValidCarStatus(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        showMsg(MyApplication.getInstance().getString(R.string.network_on_error) + e.toString());
                    }

                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if (baseResponse.isSuccess()) {
                            showMsg("提交成功");
                            finish();
                        } else {
                            showMsg(baseResponse.getMessage());
                        }
                    }
                });
    }

}