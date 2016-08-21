package com.example.giftgenius.characteristicfragment.ascynictask;

import android.os.AsyncTask;

import com.example.giftgenius.characteristicfragment.bean.BDBean;
import com.example.giftgenius.uitls.HttpURLUtils;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 彭永顺 on 2016/8/17.
 */
public class BDAscyTask extends AsyncTask<String,Void,List<BDBean>> {
    private BDCallBack mBDCallBack;

    public BDAscyTask(BDCallBack mBDCallBack) {
        this.mBDCallBack = mBDCallBack;
    }

    @Override
    protected List<BDBean> doInBackground(String... params) {
        List<BDBean> bDBeans=getResultFromNetWork(params[0]);
        return bDBeans;
    }

    private List<BDBean> getResultFromNetWork(String param) {
        List<BDBean> bdBeanList=new ArrayList<>();
        byte[] bytes = HttpURLUtils.getBytesFromNetWorkGetMethod(param);
        if (bytes==null){
            return null;
        }
        String json=new String(bytes,0,bytes.length);

        try {
            JSONObject object=new JSONObject(json);
            JSONArray list = object.getJSONArray("list");
            int length = list.length();
            for (int i = 0; i < length; i++) {
                JSONObject jsonObject = list.getJSONObject(i);
                String name = jsonObject.isNull("name")?"":jsonObject.getString("name");
                String addtime = jsonObject.isNull("addtime")?"":jsonObject.getString("addtime");
                String iconurl = jsonObject.isNull("iconurl")?"":jsonObject.getString("iconurl");
                int sid = jsonObject.isNull("sid")?0:jsonObject.getInt("sid");
                String descs = jsonObject.isNull("descs")?"":jsonObject.getString("descs");
                BDBean bean=new BDBean();
                bean.setSid(sid);
                bean.setAddtime(addtime);
                bean.setIconurl(iconurl);
                bean.setName(name);
                bean.setDescs(descs);
                bdBeanList.add(bean);
            }
            return bdBeanList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<BDBean> bdBeanList) {
        mBDCallBack.bDCallBack(bdBeanList);
    }
    public interface BDCallBack{
        void bDCallBack(List<BDBean> bdBeanList);
    }
}
