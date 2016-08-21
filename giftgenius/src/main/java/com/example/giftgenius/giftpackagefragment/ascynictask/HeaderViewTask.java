package com.example.giftgenius.giftpackagefragment.ascynictask;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


import com.example.giftgenius.comm.CommURL;
import com.example.giftgenius.giftpackagefragment.bean.GiftAd;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 彭永顺 on 2016/8/7.
 */
public class HeaderViewTask extends AsyncTask<String,Void,List<GiftAd>> {
    private HCallBack mHCallBack;

    public HeaderViewTask(HCallBack mHCallBack) {
        this.mHCallBack = mHCallBack;
    }

    @Override
    protected List<GiftAd> doInBackground(String... params) {
        List<GiftAd> giftAds = getImageUrlsFromNetWork(params[0]);
        return giftAds;
    }

    private List<GiftAd> getImageUrlsFromNetWork(String param) {
        List<GiftAd> adList = new ArrayList<>();
        InputStream inputStream = null;
        try {
            java.net.URL url = new java.net.URL(CommURL.URL_GIFT_PACKAGE_LIST);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            //传参数
            connection.getOutputStream().write(("pageno="+param).getBytes());
            connection.getOutputStream().flush();
            connection.connect();
            if (connection.getResponseCode() == 200) {
                inputStream = connection.getInputStream();
                int len = 0;
                byte[] buffer = new byte[1024];
                StringBuilder builder = new StringBuilder();
                while ((len = inputStream.read(buffer)) != -1) {
                    builder.append(new String(buffer,0,len));
                }
                String json = builder.toString();
                JSONObject jsonObj = new JSONObject(json);
                JSONArray jsonArray = jsonObj.getJSONArray("ad");
                int length = jsonArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    String iconurl = jsonObject1.getString("iconurl");
                    String giftid = jsonObject1.getString("giftid");
                    String imageUrl=CommURL.URL_GIFT_PACKAGE_HEAD+iconurl;
                    GiftAd giftAd=new GiftAd(imageUrl,giftid);
                    adList.add(giftAd);
                }
                return adList;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }finally {
            closeSream(inputStream);
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<GiftAd> giftAds) {
        mHCallBack.hCallBack(giftAds);
    }

    public  void closeSream(Closeable closeable){
        if (closeable!=null){
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public interface HCallBack{
        void hCallBack(List<GiftAd> giftAds);
    }

}

