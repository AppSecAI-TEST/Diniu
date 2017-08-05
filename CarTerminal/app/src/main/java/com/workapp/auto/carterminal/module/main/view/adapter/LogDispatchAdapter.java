package com.workapp.auto.carterminal.module.main.view.adapter;

import android.app.Activity;
import android.content.Intent;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.workapp.auto.carterminal.R;
import com.workapp.auto.carterminal.module.main.bean.DispatchListReturnBean;
import com.workapp.auto.carterminal.module.main.view.activity.DispatchCompleteActivity;

/**
 * 日志——调度adapter
 * Created by owner on 2017/7/5.
 */

public class LogDispatchAdapter extends BaseQuickAdapter<DispatchListReturnBean.DataBean.ContentBean, BaseViewHolder> {
    private Activity mContext;

    public LogDispatchAdapter(Activity context) {
        super(R.layout.adapter_log_dispatch);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, DispatchListReturnBean.DataBean.ContentBean item) {
        helper.setText(R.id.logReturnCarAdapter_tv_starTime, item.getStartTime());
        helper.setText(R.id.logReturnCarAdapter_tv_endTime, item.getEndTime());
        helper.setText(R.id.logReturnCarAdapter_tv_endName, item.getEndName());
        helper.setText(R.id.logReturnCarAdapter_tv_plateNo, item.getPlateNo());

        LinearLayout llClick = helper.getView(R.id.logReturnCarAdapter_ll_status);
        llClick.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, DispatchCompleteActivity.class);
            intent.putExtra("taskId", item.getTaskId()+"");
            mContext.startActivity(intent);
        });

    }
}
