package com.workapp.auto.carterminal.module.main.bean;

import com.workapp.auto.carterminal.base.BaseResponse;

/**领取的 还车任务详情
 * Created by Administrator on 2017/8/3 0003.
 */

public class ReturnCarDetailReturnBean extends BaseResponse{

    /**
     * data : {"taskId":"1","returnSite":"站点名称1","startTime":"2017-06-28 10:18:41.0","endTime":"2017-07-04 19:20:34.0","rentCarName":null,"tel":null,"carModel":"测试车型1","plateNo":"浙G","power":"65.0","canRange":"13.0","certificateClick":"0","carBodyPartClick":"0","carInPartClick":"0","toolsClick":"0","carDataClick":"0"}
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
         * taskId : 1
         * returnSite : 站点名称1
         * startTime : 2017-06-28 10:18:41.0
         * endTime : 2017-07-04 19:20:34.0
         * rentCarName : null
         * tel : null
         * carModel : 测试车型1
         * plateNo : 浙G
         * power : 65.0
         * canRange : 13.0
         * certificateClick : 0
         * carBodyPartClick : 0
         * carInPartClick : 0
         * toolsClick : 0
         * carDataClick : 0
         */

        private String taskId;
        private String returnSite;
        private String startTime;
        private String endTime;
        private String rentCarName;
        private String tel;
        private String carModel;
        private String plateNo;
        private String power;
        private String canRange;
        private String certificateClick;
        private String carBodyPartClick;
        private String carInPartClick;
        private String toolsClick;
        private String carDataClick;

        public String getTaskId() {
            return taskId;
        }

        public void setTaskId(String taskId) {
            this.taskId = taskId;
        }

        public String getReturnSite() {
            return returnSite;
        }

        public void setReturnSite(String returnSite) {
            this.returnSite = returnSite;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getRentCarName() {
            return rentCarName;
        }

        public void setRentCarName(String rentCarName) {
            this.rentCarName = rentCarName;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getCarModel() {
            return carModel;
        }

        public void setCarModel(String carModel) {
            this.carModel = carModel;
        }

        public String getPlateNo() {
            return plateNo;
        }

        public void setPlateNo(String plateNo) {
            this.plateNo = plateNo;
        }

        public String getPower() {
            return power;
        }

        public void setPower(String power) {
            this.power = power;
        }

        public String getCanRange() {
            return canRange;
        }

        public void setCanRange(String canRange) {
            this.canRange = canRange;
        }

        public String getCertificateClick() {
            return certificateClick;
        }

        public void setCertificateClick(String certificateClick) {
            this.certificateClick = certificateClick;
        }

        public String getCarBodyPartClick() {
            return carBodyPartClick;
        }

        public void setCarBodyPartClick(String carBodyPartClick) {
            this.carBodyPartClick = carBodyPartClick;
        }

        public String getCarInPartClick() {
            return carInPartClick;
        }

        public void setCarInPartClick(String carInPartClick) {
            this.carInPartClick = carInPartClick;
        }

        public String getToolsClick() {
            return toolsClick;
        }

        public void setToolsClick(String toolsClick) {
            this.toolsClick = toolsClick;
        }

        public String getCarDataClick() {
            return carDataClick;
        }

        public void setCarDataClick(String carDataClick) {
            this.carDataClick = carDataClick;
        }
    }
}
