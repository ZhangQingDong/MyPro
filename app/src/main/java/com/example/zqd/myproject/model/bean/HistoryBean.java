package com.example.zqd.myproject.model.bean;

import java.util.List;

/**
 * <p>Title: com.example.zqd.myproject.model.bean</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: </p>
 *
 * @author zhangqingdong
 * @date 2018/4/24 13:14
 */
public class HistoryBean {

    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"ret_code":0,"list":[{"title":"世界卫生组织宣布已经成功控制SARS","month":7,"img":"http://img.showapi.com/201107/5/099368663.jpg","year":"2003","day":5}]}
     */

    private int showapi_res_code;
    private String showapi_res_error;
    private ShowapiResBodyEntity showapi_res_body;

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public ShowapiResBodyEntity getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowapiResBodyEntity showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public static class ShowapiResBodyEntity {
        /**
         * ret_code : 0
         * list : [{"title":"世界卫生组织宣布已经成功控制SARS","month":7,"img":"http://img.showapi.com/201107/5/099368663.jpg","year":"2003","day":5}]
         */

        private int ret_code;
        private List<ListEntity> list;

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public List<ListEntity> getList() {
            return list;
        }

        public void setList(List<ListEntity> list) {
            this.list = list;
        }

        public static class ListEntity {
            /**
             * title : 世界卫生组织宣布已经成功控制SARS
             * month : 7
             * img : http://img.showapi.com/201107/5/099368663.jpg
             * year : 2003
             * day : 5
             */

            private String title;
            private int month;
            private String img;
            private String year;
            private int day;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getYear() {
                return year;
            }

            public void setYear(String year) {
                this.year = year;
            }

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }
        }
    }
}
