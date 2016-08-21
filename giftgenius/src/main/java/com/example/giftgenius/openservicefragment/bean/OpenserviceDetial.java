package com.example.giftgenius.openservicefragment.bean;

import java.util.List;

/**
 * Created by 彭永顺 on 2016/8/17.
 */
public class OpenserviceDetial {


    /**
     * id : 1442977465
     * name : 皇图
     * developers : 广州创思信息技术有限公司
     * appsize :
     * version :
     * logo : /allimgs/img_iapp/201510/_1445324917748.png
     * download_addr : http://dl.sy.9377.com/ht/ht_9377_sig_ht_mj.apk
     * upload_time : 1442977440
     * add_time : 1442977462
     * state : 1
     * keywords : 皇图
     * operator : 随逸
     * typeid : 2
     * orderid : 1
     * description : 年度最劲爆ARPG手游《皇图》震撼来袭。游戏中呈现各种劲爆玩法：狂刷BOSS，怒
     爆神装；手刃敌人，一雪前耻；诸王争霸，血战皇城；万人激战，一统天下！全自由PK系统，酷炫的
     造型，华丽的技能特效，让你领略劲爆的PK游戏！
     * good_evaluation : 0
     * bad_evaluation : 0
     * downloads : 138
     * views : 0
     * flag : 1
     * is_free : 0
     * freename : 免费
     * video_addr : http://s.9377.com/ht/
     * statename : 已上架
     * flagname : 推荐
     * typename : 角色扮演
     * imagenum : 0
     * cid : 1434014227
     * py : HT
     * vtype : 1,2
     * vtypename : [安卓]&nbsp;[IOS]&nbsp;
     * vtypeimgs : <i class='android'></i><i class='ios'></i>
     * downs : 0
     * yy : 0
     * yyname : 中文
     * isnetwork : 0
     * isgame : 0
     * true_good_evaluation : 0
     * true_bad_evaluation : 0
     * true_downloads : 138
     * true_views : 0
     * tz : 策略,
     * fmoeny : 0
     * isintegral : 0
     * gflag : 0
     * libaoimg :
     * zqshowimg :
     * iszq : 0
     * zqurl : http://
     * moulds : 0
     * bgimg :
     * remarks : 自由PK   万人攻城
     * appscore : 7.8
     * appscore1 : 7
     * appscore2 : .8
     * trueappscore : 5
     * zqcode : ht
     * isnewgame : 0
     * packagename :
     * zqflag : 0
     * zqscore : 5.0
     */

    private AppBean app;
    /**
     * id : 234566
     * address : /allimgs/img_iapp/201510/_1445324934254.png
     * orderno : 4
     * state : 0
     * appid : 1442977465
     */

    private List<ImgBean> img;

    public AppBean getApp() {
        return app;
    }

    public void setApp(AppBean app) {
        this.app = app;
    }

    public List<ImgBean> getImg() {
        return img;
    }

    public void setImg(List<ImgBean> img) {
        this.img = img;
    }

    public static class AppBean {
        private int id;
        private String name;
        private String logo;
        private String download_addr;
        private String description;
        private int downloads;
        private String video_addr;
        private String typename;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getDownload_addr() {
            return download_addr;
        }

        public void setDownload_addr(String download_addr) {
            this.download_addr = download_addr;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getDownloads() {
            return downloads;
        }

        public void setDownloads(int downloads) {
            this.downloads = downloads;
        }

        public String getVideo_addr() {
            return video_addr;
        }

        public void setVideo_addr(String video_addr) {
            this.video_addr = video_addr;
        }

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }
    }

    public static class ImgBean {
        private String address;
        private String appid;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }
    }
}
