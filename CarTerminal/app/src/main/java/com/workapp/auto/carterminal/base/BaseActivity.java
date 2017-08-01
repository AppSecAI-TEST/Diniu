package com.workapp.auto.carterminal.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

/** activity基类
 * Created by Administrator on 2016/9/15.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected String tag = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉手机信息栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //所有继承该类的Activity均有该动画
//        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
        setContentView(getLayout());
        ActivityControl.addAty(tag, this);
        initView();
        initData();
        onMyClick();
    }


    /**
     * 返回值为所要加载的布局文件
     *
     * @return
     */
    protected abstract int getLayout();

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 点击事件
     */
    protected abstract void onMyClick();


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
