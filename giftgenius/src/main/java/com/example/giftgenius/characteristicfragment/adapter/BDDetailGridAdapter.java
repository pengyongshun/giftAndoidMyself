package com.example.giftgenius.characteristicfragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.giftgenius.R;
import com.example.giftgenius.characteristicfragment.bean.BDDetialGridViewBean;
import com.example.giftgenius.comm.CommURL;
import com.example.giftgenius.uitls.ImageLoader;

import java.util.List;

/**
 * Created by 彭永顺 on 2016/8/18.
 */
public class BDDetailGridAdapter extends BaseAdapter{
    private List<BDDetialGridViewBean> list ;
    private Context context ;
    private LayoutInflater inflater;

    public BDDetailGridAdapter(Context context, List<BDDetialGridViewBean> list) {
        this.context = context;
        this.list = list;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChildViewHoder childViewHoder;
        if (convertView==null){
            convertView=inflater.inflate(R.layout.item_bd_detial_gridview,parent,false);
            childViewHoder=new ChildViewHoder(convertView);
            convertView.setTag(childViewHoder);
        }else {
            childViewHoder= (ChildViewHoder) convertView.getTag();
        }
        //添加数据，跟新Ui
        BDDetialGridViewBean bDBean= (BDDetialGridViewBean) list.get(position);
        if (bDBean==null){
            return null;
        }
        String appicon = bDBean.getAppicon();
        String appname = bDBean.getAppname();
        if (appname!=null){
            childViewHoder.appnameTv.setText(appname);
        }

        if (appicon!=null){
            String url= CommURL.URL_GIFT_PACKAGE_HEAD+appicon;
            childViewHoder.appiconIv.setImageResource(R.mipmap.ic_launcher);
            ImageLoader.init(context).load(url,childViewHoder.appiconIv);
        }
        return convertView;
    }



    class ChildViewHoder{
        ImageView appiconIv;
        TextView appnameTv;
        public ChildViewHoder(View view){
            appiconIv= (ImageView) view.findViewById(R.id.iv_item_gridview_bd_detial1);
            appnameTv= (TextView) view.findViewById(R.id.tv_item_gridview_bd_detial1);

        }
    }
}
