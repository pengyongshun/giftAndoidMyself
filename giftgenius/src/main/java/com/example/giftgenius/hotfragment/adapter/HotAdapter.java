package com.example.giftgenius.hotfragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.giftgenius.R;
import com.example.giftgenius.comm.CommURL;
import com.example.giftgenius.hotfragment.bean.GiftHotBean;
import com.example.giftgenius.openservicefragment.bean.GiftOpence;
import com.example.giftgenius.uitls.ImageLoader;

import java.util.List;

/**
 * Created by 彭永顺 on 2016/8/17.
 */
public class HotAdapter extends BaseAdapter {
    private List<GiftHotBean> list ;
    private Context context ;
    private LayoutInflater inflater;

    public HotAdapter(Context context, List<GiftHotBean> list) {
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
            convertView=inflater.inflate(R.layout.item_hotfragment_listview,parent,false);
            childViewHoder=new ChildViewHoder(convertView);
            convertView.setTag(childViewHoder);
        }else {
            childViewHoder= (ChildViewHoder) convertView.getTag();
        }
        //添加数据，跟新Ui
        GiftHotBean giftHotBean= (GiftHotBean) list.get(position);
        if (giftHotBean==null){
            return null;
        }
        String appid = giftHotBean.getAppid();
        String logo = giftHotBean.getLogo();
        String name = giftHotBean.getName();
        String size = giftHotBean.getSize();
        int count = giftHotBean.getClicks();
        String typename = giftHotBean.getTypename();
        if (name!=null){
            childViewHoder.nameIv.setText(name);
        }
        if (count!=0){
            childViewHoder.countTv.setText(count+"人在玩游戏");
        }
        if (typename!=null){
            childViewHoder.typeTv.setText(typename);
        }
        if (size!=null){
            childViewHoder.sizeTv.setText("大小："+size);
        }

        if (logo!=null){
            String url= CommURL.URL_GIFT_PACKAGE_HEAD+logo;
            childViewHoder.logoIv.setImageResource(R.mipmap.ic_launcher);
            ImageLoader.init(context).load(url,childViewHoder.logoIv);
        }
        return convertView;
    }



    class ChildViewHoder{
        TextView countTv;
        TextView sizeTv;
        TextView typeTv;
        ImageView logoIv;
        TextView nameIv;
        public ChildViewHoder(View view){
            countTv= (TextView) view.findViewById(R.id.tv_item_hotfragment_listview_count);
            sizeTv= (TextView) view.findViewById(R.id.tv_item_hotfragment_listview_size);
            typeTv= (TextView) view.findViewById(R.id.tv_item_hotfragment_listview_type);
            logoIv= (ImageView) view.findViewById(R.id.iv_item_hotfragment_listview_icon);
            nameIv= (TextView) view.findViewById(R.id.tv_item_hotfragment_listview_gname);
        }
    }
}
