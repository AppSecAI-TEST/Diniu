package com.workapp.auto.carterminal.utils;

import android.os.CountDownTimer;
import android.widget.Button;

/** 倒计时类
 * Created by Administrator on 2017/8/2 0002.
 */

public class MyCountDownTimer extends CountDownTimer {
    private Button btnCode;
    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public MyCountDownTimer(long millisInFuture, long countDownInterval, Button btnCode) {
        super(millisInFuture, countDownInterval);
        this.btnCode = btnCode;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        btnCode.setText( millisUntilFinished / 1000 + "秒");
    }

    @Override
    public void onFinish() {
        btnCode.setText("重新发送");
        btnCode.setEnabled(true);
    }
}
