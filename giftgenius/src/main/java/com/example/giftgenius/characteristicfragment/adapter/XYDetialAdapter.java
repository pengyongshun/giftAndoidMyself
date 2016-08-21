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
import com.example.giftgenius.characteristicfragment.bean.XYDetialBean;
import com.example.giftgenius.comm.CommURL;
import com.example.giftgenius.uitls.ImageLoader;

import java.util.List;

/**
 * Created by 彭永顺 on 2016/8/18.
 */
public class XYDetialAdapter extends BaseAdapter {
    private List<XYDetialBean> list ;
    private Context context ;
    private LayoutInflater inflater;

    public XYDetialAdapter(Context context, List<XYDetialBean> list) {
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
            convertView=inflater.inflate(R.layout.item_xy_detial_listview,parent,false);
            childViewHoder=new ChildViewHoder(convertView);
            convertView.setTag(childViewHoder);
        }else {
            childViewHoder= (ChildViewHoder) convertView.getTag();
        }
        //添加数据，跟新Ui
        XYDetialBean xYBean= (XYDetialBean) list.get(position);
        if (xYBean==null){
            return null;
        }
        String appname = xYBean.getAppname();
        String iconurl = xYBean.getIconurl();
        String descs = xYBean.getDescs();
        String typename = xYBean.getTypename();
        String appsize = xYBean.getAppsize();
        if (appname!=null){
            childViewHoder.appnameTv.setText(appname);
        }
        if (typename==null||appsize==null){
            return null;
        }
        childViewHoder.appSizeTv.setText("类型："+typename+"  大小："+appsize);
        if (descs!=null){
            childViewHoder.appDescTv.setText(descs);
        }
        if (iconurl!=null){
            String url= CommURL.URL_GIFT_PACKAGE_HEAD+iconurl;
            childViewHoder.appiconIv.setImageResource(R.mipmap.ic_launcher);
            ImageLoader.init(context).load(url,childViewHoder.appiconIv);
        }
        return convertView;
    }



    class ChildViewHoder{
        ImageView appiconIv;
        TextView appnameTv;
        TextView appSizeTv;
        TextView appDescTv;
        public ChildViewHoder(View view){
            appiconIv= (ImageView) view.findViewById(R.id.iv_xy_detial_listview_icon);
            appnameTv= (TextView) view.findViewById(R.id.tv_xy_detial_name);
            appSizeTv= (TextView) view.findViewById(R.id.tv_xy_detial_size);
            appDescTv= (TextView) view.findViewById(R.id.tv_xy_detial_desc);

        }
    }
}
