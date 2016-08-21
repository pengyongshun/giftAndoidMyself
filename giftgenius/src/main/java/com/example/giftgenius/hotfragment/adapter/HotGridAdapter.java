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
import com.example.giftgenius.hotfragment.bean.GiftHotBeanGridView;
import com.example.giftgenius.uitls.ImageLoader;

import java.util.List;

/**
 * Created by 彭永顺 on 2016/8/17.
 */
public class HotGridAdapter extends BaseAdapter{
    private List<GiftHotBeanGridView> list ;
    private Context context ;
    private LayoutInflater inflater;

    public HotGridAdapter(Context context, List<GiftHotBeanGridView> list) {
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
            convertView=inflater.inflate(R.layout.item_hotfragment_gridview,parent,false);
            childViewHoder=new ChildViewHoder(convertView);
            convertView.setTag(childViewHoder);
        }else {
            childViewHoder= (ChildViewHoder) convertView.getTag();
        }
        //添加数据，跟新Ui
        GiftHotBeanGridView giftHotBean= (GiftHotBeanGridView) list.get(position);
        if (giftHotBean==null){
            return null;
        }
        String logo = giftHotBean.getLogo();
        String name = giftHotBean.getName();
        if (name!=null){
            childViewHoder.nameIv.setText(name);
        }

        if (logo!=null){
            String url= CommURL.URL_GIFT_PACKAGE_HEAD+logo;
            childViewHoder.logoIv.setImageResource(R.mipmap.ic_launcher);
            ImageLoader.init(context).load(url,childViewHoder.logoIv);
        }
        return convertView;
    }



    class ChildViewHoder{
        ImageView logoIv;
        TextView nameIv;
        public ChildViewHoder(View view){
            logoIv= (ImageView) view.findViewById(R.id.iv_item_gridview_hotfraggment);
            nameIv= (TextView) view.findViewById(R.id.tv_item_gridview_hotfragment);
        }
    }
}
