package com.example.giftgenius;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.giftgenius.characteristicfragment.adapter.XYDetialAdapter;
import com.example.giftgenius.characteristicfragment.ascynictask.XYDetialAscyTask;
import com.example.giftgenius.characteristicfragment.bean.BDBean;
import com.example.giftgenius.characteristicfragment.bean.XYBean;
import com.example.giftgenius.characteristicfragment.bean.XYDetialBean;
import com.example.giftgenius.comm.CommURL;
import com.example.giftgenius.uitls.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class XYDetialActivity extends AppCompatActivity {

    private XYBean bean;
    private ListView mListView;
    private List<XYDetialBean> beanList=new ArrayList<>();
    private XYDetialAdapter mAdapter;
    private int id;
    private Button mBtnBack;
    private TextView mTvActionBarContent;
    private Button mBtnShape;
    private CircleImageView mCrIcIcon;
    private ImageView mImageView;
    private TextView mTvContent;
    private View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xydetial);
        //获取从BDfragment中的object对象
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        bean = (XYBean) bundle.getSerializable("obj");
        id = bean.getId();
        iniToolBar();
        //加载头部
        initHeaderView();
        //加载ListView
        initView();
    }


    private void initHeaderView() {
        initHView();
        loadDataH();
    }

    private void loadDataH() {
        String authorimg = bean.getAuthorimg();
        String iconurl = bean.getIconurl();
        String descs = bean.getDescs();
        String name = bean.getName();
        String author= CommURL.URL_GIFT_PACKAGE_HEAD+"/"+authorimg;
        String photo=CommURL.URL_GIFT_PACKAGE_HEAD+iconurl;
        if (name!=null){
            mTvActionBarContent.setText(name);
        }
        if (descs!=null){
            mTvContent.setText(descs);
        }
        if (iconurl!=null){
            ImageLoader.init(XYDetialActivity.this).load(photo,mImageView);
        }
        if (authorimg!=null){
            ImageLoader.init(XYDetialActivity.this).load(author,mCrIcIcon);
        }

    }

    private void initHView() {
        mView = LayoutInflater.from(XYDetialActivity.this).inflate(R.layout.xy_detial_header, null);
        mCrIcIcon = (CircleImageView) mView.findViewById(R.id.civ_xy_detial_icon1);
        mImageView = (ImageView) mView.findViewById(R.id.iv_xy_detial_photo1);
        mTvContent = (TextView) mView.findViewById(R.id.tv_xy_detial_content1);
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.lv_xy_detial_list);
        iniAdapter();
        loadData();
        mListView.addHeaderView(mView);
        mAdapter.notifyDataSetChanged();
        initListen();

    }

    private void initListen() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //转跳到下载页面
                XYDetialBean xyDetialBean = beanList.get(position-1);
                String appid = xyDetialBean.getAppid();
                if (appid==null){
                    return;
                }
                Intent intent=new Intent(XYDetialActivity.this,HotDetialActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("id",appid);
                    intent.putExtra("bundle",bundle);
                    startActivity(intent);
            }
        });
    }

    private void loadData() {
        if (id==0){
            return;
        }
        new XYDetialAscyTask(new XYDetialAscyTask.XYDetialCallBack() {
            @Override
            public void xYDetialCallBack(List<XYDetialBean> xYDetialBeanList) {
               if (xYDetialBeanList==null){
                   return;
               }
                beanList.addAll(xYDetialBeanList);
                mAdapter.notifyDataSetChanged();
            }
        }).execute(id+"");
    }

    private void iniAdapter() {
        if (beanList!=null){
             mAdapter=new XYDetialAdapter(XYDetialActivity.this,beanList);
            mListView.setAdapter(mAdapter);
        }

    }


    /***
     * 初始化ToolBar
     */
    private void iniToolBar() {
        Toolbar mToolBbar = (Toolbar) findViewById(R.id.toolbar_xy_detial_show);
        mBtnBack = (Button) findViewById(R.id.btn_xy_detial_actionbar_back);
        mTvActionBarContent = (TextView) findViewById(R.id.tv_xy_detial_actionbar_content);
        mBtnShape = (Button) findViewById(R.id.btn_xy_detial_actionbar_shape);
        setSupportActionBar(mToolBbar);
    }
    /**
     * 对返回按钮和下载按钮进行监听
     */
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_xy_detial_actionbar_back:
                finish();
                break;
            case R.id.btn_xy_detial_actionbar_shape:
                Toast.makeText(XYDetialActivity.this, "点击了分享", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
