package jjr.com.playandroids.beans.collect;

import java.util.List;

public class CollectListBean {
    /**
     * data : {"curPage":1,"datas":[{"author":"code小生","chapterId":414,"chapterName":"code小生","courseId":13,"desc":"","envelopePic":"","id":49132,"link":"https://mp.weixin.qq.com/s/hzCBLwMY04aPWrcTlJ2uPQ","niceDate":"9分钟前","origin":"","originId":7995,"publishTime":1551776950000,"title":"2019 Android 高级面试题总结","userId":17942,"visible":0,"zan":0},{"author":"互联网侦察","chapterId":421,"chapterName":"互联网侦察","courseId":13,"desc":"","envelopePic":"","id":49130,"link":"https://mp.weixin.qq.com/s/xrdQrbQcTihsGr1_KTxABg","niceDate":"9分钟前","origin":"","originId":7999,"publishTime":1551776935000,"title":"【生活现场】从洗袜子到hbase存储原理解析（下篇）","userId":17942,"visible":0,"zan":0},{"author":"dev晴天","chapterId":304,"chapterName":"基础源码","courseId":13,"desc":"","envelopePic":"","id":49124,"link":"https://www.jianshu.com/p/5a2a628e7c69","niceDate":"22分钟前","origin":"","originId":8001,"publishTime":1551776195000,"title":"使用的形参为何要为final","userId":17942,"visible":0,"zan":0},{"author":"stormzhang","chapterId":80,"chapterName":"Github用法进阶","courseId":13,"desc":"","envelopePic":"","id":49123,"link":"http://stormzhang.com/github/2016/05/25/learn-github-from-zero1/","niceDate":"23分钟前","origin":"","originId":100,"publishTime":1551776101000,"title":"从0开始学习 GitHub 系列之「初识 GitHub」","userId":17942,"visible":0,"zan":0},{"author":"Ruheng","chapterId":26,"chapterName":"基础UI控件","courseId":13,"desc":"详解Android图文混排实现。","envelopePic":"","id":49122,"link":"http://www.jianshu.com/p/6843f332c8df","niceDate":"24分钟前","origin":"","originId":1165,"publishTime":1551776047000,"title":"Android图文混排实现方式详解","userId":17942,"visible":0,"zan":0}],"offset":0,"over":true,"pageCount":1,"size":20,"total":5}
     * errorCode : 0
     * errorMsg :
     */

