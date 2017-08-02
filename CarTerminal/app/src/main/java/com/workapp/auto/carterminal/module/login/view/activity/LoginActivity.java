package com.workapp.auto.carterminal.module.login.view.activity;

import android.Manifest;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.workapp.auto.carterminal.R;
import com.workapp.auto.carterminal.base.BaseActivity;
import com.workapp.auto.carterminal.base.MyApplication;
import com.workapp.auto.carterminal.base.PermissionsActivity;
import com.workapp.auto.carterminal.module.login.presenter.LoginPresenter;
import com.workapp.auto.carterminal.module.login.view.function.ILoginView;
import com.workapp.auto.carterminal.utils.PermissionsChecker;
import com.workapp.auto.carterminal.utils.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 登录页
 * Created by Administrator on 2017/8/1 0001.
 */

public class LoginActivity extends BaseActivity implements ILoginView {
    @Bind(R.id.loginAct_et_username)
    EditText etUsername;
    @Bind(R.id.loginAct_et_psw)
    EditText etPsw;
    @Bind(R.id.loginAct_tv_forgetPsw)
    TextView tvForgetPsw;
    @Bind(R.id.loginAct_btn_login)
    Button btnLogin;

    private PermissionsChecker mPermissionsChecker;
    private static final int REQUEST_CODE = 1001;
    private static final String[] PERMISSIONS = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CAMERA
    };
    private LoginPresenter mLoginPresenter;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        hideTitle();
    }

    @Override
    protected void initData() {
        mLoginPresenter = new LoginPresenter();
        mPermissionsChecker = new PermissionsChecker(MyApplication.getInstance());
    }

    @Override
    protected void initListener() {
        tvForgetPsw.setOnClickListener(v -> startActivity(new Intent(this, ForgetPswActivity.class)));

        btnLogin.setOnClickListener(v -> {
            if (TextUtils.isEmpty(etUsername.getText().toString())) {
                ToastUtils.showShort(this, "请输入用户名");
                return;
            }

            if (TextUtils.isEmpty(etPsw.getText().toString())) {
                ToastUtils.showShort(this, "请输入用户名");
                return;
            }
            mLoginPresenter.SignIn(etUsername.getText().toString(), etPsw.getText().toString());
        });

        etUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(etUsername.getText().toString().trim()) && !TextUtils.isEmpty(etPsw.getText().toString().trim())) {
                    btnLogin.setEnabled(true);
                } else {
                    btnLogin.setEnabled(false);
                }
            }
        });

        etPsw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(etUsername.getText().toString().trim()) && !TextUtils.isEmpty(etPsw.getText().toString().trim())) {
                    btnLogin.setEnabled(true);
                } else {
                    btnLogin.setEnabled(false);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLoginPresenter.attach(this);
        if (mPermissionsChecker.lacksPermissions(PERMISSIONS)) {
            PermissionsActivity.startActivityForResult(LoginActivity.this, REQUEST_CODE, PERMISSIONS);
        }
    }

    @Override
    protected void onDestroy() {
        mLoginPresenter.detach();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
            finish();
        }
    }

    @Override
    public void showMessage(String msg) {
        showMsg(msg);
    }

    @Override
    public void showLoading() {
        showLoadingView();
    }

    @Override
    public void hideLoading() {
        hideLoadingView();
    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void toMainAct() {
        finish();
    }

    @Override
    public void toForgetPswAct() {
        startActivity(new Intent(this, ForgetPswActivity.class));
    }
}
