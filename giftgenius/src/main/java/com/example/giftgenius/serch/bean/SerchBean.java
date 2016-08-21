package com.example.giftgenius.serch.bean;

import java.util.List;

/**
 * Created by 彭永顺 on 2016/8/18.
 */
public class SerchBean {

    /**
     * id : 1471499611
     * iconurl : /allimgs/img_iapp/201605/_1463469140278.jpg
     * giftname : 手游2周年全民狂欢礼包
     * number : 29
     * exchanges : 30
     * type : 1
     * gname : 神武2
     * integral : 0
     * isintegral : 0
     * addtime : 2016-08-18
     * ptype : 1,2,
     * operators : 广州多益网络股份有限公司
     * flag : 1
     */

        private String id;
        private String iconurl;
        private String giftname;
        private int number;
        private String gname;
        private String addtime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIconurl() {
            return iconurl;
        }

        public void setIconurl(String iconurl) {
            this.iconurl = iconurl;
        }

        public String getGiftname() {
            return giftname;
        }

        public void setGiftname(String giftname) {
            this.giftname = giftname;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getGname() {
            return gname;
        }

        public void setGname(String gname) {
            this.gname = gname;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }
}
