package com.workapp.auto.carterminal.module.main.view.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.workapp.auto.carterminal.R;
import com.workapp.auto.carterminal.module.main.bean.ReturnCarListReturnBean;

/**
 * 日志——还车adapter
 * Created by owner on 2017/7/5.
 */

public class LogReturnCarAdapter extends BaseQuickAdapter<ReturnCarListReturnBean.DataBean.ContentBean, BaseViewHolder> {
    private Activity mContext;

    public LogReturnCarAdapter(Activity context) {
        super(R.layout.adapter_log_return_car);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ReturnCarListReturnBean.DataBean.ContentBean item) {
        TextView tvStatus = helper.getView(R.id.logReturnCarAdapter_tv_status);
        switch (item.getState()) {
            //验车说明 0、正常入库 1、待维修 2、报废
            case 0:
                tvStatus.setText("已完成");
                tvStatus.setTextColor(Color.parseColor("#2c2c2c"));
                break;
            case 1:
                tvStatus.setText("需要送修");
                tvStatus.setTextColor(Color.parseColor("#ff4747"));
                break;
            case 2:
                tvStatus.setText("报废");
                tvStatus.setTextColor(Color.parseColor("#ff4747"));
                break;
            default:
                break;
        }
        helper.setText(R.id.logReturnCarAdapter_tv_starTime, item.getCreateTime());
        helper.setText(R.id.logReturnCarAdapter_tv_endTime, item.getTestingTime());
        helper.setText(R.id.logReturnCarAdapter_tv_endName, item.getReturnSiteName());
        helper.setText(R.id.logReturnCarAdapter_tv_plateNo, item.getPlateNo());

        LinearLayout llClick = helper.getView(R.id.logReturnCarAdapter_ll_status);
        llClick.setOnClickListener(v -> {
//            Intent intent = new Intent(mContext, )
//            intent.putExtra("tackId", item.getTaskId());
//            mContext.startActivity(intent);
        });

    }
}
