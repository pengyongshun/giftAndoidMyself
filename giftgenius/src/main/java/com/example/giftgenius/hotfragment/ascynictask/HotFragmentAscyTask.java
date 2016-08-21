package com.example.giftgenius.hotfragment.ascynictask;

import android.os.AsyncTask;

import com.example.giftgenius.hotfragment.bean.GiftHotBean;
import com.example.giftgenius.uitls.HttpURLUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 彭永顺 on 2016/8/17.
 */
public class HotFragmentAscyTask extends AsyncTask<String,Void,List<GiftHotBean>> {
    private GiftHotCallBack mGiftHotCallBack;

    public HotFragmentAscyTask(GiftHotCallBack mGiftHotCallBack) {
        this.mGiftHotCallBack = mGiftHotCallBack;
    }

    @Override
    protected List<GiftHotBean> doInBackground(String... params) {
        List<GiftHotBean> giftHotBeanList= null;
        try {
            giftHotBeanList = getResultFromNetWork(params[0]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return giftHotBeanList;
    }

    private List<GiftHotBean> getResultFromNetWork(String url) throws JSONException {
        List<GiftHotBean> list=new ArrayList<>();
        byte[] bytes = HttpURLUtils.getBytesFromNetWorkGetMethod(url);
        if (bytes==null){
            return null;
        }
        String json=new String(bytes);
        JSONObject object=new JSONObject(json);
        JSONObject info = object.getJSONObject("info");
        JSONArray push1 = info.getJSONArray("push1");
        int length = push1.length();
        for (int i = 0; i < length; i++) {
            JSONObject jsonObject = push1.getJSONObject(i);
            int clicks= jsonObject.isNull("clicks")?0:jsonObject.getInt("clicks");
            String appid = jsonObject.isNull("appid")?"":jsonObject.getString("appid");
            String name = jsonObject.isNull("name")?"":jsonObject.getString("name");
            String typename = jsonObject.isNull("typename")?"":jsonObject.getString("typename");
            String logo = jsonObject.isNull("logo")?"":jsonObject.getString("logo");
            String size = jsonObject.isNull("size")?"":jsonObject.getString("size");
            GiftHotBean infoBean=new GiftHotBean();
            infoBean.setAppid(appid);
            infoBean.setClicks(clicks);
            infoBean.setLogo(logo);
            infoBean.setName(name);
            infoBean.setTypename(typename);
            infoBean.setSize(size);
            list.add(infoBean);
        }

        return list;
    }

    @Override
    protected void onPostExecute(List<GiftHotBean> giftHotBeanList) {
        mGiftHotCallBack.hotCallBack(giftHotBeanList);
    }
    public interface GiftHotCallBack{
        void hotCallBack(List<GiftHotBean> giftHotBeanList);
    }
}
