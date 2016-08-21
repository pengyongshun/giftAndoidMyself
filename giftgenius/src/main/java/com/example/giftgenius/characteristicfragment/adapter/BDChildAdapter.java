package com.example.giftgenius.characteristicfragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.giftgenius.R;
import com.example.giftgenius.characteristicfragment.bean.BDBean;
import com.example.giftgenius.comm.CommURL;
import com.example.giftgenius.uitls.ImageLoader;

import java.util.List;

/**
 * Created by 彭永顺 on 2016/8/17.
 */
public class BDChildAdapter extends BaseAdapter {
    private List<BDBean> list ;
    private Context context ;
    private LayoutInflater inflater;

    public BDChildAdapter(Context context, List<BDBean> list) {
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
            convertView=inflater.inflate(R.layout.item_bd_listview,parent,false);
            childViewHoder=new ChildViewHoder(convertView);
            convertView.setTag(childViewHoder);
        }else {
            childViewHoder= (ChildViewHoder) convertView.getTag();
        }
        //添加数据，跟新Ui
        BDBean bean= (BDBean) list.get(position);
        if (bean==null){
            return null;
        }
        String addtime = bean.getAddtime();
        String gname = bean.getName();
        String iconurl = bean.getIconurl();
        if (gname!=null){
            childViewHoder.gNameTv.setText(gname);
        }

        if (addtime!=null){
            childViewHoder.addtimeTv.setText(addtime);
        }

        if (iconurl!=null){
            String url= CommURL.URL_GIFT_PACKAGE_HEAD+iconurl;
            childViewHoder.iconurlIv.setImageResource(R.mipmap.ic_launcher);
            ImageLoader.init(context).load(url,childViewHoder.iconurlIv);
        }
        return convertView;
    }



    class ChildViewHoder{
        TextView gNameTv;
        TextView addtimeTv;
        ImageView iconurlIv;
        public ChildViewHoder(View view){
            addtimeTv= (TextView) view.findViewById(R.id.tv_item_bd_childfragment_time);
            gNameTv= (TextView) view.findViewById(R.id.tv_item_bd_childfragment_name);
            iconurlIv= (ImageView) view.findViewById(R.id.iv_item_bd_childfragment_photo);
        }
    }
}
