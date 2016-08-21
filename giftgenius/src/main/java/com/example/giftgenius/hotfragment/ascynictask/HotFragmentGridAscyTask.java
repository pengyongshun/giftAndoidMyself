package com.example.giftgenius.hotfragment.ascynictask;

import android.os.AsyncTask;

import com.example.giftgenius.hotfragment.bean.GiftHotBean;
import com.example.giftgenius.hotfragment.bean.GiftHotBeanGridView;
import com.example.giftgenius.uitls.HttpURLUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 彭永顺 on 2016/8/17.
 */
public class HotFragmentGridAscyTask extends AsyncTask<String,Void,List<GiftHotBeanGridView>> {
    private GiftHotGridCallBack mGiftHotGridCallBack;

    public HotFragmentGridAscyTask(GiftHotGridCallBack mGiftHotGridCallBack) {
        this.mGiftHotGridCallBack = mGiftHotGridCallBack;
    }

    @Override
    protected List<GiftHotBeanGridView> doInBackground(String... params) {
        List<GiftHotBeanGridView> giftHotGridBeanList= null;
        try {
            giftHotGridBeanList = getResultFromNetWork(params[0]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return giftHotGridBeanList;
    }

    private List<GiftHotBeanGridView> getResultFromNetWork(String url) throws JSONException {
        List<GiftHotBeanGridView> list=new ArrayList<>();
        byte[] bytes = HttpURLUtils.getBytesFromNetWorkGetMethod(url);
        if (bytes==null){
            return null;
        }
        String json=new String(bytes);
        JSONObject object=new JSONObject(json);
        JSONObject info = object.getJSONObject("info");
        JSONArray push1 = info.getJSONArray("push2");
        int length = push1.length();
        for (int i = 0; i < length; i++) {
            JSONObject jsonObject = push1.getJSONObject(i);
            String appid = jsonObject.isNull("appid")?"":jsonObject.getString("appid");
            String name = jsonObject.isNull("name")?"":jsonObject.getString("name");
            String logo = jsonObject.isNull("logo")?"":jsonObject.getString("logo");
            GiftHotBeanGridView infoBean=new GiftHotBeanGridView();
            infoBean.setAppid(appid);
            infoBean.setLogo(logo);
            infoBean.setName(name);
            list.add(infoBean);
        }

        return list;
    }

    @Override
    protected void onPostExecute(List<GiftHotBeanGridView> giftHotGridBeanList) {
        mGiftHotGridCallBack.hotGridCallBack(giftHotGridBeanList);
    }
    public interface GiftHotGridCallBack{
        void hotGridCallBack(List<GiftHotBeanGridView> giftHotGridBeanList);
    }
}
