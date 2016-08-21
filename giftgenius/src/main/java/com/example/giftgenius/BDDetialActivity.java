package com.example.giftgenius;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.giftgenius.characteristicfragment.adapter.BDDetailGridAdapter;
import com.example.giftgenius.characteristicfragment.ascynictask.BDDetialGridAscyTask;
import com.example.giftgenius.characteristicfragment.bean.BDBean;
import com.example.giftgenius.characteristicfragment.bean.BDDetialGridViewBean;
import com.example.giftgenius.comm.CommURL;
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
    private GridView mGvContent;
    private BDBean bean;
    private String addtime;
    private String descs;
    private String name;
    private int sid;
    private String iconurl;
    private List<BDDetialGridViewBean> beanList=new ArrayList<>();
    private BDDetailGridAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bddetial);
        //获取从BDfragment中的object对象
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        bean = (BDBean) bundle.getSerializable("obj");
        iniToolBar();
        initHView();
        loadData();
        initAdapter();
        LoadDataGrid();
        initListen();
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

    private void loadData() {
         addtime = bean.getAddtime();
         descs = bean.getDescs();
         name = bean.getName();
         sid = bean.getSid();
         iconurl = bean.getIconurl();
        if (addtime!=null){
            mTvTime.setText(addtime);
        }
        if (descs!=null){
            mTvContent.setText("导读："+descs);
        }
        if(iconurl!=null){
            String url= CommURL.URL_GIFT_PACKAGE_HEAD+iconurl;
            ImageLoader.init(this).load(url,mIvBDdetial);
        }
        if (name!=null){
            mTvActionBarContent.setText(name);
        }
    }

    private void initHView() {

        mIvBDdetial = (ImageView) findViewById(R.id.iv_bd_detial_photo1);
        mTvTime = (TextView) findViewById(R.id.tv_bd_detial_time1);
        mTvContent = (TextView) findViewById(R.id.tv_bd_detial_content1);
        mGvContent = (GridView) findViewById(R.id.gv_bd_detial_content1);

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
