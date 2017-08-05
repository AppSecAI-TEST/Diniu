package com.workapp.auto.carterminal.module.main.bean;

import com.workapp.auto.carterminal.base.BaseResponse;

/**车务端当前任务
 * Created by Administrator on 2017/8/4 0004.
 */

public class CurrentTaskReturnBean extends BaseResponse{

    /**
     * data : {"taskId":1,"taskType":"1","plateNo":"浙AZ1K87","endName":"站点名称2","endId":"2","distance":20.2,"carModel":"测试车型1","power":64,"canRange":12.8,"lng":119.980086,"lat":30.275682}
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
         * taskType : 1
         * plateNo : 浙AZ1K87
         * endName : 站点名称2
         * endId : 2
         * distance : 20.2
         * carModel : 测试车型1
         * power : 64
         * canRange : 12.8
         * lng : 119.980086
         * lat : 30.275682
         */

        private int taskId;
        private String taskType;
        private String plateNo;
        private String endName;
        private String endId;
        private double distance;
        private String carModel;
        private int power;
        private double canRange;
        private double lng;
        private double lat;

        public int getTaskId() {
            return taskId;
        }

        public void setTaskId(int taskId) {
            this.taskId = taskId;
        }

        public String getTaskType() {
            return taskType;
        }

        public void setTaskType(String taskType) {
            this.taskType = taskType;
        }

        public String getPlateNo() {
            return plateNo;
        }

        public void setPlateNo(String plateNo) {
            this.plateNo = plateNo;
        }

        public String getEndName() {
            return endName;
        }

        public void setEndName(String endName) {
            this.endName = endName;
        }

        public String getEndId() {
            return endId;
        }

        public void setEndId(String endId) {
            this.endId = endId;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public String getCarModel() {
            return carModel;
        }

        public void setCarModel(String carModel) {
            this.carModel = carModel;
        }

        public int getPower() {
            return power;
        }

        public void setPower(int power) {
            this.power = power;
        }

        public double getCanRange() {
            return canRange;
        }

        public void setCanRange(double canRange) {
            this.canRange = canRange;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }
    }
}
