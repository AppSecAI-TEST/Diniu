package com.workapp.auto.carterminal.module.main.view.adapter;

import android.graphics.Color;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.workapp.auto.carterminal.R;
import com.workapp.auto.carterminal.module.main.bean.CarInfoCheckBean;

/**
 * 车辆需要检查的信息适配器
 * Created by Administrator on 2017/8/3 0003.
 */

public class CarInfoCheckAdapter extends BaseQuickAdapter<CarInfoCheckBean, BaseViewHolder> {
    private boolean mIsCanSelect;                        //是否能选择
    public CarInfoCheckAdapter( boolean isCanSelect) {
        super(R.layout.adapter_car_info_check);
        this.mIsCanSelect = isCanSelect;
    }

    @Override
    protected void convert(BaseViewHolder helper, CarInfoCheckBean item) {
        helper.setText(R.id.carInfoCheckAdapter_tv_name, item.getInfoName());
        TextView tvStatus = helper.getView(R.id.carInfoCheckAdapter_tv_status);
        switch (item.getStatus()) {
            //状态 0 无 1 好 2 坏 3 缺
            case "0":
                tvStatus.setTextColor(Color.parseColor("#969696"));
                tvStatus.setText("无此配置");
                break;
            case "1":
                tvStatus.setTextColor(Color.parseColor("#5E9ED8"));
                tvStatus.setText("配置正常");
                break;
            case "2":
                tvStatus.setTextColor(Color.parseColor("#FF4141"));
                tvStatus.setText("配置损坏");
                break;
            case "3":
                tvStatus.setTextColor(Color.parseColor("#FF4141"));
                tvStatus.setText("缺少配置");
                break;
            default:
                tvStatus.setTextColor(Color.parseColor("#5E9ED8"));
                tvStatus.setText("配置正常");
                break;
        }

        RadioGroup radioGroup = helper.getView(R.id.carInfoCheckAdapter_radioGroup);
        RadioButton radioButton0 = helper.getView(R.id.carInfoCheckAdapter_radioBtn_0);
        RadioButton radioButton1 = helper.getView(R.id.carInfoCheckAdapter_radioBtn_1);
        RadioButton radioButton2 = helper.getView(R.id.carInfoCheckAdapter_radioBtn_2);
        RadioButton radioButton3 = helper.getView(R.id.carInfoCheckAdapter_radioBtn_3);
        switch (item.getStatus()){
            case "0":
                radioButton0.setChecked(true);
                break;
            case "1":
                radioButton1.setChecked(true);
                break;
            case "2":
                radioButton2.setChecked(true);
                break;
            case "3":
                radioButton3.setChecked(true);
                break;
            default:
                break;
        }
        ImageView ivArrow = helper.getView(R.id.carInfoCheckAdapter_iv_arrow);
        LinearLayout llClick = helper.getView(R.id.carInfoCheckAdapter_ll_click);
        llClick.setOnClickListener(v -> {
            if (radioGroup.getVisibility() == View.VISIBLE) {
                radioGroup.setVisibility(View.GONE);
                ivArrow.setImageResource(R.mipmap.down);
            } else {
                radioGroup.setVisibility(View.VISIBLE);
                ivArrow.setImageResource(R.mipmap.up);
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.carInfoCheckAdapter_radioBtn_0:
                        item.setStatus("0");
                        break;
                    case R.id.carInfoCheckAdapter_radioBtn_1:
                        item.setStatus("1");
                        break;
                    case R.id.carInfoCheckAdapter_radioBtn_2:
                        item.setStatus("2");
                        break;
                    case R.id.carInfoCheckAdapter_radioBtn_3:
                        item.setStatus("3");
                        break;
                    default:
                        break;
                }
                notifyDataSetChanged();
            }
        });

        /*
         * 完成时不能点击
         */
        if(mIsCanSelect){
            llClick.setEnabled(true);
            ivArrow.setVisibility(View.VISIBLE);
        }else{
            llClick.setEnabled(false);
            ivArrow.setVisibility(View.GONE);
        }

    }
}
