package com.workapp.auto.carterminal.module.main.bean;

/**车辆状态实体类
 * Created by Administrator on 2017/8/3 0003.
 */

public class CarInfoCheckBean {
    private String infoName;     //参数名称
    private String status = "1";      //状态 0 无 1 好 2 坏 3 缺

    public CarInfoCheckBean(String infoName, String status) {
        this.infoName = infoName;
        this.status = status;
    }

    public String getInfoName() {
        return infoName;
    }

    public void setInfoName(String infoName) {
        this.infoName = infoName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
