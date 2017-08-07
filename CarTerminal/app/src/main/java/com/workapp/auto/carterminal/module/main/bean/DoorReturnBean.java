package com.workapp.auto.carterminal.module.main.bean;

import com.workapp.auto.carterminal.base.BaseResponse;

/**开门关门返回实体类
 * Created by Administrator on 2017/8/7 0007.
 */

public class DoorReturnBean extends BaseResponse{

    /**
     * data : true
     */

    private boolean data;

    public boolean isData() {
        return data;
    }

    public void setData(boolean data) {
        this.data = data;
    }
}
