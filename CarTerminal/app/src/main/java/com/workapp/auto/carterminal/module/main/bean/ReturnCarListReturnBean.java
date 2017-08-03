package com.workapp.auto.carterminal.module.main.bean;

import com.workapp.auto.carterminal.base.BaseResponse;

import java.util.List;

/**还车列表实体类
 * Created by owner on 2017/7/5.
 */

public class ReturnCarListReturnBean extends BaseResponse{

    /**
     * data : {"content":[{"taskId":3,"plateNo":"浙F741256","carModel":"车型22","canRange":0,"distance":0,"power":0,"frameNo":"6321450"},{"taskId":2,"plateNo":"浙AZ1K87","carModel":"测试车型1","canRange":14.2,"distance":0,"power":71,"frameNo":"LD923A161G9CDL156"}],"page":1,"size":5,"pages":0,"total":0,"first":true,"last":true}
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
         * content : [{"taskId":3,"plateNo":"浙F741256","carModel":"车型22","canRange":0,"distance":0,"power":0,"frameNo":"6321450"},{"taskId":2,"plateNo":"浙AZ1K87","carModel":"测试车型1","canRange":14.2,"distance":0,"power":71,"frameNo":"LD923A161G9CDL156"}]
         * page : 1
         * size : 5
         * pages : 0
         * total : 0
         * first : true
         * last : true
         */

        private int page;
        private int size;
        private int pages;
        private int total;
        private boolean first;
        private boolean last;
        private List<ContentBean> content;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public boolean isFirst() {
            return first;
        }

        public void setFirst(boolean first) {
            this.first = first;
        }

        public boolean isLast() {
            return last;
        }

        public void setLast(boolean last) {
            this.last = last;
        }

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public static class ContentBean {
            /**
             * taskId : 3
             * plateNo : 浙F741256
             * carModel : 车型22
             * canRange : 0
             * distance : 0
             * power : 0
             * frameNo : 6321450
             */

            private int taskId;
            private String plateNo;
            private String carModel;
            private double canRange;
            private int distance;
            private int power;
            private String frameNo;

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

            public double getCanRange() {
                return canRange;
            }

            public void setCanRange(double canRange) {
                this.canRange = canRange;
            }

            public int getDistance() {
                return distance;
            }

            public void setDistance(int distance) {
                this.distance = distance;
            }

            public int getPower() {
                return power;
            }

            public void setPower(int power) {
                this.power = power;
            }

            public String getFrameNo() {
                return frameNo;
            }

            public void setFrameNo(String frameNo) {
                this.frameNo = frameNo;
            }
        }
    }
}
