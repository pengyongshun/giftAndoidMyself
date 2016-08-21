package com.example.giftgenius.characteristicfragment.ascynictask;

import android.os.AsyncTask;

import com.example.giftgenius.characteristicfragment.bean.BDDetialGridViewBean;
import com.example.giftgenius.comm.CommURL;
import com.example.giftgenius.uitls.HttpURLUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 彭永顺 on 2016/8/18.
 */
public class BDDetialGridAscyTask extends AsyncTask<String,Void,List<BDDetialGridViewBean>> {
    private BDDetialGridCallBack mBDDetialGridCallBack;

    public BDDetialGridAscyTask(BDDetialGridCallBack mBDDetialGridCallBack) {
        this.mBDDetialGridCallBack = mBDDetialGridCallBack;
    }

    @Override
    protected List<BDDetialGridViewBean> doInBackground(String... params) {
        List<BDDetialGridViewBean> bDDetialGridViewBeans= null;
        try {
            bDDetialGridViewBeans = getResultFromNetWork(params[0]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bDDetialGridViewBeans;
    }

    private List<BDDetialGridViewBean> getResultFromNetWork(String params) throws JSONException {
        List<BDDetialGridViewBean> list=new ArrayList<>();
        byte[] bytes = HttpURLUtils.getBytesFromNetWorkPostMethod(CommURL.URL_GIFT_BD_DETIAL,CommURL.URL_GIFT_DETEAL_KEY,params);
        if (bytes==null){
            return null;
        }
        String json=new String(bytes);
        JSONObject object=new JSONObject(json);
        JSONArray beanList = object.getJSONArray("list");
        int length = beanList.length();
        for (int i = 0; i < length; i++) {
            JSONObject jsonObject = beanList.getJSONObject(i);
            String appid = jsonObject.isNull("appid")?"":jsonObject.getString("appid");
            String appname = jsonObject.isNull("appname")?"":jsonObject.getString("appname");
            String appicon = jsonObject.isNull("appicon")?"":jsonObject.getString("appicon");
            BDDetialGridViewBean infoBean=new BDDetialGridViewBean();
            infoBean.setAppid(appid);
            infoBean.setAppicon(appicon);
            infoBean.setAppname(appname);
            list.add(infoBean);
        }
        return list;
    }

    @Override
    protected void onPostExecute(List<BDDetialGridViewBean> bDDetialGridViewList) {
        mBDDetialGridCallBack.bDDetialGridCallBack(bDDetialGridViewList);
    }
    public interface BDDetialGridCallBack{
        void bDDetialGridCallBack(List<BDDetialGridViewBean> bDDetialGridViewList);
    }
}
