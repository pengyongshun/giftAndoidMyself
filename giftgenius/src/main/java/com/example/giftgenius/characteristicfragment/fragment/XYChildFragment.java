package com.example.giftgenius.characteristicfragment.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.giftgenius.BDDetialActivity;
import com.example.giftgenius.R;
import com.example.giftgenius.XYDetialActivity;
import com.example.giftgenius.characteristicfragment.adapter.BDChildAdapter;
import com.example.giftgenius.characteristicfragment.adapter.XYChildAdapter;
import com.example.giftgenius.characteristicfragment.ascynictask.BDAscyTask;
import com.example.giftgenius.characteristicfragment.ascynictask.XYAscyTask;
import com.example.giftgenius.characteristicfragment.bean.BDBean;
import com.example.giftgenius.characteristicfragment.bean.XYBean;
import com.example.giftgenius.comm.CommURL;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 彭永顺 on 2016/8/17.
 */
public class XYChildFragment extends Fragment {
    private Context context;
    private List<XYBean> beans=new ArrayList<>();
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //加载新数据
            loadData();
            //进度消失
            mListView.onRefreshComplete();
        }
    };
    private PullToRefreshListView mListView;
    private XYChildAdapter mAdapter;

    public static XYChildFragment newTnastance(){
        XYChildFragment fragment=new XYChildFragment();
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
        View view=inflater.inflate(R.layout.childfragment_xy,container,false);
        initView(view);
        initAdapter();
        loadData();
        initLister();
        return view;
    }
    private void initLister() {
        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //刷新数据
                if(beans!=null){
                    beans.clear();
                }
                mHandler.sendEmptyMessageDelayed(1,1000);


            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
        //转跳到详情界面
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                XYBean xyBean = beans.get(position - 1);
                if (xyBean==null){
                    return;
                }
                Intent intent=new Intent(context, XYDetialActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("obj",xyBean);
                intent.putExtra("bundle",bundle);
                startActivity(intent);
            }
        });
    }

    private void loadData() {
      new XYAscyTask(new XYAscyTask.XYCallBack() {
          @Override
          public void xYCallBack(List<XYBean> xyBeanList) {
              if (xyBeanList==null){
                  return;
              }
              beans.addAll(xyBeanList);
              mAdapter.notifyDataSetChanged();
          }
      }).execute(CommURL.URL_GIFT_CHARACTERISITIC_XY);
    }

    private void initAdapter() {
        if (beans==null){
            return;
        }
        mAdapter=new XYChildAdapter(context,beans);
        mListView.setAdapter(mAdapter);

    }

    private void initView(View view) {
        mListView= (PullToRefreshListView) view.findViewById(R.id.ptfl_xy_childfragment_listview);
    }
}
