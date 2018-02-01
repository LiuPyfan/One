package com.fantasy.pf.one.model.bean;

import java.util.List;

/**
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Created by pf on 2018/1/23.
 * let none that wait on thee be ashamed
 */

public class OneListBean {

    private String id;
    private WeatherBean weather;
    private String date;
    private MenuBean menu;
    private List<ContentListBean> content_list;

//    public OneListBean(String id, String date) {
//        this.id = id;
//        this.date = date;
//    }
    /***********************/
    public WeatherBean getWeather() {
        return weather;
    }

    public void setWeather(WeatherBean weather) {
        this.weather = weather;
    }
    /***********************/

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

    /***********************/
    public MenuBean getMenu() {
        return menu;
    }

    public void setMenu(MenuBean menu) {
        this.menu = menu;
    }

    public List<ContentListBean> getContentList() {
        return content_list;
    }

    public void setContent_list(List<ContentListBean> content_list) {
        this.content_list = content_list;
    }
    /***********************/


    @Override
    public String toString() {
        return "OneListBean{" +
                "id=" + id +
                ", date=" + date +
                '}';
    }
    /***********************/
    public static class WeatherBean {
        /**
         * city_name : 地球
         * date : 2018-01-25
         * temperature : -275
         * humidity : 120
         * climate : 对流层
         * wind_direction : 一阵妖风
         * hurricane : 36级
         * icons : {"day":"weather_icon_unknown","night":"weather_icon_unknown_night"}
         */

        private String city_name;
        private String date;
        private String temperature;
        private String humidity;
        private String climate;
        private String wind_direction;
        private String hurricane;
        private IconsBean icons;

        public String getCityName() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getClimate() {
            return climate;
        }

        public void setClimate(String climate) {
            this.climate = climate;
        }

        public String getWind_direction() {
            return wind_direction;
        }

        public void setWind_direction(String wind_direction) {
            this.wind_direction = wind_direction;
        }

        public String getHurricane() {
            return hurricane;
        }

        public void setHurricane(String hurricane) {
            this.hurricane = hurricane;
        }

        public IconsBean getIcons() {
            return icons;
        }

        public void setIcons(IconsBean icons) {
            this.icons = icons;
        }

        public static class IconsBean {
            /**
             * day : weather_icon_unknown
             * night : weather_icon_unknown_night
             */

            private String day;
            private String night;

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }

            public String getNight() {
                return night;
            }

            public void setNight(String night) {
                this.night = night;
            }
        }
    }
    /***********************/
    public static class MenuBean {
        /**
         * vol : 1937
         * list : [{"content_type":"1","content_id":"3050","title":"大寒","tag":{"id":"7","title":"ONE STORY"}},{"content_type":"3","content_id":"1988","title":"如何看待\u201c媚俗\u201d？"},{"content_type":"2","content_id":"530","serial_list":["502","504","506","508","509","511","514","516","518","520","522","527","528","529","530"],"title":"他点火的方式 · 最终章"},{"content_type":"4","content_id":"2534","title":"23岁听赵雷的《十九岁》，才记起，原来恋爱应该这么谈"},{"content_type":"5","content_id":"1298","title":"别人笑我太疯癫，我笑他人看不穿"}]
         */

        private String vol;
        private List<OneListBean.MenuBean.ListBean> list;

        public String getVol() {
            return vol;
        }

        public void setVol(String vol) {
            this.vol = vol;
        }

        public List<OneListBean.MenuBean.ListBean> getList() {
            return list;
        }

        public void setList(List<OneListBean.MenuBean.ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * content_type : 1
             * content_id : 3050
             * title : 大寒
             * tag : {"id":"7","title":"ONE STORY"}
             * serial_list : ["502","504","506","508","509","511","514","516","518","520","522","527","528","529","530"]
             */

            private String content_type;
            private String content_id;
            private String title;
            private OneListBean.MenuBean.ListBean.TagBean tag;
            private List<String> serial_list;

            public String getContent_type() {
                return content_type;
            }

            public void setContent_type(String content_type) {
                this.content_type = content_type;
            }

            public String getContent_id() {
                return content_id;
            }

            public void setContent_id(String content_id) {
                this.content_id = content_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public OneListBean.MenuBean.ListBean.TagBean getTag() {
                return tag;
            }

            public void setTag(OneListBean.MenuBean.ListBean.TagBean tag) {
                this.tag = tag;
            }

            public List<String> getSerial_list() {
                return serial_list;
            }

            public void setSerial_list(List<String> serial_list) {
                this.serial_list = serial_list;
            }

            public static class TagBean {
                /**
                 * id : 7
                 * title : ONE STORY
                 */

                private String id;
                private String title;

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
            }
        }
    }
    /***********************/




}
