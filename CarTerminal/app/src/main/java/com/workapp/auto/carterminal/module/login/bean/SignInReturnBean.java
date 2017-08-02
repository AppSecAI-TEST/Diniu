package com.workapp.auto.carterminal.module.login.bean;

import com.workapp.auto.carterminal.base.BaseResponse;

/**用户登录实体类
 * Created by Administrator on 2017/8/2 0002.
 */

public class SignInReturnBean extends BaseResponse {

    /**
     * data : {"token":"53200212-693c-49d0-ab41-bcb0007e6c16"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * token : 53200212-693c-49d0-ab41-bcb0007e6c16
         */

        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
