package com.example.giftgenius.hotfragment.bean;

/**
 * Created by 彭永顺 on 2016/8/17.
 */
public class GiftHotBeanGridView {
    private String appid;
    private String name;
    private String logo;

    @Override
    public String toString() {
        return "GiftHotBeanGridView{" +
                "appid='" + appid + '\'' +
                ", name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
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
}
