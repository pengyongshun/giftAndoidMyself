package com.example.giftgenius.openservicefragment.ascynictask;

import android.os.AsyncTask;
import android.view.View;

import com.example.giftgenius.giftpackagefragment.ascynictask.GiftDetialAscyTask;
import com.example.giftgenius.openservicefragment.bean.GiftOpenservice;
import com.example.giftgenius.uitls.HttpURLUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 彭永顺 on 2016/8/16.
 */
public class GiftOpenserviceAscyTask extends AsyncTask<String,Void,List<GiftOpenservice>> {
    private GiftOpenserviceCallBack mGiftOpenserviceCallBack;

    public GiftOpenserviceAscyTask(GiftOpenserviceCallBack mGiftOpenserviceCallBack) {
        this.mGiftOpenserviceCallBack = mGiftOpenserviceCallBack;
    }

    @Override
    protected List<GiftOpenservice> doInBackground(String... params) {
        List<GiftOpenservice> giftOpenservices= null;
        try {
            giftOpenservices = getResultFromNetWork(params[0]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return giftOpenservices;
    }

    private List<GiftOpenservice> getResultFromNetWork(String url) throws JSONException {
        List<GiftOpenservice> list=new ArrayList<>();
        byte[] bytes = HttpURLUtils.getBytesFromNetWorkGetMethod(url);
        if (bytes==null){
            return null;
        }
        String json=new String(bytes);
        JSONObject object=new JSONObject(json);
        JSONArray info = object.getJSONArray("info");
        int length = info.length();
        for (int i = 0; i < length; i++) {
            JSONObject jsonObject = info.getJSONObject(i);
            int id = jsonObject.isNull("id")?0:jsonObject.getInt("id");
            String iconurl = jsonObject.isNull("iconurl")?"":jsonObject.getString("iconurl");
            String gname = jsonObject.isNull("gname")?"":jsonObject.getString("gname");
            String linkurl = jsonObject.isNull("linkurl")?"":jsonObject.getString("linkurl");
            String operators = jsonObject.isNull("operators")?"":jsonObject.getString("operators");
            String addtime = jsonObject.isNull("addtime")?"":jsonObject.getString("addtime");
            String area = jsonObject.isNull("area")?"":jsonObject.getString("area");
            String gid = jsonObject.isNull("gid")?"":jsonObject.getString("gid");
            GiftOpenservice infoBean=new GiftOpenservice();
            infoBean.setId(id);
            infoBean.setAddtime(addtime);
            infoBean.setArea(area);
            infoBean.setGid(gid);
            infoBean.setGname(gname);
            infoBean.setLinkurl(linkurl);
            infoBean.setIconurl(iconurl);
            infoBean.setOperators(operators);
            list.add(infoBean);
        }

        return list;
    }

    @Override
    protected void onPostExecute(List<GiftOpenservice> giftOpenservices) {
       mGiftOpenserviceCallBack.giftOpenserviceCallBack(giftOpenservices);
    }
    public interface GiftOpenserviceCallBack{
        void giftOpenserviceCallBack(List<GiftOpenservice> giftOpenserviceList);
    }
}
