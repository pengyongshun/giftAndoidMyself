package com.example.giftgenius;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.giftgenius.log.LogUtils;
import com.example.giftgenius.serch.adapter.SerchAdapter;
import com.example.giftgenius.serch.asyctask.SerchAscyTask;
import com.example.giftgenius.serch.bean.SerchBean;

import java.util.ArrayList;
import java.util.List;

public class SerchActivity extends AppCompatActivity {

    private Button mBtnActionBar;
    private TextView mEtActionBar;
    private Button mBtnSerCh;
    private ListView mListView;
    private TextView mTvEmpty;
    private List<SerchBean> serchBeanList=new ArrayList<>();
    private SerchAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serch);
        //获取从首界面传来的 key
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        String key = bundle.getString("key");
        iniToolBar();
        initView();
        iniAdapter();
        LoadData(key);
        intListener();
    }

    private void intListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SerchBean serchBean = serchBeanList.get(position);
                String id1 = serchBean.getId();
                if (id1==null){
                    return;
                }
                Intent intent=new Intent(SerchActivity.this,GiftDetalHeaderActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("headerId",id1);
                intent.putExtra("bundle",bundle);
                startActivity(intent);
            }
        });
    }

    private void iniAdapter() {
        if (serchBeanList==null){
            return;
        }
        mAdapter = new SerchAdapter(SerchActivity.this,serchBeanList);
        mListView.setAdapter(mAdapter);
    }

    private void LoadData(String key) {
        if (key==null){
            return;
        }
        new SerchAscyTask(new SerchAscyTask.SerchBeanCallBack() {
            @Override
            public void serchBeanCallBack(List<SerchBean> serchBeans) {
                if (serchBeans!=null){
                    serchBeanList.addAll(serchBeans);
                    mAdapter.notifyDataSetChanged();

                }
            }
        }).execute(key);
    }

    /***
     *
     * 初始化ToolBar
     * */
    private void iniToolBar() {
        Toolbar mToolBbar = (Toolbar)findViewById(R.id.toolbar_show);
        mBtnActionBar = (Button)findViewById(R.id.btn_serch_opendrawer_actionbar);
        mEtActionBar = (TextView)findViewById(R.id.et_serch_actionbar);
        mBtnSerCh = (Button) findViewById(R.id.btn_serch_service_actionbar);
        setSupportActionBar(mToolBbar);
    }
    //搜索和打开抽屉进行监听
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_serch_opendrawer_actionbar:
                finish();
                break;
            case R.id.btn_serch_service_actionbar:
                // 处理搜索的事件
                if (serchBeanList!=null){
                    serchBeanList.clear();
                }
                String result = mEtActionBar.getEditableText().toString();
                LogUtils.log(SerchActivity.class,result);
                if (result==null){
                    Toast.makeText(SerchActivity.this, "输入框不能为空", Toast.LENGTH_SHORT).show();
                }
                LoadData(result);
                break;
        }
    }
    private void initView() {
        mListView = (ListView) findViewById(R.id.lv_serch_content);
        mTvEmpty = (TextView) findViewById(R.id.tv_serch_empty);
        mListView.setEmptyView(mTvEmpty);
    }
}
