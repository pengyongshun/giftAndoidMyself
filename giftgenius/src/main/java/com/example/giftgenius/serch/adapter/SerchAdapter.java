package com.example.giftgenius.serch.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.giftgenius.R;
import com.example.giftgenius.comm.CommURL;
import com.example.giftgenius.openservicefragment.bean.GiftOpence;
import com.example.giftgenius.serch.bean.SerchBean;
import com.example.giftgenius.uitls.ImageLoader;

import java.util.List;

/**
 * Created by 彭永顺 on 2016/8/18.
 */
public class SerchAdapter extends BaseAdapter {
    private List<SerchBean> list ;
    private Context context ;
    private LayoutInflater inflater;

    public SerchAdapter(Context context, List<SerchBean> list) {
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
            convertView=inflater.inflate(R.layout.item_serch_listview,parent,false);
            childViewHoder=new ChildViewHoder(convertView);
            convertView.setTag(childViewHoder);
        }else {
            childViewHoder= (ChildViewHoder) convertView.getTag();
        }
        //添加数据，跟新Ui
        SerchBean serchBean= (SerchBean) list.get(position);
        if ( serchBean==null){
            return null;
        }
        String addtime =  serchBean.getAddtime();
        String gname =  serchBean.getGname();
        String iconurl =  serchBean.getIconurl();
        String id = serchBean.getId();
        int number = serchBean.getNumber();
        String giftname = serchBean.getGiftname();
        if (gname!=null){
            childViewHoder.gNameTv.setText(gname);
        }
        if (giftname!=null){
            childViewHoder.giftNameTv.setText(giftname);
        }
        if (addtime!=null&&number!=0){
            childViewHoder.linkurlAndAddtimeTv.setText("剩余："+number+"   时间："+addtime);
        }

        if (iconurl!=null){
            String url= CommURL.URL_GIFT_PACKAGE_HEAD+iconurl;
            childViewHoder.iconurlIv.setImageResource(R.mipmap.ic_launcher);
            ImageLoader.init(context).load(url,childViewHoder.iconurlIv);
        }
        return convertView;
    }



    class ChildViewHoder{
        TextView giftNameTv;
        TextView gNameTv;
        TextView linkurlAndAddtimeTv;
        ImageView iconurlIv;
        public ChildViewHoder(View view){
            linkurlAndAddtimeTv= (TextView) view.findViewById(R.id.tv_serch_listview_date);
            gNameTv= (TextView) view.findViewById(R.id.tv_serch_listview_name);
            giftNameTv= (TextView) view.findViewById(R.id.tv_serch_listview_giftname);
            iconurlIv= (ImageView) view.findViewById(R.id.iv_serch_listview_icon);
        }
    }
}
