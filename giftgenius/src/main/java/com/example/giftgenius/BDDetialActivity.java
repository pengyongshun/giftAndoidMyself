package com.example.giftgenius;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.giftgenius.characteristicfragment.adapter.BDDetailGridAdapter;
import com.example.giftgenius.characteristicfragment.adapter.BDDetialListViewAdapter;
import com.example.giftgenius.characteristicfragment.ascynictask.BDDetialGridAscyTask;
import com.example.giftgenius.characteristicfragment.bean.BDBean;
import com.example.giftgenius.characteristicfragment.bean.BDDetialGridViewBean;
import com.example.giftgenius.characteristicfragment.bean.BDListViewBean;
import com.example.giftgenius.comm.CommURL;
import com.example.giftgenius.cusview.CustomGridView;
import com.example.giftgenius.log.LogUtils;
import com.example.giftgenius.uitls.ImageLoader;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BDDetialActivity extends AppCompatActivity {

    private ImageView mIvBDdetial;
    private Button mBtnBack;
    private TextView mTvActionBarContent;
    private Button mBtnShape;
    private TextView mTvTime;
    private TextView mTvContent;
    private CustomGridView mGvContent;
    private BDBean bean;
    private String addtime;
    private String descs;
    private String name;
    private int sid;
    private String iconurl;
    private List<BDDetialGridViewBean> beanList=new ArrayList<>();
    private List<BDListViewBean> bdListViewBeanList=new ArrayList<>();
    private BDDetailGridAdapter mAdapter;
    private ListView mListView;
    private View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bddetial);
        //获取从BDfragment中的object对象
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        bean = (BDBean) bundle.getSerializable("obj");
        iniToolBar();
        initBootmView();
        initListView();

    }
    /**
     *
     * 加载GridView部分
     * */

    private void initBootmView() {
        if (bean==null){
            return;
        }
        sid=bean.getSid();
        mView = LayoutInflater.from(BDDetialActivity.this).inflate(R.layout.header_bd_detial,null);
        mGvContent= (CustomGridView) mView.findViewById(R.id.gv_bd_detial_content1);
        initAdapter();
        LoadDataGrid();
        initListen();
    }

    /**
     *
     * 加载ListView部分
     * */
    private void initListView() {
        mListView = (ListView) findViewById(R.id.lv_ba_detial_listview1);
        if (bean==null){
            return;
        }
        iconurl=bean.getIconurl();
        descs=bean.getDescs();
        addtime=bean.getAddtime();
        BDListViewBean bdListViewBean=new BDListViewBean(addtime,descs,iconurl);
        bdListViewBeanList.add(bdListViewBean);
        BDDetialListViewAdapter mLAdapter=new BDDetialListViewAdapter(BDDetialActivity.this,bdListViewBeanList);
        mListView.addFooterView(mView);
        mListView.setAdapter(mLAdapter);
        mLAdapter.notifyDataSetChanged();

    }

    private void initListen() {
        mGvContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (beanList==null){
                    return;
                }
                BDDetialGridViewBean gridViewBean = beanList.get(position);
                String appid = gridViewBean.getAppid();
                Intent intent=new Intent(BDDetialActivity.this,HotDetialActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("id",appid);
                intent.putExtra("bundle",bundle);
                startActivity(intent);
            }
        });
    }

    private void initAdapter() {
        if (beanList!=null){
            LogUtils.log(BDDetialActivity.class,beanList.toString());
            mAdapter=new BDDetailGridAdapter(this,beanList);
            mGvContent.setAdapter(mAdapter);
        }
    }

    private void LoadDataGrid() {
        if (sid==0){
            return;
        }
        new BDDetialGridAscyTask(new BDDetialGridAscyTask.BDDetialGridCallBack() {
            @Override
            public void bDDetialGridCallBack(List<BDDetialGridViewBean> bDDetialGridViewList) {
                  if (bDDetialGridViewList==null){
                      return;
                  }
                beanList.addAll(bDDetialGridViewList);
                LogUtils.log(BDDetialActivity.class,beanList.toString());
                mAdapter.notifyDataSetChanged();
            }
        }).execute(sid+"");
    }

    /***
     * 初始化ToolBar
     */
    private void iniToolBar() {
        Toolbar mToolBbar = (Toolbar) findViewById(R.id.toolbar_bd_detial_show1);
        mBtnBack = (Button) findViewById(R.id.btn_bd_detial_actionbar_back1);
        mTvActionBarContent = (TextView) findViewById(R.id.tv_bd_detial_actionbar_content1);
        mBtnShape = (Button) findViewById(R.id.btn_bd_detial_actionbar_shape1);
        setSupportActionBar(mToolBbar);
        name=bean.getName();
        if (name!=null){
            mTvActionBarContent.setText(name);
        }
    }
    /**
     * 对返回按钮和下载按钮进行监听
     */
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_bd_detial_actionbar_back1:
                finish();
                break;
            case R.id.btn_bd_detial_actionbar_shape1:
                Toast.makeText(BDDetialActivity.this, "点击了分享", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
