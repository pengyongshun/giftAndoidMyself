package com.example.giftgenius.characteristicfragment.bean;


import java.io.Serializable;

/**
 * Created by 彭永顺 on 2016/8/17.
 */
public class XYBean implements Serializable {


    /**
     * id : 20160534
     * name : 新游周刊第89期
     * author : 凯子
     * authorimg : images/head/kaizi.jpg
     * iconurl : /allimgs/img_iapp/201608/_1471244542064.jpg
     * showiconurl : /allimgs/img_iapp/201608/_1471244545003.jpg
     * descs : 新游周刊为大家介绍每周新游，本周为大家介绍的新游有：西山居二次元手游新作《少女咖啡枪》，战锤40K外传《艾森霍恩：异形审判官》，Gamevil横版动作新作《假面破坏王》，SquareEnix经典体育经营系列《冠军足球经理17》。更多精彩新游，一起看看本期的详细介绍吧！
     * newsurl : http://www.1688wan.com/news/pingce/201608/info_148222.html
     * newsicon : http://www.1688wan.com/allimgs/image/2016/07/20/20160720175953_334.jpg
     * flag : 1
     * views : 268
     * orderno : 089
     * addtime : 2016-08-12
     * keyword : 最新手游推荐,好玩的手游推荐,新游周刊
     * newid : 0
     * nums : 9
     */
        private int id;
        private String name;
        private String authorimg;
        private String iconurl;
        private String addtime;
        private String descs;

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

    public String getAuthorimg() {
        return authorimg;
    }

    public void setAuthorimg(String authorimg) {
        this.authorimg = authorimg;
    }

    public String getIconurl() {
        return iconurl;
    }

    public void setIconurl(String iconurl) {
        this.iconurl = iconurl;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }

    @Override
    public String toString() {
        return "XYBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", authorimg='" + authorimg + '\'' +
                ", iconurl='" + iconurl + '\'' +
                ", addtime='" + addtime + '\'' +
                ", descs='" + descs + '\'' +
                '}';
    }
}
