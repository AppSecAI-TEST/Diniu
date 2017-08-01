package com.workapp.auto.carterminal.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/** fragment基类
 * Created by Administrator on 2016/9/15.
 */
public abstract class BaseFragment extends Fragment{
    @Nullable//@Nullable 表示定义的字段可以为空.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return getLayout(inflater, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        onMyClick();
    }



    /**
     * 加载布局
     */
    protected abstract View getLayout(LayoutInflater inflater, ViewGroup container);

    /**
     * 初始化组件
     */
    protected abstract void initView(View view);

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 添加点击事件
     */
    protected abstract void onMyClick();

}
