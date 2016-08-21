package com.example.giftgenius.characteristicfragment.bean;

/**
 * Created by 彭永顺 on 2016/8/21.
 */
public class BDListViewBean {
    private String iconurl;
    private String descs;
    private String addtime;

    public BDListViewBean( String addtime, String descs, String iconurl) {
        this.addtime = addtime;
        this.descs = descs;
        this.iconurl = iconurl;
    }

    @Override
    public String toString() {
        return "BDListViewBean{" +
                "iconurl='" + iconurl + '\'' +
                ", descs='" + descs + '\'' +
                ", addtime='" + addtime + '\'' +
                '}';
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

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }
}
