package com.example.giftgenius.hotfragment.bean;

/**
 * Created by 彭永顺 on 2016/8/17.
 */
public class GiftHotBean {

    /**
     * id : 8
     * appid : 1443409159
     * type : 1
     * clicks : 13653
     * flag : 1
     * platform : 0
     * name : 穿越火线：枪战王者
     * typename : 射击空战
     * logo : /allimgs/img_iapp/201608/_1470385078583.jpg
     * size : 255M
     * addtime : 2016-08-09 12:37:27.0
     */
    private String appid;
    private int clicks;
    private String name;
    private String typename;
    private String logo;
    private String size;

    @Override
    public String toString() {
        return "GiftBean{" +
                "appid='" + appid + '\'' +
                ", clicks=" + clicks +
                ", name='" + name + '\'' +
                ", typename='" + typename + '\'' +
                ", logo='" + logo + '\'' +
                ", size='" + size + '\'' +
                '}';
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public int getClicks() {
        return clicks;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
