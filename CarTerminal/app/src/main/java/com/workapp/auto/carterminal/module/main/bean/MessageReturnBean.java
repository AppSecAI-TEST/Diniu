package com.workapp.auto.carterminal.module.main.bean;

import com.workapp.auto.carterminal.base.BaseResponse;

import java.util.List;

/**消息接口-- 公告
 * Created by Administrator on 2017/8/8 0008.
 */

public class MessageReturnBean extends BaseResponse{

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 2
         * type : 2
         * title : 测试活动公告
         * description : 测试活动公告
         * startTime : 2017-06-30 13:35:30
         * endTime : 2017-07-01 13:35:30
         * target : 3
         * channel : 0
         * way : 0
         * content : a
         * url : 测试活动公告
         * isNotice : true
         */

        private int id;
        private int type;
        private String title;
        private String description;
        private String startTime;
        private String endTime;
        private int target;
        private int channel;
        private int way;
        private String content;
        private String url;
        private boolean isNotice;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
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

        public int getTarget() {
            return target;
        }

        public void setTarget(int target) {
            this.target = target;
        }

        public int getChannel() {
            return channel;
        }

        public void setChannel(int channel) {
            this.channel = channel;
        }

        public int getWay() {
            return way;
        }

        public void setWay(int way) {
            this.way = way;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isIsNotice() {
            return isNotice;
        }

        public void setIsNotice(boolean isNotice) {
            this.isNotice = isNotice;
        }
    }
}
