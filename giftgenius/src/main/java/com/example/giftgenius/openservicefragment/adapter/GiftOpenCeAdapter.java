package com.example.giftgenius.openservicefragment.adapter;

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
import com.example.giftgenius.openservicefragment.bean.GiftOpenservice;
import com.example.giftgenius.uitls.ImageLoader;

import java.util.List;

/**
 * Created by 彭永顺 on 2016/8/17.
 */
public class GiftOpenCeAdapter extends BaseAdapter {
    private List<GiftOpence> list ;
    private Context context ;
    private LayoutInflater inflater;

    public GiftOpenCeAdapter(Context context, List<GiftOpence> list) {
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
                convertView=inflater.inflate(R.layout.item_childfragment_opence,parent,false);
                childViewHoder=new ChildViewHoder(convertView);
                convertView.setTag(childViewHoder);
            }else {
                childViewHoder= (ChildViewHoder) convertView.getTag();
            }
            //添加数据，跟新Ui
            GiftOpence giftOpence= (GiftOpence) list.get(position);
            if (giftOpence==null){
                return null;
            }
            String addtime = giftOpence.getAddtime();
            String gname = giftOpence.getGname();
            String iconurl = giftOpence.getIconurl();
            String linkurl = giftOpence.getLinkurl();
            String operators = giftOpence.getOperators();
            if (gname!=null){
                childViewHoder.gNameTv.setText(gname);
            }
            if (operators!=null){
                childViewHoder.operatorsTv.setText(operators);
            }
            if (addtime!=null&&linkurl!=null){
                childViewHoder.linkurlAndAddtimeTv.setText(addtime+" "+linkurl);
            }

            if (iconurl!=null){
                String url= CommURL.URL_GIFT_PACKAGE_HEAD+iconurl;
                childViewHoder.iconurlIv.setImageResource(R.mipmap.ic_launcher);
                ImageLoader.init(context).load(url,childViewHoder.iconurlIv);
            }
        return convertView;
    }



    class ChildViewHoder{
        TextView  operatorsTv;
        TextView gNameTv;
        TextView linkurlAndAddtimeTv;
        ImageView iconurlIv;
        public ChildViewHoder(View view){
            linkurlAndAddtimeTv= (TextView) view.findViewById(R.id.tv_childfragment_opence_date);
            gNameTv= (TextView) view.findViewById(R.id.tv_childfragment_opence_title);
            operatorsTv= (TextView) view.findViewById(R.id.tv_childfragment_opence_content);
            iconurlIv= (ImageView) view.findViewById(R.id.iv_childfragment_opence_listview_icon);
        }
    }
}
