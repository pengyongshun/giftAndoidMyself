package com.example.giftgenius.characteristicfragment.ascynictask;

import android.os.AsyncTask;

import com.example.giftgenius.characteristicfragment.bean.BDDetialGridViewBean;
import com.example.giftgenius.characteristicfragment.bean.XYDetialBean;
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
public class XYDetialAscyTask extends AsyncTask<String,Void,List<XYDetialBean>>{
    private XYDetialCallBack mXYDetialCallBack;

    public  XYDetialAscyTask(XYDetialCallBack mXYDetialCallBack) {
        this.mXYDetialCallBack = mXYDetialCallBack;
    }

    @Override
    protected List<XYDetialBean> doInBackground(String... params) {
        List<XYDetialBean> bDDetialGridViewBeans= null;
        try {
            bDDetialGridViewBeans = getResultFromNetWork(params[0]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bDDetialGridViewBeans;
    }

    private List<XYDetialBean> getResultFromNetWork(String params) throws JSONException {
        List<XYDetialBean> list=new ArrayList<>();
        byte[] bytes = HttpURLUtils.getBytesFromNetWorkPostMethod(CommURL.URL_GIFT_XY_DETIAL,CommURL.URL_GIFT_DETEAL_KEY,params);
        if (bytes==null){
            return null;
        }
        String json=new String(bytes,0,bytes.length);
        JSONObject object=new JSONObject(json);
        JSONArray beanList = object.getJSONArray("list");
        int length = beanList.length();
        for (int i = 0; i < length; i++) {
            JSONObject jsonObject = beanList.getJSONObject(i);
            String appid = jsonObject.isNull("appid")?"":jsonObject.getString("appid");
            String appname = jsonObject.isNull("appname")?"":jsonObject.getString("appname");
            String iconurl = jsonObject.isNull("iconurl")?"":jsonObject.getString("iconurl");
            String descs= jsonObject.isNull("descs")?"":jsonObject.getString("descs");
            String appsize = jsonObject.isNull("appsize")?"":jsonObject.getString("appsize");
            String typename = jsonObject.isNull("typename")?"":jsonObject.getString("typename");
            XYDetialBean infoBean=new XYDetialBean();
            infoBean.setAppid(appid);
            infoBean.setIconurl(iconurl);
            infoBean.setAppname(appname);
            infoBean.setTypename(typename);
            infoBean.setAppsize(appsize);
            infoBean.setDescs(descs);
            list.add(infoBean);
        }
        return list;
    }

    @Override
    protected void onPostExecute(List<XYDetialBean> xYDetialBeanList) {
        mXYDetialCallBack.xYDetialCallBack(xYDetialBeanList);
    }
    public interface XYDetialCallBack{
        void xYDetialCallBack(List<XYDetialBean> xYDetialBeanList);
    }
}
