package com.workapp.auto.carterminal.module.main.bean;

import com.workapp.auto.carterminal.base.BaseResponse;

/**调用详情已完成
 * Created by Administrator on 2017/8/4 0004.
 */

public class DispatchCompleteReturnBean extends BaseResponse{

    /**
     * data : {"taskId":3,"plateNo":"浙AZ1K87","startName":"下城区","endName":"站点名称4","endId":"4","distance":null,"carModel":"大型面包","power":0,"canRange":0,"frameNo":"LD923A161G9CDL156","startTime":"2017-06-13 13:42:55.0","endTime":"2017-07-22 11:00:45.0","lng":119.99051929,"lat":30.27491266}
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
         * taskId : 3
         * plateNo : 浙AZ1K87
         * startName : 下城区
         * endName : 站点名称4
         * endId : 4
         * distance : null
         * carModel : 大型面包
         * power : 0
         * canRange : 0
         * frameNo : LD923A161G9CDL156
         * startTime : 2017-06-13 13:42:55.0
         * endTime : 2017-07-22 11:00:45.0
         * lng : 119.99051929
         * lat : 30.27491266
         */

        private int taskId;
        private String plateNo;
        private String startName;
        private String endName;
        private String endId;
        private String distance;
        private String carModel;
        private int power;
        private int canRange;
        private String frameNo;
        private String startTime;
        private String endTime;
        private double lng;
        private double lat;

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

        public String getStartName() {
            return startName;
        }

        public void setStartName(String startName) {
            this.startName = startName;
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

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
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

        public int getCanRange() {
            return canRange;
        }

        public void setCanRange(int canRange) {
            this.canRange = canRange;
        }

        public String getFrameNo() {
            return frameNo;
        }

        public void setFrameNo(String frameNo) {
            this.frameNo = frameNo;
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
