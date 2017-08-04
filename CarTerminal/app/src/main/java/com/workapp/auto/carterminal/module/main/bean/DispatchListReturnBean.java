package com.workapp.auto.carterminal.module.main.bean;

import com.workapp.auto.carterminal.base.BaseResponse;

import java.util.List;

/**调度列表
 * Created by Administrator on 2017/8/4 0004.
 */

public class DispatchListReturnBean extends BaseResponse{

    /**
     * data : {"content":[{"taskId":"3","plateNo":"浙B157w8","startName":"站点名称1","endName":"站点名称4","distance":7659269,"carModel":"车型1","power":0,"canRange":0,"frameNo":"LD923A168G9CDL266"}],"page":1,"size":5,"pages":1,"total":3,"first":true,"last":true}
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
         * content : [{"taskId":"3","plateNo":"浙B157w8","startName":"站点名称1","endName":"站点名称4","distance":7659269,"carModel":"车型1","power":0,"canRange":0,"frameNo":"LD923A168G9CDL266"}]
         * page : 1
         * size : 5
         * pages : 1
         * total : 3
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
             * plateNo : 浙B157w8
             * startName : 站点名称1
             * endName : 站点名称4
             * distance : 7659269
             * carModel : 车型1
             * power : 0
             * canRange : 0
             * frameNo : LD923A168G9CDL266
             */

            private String taskId;
            private String plateNo;
            private String startName;
            private String endName;
            private int distance;
            private String carModel;
            private int power;
            private int canRange;
            private String frameNo;

            public String getTaskId() {
                return taskId;
            }

            public void setTaskId(String taskId) {
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

            public int getDistance() {
                return distance;
            }

            public void setDistance(int distance) {
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
        }
    }
}
