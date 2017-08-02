package com.workapp.auto.carterminal.module.login.view.activity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.workapp.auto.carterminal.R;
import com.workapp.auto.carterminal.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 输入新密码页
 * Created by Administrator on 2017/8/1 0001.
 */

public class NewPswActivity extends BaseActivity {
    @Bind(R.id.title_iv_back)
    ImageView ivBack;
    @Bind(R.id.title_tv_title)
    TextView tvTitle;
    @Bind(R.id.newPswAct_et_newPsw)
    EditText etNewPsw;
    @Bind(R.id.newPswAct_et_repeat)
    EditText etRepeat;
    @Bind(R.id.newPswAct_btn_submit)
    Button btnSubmit;

    @Override
    protected int getLayout() {
        return R.layout.activity_forget_psw;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

}
