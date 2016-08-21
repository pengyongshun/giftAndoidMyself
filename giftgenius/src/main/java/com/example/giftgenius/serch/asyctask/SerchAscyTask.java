package com.example.giftgenius.serch.asyctask;

import android.os.AsyncTask;

import com.example.giftgenius.comm.CommURL;
import com.example.giftgenius.serch.bean.SerchBean;
import com.example.giftgenius.uitls.HttpURLUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 彭永顺 on 2016/8/18.
 */
public class SerchAscyTask extends AsyncTask<String,Void,List<SerchBean>> {
    private SerchBeanCallBack mSerchBeanCallBack;

    public SerchAscyTask(SerchBeanCallBack mSerchBeanCallBack) {
        this.mSerchBeanCallBack = mSerchBeanCallBack;
    }

    @Override
    protected List<SerchBean> doInBackground(String... params) {
        List<SerchBean> serchBeans= null;
        serchBeans = getResultFromNetWork(params[0]);
        return serchBeans;
    }

    private List<SerchBean> getResultFromNetWork(String parm){
        List<SerchBean> list=new ArrayList<>();
        byte[] bytes = HttpURLUtils.getBytesFromNetWorkPostMethod(CommURL.URL_GIFT_SERCH,"key=",parm);
        if (bytes==null){
            return null;
        }
        String json=new String(bytes,0,bytes.length);
        JSONObject object= null;
        try {
            object = new JSONObject(json);
            JSONArray info = object.getJSONArray("list");
            int length = info.length();
            for (int i = 0; i < length; i++) {
                JSONObject jsonObject = info.getJSONObject(i);
                String id = jsonObject.isNull("id")?"":jsonObject.getString("id");
                String iconurl = jsonObject.isNull("iconurl")?"":jsonObject.getString("iconurl");
                String giftname = jsonObject.isNull("giftname")?"":jsonObject.getString("giftname");
                String gname = jsonObject.isNull("gname")?"":jsonObject.getString("gname");
                String addtime = jsonObject.isNull("addtime")?"":jsonObject.getString("addtime");
                int number = jsonObject.isNull("number")?0:jsonObject.getInt("number");
                SerchBean infoBean=new SerchBean();
                infoBean.setId(id);
                infoBean.setAddtime(addtime);
                infoBean.setGname(gname);
                infoBean.setIconurl(iconurl);
                infoBean.setNumber(number);
                infoBean.setGiftname(giftname);
                list.add(infoBean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return list;
    }

    @Override
    protected void onPostExecute(List<SerchBean> serchBeans) {
        mSerchBeanCallBack.serchBeanCallBack(serchBeans);
    }
    public interface SerchBeanCallBack{
        void serchBeanCallBack(List<SerchBean> serchBeans);
    }
}
