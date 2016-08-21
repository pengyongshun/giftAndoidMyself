package com.example.giftgenius.characteristicfragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.giftgenius.R;
import com.example.giftgenius.characteristicfragment.bean.BDListViewBean;
import com.example.giftgenius.comm.CommURL;
import com.example.giftgenius.uitls.ImageLoader;

import java.util.List;

/**
 * Created by 彭永顺 on 2016/8/21.
 */
public class BDDetialListViewAdapter extends BaseAdapter {
    private List<BDListViewBean> bdListViewBeanList;
    private LayoutInflater mLayoutInflater;
    private Context context;

    public BDDetialListViewAdapter(Context context, List<BDListViewBean> bdListViewBeanList) {
        this.context = context;
        this.bdListViewBeanList = bdListViewBeanList;
        mLayoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return bdListViewBeanList==null?0:bdListViewBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return bdListViewBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BDListViewHodler bdListViewHodler;
        if (convertView==null){
            convertView=mLayoutInflater.inflate(R.layout.item_bd_detial_listview_content,parent,false);
            bdListViewHodler=new BDListViewHodler(convertView);
            convertView.setTag(bdListViewHodler);
        }else {
            bdListViewHodler= (BDListViewHodler) convertView.getTag();
        }
        //更新UI
        BDListViewBean bean = bdListViewBeanList.get(position);
        String addtime = bean.getAddtime();
        String descs = bean.getDescs();
        String iconurl = bean.getIconurl();
        if (addtime!=null){
            bdListViewHodler.tvAddtime.setText(addtime);
        }
        if (descs!=null){
            bdListViewHodler.tvDesic.setText("导读："+descs);
        }
        if(iconurl!=null){
            String url= CommURL.URL_GIFT_PACKAGE_HEAD+iconurl;
            bdListViewHodler.imageView.setImageResource(R.mipmap.ic_launcher);
            ImageLoader.init(context).load(url,bdListViewHodler.imageView);
        }
        return convertView;
    }
    class BDListViewHodler{
        TextView tvAddtime;
        TextView tvDesic;
        ImageView imageView;
        public BDListViewHodler(View view){
           tvAddtime= (TextView) view.findViewById(R.id.tv_bd_detial_time1);
            tvDesic= (TextView) view.findViewById(R.id.tv_bd_detial_content1);
            imageView= (ImageView) view.findViewById(R.id.iv_bd_detial_photo1);
        }
    }
}
