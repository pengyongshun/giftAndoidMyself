package com.example.giftgenius.giftpackagefragment.bean;

/**
 * Created by 彭永顺 on 2016/8/15.
 */
public class GiftAd {
    private String giftid;
    private String iconurl;

    public GiftAd(String iconurl, String giftid) {
        this.iconurl = iconurl;
        this.giftid = giftid;
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

    @Override
    public String toString() {
        return "GiftAd{" +
                "giftid='" + giftid + '\'' +
                ", iconurl='" + iconurl + '\'' +
                '}';
    }
}
