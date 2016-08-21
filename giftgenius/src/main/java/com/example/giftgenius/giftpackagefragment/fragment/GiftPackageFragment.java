package com.example.giftgenius.giftpackagefragment.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.example.giftgenius.GiftDetalHeaderActivity;
import com.example.giftgenius.R;
import com.example.giftgenius.giftpackagefragment.adapter.GiftPackageAdapter;
import com.example.giftgenius.giftpackagefragment.adapter.HeaderViewAdapter;
import com.example.giftgenius.giftpackagefragment.ascynictask.GiftPackageListAnscank;
import com.example.giftgenius.giftpackagefragment.ascynictask.HeaderViewTask;
import com.example.giftgenius.giftpackagefragment.bean.GiftAd;
import com.example.giftgenius.giftpackagefragment.bean.GiftPackageList;
import com.example.giftgenius.log.LogUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by 彭永顺 on 2016/8/5.
 */
public class GiftPackageFragment extends Fragment implements ViewPager.OnPageChangeListener, View.OnTouchListener {
    public static final String URL_JSON="url";
    private Context context;
    private PullToRefreshListView mListView;
    private List<GiftPackageList> mGiftPackageListList=new ArrayList<>();
    private GiftPackageAdapter mAdapter;
    private ViewPager mViewPager;
    private LinearLayout mLlDots;
    private int count=0;
    private List<GiftAd> giftAds =new ArrayList<>();
    private HeaderViewAdapter mHeaderAdaper;
    private boolean bottom=false;
    private int i=1;
    private boolean isAutoScoll=true;
    private View mView;
    private List<String>giftHeaderIds=new ArrayList<>();
    private List<String>giftDetialIds=new ArrayList<>();
    private List<String> adImageUrlList=new ArrayList<>();
    /**
     *
     * 将ViewPage循环自动翻页
     * **/
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (count>Integer.MAX_VALUE){
                count=0;
            }
            mViewPager.setCurrentItem(count%4);
            count++;
            if (isAutoScoll){
                mHandler.sendEmptyMessageDelayed(1,1000);
            }
        }
    };
    /**
     * 下拉刷新
     *
     * **/
   private Handler rlHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            loadListData("1");
            //刷新结束
            mListView.onRefreshComplete();
        }
    };
    private Handler uHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //加载更多
            i++;
            loadListData((2*i-1)+"");
            mListView.onRefreshComplete();
        }
    };

    /**
     * 使用工厂模式来进行activity向fragment传值
     *
     * **/
    public static GiftPackageFragment newInstance(String url){
        GiftPackageFragment fragment=new GiftPackageFragment();
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
        View view=inflater.inflate(R.layout.fragment_gift_package,container,false);
        initHeaderView(container);
        initListView(view);
        mHandler.sendEmptyMessageDelayed(1,1000);
        return view;
    }

    /**
     * 头部部分
     *
     * **/
    private void initHeaderView(ViewGroup container) {
        mView=LayoutInflater.from(context).inflate(R.layout.header_view,container,false);
        mViewPager= (ViewPager) mView.findViewById(R.id.vp_header_view);
        initDots(mView);
        initHeaderViewAdapter();
        loadHeaderViewData("1");
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setOnTouchListener(this);

    }

    /**
     * ListView部分
     *
     * **/

    private void initListView(View view) {
        mListView = (PullToRefreshListView) view.findViewById(R.id.lv_frag_gift_content);
        ListView refreshableView = mListView.getRefreshableView();
        mListView.setLastUpdatedLabel("上次刷新:2016-8-11");
        //设置支持加载更多功能
        //同时支持刷新和加载更多功能
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        initListAdapter();
        loadListData("1");
        refreshableView.addHeaderView(mView);
        //mListView.setOnScrollListener(this);
        mAdapter.notifyDataSetChanged();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //转跳到详情界面

                Intent intent=new Intent(context,GiftDetalHeaderActivity.class);
                Bundle bundle=new Bundle();
                if (giftDetialIds==null){
                    return;
                }
                String giftDetialId = giftDetialIds.get(position-2);
                LogUtils.log(GiftPackageFragment.class,giftDetialId);
                bundle.putString("headerId",giftDetialId);
                intent.putExtra("bundle",bundle);
                startActivity(intent);
            }
        });
        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (mGiftPackageListList!=null){
                    mGiftPackageListList.clear();
                }
                //刷新数据
                rlHandler.sendEmptyMessageDelayed(2,1000);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                //加载更多
                uHandler.sendEmptyMessageDelayed(3,1000);

            }
        });

    }


    /***
     *
     * 初始化原点
     * **/
    private void initDots(View view) {
        mLlDots = (LinearLayout) view.findViewById(R.id.ll_indtor_dots);
        int childCount = mLlDots.getChildCount();
        for (int i = 0; i <childCount ; i++) {
            View dot = mLlDots.getChildAt(i);
            dot.setEnabled(false);
        }
        View fristView = mLlDots.getChildAt(0);
        fristView.setEnabled(true);
    }

    /***
     *
     * 广告栏的适配器初始化
     *
     */

    private void initHeaderViewAdapter() {

        if (adImageUrlList!=null){
            mHeaderAdaper=new HeaderViewAdapter(adImageUrlList,context);
            mViewPager.setAdapter(mHeaderAdaper);
        }

    }

    /****
     * 加载广告栏的数据
     *
     *
     */

    private void loadHeaderViewData(String param) {
         new HeaderViewTask(new HeaderViewTask.HCallBack() {
             @Override
             public void hCallBack(List<GiftAd> giftAdList) {
                 giftAds.addAll(giftAdList);
                 if (giftAds==null){
                     return;
                 }
                 int size = giftAds.size();
                 for (int i = 0; i <size; i++) {
                     GiftAd giftAd = giftAds.get(i);
                     String giftid = giftAd.getGiftid();
                     String iconurl = giftAd.getIconurl();
                     giftHeaderIds.add(giftid);
                     adImageUrlList.add(iconurl);
                 }
                 mHeaderAdaper.notifyDataSetChanged();
             }
         }).execute(param);
    }

    /****
     * 对广告栏的ViewPage进行监听
     *
     *】
     */

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(final int position) {
        for (int i = 0; i < mLlDots.getChildCount(); i++) {
            mLlDots.getChildAt(i).setEnabled(false);
        }
        View dot = mLlDots.getChildAt(position);
        dot.setEnabled(true);
        //点击转跳头部详情界面
        View view = mViewPager.getChildAt(position);
        if (view==null){
            return;
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (giftHeaderIds==null){
                    return;
                }
                String id = giftHeaderIds.get(position);
                Intent intent=new Intent(context, GiftDetalHeaderActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("headerId",id);
                intent.putExtra("bundle",bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onPageScrollStateChanged(int state) {


    }

    /**
     * 对广告栏的头部viewPage触摸进行监听
     *
     * **/
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //按下时，自动播放停止
                isAutoScoll=false;
                mHandler.removeCallbacksAndMessages(null);

                break;
            case MotionEvent.ACTION_UP:
                //抬起时，恢复自动播放
                isAutoScoll=true;
                mHandler.sendEmptyMessageDelayed(1,1000);
                break;
        }
        return false;
    }
    //......................................................................................

    /**
     * 对ListView部分的数据加载
     *
     * **/
    private void loadListData(String param) {
        new GiftPackageListAnscank(new GiftPackageListAnscank.CallBack() {
            @Override
            public void callBack(List<GiftPackageList> giftPackageListList) {
                if (giftPackageListList!=null){
                    mGiftPackageListList.addAll(giftPackageListList);
                    int size = mGiftPackageListList.size();
                    for (int i = 0; i < size; i++) {
                        GiftPackageList giftPackageList = mGiftPackageListList.get(i);
                        String giftid = giftPackageList.getGiftid();
                        LogUtils.log(GiftPackageFragment.class,giftid.toString());
                        giftDetialIds.add(giftid);
                    }
                    mAdapter.notifyDataSetChanged();
                }

            }
        }).execute(param);
    }


    /**
     * 对ListView部分的适配器初始化
     *
     * **/
    private void initListAdapter() {
        if (mGiftPackageListList!=null){
            mAdapter=new GiftPackageAdapter(context,mGiftPackageListList);
            mListView.setAdapter(mAdapter);
        }
    }


//    /**
//     * 对ListView部分的滚动进行监听  判断是否达到底部，到达底部就加载下一页
//     *
//     * **/
//
//    @Override
//    public void onScrollStateChanged(AbsListView view, int scrollState) {
//        //下拉刷新加载下一页的数据
//        if (bottom&& scrollState== AbsListView.OnScrollListener.SCROLL_STATE_IDLE){
//            loadHeaderViewData((2*i-1)+"");
//            loadListData((2*i-1)+"");
//
//        }
//    }
//
//    /**
//     * 滑动过程中进行监听  判断是否到达底部
//     * */
//    @Override
//    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//        //判断是否滑到了底部
//        if (firstVisibleItem+visibleItemCount==totalItemCount){
//            bottom=true;
//            i++;
//
//        }
//    }


}
