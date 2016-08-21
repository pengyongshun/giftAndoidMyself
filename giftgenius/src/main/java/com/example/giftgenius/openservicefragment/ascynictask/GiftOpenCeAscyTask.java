package com.example.giftgenius.openservicefragment.ascynictask;

import android.os.AsyncTask;

import com.example.giftgenius.log.LogUtils;
import com.example.giftgenius.openservicefragment.bean.GiftOpence;
import com.example.giftgenius.openservicefragment.bean.GiftOpenservice;
import com.example.giftgenius.uitls.HttpURLUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 彭永顺 on 2016/8/17.
 */
public class GiftOpenCeAscyTask extends AsyncTask<String,Void,List<GiftOpence>> {
    private GiftOpenCeCallBack mGiftOpenCeCallBack;

    public GiftOpenCeAscyTask(GiftOpenCeCallBack mGiftOpenCeCallBack) {
        this.mGiftOpenCeCallBack = mGiftOpenCeCallBack;
    }

    @Override
    protected List<GiftOpence> doInBackground(String... params) {
        List<GiftOpence> giftOpences= null;

            giftOpences = getResultFromNetWork(params[0]);

        return giftOpences;
    }

    private List<GiftOpence> getResultFromNetWork(String url){
        List<GiftOpence> list=new ArrayList<>();
        byte[] bytes = HttpURLUtils.getBytesFromNetWorkGetMethod(url);
        if (bytes==null){
            return null;
        }
        String json=new String(bytes,0,bytes.length);
        JSONObject object= null;
        try {
            object = new JSONObject(json);
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
                String gid = jsonObject.isNull("gid")?"":jsonObject.getString("gid");
                GiftOpence infoBean=new GiftOpence();
                infoBean.setId(id);
                infoBean.setAddtime(addtime);
                infoBean.setGname(gname);
                infoBean.setLinkurl(linkurl);
                infoBean.setIconurl(iconurl);
                infoBean.setOperators(operators);
                infoBean.setGid(gid);
                list.add(infoBean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return list;
    }

    @Override
    protected void onPostExecute(List<GiftOpence> giftOpences) {
        mGiftOpenCeCallBack.giftOpenCeCallBack(giftOpences);
    }
    public interface GiftOpenCeCallBack{
        void giftOpenCeCallBack(List<GiftOpence> giftOpenceList);
    }
}
