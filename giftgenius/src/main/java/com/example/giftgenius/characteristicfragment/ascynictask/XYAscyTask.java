package com.example.giftgenius.characteristicfragment.ascynictask;

import android.os.AsyncTask;
import com.example.giftgenius.characteristicfragment.bean.XYBean;
import com.example.giftgenius.uitls.HttpURLUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 彭永顺 on 2016/8/17.
 */
public class XYAscyTask extends AsyncTask<String,Void,List<XYBean>>{
    private XYCallBack mXYCallBack;

    public XYAscyTask(XYCallBack mXYCallBack) {
        this.mXYCallBack = mXYCallBack;
    }

    @Override
    protected List<XYBean> doInBackground(String... params) {
        List<XYBean> xyBeens=getResultFromNetWork(params[0]);
        return xyBeens;
    }

    private List<XYBean> getResultFromNetWork(String param) {
        List<XYBean> xyBeanList=new ArrayList<>();
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
                String authorimg = jsonObject.isNull("authorimg")?"":jsonObject.getString("authorimg");
                int id = jsonObject.isNull("id")?0:jsonObject.getInt("id");
                String descs = jsonObject.isNull("descs")?"":jsonObject.getString("descs");
                XYBean bean=new XYBean();
                bean.setId(id);
                bean.setAddtime(addtime);
                bean.setIconurl(iconurl);
                bean.setName(name);
                bean.setAuthorimg(authorimg);
                bean.setDescs(descs);
                xyBeanList.add(bean);
            }
            return xyBeanList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<XYBean> xyBeanList) {
        mXYCallBack.xYCallBack(xyBeanList);
    }
    public interface XYCallBack{
        void xYCallBack(List<XYBean> xyBeanList);
    }
}
