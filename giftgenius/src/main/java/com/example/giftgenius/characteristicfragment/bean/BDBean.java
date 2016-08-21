package com.example.giftgenius.characteristicfragment.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 彭永顺 on 2016/8/17.
 */
public class BDBean implements Serializable{

    /**
     * sid : 112
     * name : 炎炎夏日 一秒透心
     * state : 0
     * addtime : 2016-08-16
     * clicks : 39
     * iconurl : /allimgs/img_iapp/201608/_1471346567926.jpg
     * istop : 0
     * good : 0
     * bad : 0
     * keyword :
     * descs : 马上就快到中元节了，中元节又俗称鬼节，这个传统节日总觉得有点瘆的慌呀，胆小的小编在写这篇恐怖手游盘点时，内心其实是拒绝的@.@！不过据说恐怖游戏会令人脉搏加快、心率提升，从而增强血液循环，让压力在瞬间得到释放，同时消耗热量，对健康很有益（能减肥？）……！
     * orderno : 25
     * nums : 9
     */

        private int sid;
        private String name;
        private String addtime;
        private String iconurl;
        private String descs;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getIconurl() {
        return iconurl;
    }

    public void setIconurl(String iconurl) {
        this.iconurl = iconurl;
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }

    @Override
    public String toString() {
        return "BDBean{" +
                "sid=" + sid +
                ", name='" + name + '\'' +
                ", addtime='" + addtime + '\'' +
                ", iconurl='" + iconurl + '\'' +
                ", descs='" + descs + '\'' +
                '}';
    }
}
