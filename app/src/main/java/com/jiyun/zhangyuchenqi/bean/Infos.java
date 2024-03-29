package com.jiyun.zhangyuchenqi.bean;

import java.util.List;

public class Infos {
    private List<RecentBean> recent;

    public List<RecentBean> getRecent() {
        return recent;
    }

    public void setRecent(List<RecentBean> recent) {
        this.recent = recent;
    }

    public static class RecentBean {
        /**
         * news_id : 9712705
         * url : http://news-at.zhihu.com/api/2/news/9712705
         * thumbnail : https://pic2.zhimg.com/v2-f072a59a76ab7fb9ea1fb4dcc98224a9.jpg
         * title : 小事 · 为了高考，他们偷偷让孩子吸毒
         */

        private int news_id;
        private String url;
        private String thumbnail;
        private String title;

        public int getNews_id() {
            return news_id;
        }

        public void setNews_id(int news_id) {
            this.news_id = news_id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
