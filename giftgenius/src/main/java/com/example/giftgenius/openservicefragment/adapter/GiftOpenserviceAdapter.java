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
import com.example.giftgenius.openservicefragment.bean.GiftOpenservice;
import com.example.giftgenius.uitls.ImageLoader;

import java.util.List;
import java.util.Map;

/**
 * Created by 彭永顺 on 2016/8/16.
 */
public class GiftOpenserviceAdapter extends BaseAdapter {
    private static final int TYPE_DATE = 0;
    private static final int TYPE_ITEM = 1;
    private List<Object> list = null;
    private Context context = null;
    private LayoutInflater inflater;

    public GiftOpenserviceAdapter(Context context, List<Object> list) {
        this.context = context;
        this.list = list;
        inflater=LayoutInflater.from(context);
    }

    //获得布局类型的个数
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    //得到布局的类型
    @Override
    public int getItemViewType(int position) {
        Object obj = list.get(position);
        return obj instanceof GiftOpenservice ? TYPE_ITEM : TYPE_DATE;
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
        //获得布局类型
        int type = getItemViewType(position);
        if (type==TYPE_DATE){

              GroupViewHoder groupViewHoder;
           if (convertView==null){
               convertView=inflater.inflate(R.layout.item_group_childfragment_open,parent,false);
               groupViewHoder=new GroupViewHoder(convertView);
               convertView.setTag(groupViewHoder);
           }else {
               groupViewHoder= (GroupViewHoder) convertView.getTag();

           }
            //添加数据，跟新Ui
            String time = list.get(position).toString();
           if (time==null){
             return null;
           }
           groupViewHoder.gTvTime.setText(time);
        }else if (type==TYPE_ITEM){
               ChildViewHoder childViewHoder;
             if (convertView==null){
                convertView=inflater.inflate(R.layout.itemchildfragment_open,parent,false);
                 childViewHoder=new ChildViewHoder(convertView);
                convertView.setTag(childViewHoder);
             }else {
                childViewHoder= (ChildViewHoder) convertView.getTag();
            }
              //添加数据，跟新Ui
               GiftOpenservice giftOpenservice= (GiftOpenservice) list.get(position);
             if (giftOpenservice==null){
                return null;
             }
              String area = giftOpenservice.getArea();
              String gname = giftOpenservice.getGname();
              String iconurl = giftOpenservice.getIconurl();
              String linkurl = giftOpenservice.getLinkurl();
              String operators = giftOpenservice.getOperators();
            if (gname!=null){
              childViewHoder.gNameTv.setText(gname);
            }
            if (operators!=null){
              childViewHoder.operatorsTv.setText(operators);
            }
            if (area!=null&&linkurl!=null){
              childViewHoder.linkurlAndAreaTv.setText(linkurl+"    "+area);
            }

            if (iconurl!=null){
                String url= CommURL.URL_GIFT_PACKAGE_HEAD+iconurl;
              childViewHoder.iconurlIv.setImageResource(R.mipmap.ic_launcher);
              ImageLoader.init(context).load(url,childViewHoder.iconurlIv);
            }
        }
        return convertView;
    }



    class GroupViewHoder{
        TextView gTvTime;
        public GroupViewHoder(View view){
            gTvTime= (TextView) view.findViewById(R.id.tv_item_group_childfragment_open);
        }
    }

    class ChildViewHoder{
        TextView  operatorsTv;
        TextView gNameTv;
        TextView linkurlAndAreaTv;
        ImageView iconurlIv;
        public ChildViewHoder(View view){
            operatorsTv= (TextView) view.findViewById(R.id.tv_childfragment_open_date);
            gNameTv= (TextView) view.findViewById(R.id.tv_childfragment_open_title);
            linkurlAndAreaTv= (TextView) view.findViewById(R.id.tv_childfragment_open_content);
            iconurlIv= (ImageView) view.findViewById(R.id.iv_childfragment_open_listview_icon);
        }
    }
}
