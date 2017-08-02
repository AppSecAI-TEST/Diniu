package com.workapp.auto.carterminal.base;

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
        return code == 0;
    }
}
