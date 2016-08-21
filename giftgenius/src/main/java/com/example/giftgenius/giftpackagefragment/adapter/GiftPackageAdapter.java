package com.example.giftgenius.giftpackagefragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.giftgenius.R;
import com.example.giftgenius.comm.CommURL;
import com.example.giftgenius.giftpackagefragment.bean.GiftPackageList;
import com.example.giftgenius.uitls.ImageLoader;

import java.util.List;

/**
 * Created by 彭永顺 on 2016/8/6.
 */
public class GiftPackageAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private List<GiftPackageList> giftPackageLists;
    private Context context;

    public GiftPackageAdapter(Context context, List<GiftPackageList> giftPackageLists) {
        this.context = context;
        this.giftPackageLists = giftPackageLists;
        mLayoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return giftPackageLists==null?0:giftPackageLists.size();
    }

    @Override
    public Object getItem(int position) {
        return giftPackageLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoder mViewHoder;
        if (convertView==null){
            convertView=mLayoutInflater.inflate(R.layout.item_frag_giftpackage,parent,false);
            mViewHoder=new ViewHoder(convertView);
            convertView.setTag(mViewHoder);
        }else {
            mViewHoder= (ViewHoder) convertView.getTag();
        }
        GiftPackageList giftpackageList = giftPackageLists.get(position);
        mViewHoder.tvGname.setText(giftpackageList.getGname());
        mViewHoder.tvGiftname.setText(giftpackageList.getGiftname());
        mViewHoder.tvAddtimeWithNumber.setText("剩余："+giftpackageList.getNumber()+"  时间："+giftpackageList.getAddtime());
        String iconurl = giftpackageList.getIconurl();
        String url= CommURL.URL_GIFT_PACKAGE_HEAD +iconurl;
        //设置防止图片错位
        mViewHoder.ivIcon.setImageResource(R.mipmap.ic_launcher);
        ImageLoader.init(context).load(url,mViewHoder.ivIcon);
        return convertView;
    }
    class ViewHoder{
        ImageView ivIcon;
        TextView tvGiftname;
        TextView tvGname;
        TextView tvAddtimeWithNumber;
        public ViewHoder(View view){
            ivIcon= (ImageView) view.findViewById(R.id.iv_gifts_listview_icon);
            tvGname= (TextView) view.findViewById(R.id.tv_gifts_title);
            tvGiftname= (TextView) view.findViewById(R.id.tv_gifts_content);
            tvAddtimeWithNumber= (TextView) view.findViewById(R.id.tv_gifts_date);
        }
    }
}
