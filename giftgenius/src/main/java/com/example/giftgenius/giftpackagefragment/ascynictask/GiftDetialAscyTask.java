package com.example.giftgenius.giftpackagefragment.ascynictask;

import android.os.AsyncTask;

import com.example.giftgenius.comm.CommURL;
import com.example.giftgenius.giftpackagefragment.bean.GiftDetial;
import com.example.giftgenius.uitls.HttpURLUtils;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Reader;
import java.io.StringReader;


/**
 * Created by 彭永顺 on 2016/8/15.
 */
public class GiftDetialAscyTask extends AsyncTask<String,Void,GiftDetial> {
    private GiftDetialCallBack mGiftDetialCallBack;

    public GiftDetialAscyTask(GiftDetialCallBack mGiftDetialCallBack) {
        this.mGiftDetialCallBack = mGiftDetialCallBack;
    }

    @Override
    protected GiftDetial doInBackground(String... params) {
        GiftDetial giftDetial= null;
        try {
            giftDetial = getResultFromNetWork(params[0]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return giftDetial;
    }

    private GiftDetial getResultFromNetWork(String param) throws JSONException {
        if (param==null){
            return null;
        }
        byte[] bytes = HttpURLUtils.getBytesFromNetWorkPostMethod(CommURL.URL_GIFT_DETIAL, CommURL.URL_GIFT_DETEAL_KEY, param);
        String json=new String(bytes);
        if (json==null){
            return null;
        }
        JSONObject object=new JSONObject(json);
        JSONObject info = object.getJSONObject("info");
        String iconurl = info.isNull("iconurl")?"":info.getString("iconurl");
        String giftname = info.isNull("giftname")?"":info.getString("giftname");
        int number = info.isNull("number")?0:info.getInt("number");
        String explains = info.isNull("explains")?"":info.getString("explains");
        String descs = info.isNull("descs")?"":info.getString("descs");
        String overtime = info.isNull("overtime")?"":info.getString("overtime");
        String gname = info.isNull("gname")?"":info.getString("gname");
        GiftDetial giftDetial=new GiftDetial();
        GiftDetial.InfoBean infoBean=new GiftDetial.InfoBean();
        infoBean.setIconurl(iconurl);
        infoBean.setOvertime(overtime);
        infoBean.setNumber(number);
        infoBean.setDescs(descs);
        infoBean.setExplains(explains);
        infoBean.setGiftname(giftname);
        infoBean.setGname(gname);
        giftDetial.setInfo(infoBean);
        return giftDetial;
    }

    @Override
    protected void onPostExecute(GiftDetial giftDetial) {
        super.onPostExecute(giftDetial);
        mGiftDetialCallBack.giftDetialCallBack(giftDetial);
    }
    public interface GiftDetialCallBack{
        void giftDetialCallBack(GiftDetial giftDetial);
    }
}
