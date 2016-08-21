package com.example.giftgenius.hotfragment.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.example.giftgenius.HotDetialActivity;
import com.example.giftgenius.R;
import com.example.giftgenius.comm.CommURL;
import com.example.giftgenius.hotfragment.adapter.HotAdapter;
import com.example.giftgenius.hotfragment.adapter.HotGridAdapter;
import com.example.giftgenius.hotfragment.ascynictask.HotFragmentAscyTask;
import com.example.giftgenius.hotfragment.ascynictask.HotFragmentGridAscyTask;
import com.example.giftgenius.hotfragment.bean.GiftHotBean;
import com.example.giftgenius.hotfragment.bean.GiftHotBeanGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 彭永顺 on 2016/8/5.
 */
public class HotFragment extends Fragment{
    public static final String URL_JSON="url";
    private Context context;
    private ListView mListView;
    private GridView mGridView;
    private List<GiftHotBean> giftHotBeens=new ArrayList<>();
    private HotAdapter mAdapter;
    private List<GiftHotBeanGridView> giftHotBeanGridViews=new ArrayList<>();
    private HotGridAdapter mHotGridAdapter;

    public static HotFragment newInstance(String url){
        HotFragment fragment=new HotFragment();
        Bundle bundle=new Bundle();
        bundle.putString(URL_JSON,url);
        fragment.setArguments(bundle);
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
       View view=inflater.inflate(R.layout.fragment_hot,container,false);
        initListView(view);
        initGridView(view);
        return view;
    }

    private void initGridView(View view) {
        mGridView = (GridView) view.findViewById(R.id.gv_hotfragment);
        iniGAdapter();
        loadDataGrid();
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GiftHotBeanGridView giftHotBeanGridView = giftHotBeanGridViews.get(position);
                String appid = giftHotBeanGridView.getAppid();
                if (appid==null){
                    return;
                }
                Intent intent=new Intent(context, HotDetialActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("id",appid);
                intent.putExtra("bundle",bundle);
                startActivity(intent);
            }
        });
    }

    private void loadDataGrid() {
        new HotFragmentGridAscyTask(new HotFragmentGridAscyTask.GiftHotGridCallBack() {
            @Override
            public void hotGridCallBack(List<GiftHotBeanGridView> giftHotGridBeanList) {
                if (giftHotGridBeanList==null){
                    return;
                }
                giftHotBeanGridViews.addAll(giftHotGridBeanList);
                mHotGridAdapter.notifyDataSetChanged();
            }
        }).execute(CommURL.URL_GIFT_HOT);
    }

    private void iniGAdapter() {
        if (giftHotBeanGridViews==null){
            return;
        }
        mHotGridAdapter = new HotGridAdapter(context,giftHotBeanGridViews);
        mGridView.setAdapter(mHotGridAdapter);
    }
    //..............................................................................

    /**
     * 初始化ListView部分
     *
     * **/
    private void initListView(View view) {
        initView(view);
        iniAdapter();
        loadData();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GiftHotBean giftHotBean = giftHotBeens.get(position);
                String appid = giftHotBean.getAppid();
                if (appid==null){
                    return;
                }
                Intent intent=new Intent(context, HotDetialActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("id",appid);
                intent.putExtra("bundle",bundle);
                startActivity(intent);
            }
        });
    }

    private void loadData() {
        new HotFragmentAscyTask(new HotFragmentAscyTask.GiftHotCallBack() {
            @Override
            public void hotCallBack(List<GiftHotBean> giftHotBeanList) {
                if (giftHotBeanList==null){
                    return;
                }
                giftHotBeens.addAll(giftHotBeanList);
                mAdapter.notifyDataSetChanged();
            }
        }).execute(CommURL.URL_GIFT_HOT);
    }

    private void iniAdapter() {
        if (giftHotBeens==null){
            return;
        }
        mAdapter=new HotAdapter(context,giftHotBeens);
        mListView.setAdapter(mAdapter);
    }

    private void initView(View view) {
        mListView = (ListView) view.findViewById(R.id.lv_hotfragment);

    }

}
