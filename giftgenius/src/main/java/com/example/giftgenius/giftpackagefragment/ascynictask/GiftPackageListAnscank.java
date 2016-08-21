package com.example.giftgenius.giftpackagefragment.ascynictask;

import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;


import com.example.giftgenius.comm.CommURL;
import com.example.giftgenius.giftpackagefragment.bean.GiftPackageList;
import com.example.giftgenius.log.LogUtils;
import com.example.giftgenius.uitls.HttpURLUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 彭永顺 on 2016/8/15.
 */
public class GiftPackageListAnscank extends AsyncTask<String,Void,List<GiftPackageList>> {
    private static final String TAG ="tag";
    private CallBack mCallBack;

    public GiftPackageListAnscank(CallBack mCallBack) {
        this.mCallBack = mCallBack;

    }

    @Override
    protected List<GiftPackageList> doInBackground(String... params) {
        List<GiftPackageList> giftPackageListList= null;
        try {
            giftPackageListList = getGiftListFromNetWork(params[0]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return giftPackageListList;
    }

    @Nullable
    private List<GiftPackageList> getGiftListFromNetWork(String param) throws JSONException {
        List<GiftPackageList> list=new ArrayList<>();
        byte[] bytes = HttpURLUtils.getBytesFromNetWorkPostMethod(CommURL.URL_GIFT_PACKAGE_LIST, CommURL.URL_GIFT_PACKAGE_PARAM,param);
        if (bytes==null){
            return null;
        }
        String json=new String(bytes);
        JSONObject object=new JSONObject(json);
        JSONArray jsonArray = object.getJSONArray("list");
        int length = jsonArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String giftname = jsonObject.isNull("giftname")?"":jsonObject.getString("giftname");
            String gname = jsonObject.isNull("gname")?"":jsonObject.getString("gname");
            String iconurl = jsonObject.isNull("iconurl")?"":jsonObject.getString("iconurl");
            String giftid = jsonObject.isNull("id")?"":jsonObject.getString("id");
            String addtime = jsonObject.isNull("addtime")?"":jsonObject.getString("addtime");
            int number = jsonObject.isNull("number")?0:jsonObject.getInt("number");
            GiftPackageList giftPackageList=new GiftPackageList(giftid,iconurl,giftname,number,gname,addtime);
            list.add(giftPackageList);
        }
        return list;
    }


    @Override
    protected void onPostExecute(List<GiftPackageList> giftPackageListList) {
        Log.i(TAG, "getBytesFromNetWork: list="+giftPackageListList);
        mCallBack.callBack(giftPackageListList);
    }

    public interface CallBack{
         void callBack(List<GiftPackageList> giftPackageListList);
    }

}
