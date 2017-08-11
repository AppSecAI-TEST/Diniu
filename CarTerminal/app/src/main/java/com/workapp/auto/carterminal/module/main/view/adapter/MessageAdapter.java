package com.workapp.auto.carterminal.module.main.view.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.workapp.auto.carterminal.R;
import com.workapp.auto.carterminal.base.WebActivity;
import com.workapp.auto.carterminal.module.main.bean.MessageReturnBean;

/**
 * 消息列表适配器
 * Created by Administrator on 2017/8/8 0008.
 */

public class MessageAdapter extends BaseQuickAdapter<MessageReturnBean.DataBean, BaseViewHolder> {
    private Activity mActivity;

    public MessageAdapter(Activity activity) {
        super(R.layout.adapter_message);
        this.mActivity = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageReturnBean.DataBean item) {
        helper.setText(R.id.messageAdapter_tv_title, item.getTitle());
        helper.setText(R.id.messageAdapter_tv_content, item.getContent());
        RelativeLayout rlClick = helper.getView(R.id.messageAdapter_rl_click);
        View redDot = helper.getView(R.id.messageAdapter_redDot);
        rlClick.setOnClickListener(v -> {
            Intent intent = new Intent(mActivity, WebActivity.class);
            intent.putExtra("url", item.getUrl());
            intent.putExtra("title", item.getTitle());
            mActivity.startActivity(intent);
        });
    }
}
