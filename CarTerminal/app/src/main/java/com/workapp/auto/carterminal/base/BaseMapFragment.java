package com.workapp.auto.carterminal.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/** fragmentMap基类
 * Created by Administrator on 2016/9/15.
 */
public abstract class BaseMapFragment extends Fragment {
    @Nullable//@Nullable 表示定义的字段可以为空.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return getLayout(inflater, container,savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view,savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initListener();
    }



    /**
     * 加载布局
     */
    protected abstract View getLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * 初始化组件
     */
    protected abstract void initView(View view,Bundle savedInstanceState);

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 添加事件监听
     */
    protected abstract void initListener();

}
