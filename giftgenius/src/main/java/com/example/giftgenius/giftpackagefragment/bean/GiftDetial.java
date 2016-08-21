package com.example.giftgenius.giftpackagefragment.bean;

import java.io.Serializable;

/**
 * Created by 彭永顺 on 2016/8/15.
 */
public class GiftDetial implements Serializable{


    /**
     * id : 1470898274
     * iconurl : /allimgs/img_iapp/201604/_1460436962836.jpg
     * giftname : 独家iOS兑换码
     * number : 5
     * exchanges : 1
     * flag : 1
     * explains : 以游戏内领取为准
     * descs : 进入App Store-点击最下方【兑换】-输入代码
     * addtime : 2016-08-11
     * sendtime : 1970-01-01
     * overtime : 2022-08-11
     * type : 1
     * typename : 礼包卡
     * operators : 厦门枫魔互动信息科技有限公司
     * surplus : 0
     * gid : 1460436992
     * gname : 明星保卫战
     * uid : e77468d250138e63015018079aed0316
     * integral : 0
     * isintegral : 0
     * vtypeimg : <i class='android'></i><i class='ios'></i>
     * vtype : 0
     * marks : 0
     * vtypename : 网站
     * ptype : 1,2,
     * indexpy : 0
     * gtype : 2
     * onclicks : 0
     * turl :
     * showimg :
     */

    private InfoBean info;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class InfoBean {
        private String id;
        private String iconurl;
        private String giftname;
        private int number;
        private int exchanges;
        private String explains;
        private String descs;
        private String overtime;
        private String gname;

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

        public int getExchanges() {
            return exchanges;
        }

        public void setExchanges(int exchanges) {
            this.exchanges = exchanges;
        }

        public String getExplains() {
            return explains;
        }

        public void setExplains(String explains) {
            this.explains = explains;
        }

        public String getDescs() {
            return descs;
        }

        public void setDescs(String descs) {
            this.descs = descs;
        }

        public String getOvertime() {
            return overtime;
        }

        public void setOvertime(String overtime) {
            this.overtime = overtime;
        }

        public String getGname() {
            return gname;
        }

        public void setGname(String gname) {
            this.gname = gname;
        }
    }
}
