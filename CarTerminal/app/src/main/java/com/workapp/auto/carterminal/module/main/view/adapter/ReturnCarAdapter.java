package com.workapp.auto.carterminal.module.main.view.adapter;

import android.support.annotation.LayoutRes;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.workapp.auto.carterminal.R;
import com.workapp.auto.carterminal.module.main.bean.ReturnCarListReturnBean;

/**
 * 还车adapter
 * Created by owner on 2017/7/5.
 */

public class ReturnCarAdapter extends BaseQuickAdapter<ReturnCarListReturnBean.DataBean.ContentBean, BaseViewHolder> {

    public ReturnCarAdapter() {
        super(R.layout.adapter_return_car);
    }

    @Override
    protected void convert(BaseViewHolder helper, ReturnCarListReturnBean.DataBean.ContentBean item) {
        helper.setText(R.id.returnCarAdapter_tv_carNum, item.getPlateNo());
        helper.setText(R.id.returnCarAdapter_tv_carModel, item.getCarModel());
        helper.setText(R.id.returnCarAdapter_tv_power, item.getPower() + "%");
        helper.setText(R.id.returnCarAdapter_tv_canRange, item.getCanRange() + "km");
        helper.setText(R.id.returnCarAdapter_tv_distance, item.getDistance());

    }
}
