package com.workapp.auto.carterminal.module.main.bean;

import com.workapp.auto.carterminal.base.BaseResponse;

/**返回的data 只有布尔值得返回信息实体类
 * Created by Administrator on 2017/8/7 0007.
 */

public class BooleanReturnBean extends BaseResponse{

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
