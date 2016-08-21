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
import com.example.giftgenius.characteristicfragment.bean.XYBean;
import com.example.giftgenius.comm.CommURL;
import com.example.giftgenius.uitls.ImageLoader;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 彭永顺 on 2016/8/18.
 */
public class XYChildAdapter extends BaseAdapter {
    private List<XYBean> list ;
    private Context context ;
    private LayoutInflater inflater;

    public XYChildAdapter(Context context, List<XYBean> list) {
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
            convertView=inflater.inflate(R.layout.item_xy_listview,parent,false);
            childViewHoder=new ChildViewHoder(convertView);
            convertView.setTag(childViewHoder);
        }else {
            childViewHoder= (ChildViewHoder) convertView.getTag();
        }
        //添加数据，跟新Ui
       XYBean bean= (XYBean) list.get(position);
        if (bean==null){
            return null;
        }
        String gname = bean.getName();
        String iconurl = bean.getIconurl();
        String authorimg = bean.getAuthorimg();

        if (gname!=null){
            childViewHoder.gNameTv.setText(gname);
        }

        if (authorimg!=null){
           String icon=CommURL.URL_GIFT_PACKAGE_HEAD+"/"+authorimg;
            childViewHoder.iconCiv.setImageResource(R.mipmap.ic_launcher);
            ImageLoader.init(context).load(icon,childViewHoder.iconCiv);
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
        CircleImageView iconCiv;
        ImageView iconurlIv;
        public ChildViewHoder(View view){
           iconCiv= (CircleImageView) view.findViewById(R.id.civ_item_xy_childfragment_icon);
            gNameTv= (TextView) view.findViewById(R.id.tv_item_xy_childfragment_name);
            iconurlIv= (ImageView) view.findViewById(R.id.iv_item_xy_childfragment_photo);
        }
    }
}
