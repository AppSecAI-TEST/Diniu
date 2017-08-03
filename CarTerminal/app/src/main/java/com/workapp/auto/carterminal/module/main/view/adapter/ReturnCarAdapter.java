package com.workapp.auto.carterminal.module.main.view.adapter;

import android.app.Activity;
import android.content.Intent;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.workapp.auto.carterminal.R;
import com.workapp.auto.carterminal.module.main.bean.ReturnCarListReturnBean;
import com.workapp.auto.carterminal.module.main.view.activity.ReturnCarInfoActivity;

/**
 * 还车adapter
 * Created by owner on 2017/7/5.
 */

public class ReturnCarAdapter extends BaseQuickAdapter<ReturnCarListReturnBean.DataBean.ContentBean, BaseViewHolder> {
    private Activity mContext;
    public ReturnCarAdapter(Activity context) {
        super(R.layout.adapter_return_car);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ReturnCarListReturnBean.DataBean.ContentBean item) {
        helper.setText(R.id.returnCarAdapter_tv_carNum, item.getPlateNo());
        helper.setText(R.id.returnCarAdapter_tv_carModel, item.getCarModel());
        helper.setText(R.id.returnCarAdapter_tv_power, item.getPower() + "%");
        helper.setText(R.id.returnCarAdapter_tv_canRange, item.getCanRange() + "km");
        helper.setText(R.id.returnCarAdapter_tv_distance, item.getDistance() + "");

        Button btnReceive = helper.getView(R.id.returnCarAdapter_btn_getTask);
        btnReceive.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, ReturnCarInfoActivity.class);
            intent.putExtra("taskId",item.getTaskId()+"");
            mContext.startActivity(intent);
        });

    }
}
