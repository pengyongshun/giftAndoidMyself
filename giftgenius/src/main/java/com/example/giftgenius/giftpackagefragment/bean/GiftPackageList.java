package com.example.giftgenius.giftpackagefragment.bean;

/**
 * Created by 彭永顺 on 2016/8/6.
 */
public class GiftPackageList {
    private String giftid;
    private String iconurl;
    private String giftname;
    private int number;
    private String gname;
    private String addtime;

    public GiftPackageList(String giftid, String iconurl, String giftname, int number, String gname, String addtime) {
        this.giftid = giftid;
        this.iconurl = iconurl;
        this.giftname = giftname;
        this.number = number;
        this.gname = gname;
        this.addtime = addtime;
    }

    public String getGiftid() {
        return giftid;
    }

    public void setGiftid(String giftid) {
        this.giftid = giftid;
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

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }
}
