package com.workapp.auto.carterminal.module.main.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.workapp.auto.carterminal.R;
import com.workapp.auto.carterminal.base.BaseFragment;

/**日志
 * Created by owner on 2017/7/5.
 */

public class LogFragment extends BaseFragment{
    public static LogFragment newInstance() {
        Bundle args = new Bundle();
        LogFragment fragment = new LogFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected View getLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_log,null);
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
