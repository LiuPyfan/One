package com.fantasy.pf.one.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * let none that wait on thee be ashamed
 * Created by pf on 2018/2/5.
 * 包名:com.fantasy.pf.one.model.bean
 * 详情评论列表
 * http://v3.wufazhuce.com:8000/api/comment/praiseandtime/essay/3070/0
 */

public class CommentBean {

    private int count;
    private List<DataBean> data;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 71862
         * quote :
         * content : 承诺是用来调情的不是用来兑现的
         * praisenum : 46
         * device_token :
         * del_flag : 0
         * reviewed : 0
         * user_info_id : 0
         * input_date : 2018-02-05 08:29:42
         * created_at : 2018-02-05 08:29:42
         * updated_at : 0000-00-00 00:00:00
         * user : {"user_id":"7429466","user_name":"拥抱","web_url":"http://image.wufazhuce.com/FgulHuPl4l-uELBVYhGsXFmXTyPQ?imageView2/1/w/80/h/80/q/75"}
         * touser : null
         * type : 0
         */

        private String id;
        private String quote;
        private String content;
        private int praisenum;
        private String device_token;
        private String del_flag;
        private String reviewed;
        private String user_info_id;
        private String input_date;
        private String created_at;
        private String updated_at;
        private UserBean user;
        // 用户回复另一个用户的话
        private ToUserBean touser;
        private int type;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getQuote() {
            return quote;
        }

        public void setQuote(String quote) {
            this.quote = quote;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getPraisenum() {
            return praisenum;
        }

        public void setPraisenum(int praisenum) {
            this.praisenum = praisenum;
        }

        public String getDevice_token() {
            return device_token;
        }

        public void setDevice_token(String device_token) {
            this.device_token = device_token;
        }

        public String getDel_flag() {
            return del_flag;
        }

        public void setDel_flag(String del_flag) {
            this.del_flag = del_flag;
        }

        public String getReviewed() {
            return reviewed;
        }

        public void setReviewed(String reviewed) {
            this.reviewed = reviewed;
        }

        public String getUser_info_id() {
            return user_info_id;
        }

        public void setUser_info_id(String user_info_id) {
            this.user_info_id = user_info_id;
        }

        public String getInput_date() {
            return input_date;
        }

        public void setInput_date(String input_date) {
            this.input_date = input_date;
        }

        public String getCreatedAt() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public ToUserBean getTouser() {
            return touser;
        }

        public void setTouser(ToUserBean touser) {
            this.touser = touser;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }


        public static class UserBean {
            /**
             * user_id : 7429466
             * user_name : 拥抱
             * web_url : http://image.wufazhuce.com/FgulHuPl4l-uELBVYhGsXFmXTyPQ?imageView2/1/w/80/h/80/q/75
             */

            private String user_id;
            private String user_name;
            private String web_url;

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getUserName() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getWebUrl() {
                return web_url;
            }

            public void setWeb_url(String web_url) {
                this.web_url = web_url;
            }
        }

/*
{
        "id": "75735",
        "quote": "城南有旧事， 城北有信使， 林深时见鹿， 海蓝时见鲸，梦醒时见你， 树深时雾起， 海深时浪涌， 梦醒时夜续，不见鹿， 不见鲸， 也不见你……",
        "content": "喜欢这段话",
        "praisenum": 0,
        "device_token": "",
        "del_flag": "0",
        "reviewed": "0",
        "user_info_id": "0",
        "input_date": "2018-02-06 14:30:53",
        "created_at": "2018-02-06 14:30:53",
        "updated_at": "0000-00-00 00:00:00",
        "user": {
          "user_id": "8841297",
          "user_name": "张晓琥",
          "web_url": "http://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83er6F6ZTZK8p6RHod70UrDde1zwFcOhNorNicNia0YEnicbvo0ZxNmKibYNvJUOiaq6WlrnLiaCo7eo2FBicQ/132"
        },
        "touser": {
          "user_id": "8147226",
          "user_name": "HM",
          "web_url": "http://q.qlogo.cn/qqapp/1104596227/7F101CC37BA89C48CC5BFDDD40027183/40"
        },
        "type": 1
      }
      */

        public static class ToUserBean {

            /**
             * user_id : 8147226
             * user_name : HM
             * web_url : http://q.qlogo.cn/qqapp/1104596227/7F101CC37BA89C48CC5BFDDD40027183/40
             */

            private String user_id;
            private String user_name;
            private String web_url;

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getUserName() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getWebUrl() {
                return web_url;
            }

            public void setWeb_url(String web_url) {
                this.web_url = web_url;
            }
        }


    }

}
