package com.workapp.auto.carterminal.module.main.view.adapter;

import android.app.Activity;
import android.content.Intent;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.workapp.auto.carterminal.R;
import com.workapp.auto.carterminal.module.main.bean.DispatchListReturnBean;
import com.workapp.auto.carterminal.module.main.view.activity.MissionReturnCarInfoActivity;

/**
 * 任务——调度adapter
 * Created by owner on 2017/7/5.
 */

public class MissionDispatchAdapter extends BaseQuickAdapter<DispatchListReturnBean.DataBean.ContentBean, BaseViewHolder> {
    private Activity mContext;

    public MissionDispatchAdapter(Activity context) {
        super(R.layout.adapter_mission_dispatch);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, DispatchListReturnBean.DataBean.ContentBean item) {
        helper.setText(R.id.missionDispatchAdapter_tv_startName, item.getStartName());
        helper.setText(R.id.missionDispatchAdapter_tv_carNum, item.getPlateNo());
        helper.setText(R.id.missionDispatchAdapter_tv_endName, item.getEndName());
        helper.setText(R.id.missionDispatchAdapter_tv_carModel, item.getCarModel());
        helper.setText(R.id.missionDispatchAdapter_tv_power, item.getPower() + "%");
        helper.setText(R.id.missionDispatchAdapter_tv_canRange, item.getCanRange() + "km");
        helper.setText(R.id.missionDispatchAdapter_tv_distance, item.getDistance() + "km");

        Button btnReceive = helper.getView(R.id.missionDispatchAdapter_btn_getTask);
        btnReceive.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, MissionReturnCarInfoActivity.class);
            intent.putExtra("taskId", item.getTaskId() + "");
            mContext.startActivity(intent);
        });

    }
}
