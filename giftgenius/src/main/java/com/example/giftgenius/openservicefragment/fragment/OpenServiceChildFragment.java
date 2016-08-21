package com.example.giftgenius.openservicefragment.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.example.giftgenius.HotDetialActivity;
import com.example.giftgenius.R;
import com.example.giftgenius.comm.CommURL;
import com.example.giftgenius.openservicefragment.adapter.GiftOpenserviceAdapter;
import com.example.giftgenius.openservicefragment.ascynictask.GiftOpenserviceAscyTask;
import com.example.giftgenius.openservicefragment.bean.GiftOpenservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 彭永顺 on 2016/8/16.
 */
public class OpenServiceChildFragment extends Fragment {
    private Context context;
    private ListView mListView;
    private List<Object> objectList=new ArrayList<Object>();
    private GiftOpenserviceAdapter mAdapter;
    private List<GiftOpenservice> mGiftOpenservices=new ArrayList<>();
    private  GiftOpenservice openservice;

    public static OpenServiceChildFragment newTnastance(){
        OpenServiceChildFragment fragment=new OpenServiceChildFragment();
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.childfragment_openservice,container,false);
        initView(view);
        iniAdapter();
        loadData();
        initListener();
        return view;
    }
    /**
     * 对listView进行监听  转跳到详情页面
     * **/

    private void initListener() {

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object obj = objectList.get(position);
                if (obj instanceof String){
                    return;
                }

                openservice= (GiftOpenservice) obj;
                String gid = openservice.getGid();
                if (gid==null){
                    return;
                }
                Intent intent=new Intent(context, HotDetialActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("id",gid);
                intent.putExtra("bundle",bundle);
                startActivity(intent);

            }
        });
    }

    private void loadData() {
        new GiftOpenserviceAscyTask(new GiftOpenserviceAscyTask.GiftOpenserviceCallBack() {
            @Override
            public void giftOpenserviceCallBack(List<GiftOpenservice> giftOpenserviceList) {
                if (giftOpenserviceList==null){
                    return;
                }
                mGiftOpenservices.addAll(giftOpenserviceList);
                //进行分组
                String time0 = mGiftOpenservices.get(0).getAddtime();
                objectList.add(time0);

                int size = mGiftOpenservices.size();
                for (int i = 0; i <size; i++) {
                    GiftOpenservice gift = mGiftOpenservices.get(i);
                    //判断是否为一个时间
                    if (time0.equals(gift.getAddtime())){
                        //添加一个日期内的对象
                        objectList.add(gift);
                    }else {
                        //添加新日期
                       time0=gift.getAddtime();
                        objectList.add(time0);
                        //添加对象
                        objectList.add(gift);
                    }

                }


                mAdapter.notifyDataSetChanged();
            }
        }).execute(CommURL.URL_GIFT_OPENSERVICE);
    }

    private void iniAdapter() {
        if (objectList==null){
            return;
        }
        mAdapter=new GiftOpenserviceAdapter(context,objectList);
        mListView.setAdapter(mAdapter);
    }

    private void initView(View view) {
        mListView= (ListView) view.findViewById(R.id.lv_childgragment_openservice);
    }

}
