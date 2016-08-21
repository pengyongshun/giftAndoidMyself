package com.example.giftgenius.openservicefragment.bean;

import java.util.List;

/**
 * Created by 彭永顺 on 2016/8/16.
 */
public class GiftOpenservice {

    /**
     * id : 1470900262
     * iconurl : /allimgs/img_iapp/201510/_1445324917748.png
     * gname : 皇图
     * openstate : 1
     * opentype : 2
     * score : 5.0
     * linkurl : 08-16 10:00
     * istop : 0
     * colors : 0
     * platform : 1
     * operators : 9377
     * state : 1
     * addtime : 2016-08-16
     * starttime : 2016-08-16 10:00
     * keyword :
     * area : 355服
     * uid : 402881d2554284e2015547751122012a
     * gid : 1442977465
     * indexpy : 0
     * isdel : 1
     * openflag : 3
     * vtypeimage : <i class='android'></i>
     */

    private int id;
    private String iconurl;
    private String gname;
    private String linkurl;
    private String operators;
    private String addtime;
    private String area;
    private String gid;

    @Override
    public String toString() {
        return "GiftOpenservice{" +
                "id=" + id +
                ", iconurl='" + iconurl + '\'' +
                ", gname='" + gname + '\'' +
                ", linkurl='" + linkurl + '\'' +
                ", operators='" + operators + '\'' +
                ", addtime='" + addtime + '\'' +
                ", area='" + area + '\'' +
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }
}
