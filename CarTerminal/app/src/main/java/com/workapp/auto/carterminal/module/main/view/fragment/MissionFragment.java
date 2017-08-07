package com.workapp.auto.carterminal.module.main.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.workapp.auto.carterminal.R;
import com.workapp.auto.carterminal.base.BaseFragment;
import com.workapp.auto.carterminal.utils.TabLayoutUtils;
import com.workapp.auto.carterminal.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 任务
 * Created by owner on 2017/7/5.
 */

public class MissionFragment extends BaseFragment {
    @Bind(R.id.missionFrg_tabLayout)
    TabLayout tabLayout;                //有任务不显示
    @Bind(R.id.missionFrg_viewPager)
    NoScrollViewPager viewPager;
    @Bind(R.id.missionFrg_iv_title)
    ImageView ivTitle;                  //有任务不显示

    private String[] mTitles = {"还车", "调度"};
    private List<Fragment> mFragments = new ArrayList<>();

    public static MissionFragment newInstance() {
        Bundle args = new Bundle();
        MissionFragment fragment = new MissionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View getLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_mission, null);
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    protected void initData() {
        setFragments();
    }

    private void setFragments() {
        mFragments.add(MissionReturnCarFragment.newInstance());
        mFragments.add(MissionDispatchFragment.newInstance());
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];
            }
        });
        tabLayout.setupWithViewPager(viewPager);
        TabLayoutUtils.setIndicator(getActivity(), tabLayout, 60, 60);
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric;
    }

    public void showTabView(){
        tabLayout.setVisibility(View.VISIBLE);
        ivTitle.setVisibility(View.VISIBLE);
    }

    public void hideTabView(){
        tabLayout.setVisibility(View.GONE);
        ivTitle.setVisibility(View.GONE);
    }

    public void setCurrentViewPagerItem(int pos){
        viewPager.setCurrentItem(pos);
    }

}
