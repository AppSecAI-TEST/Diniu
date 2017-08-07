package com.workapp.auto.carterminal.module.login.view.activity;

import android.content.DialogInterface;
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
import com.workapp.auto.carterminal.http.RetrofitUtil;
import com.workapp.auto.carterminal.utils.ToastUtils;
import com.workapp.auto.carterminal.widget.CustomIconSingleDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 输入新密码页
 * Created by Administrator on 2017/8/1 0001.
 */

public class NewPswActivity extends BaseActivity {
    @Bind(R.id.newPswAct_et_newPsw)
    EditText etNewPsw;
    @Bind(R.id.newPswAct_et_repeat)
    EditText etRepeat;
    @Bind(R.id.newPswAct_btn_submit)
    Button btnSubmit;

    private String mPhone;
    @Override
    protected int getLayout() {
        return R.layout.activity_new_psw;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        setTitle("输入新密码");
    }

    @Override
    protected void initData() {
        mPhone = getIntent().getStringExtra("phone");

    }

    @Override
    protected void initListener() {
        etNewPsw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!TextUtils.isEmpty(etNewPsw.getText().toString()) && !TextUtils.isEmpty(etRepeat.getText().toString())){
                    btnSubmit.setEnabled(true);
                }else{
                    btnSubmit.setEnabled(false);
                }
            }
        });

        etRepeat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!TextUtils.isEmpty(etNewPsw.getText().toString()) && !TextUtils.isEmpty(etRepeat.getText().toString())){
                    btnSubmit.setEnabled(true);
                }else{
                    btnSubmit.setEnabled(false);
                }
            }
        });

        btnSubmit.setOnClickListener(v -> {
            if(!etNewPsw.getText().toString().equals(etRepeat.getText().toString())){
                ToastUtils.showShort(this,"两次密码输入不一致");
                return;
            }
            RetrofitUtil.getInstance().api().forgetPsw(mPhone,etRepeat.getText().toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<BaseResponse>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            ToastUtils.showShort(MyApplication.getInstance(), MyApplication.getInstance().getString(R.string.network_on_error) + e.toString());
                        }

                        @Override
                        public void onNext(BaseResponse baseResponse) {
                            if(baseResponse.isSuccess()){
                                showDialog();
                            }else{
                                ToastUtils.showShort(MyApplication.getInstance(), baseResponse.getMessage());
                            }
                        }
                    });
        });
    }

    private void showDialog(){
        CustomIconSingleDialog.Builder builder = new CustomIconSingleDialog.Builder(this);
        builder.setMessage("密码已重置，请重新登录！");
        builder.setPositiveButton("去登录", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(NewPswActivity.this,LoginActivity.class));
            }
        });
        CustomIconSingleDialog customIconDialog = builder.create();
        customIconDialog.setCancelable(false);
        customIconDialog.show();
    }

}
