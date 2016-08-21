package com.example.giftgenius.openservicefragment.bean;

import java.util.List;

/**
 * Created by 彭永顺 on 2016/8/17.
 */
public class GiftOpence {

    /**
     * id : 1470969199
     * iconurl : /allimgs/img_iapp/201608/_1470810221345.jpg
     * gname : 乱轰三国志
     * testtype : 6
     * score : 5.0
     * linkurl : 10:00
     * istop : 0
     * colors : 0
     * platform : 1
     * operators : 云渺游戏
     * state : 1
     * addtime : 08-17 10:00
     * teststarttime : 2016-08-17 10:00:00
     * gift : 策略塔防
     * keyword :
     * uid : 402881d254be7c330154e57e676a0926
     * gid : 1470810357
     * indexpy : 0
     * isdel : 1
     * openflag : 1
     * openflagname : 内测
     * vtypeimage : <i class='android'></i>
     */
    private int id;
    private String iconurl;
    private String gname;
    private String linkurl;
    private String operators;
    private String addtime;
    private String gift;
    private String gid;

    @Override
    public String toString() {
        return "GiftOpence{" +
                "id=" + id +
                ", iconurl='" + iconurl + '\'' +
                ", gname='" + gname + '\'' +
                ", linkurl='" + linkurl + '\'' +
                ", operators='" + operators + '\'' +
                ", addtime='" + addtime + '\'' +
                ", gift='" + gift + '\'' +
                ", gid='" + gid + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIconurl() {
        return iconurl;
    }

    public void setIconurl(String iconurl) {
        this.iconurl = iconurl;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getLinkurl() {
        return linkurl;
    }

    public void setLinkurl(String linkurl) {
        this.linkurl = linkurl;
    }

    public String getOperators() {
        return operators;
    }

    public void setOperators(String operators) {
        this.operators = operators;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getGift() {
        return gift;
    }

    public void setGift(String gift) {
        this.gift = gift;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }
}
