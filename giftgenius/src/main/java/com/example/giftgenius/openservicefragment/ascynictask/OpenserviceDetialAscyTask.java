package com.example.giftgenius.openservicefragment.ascynictask;

import android.os.AsyncTask;

import com.example.giftgenius.comm.CommURL;
import com.example.giftgenius.openservicefragment.bean.OpenserviceDetial;
import com.example.giftgenius.uitls.HttpURLUtils;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by 彭永顺 on 2016/8/17.
 */
public class OpenserviceDetialAscyTask extends AsyncTask<String,Void,OpenserviceDetial> {
    private DetialCallBack mDetialCallBack;

    public OpenserviceDetialAscyTask(DetialCallBack mDetialCallBack) {
        this.mDetialCallBack = mDetialCallBack;
    }

    @Override
    protected OpenserviceDetial doInBackground(String... params) {
        OpenserviceDetial openserviceDetial=getResultFromNetWork(params[0]);
        return openserviceDetial;
    }

    private OpenserviceDetial getResultFromNetWork(String param) {
        byte[] bytes = HttpURLUtils.getBytesFromNetWorkPostMethod(CommURL.URL_GIFT_OPENSERVICE_DETIAL, CommURL.URL_GIFT_DETEAL_KEY, param);
        String json=new String(bytes,0,bytes.length);
        if (json==null){
            return null;
        }
        Gson gson=new Gson();
        OpenserviceDetial detial = gson.fromJson(json, OpenserviceDetial.class);
        return detial;
    }

    @Override
    protected void onPostExecute(OpenserviceDetial openserviceDetial) {
        mDetialCallBack.detialCallBack(openserviceDetial);
    }
    public interface DetialCallBack{
        void detialCallBack(OpenserviceDetial openserviceDetial);
    }
}
