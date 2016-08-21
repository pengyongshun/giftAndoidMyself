package com.example.giftgenius.openservicefragment.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ArrayRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.giftgenius.HotDetialActivity;
import com.example.giftgenius.R;
import com.example.giftgenius.comm.CommURL;
import com.example.giftgenius.log.LogUtils;
import com.example.giftgenius.openservicefragment.adapter.GiftOpenCeAdapter;
import com.example.giftgenius.openservicefragment.ascynictask.GiftOpenCeAscyTask;
import com.example.giftgenius.openservicefragment.bean.GiftOpence;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 彭永顺 on 2016/8/16.
 */
public class OpenCeChildFragment extends Fragment {
    private Context context;
    private ListView mListView;
    private List<GiftOpence> giftOpences=new ArrayList<>();
    private GiftOpenCeAdapter mAdapter;

    public static OpenCeChildFragment newTnastance(){
        OpenCeChildFragment fragment=new OpenCeChildFragment();
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
        View view=inflater.inflate(R.layout.childfragment_opence,container,false);
        initView(view);
        initAdapter();
        loadData();
        initListener();
        return view;
    }

    private void initListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GiftOpence giftOpence = giftOpences.get(position);
                String id1 = giftOpence.getGid();
                if (id1==null){
                    return;
                }
                Intent intent=new Intent(context, HotDetialActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("id",id1);
                LogUtils.log(OpenCeChildFragment.class,id1);
                intent.putExtra("bundle",bundle);
                startActivity(intent);

            }
        });
    }

    private void loadData() {
        new GiftOpenCeAscyTask(new GiftOpenCeAscyTask.GiftOpenCeCallBack() {
            @Override
            public void giftOpenCeCallBack(List<GiftOpence> giftOpenceList) {
                if (giftOpenceList==null){
                    return;
                }
                giftOpences.addAll(giftOpenceList);
                mAdapter.notifyDataSetChanged();
            }
        }).execute(CommURL.URL_GIFT_OPENCE);
    }

    private void initAdapter() {
        if (giftOpences==null){
            return;
        }
         mAdapter=new GiftOpenCeAdapter(context,giftOpences);
        mListView.setAdapter(mAdapter);
    }

    private void initView(View view) {
         mListView= (ListView) view.findViewById(R.id.lv_childfraggment_opence);
    }


}
