package com.example.giftgenius;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.giftgenius.comm.CommURL;
import com.example.giftgenius.log.LogUtils;
import com.example.giftgenius.openservicefragment.ascynictask.OpenserviceDetialAscyTask;
import com.example.giftgenius.openservicefragment.bean.OpenserviceDetial;
import com.example.giftgenius.uitls.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HotDetialActivity extends AppCompatActivity {

    private Button mBtnBack;
    private TextView mTvActionBarContent;
    private TextView mTvHotDetialGName;
    private TextView mTvHotDetialType;
    private TextView mTvHotDetialSize;
    private ImageView mIvHotDetialIcon;
    private HorizontalScrollView mHsvHotDetial;
    private TextView mTvHotDetialContent;
    private String id;
    private OpenserviceDetial detial;
    private List<String> urls=new ArrayList<>();
    private LinearLayout mGalleryLinearLayout;
    private LinearLayout mLLShv;
    private int width;
    private int height;
    private LinearLayout.LayoutParams params;
    private Button mBtnLoad;
    private String download_addr="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_detial);
        //平分屏幕
        Display display = getWindowManager().getDefaultDisplay();
         width = display.getWidth() / 2;
         height = 500;
        //横向列表的设置
         params =new LinearLayout.LayoutParams(width,height);
         params.setMargins(5,0,0,0);
        //获取从列表界面传来的id
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        id = bundle.getString("id");
        iniToolBar();
        initView();
        loadData();
    }

    /**
     * 加载数据
     * */

    private void loadData() {
         if (id==null){
             Toast.makeText(HotDetialActivity.this, "没有获取到id+", Toast.LENGTH_SHORT).show();
             return;

         }
        new OpenserviceDetialAscyTask(new OpenserviceDetialAscyTask.DetialCallBack() {
            @Override
            public void detialCallBack(OpenserviceDetial openserviceDetial) {
                if (openserviceDetial==null){
                    return;
                }
                //上面部分
                detial=openserviceDetial;
                OpenserviceDetial.AppBean app = detial.getApp();
                String logo = app.getLogo();
                String name = app.getName();
                String typename = app.getTypename();
                int downloads = app.getDownloads();
                String logoNew=CommURL.URL_GIFT_PACKAGE_HEAD+logo;
                mTvActionBarContent.setText(name);
                mTvHotDetialGName.setText(name);
                mTvHotDetialSize.setText("大小："+downloads+"MB");
                mTvHotDetialType.setText("类型："+typename);
                ImageLoader.init(HotDetialActivity.this).load(logoNew,mIvHotDetialIcon);

                //下面部分
                String description = app.getDescription();
                mTvHotDetialContent.setText(description);
                //中间部分
                List<OpenserviceDetial.ImgBean> img = detial.getImg();
                int size = img.size();
                for (int i = 0; i < size; i++) {
                    OpenserviceDetial.ImgBean imgBean = img.get(i);
                    String address = imgBean.getAddress();
                    String url= CommURL.URL_GIFT_PACKAGE_HEAD+address;
                    urls.add(url);
                }
                int size1 = urls.size();
                for (int i = 0; i < size1; i++) {
                    String url1 = urls.get(i);
                    ImageView imageView=new ImageView(HotDetialActivity.this);
                    imageView.setLayoutParams(params);
                    imageView.setImageResource(R.mipmap.ic_launcher);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    ImageLoader.init(HotDetialActivity.this).load(url1,imageView);
                    mLLShv.addView(imageView);
                }
                //按钮部分 获取下载地址
                 download_addr = app.getDownload_addr();
                if (download_addr.isEmpty()){
                    mBtnLoad.setEnabled(false);
                    mBtnLoad.setText("已经下载");
                }else {
                    mBtnLoad.setEnabled(true);
                }

            }
        }).execute(id);
    }

    /***
     * 初始化ToolBar
     */
    private void iniToolBar() {
        Toolbar mToolBbar = (Toolbar) findViewById(R.id.toolbar_hotfragment_detial_show);
        mBtnBack = (Button) findViewById(R.id.btn_hotfragment_detial_actionbar_back);
        mTvActionBarContent = (TextView) findViewById(R.id.tv_hotfragment_detial_actionbar_content);
        setSupportActionBar(mToolBbar);
    }

    private void initView() {
        mTvHotDetialGName = (TextView) findViewById(R.id.tv_hotfragment_detial_gname);
        mTvHotDetialType = (TextView) findViewById(R.id.tv_hotfragment_detial_type);
        mTvHotDetialSize = (TextView) findViewById(R.id.tv_hotfragment_detial_size);
        mIvHotDetialIcon = (ImageView) findViewById(R.id.iv_hotfragment_detial_icon);
        mHsvHotDetial = (HorizontalScrollView) findViewById(R.id.hsv_hotfragment_detial);
        mTvHotDetialContent = (TextView) findViewById(R.id.tv_hotfragment_detial_content);
        mLLShv = (LinearLayout) findViewById(R.id.ll_hotfragment_detial_shv);
        mBtnLoad = (Button) findViewById(R.id.btn_hotfragment_detial);


    }


    /**
     * 对返回按钮和下载按钮进行监听
     */
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_hotfragment_detial_actionbar_back:
                finish();
                break;
            case R.id.btn_hotfragment_detial:
                createDelioage();
                break;
        }
    }
    /**
     * 下载对话框
     *
     * **/
    private void createDelioage(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("下载");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("是否需要下载当前礼包？");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //下载礼包
                Uri uri=Uri.parse(download_addr);
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("NO",null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
