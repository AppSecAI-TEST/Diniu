package com.workapp.auto.carterminal.module.login.view.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.workapp.auto.carterminal.R;
import com.workapp.auto.carterminal.base.BaseActivity;
import com.workapp.auto.carterminal.base.BaseResponse;
import com.workapp.auto.carterminal.base.MyApplication;
import com.workapp.auto.carterminal.http.ProgressSubscriber;
import com.workapp.auto.carterminal.http.RetrofitUtil;
import com.workapp.auto.carterminal.utils.MyCountDownTimer;
import com.workapp.auto.carterminal.utils.StringUtil;
import com.workapp.auto.carterminal.utils.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 忘记密码页
 * Created by Administrator on 2017/8/1 0001.
 */

public class ForgetPswActivity extends BaseActivity {
    @Bind(R.id.forgetPswAct_et_mobile)
    EditText etMobile;
    @Bind(R.id.forgetPswAct_et_check_code)
    EditText etCheckCode;
    @Bind(R.id.forgetPswAct_btn_check_code)
    Button btnCheckCode;
    @Bind(R.id.forgetPswAct_btn_next)
    Button btnNext;

    private MyCountDownTimer myCountDownTimer;

    @Override
    protected int getLayout() {
        return R.layout.activity_forget_psw;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        setTitle("忘记密码");
    }

    @Override
    protected void initData() {
        myCountDownTimer = new MyCountDownTimer(60000, 1000, btnCheckCode);
    }

    @Override
    protected void initListener() {

        etMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(etMobile.getText().toString()) && !TextUtils.isEmpty(etCheckCode.getText().toString())) {
                    btnNext.setEnabled(true);
                } else {
                    btnNext.setEnabled(false);
                }
            }
        });

        etCheckCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(etMobile.getText().toString()) && !TextUtils.isEmpty(etCheckCode.getText().toString())) {
                    btnNext.setEnabled(true);
                } else {
                    btnNext.setEnabled(false);
                }
            }
        });

        btnCheckCode.setOnClickListener(v -> {
            if (TextUtils.isEmpty(etMobile.getText().toString())) {
                ToastUtils.showShort(this, "请输入手机号码");
                return;
            }
            if (StringUtil.isPhoneNumber(etMobile.getText().toString())) {
                ToastUtils.showShort(this, "请输入正确的手机号码");
                return;
            }
            btnCheckCode.setEnabled(false);
            myCountDownTimer.start();
            RetrofitUtil.getInstance().api().sendSmsCode(etMobile.getText().toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new ProgressSubscriber<BaseResponse>(ForgetPswActivity.this) {
                        @Override
                        public void onNext(BaseResponse baseResponse) {
                            if (baseResponse.isSuccess()) {
                                ToastUtils.showShort(MyApplication.getInstance(), "验证码发送成功");
                            } else {
                                ToastUtils.showShort(MyApplication.getInstance(), baseResponse.getMessage());
                            }
                        }
                    });
        });

        btnNext.setOnClickListener(v -> {
            RetrofitUtil.getInstance().api().checkSmsCode(etMobile.getText().toString(), etCheckCode.getText().toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new ProgressSubscriber<BaseResponse>(ForgetPswActivity.this) {
                        @Override
                        public void onNext(BaseResponse baseResponse) {
                            if (baseResponse.isSuccess()) {
                                Intent intent = new Intent(ForgetPswActivity.this, NewPswActivity.class);
                                intent.putExtra("phone", etMobile.getText().toString());
                                startActivity(intent);
                            } else {
                                ToastUtils.showShort(MyApplication.getInstance(), baseResponse.getMessage());
                            }
                        }
                    });
        });
    }

}
