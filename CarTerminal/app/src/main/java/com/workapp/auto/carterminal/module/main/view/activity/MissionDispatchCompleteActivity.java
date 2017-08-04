package com.workapp.auto.carterminal.module.main.view.activity;

import android.widget.TextView;

import com.workapp.auto.carterminal.R;
import com.workapp.auto.carterminal.base.BaseActivity;
import com.workapp.auto.carterminal.base.MyApplication;
import com.workapp.auto.carterminal.http.RetrofitUtil;
import com.workapp.auto.carterminal.module.main.bean.DispatchCompleteReturnBean;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 任务——调度——已完成页
 * Created by Administrator on 2017/8/4 0004.
 */

public class MissionDispatchCompleteActivity extends BaseActivity {
    @Bind(R.id.missionDisComAct_tv_starTime)
    TextView tvStarTime;
    @Bind(R.id.missionDisComAct_tv_endTime)
    TextView tvEndTime;
    @Bind(R.id.missionDisComAct_tv_plateNo)
    TextView tvPlateNo;
    @Bind(R.id.missionDisComAct_tv_endName)
    TextView tvEndName;
    @Bind(R.id.missionDisComAct_tv_carModel)
    TextView tvCarModel;
    @Bind(R.id.missionDisComAct_tv_powerAndCanRange)
    TextView tvPowerAndCanRange;

    private String mTaskId;

    @Override
    protected int getLayout() {
        return R.layout.activity_mission_diapath_complete;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        setTitle("已完成");
    }

    @Override
    protected void initData() {
        mTaskId = getIntent().getStringExtra("taskId");
        getNetData();
    }

    @Override
    protected void initListener() {

    }

    private void getNetData() {
        RetrofitUtil.getInstance().api().dispatchReceive(mTaskId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DispatchCompleteReturnBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        showMsg(MyApplication.getInstance().getString(R.string.network_on_error) + e.toString());
                    }

                    @Override
                    public void onNext(DispatchCompleteReturnBean dispatchCompleteReturnBean) {
                        if (dispatchCompleteReturnBean.isSuccess()) {
                            setView(dispatchCompleteReturnBean);
                        } else {
                            showMsg(dispatchCompleteReturnBean.getMessage());
                        }
                    }
                });
    }

    private void setView(DispatchCompleteReturnBean dispatchCompleteReturnBean) {
        DispatchCompleteReturnBean.DataBean data = dispatchCompleteReturnBean.getData();
        tvStarTime.setText(data.getStartTime());
        tvEndTime.setText(data.getEndTime());
        tvEndName.setText(data.getEndName());
        tvPlateNo.setText(data.getPlateNo());
        tvCarModel.setText(data.getCarModel());
        tvPowerAndCanRange.setText(data.getPower() + "%" + "（" + data.getCanRange() + "Km）");
    }
}
