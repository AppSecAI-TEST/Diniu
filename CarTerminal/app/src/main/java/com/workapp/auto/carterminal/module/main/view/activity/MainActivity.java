package com.workapp.auto.carterminal.module.main.view.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.widget.ImageView;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.workapp.auto.carterminal.R;
import com.workapp.auto.carterminal.base.BaseActivity;
import com.workapp.auto.carterminal.module.login.view.activity.LoginActivity;
import com.workapp.auto.carterminal.module.main.view.fragment.LogFragment;
import com.workapp.auto.carterminal.module.main.view.fragment.MissionFragment;
import com.workapp.auto.carterminal.utils.SharedPreferencesUtils;
import com.workapp.auto.carterminal.widget.CustomIconDialog;
import com.workapp.auto.carterminal.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    @Bind(R.id.dl_main)
    DrawerLayout drawerLayout;
    @Bind(R.id.ib_title_left)
    ImageView ivLeftClick;
    @Bind(R.id.bt_home_right)
    ImageView ivRightClick;
    @Bind(R.id.mainAct_tabLayout)
    SegmentTabLayout tabLayout;
    @Bind(R.id.mainAct_viewPager)
    NoScrollViewPager viewPager;

    private String[] mTitles = {"任务", "日志"};
    private List<Fragment> mFragments = new ArrayList<>();
    private boolean isExit;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        hideTitle();
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        tabLayout.setTabData(mTitles);
        mFragments.add(MissionFragment.newInstance());
        mFragments.add(LogFragment.newInstance());
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
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

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        ivLeftClick.setOnClickListener(v -> {
            /*if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                drawerLayout.closeDrawer(Gravity.LEFT);
            } else {
                drawerLayout.openDrawer(Gravity.LEFT);
            }*/
            showDialog();
        });

        ivRightClick.setOnClickListener(v -> {
            startActivity(new Intent(this, MessageActivity.class));
        });
    }

    private void showDialog() {
        CustomIconDialog.Builder builder = new CustomIconDialog.Builder(this);
        builder.setMessage("确定要退出登录吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                logOut();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        CustomIconDialog customIconDialog = builder.create();
        customIconDialog.setCancelable(false);
        customIconDialog.show();
    }

    private void logOut() {
        SharedPreferencesUtils.remove(this, "X-Auth-Token");
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void onBackPressed() {
        exitBy2Click();
    }

    /**
     * 双击退出
     */
    private void exitBy2Click() {
        Timer tExit;
        if (!isExit) {
            isExit = true; // 准备退出
            showMsg("再按一次退出程序");
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 3000); // 如果3秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            this.finish();
        }
    }

}
