package com.czh.retrofit.domain;

import java.util.List;

public class JsonResult {

    /**
     * success : true
     * code : 10000
     * message : 获取成功
     * data : [{"id":"1301119127458869248","title":"Android加载大图片，解决OOM问题","viewCount":98,"commentCount":88,"publishTime":"2020-09-02T11:25:48.267+0000","userName":"程序员拉大锯","cover":"/imgs/3.png"},{"id":"1301119127458869249","title":"Volley/Xutils对大图片处理算法源码分析","viewCount":195,"commentCount":58,"publishTime":"2020-09-02T11:25:48.267+0000","userName":"程序员拉大锯","cover":"/imgs/12.png"},{"id":"1301119127458869250","title":"Android开发网络安全配置","viewCount":137,"commentCount":103,"publishTime":"2020-09-02T11:25:48.267+0000","userName":"程序员拉大锯","cover":"/imgs/3.png"},{"id":"1301119127458869251","title":"Android开发网络编程，请求图片","viewCount":175,"commentCount":89,"publishTime":"2020-09-02T11:25:48.267+0000","userName":"程序员拉大锯","cover":"/imgs/14.png"},{"id":"1301119127458869252","title":"Intent页面跳转工具类分享","viewCount":223,"commentCount":94,"publishTime":"2020-09-02T11:25:48.267+0000","userName":"程序员拉大锯","cover":"/imgs/9.png"},{"id":"1301119127458869253","title":"阳光沙滩商城的API文档","viewCount":123,"commentCount":13,"publishTime":"2020-09-02T11:25:48.267+0000","userName":"程序员拉大锯","cover":"/imgs/8.png"},{"id":"1301119127458869254","title":"Android课程视频打包下载","viewCount":282,"commentCount":47,"publishTime":"2020-09-02T11:25:48.267+0000","userName":"程序员拉大锯","cover":"/imgs/14.png"},{"id":"1301119127458869255","title":"非常轻量级的gif录制软件","viewCount":131,"commentCount":106,"publishTime":"2020-09-02T11:25:48.267+0000","userName":"程序员拉大锯","cover":"/imgs/4.png"},{"id":"1301119127458869256","title":"Fiddler抓包工具，墙裂推荐，功能很强大很全的一个工具","viewCount":314,"commentCount":98,"publishTime":"2020-09-02T11:25:48.267+0000","userName":"程序员拉大锯","cover":"/imgs/4.png"},{"id":"1301119127458869257","title":"AndroidStudio奇淫技巧-代码管理","viewCount":153,"commentCount":65,"publishTime":"2020-09-02T11:25:48.267+0000","userName":"程序员拉大锯","cover":"/imgs/16.png"},{"id":"1301119127458869258","title":"OC和Swift混编","viewCount":97,"commentCount":58,"publishTime":"2020-09-02T11:25:48.267+0000","userName":"程序员拉大锯","cover":"/imgs/8.png"},{"id":"1301119127458869259","title":"最新的Android studio是不是没有Android Device Monitor","viewCount":157,"commentCount":107,"publishTime":"2020-09-02T11:25:48.267+0000","userName":"程序员拉大锯","cover":"/imgs/11.png"}]
     */

    private boolean success;
    private int code;
    private String message;
    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1301119127458869248
         * title : Android加载大图片，解决OOM问题
         * viewCount : 98
         * commentCount : 88
         * publishTime : 2020-09-02T11:25:48.267+0000
         * userName : 程序员拉大锯
         * cover : /imgs/3.png
         */

        private String id;
        private String title;
        private int viewCount;
        private int commentCount;
        private String publishTime;
        private String userName;
        private String cover;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getViewCount() {
            return viewCount;
        }

        public void setViewCount(int viewCount) {
            this.viewCount = viewCount;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public String getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }
    }
}