    private DataBean data;
    private int errorCode;
    private String errorMsg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public static class DataBean {
        /**
         * curPage : 1
         * datas : [{"author":"code小生","chapterId":414,"chapterName":"code小生","courseId":13,"desc":"","envelopePic":"","id":49132,"link":"https://mp.weixin.qq.com/s/hzCBLwMY04aPWrcTlJ2uPQ","niceDate":"9分钟前","origin":"","originId":7995,"publishTime":1551776950000,"title":"2019 Android 高级面试题总结","userId":17942,"visible":0,"zan":0},{"author":"互联网侦察","chapterId":421,"chapterName":"互联网侦察","courseId":13,"desc":"","envelopePic":"","id":49130,"link":"https://mp.weixin.qq.com/s/xrdQrbQcTihsGr1_KTxABg","niceDate":"9分钟前","origin":"","originId":7999,"publishTime":1551776935000,"title":"【生活现场】从洗袜子到hbase存储原理解析（下篇）","userId":17942,"visible":0,"zan":0},{"author":"dev晴天","chapterId":304,"chapterName":"基础源码","courseId":13,"desc":"","envelopePic":"","id":49124,"link":"https://www.jianshu.com/p/5a2a628e7c69","niceDate":"22分钟前","origin":"","originId":8001,"publishTime":1551776195000,"title":"使用的形参为何要为final","userId":17942,"visible":0,"zan":0},{"author":"stormzhang","chapterId":80,"chapterName":"Github用法进阶","courseId":13,"desc":"","envelopePic":"","id":49123,"link":"http://stormzhang.com/github/2016/05/25/learn-github-from-zero1/","niceDate":"23分钟前","origin":"","originId":100,"publishTime":1551776101000,"title":"从0开始学习 GitHub 系列之「初识 GitHub」","userId":17942,"visible":0,"zan":0},{"author":"Ruheng","chapterId":26,"chapterName":"基础UI控件","courseId":13,"desc":"详解Android图文混排实现。","envelopePic":"","id":49122,"link":"http://www.jianshu.com/p/6843f332c8df","niceDate":"24分钟前","origin":"","originId":1165,"publishTime":1551776047000,"title":"Android图文混排实现方式详解","userId":17942,"visible":0,"zan":0}]
         * offset : 0
         * over : true
         * pageCount : 1
         * size : 20
         * total : 5
         */

        private int curPage;
        private int offset;
        private boolean over;
        private int pageCount;
        private int size;
        private int total;
        private List<DatasBean> datas;

        @Override
        public String toString() {
            return "DataBean{" +
                    "curPage=" + curPage +
                    ", offset=" + offset +
                    ", over=" + over +
                    ", pageCount=" + pageCount +
                    ", size=" + size +
                    ", total=" + total +
                    ", datas=" + datas +
                    '}';
        }

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public boolean isOver() {
            return over;
        }

        public void setOver(boolean over) {
            this.over = over;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<DatasBean> getDatas() {
            return datas;
        }

        public void setDatas(List<DatasBean> datas) {
            this.datas = datas;
        }

        public static class DatasBean {

            /**
             * author : code小生
             * chapterId : 414
             * chapterName : code小生
             * courseId : 13
             * desc :
             * envelopePic :
             * id : 49132
             * link : https://mp.weixin.qq.com/s/hzCBLwMY04aPWrcTlJ2uPQ
             * niceDate : 9分钟前
             * origin :
             * originId : 7995
             * publishTime : 1551776950000
             * title : 2019 Android 高级面试题总结
             * userId : 17942
             * visible : 0
             * zan : 0
             */

            private String author;
            private int chapterId;
            private String chapterName;
            private int courseId;
            private String desc;
            private String envelopePic;
            private int id;
            private String link;
            private String niceDate;
            private String origin;
            private int originId;
            private long publishTime;
            private String title;
            private int userId;
            private int visible;
            private int zan;

            @Override
            public String toString() {
                return "DatasBean{" +
                        "author='" + author + '\'' +
                        ", chapterId=" + chapterId +
                        ", chapterName='" + chapterName + '\'' +
                        ", courseId=" + courseId +
                        ", desc='" + desc + '\'' +
                        ", envelopePic='" + envelopePic + '\'' +
                        ", id=" + id +
                        ", link='" + link + '\'' +
                        ", niceDate='" + niceDate + '\'' +
                        ", origin='" + origin + '\'' +
                        ", originId=" + originId +
                        ", publishTime=" + publishTime +
                        ", title='" + title + '\'' +
                        ", userId=" + userId +
                        ", visible=" + visible +
                        ", zan=" + zan +
                        '}';
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public int getChapterId() {
                return chapterId;
            }

            public void setChapterId(int chapterId) {
                this.chapterId = chapterId;
            }

            public String getChapterName() {
                return chapterName;
            }

            public void setChapterName(String chapterName) {
                this.chapterName = chapterName;
            }

            public int getCourseId() {
                return courseId;
            }

            public void setCourseId(int courseId) {
                this.courseId = courseId;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getEnvelopePic() {
                return envelopePic;
            }

            public void setEnvelopePic(String envelopePic) {
                this.envelopePic = envelopePic;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getNiceDate() {
                return niceDate;
            }

            public void setNiceDate(String niceDate) {
                this.niceDate = niceDate;
            }

            public String getOrigin() {
                return origin;
            }

            public void setOrigin(String origin) {
                this.origin = origin;
            }

            public int getOriginId() {
                return originId;
            }

            public void setOriginId(int originId) {
                this.originId = originId;
            }

            public long getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(long publishTime) {
                this.publishTime = publishTime;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getVisible() {
                return visible;
            }

            public void setVisible(int visible) {
                this.visible = visible;
            }

            public int getZan() {
                return zan;
            }

            public void setZan(int zan) {
                this.zan = zan;
            }
        }
    }
}
