package com.fantasy.pf.one.model.bean;

/**
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Created by pf on 2018/1/23.
 * let none that wait on thee be ashamed
 */

public class OneListBean {

        private String id;
        private String date;

        public OneListBean(String id, String date) {
            this.id = id;
            this.date = date;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        @Override
        public String toString() {
            return "OneListBean{" +
                    "id=" + id +
                    ", date=" + date +
                    '}';
        }

}
