package com.workapp.auto.carterminal.module.main.bean;

import com.workapp.auto.carterminal.base.BaseResponse;

/**日志——还车详情
 * Created by Administrator on 2017/8/5 0005.
 */

public class FindReturnCarDetailReturnBean extends BaseResponse{

    /**
     * data : {"taskId":1,"plateNo":"浙A.888888","carModel":"车型1","canRange":0,"power":"0.0","frameNo":"LD923A166G9CDL315","returnSite":"站点名称2","startTime":"2017-06-19 19:47:25.0","endTime":"2017-06-19 15:14:40.0","rentCarName":"张三","tel":"18888888881","certificate":true,"bodywork":true,"carParts":true,"toolObject":true,"carData":false,"state":"1","remarks":"xxx"}
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
         * plateNo : 浙A.888888
         * carModel : 车型1
         * canRange : 0
         * power : 0.0
         * frameNo : LD923A166G9CDL315
         * returnSite : 站点名称2
         * startTime : 2017-06-19 19:47:25.0
         * endTime : 2017-06-19 15:14:40.0
         * rentCarName : 张三
         * tel : 18888888881
         * certificate : true
         * bodywork : true
         * carParts : true
         * toolObject : true
         * carData : false
         * state : 1
         * remarks : xxx
         */

        private int taskId;
        private String plateNo;
        private String carModel;
        private int canRange;
        private String power;
        private String frameNo;
        private String returnSite;
        private String startTime;
        private String endTime;
        private String rentCarName;
        private String tel;
        private boolean certificate;
        private boolean bodywork;
        private boolean carParts;
        private boolean toolObject;
        private boolean carData;
        private String state;
        private String remarks;

        public int getTaskId() {
            return taskId;
        }

        public void setTaskId(int taskId) {
            this.taskId = taskId;
        }

        public String getPlateNo() {
            return plateNo;
        }

        public void setPlateNo(String plateNo) {
            this.plateNo = plateNo;
        }

        public String getCarModel() {
            return carModel;
        }

        public void setCarModel(String carModel) {
            this.carModel = carModel;
        }

        public int getCanRange() {
            return canRange;
        }

        public void setCanRange(int canRange) {
            this.canRange = canRange;
        }

        public String getPower() {
            return power;
        }

        public void setPower(String power) {
            this.power = power;
        }

        public String getFrameNo() {
            return frameNo;
        }

        public void setFrameNo(String frameNo) {
            this.frameNo = frameNo;
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

        public boolean isCertificate() {
            return certificate;
        }

        public void setCertificate(boolean certificate) {
            this.certificate = certificate;
        }

        public boolean isBodywork() {
            return bodywork;
        }

        public void setBodywork(boolean bodywork) {
            this.bodywork = bodywork;
        }

        public boolean isCarParts() {
            return carParts;
        }

        public void setCarParts(boolean carParts) {
            this.carParts = carParts;
        }

        public boolean isToolObject() {
            return toolObject;
        }

        public void setToolObject(boolean toolObject) {
            this.toolObject = toolObject;
        }

        public boolean isCarData() {
            return carData;
        }

        public void setCarData(boolean carData) {
            this.carData = carData;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }
    }
}
