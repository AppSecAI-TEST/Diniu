package com.workapp.auto.carterminal.base;

import android.content.DialogInterface;
import android.content.Intent;

import com.workapp.auto.carterminal.module.login.view.activity.LoginActivity;
import com.workapp.auto.carterminal.utils.SharedPreferencesUtils;
import com.workapp.auto.carterminal.utils.ToastUtils;
import com.workapp.auto.carterminal.widget.CustomIconDialog;

/**
 * 返回参数基类
 * Created by Administrator on 2017/8/2 0002.
 */

public class BaseResponse {
    /**
     * code : 0
     * message : success
     */

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        if (code == 90005) {
            ToastUtils.showLong(MyApplication.getInstance(), "您的账号已在另一设备登录，或者Token已失效，请重新登录");
            logOut();
        }
        return code == 0;
    }

    private void logOut() {
        SharedPreferencesUtils.remove(MyApplication.getInstance(), "X-Auth-Token");
        Intent intent = new Intent(MyApplication.getInstance(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        MyApplication.getInstance().startActivity(intent);
    }
}
