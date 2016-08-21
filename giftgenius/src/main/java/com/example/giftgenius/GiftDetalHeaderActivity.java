package com.example.giftgenius;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.giftgenius.comm.CommURL;
import com.example.giftgenius.giftpackagefragment.ascynictask.GiftDetialAscyTask;
import com.example.giftgenius.giftpackagefragment.bean.GiftDetial;
import com.example.giftgenius.log.LogUtils;
import com.example.giftgenius.uitls.ImageLoader;

import de.hdodenhof.circleimageview.CircleImageView;

public class GiftDetalHeaderActivity extends AppCompatActivity {

    private ImageView mIvGetGift;
    private TextView mTvDiftDetalTitle;
    private CircleImageView mIvGiftDetialPhoto;
    private TextView mTvGiftDetialDate;
    private TextView mTvGiftDetialTitle;
    private TextView mTvGiftDetialCount;
    private TextView mTvGiftDetialGiftContent;
    private TextView mTvGiftDetialChangeContent;
    private String headerId="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_detal_header);
        //获取从礼包列表界面传来的id
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        headerId = bundle.getString("headerId");
        initView();
        iniToolBar();
        iniData();
    }

    private void iniData() {
        if (headerId==null){
            return;
        }
       new GiftDetialAscyTask(new GiftDetialAscyTask.GiftDetialCallBack() {
           @Override
           public void giftDetialCallBack(GiftDetial giftDetial) {
               if (giftDetial==null){
                   return;
               }
               GiftDetial.InfoBean info = giftDetial.getInfo();
               String iconurl = info.getIconurl();
               String gname = info.getGname();
               String giftname = info.getGiftname();
               int number = info.getNumber();
               String descs = info.getDescs();
               String explains = info.getExplains();
               String overtime = info.getOvertime();
               String url= CommURL.URL_GIFT_PACKAGE_HEAD+iconurl;
               //更新UI
               mTvDiftDetalTitle.setText(gname+"-"+giftname.toString());
               mTvGiftDetialDate.setText("有限期："+overtime.toString());
               mTvGiftDetialCount.setText("礼包剩余："+number);
               mTvGiftDetialGiftContent.setText(explains.toString());
               mTvGiftDetialChangeContent.setText(descs.toString());
               ImageLoader.init(GiftDetalHeaderActivity.this).load(url,mIvGiftDetialPhoto);
           }
       }).execute(headerId);
    }

    private void initView() {
        mIvGiftDetialPhoto = (CircleImageView) findViewById(R.id.iv_activity_gifts_show);
        mTvGiftDetialDate = (TextView) findViewById(R.id.tv_activity_gifts_date);
        mTvGiftDetialTitle = (TextView) findViewById(R.id.tv_giftdetal_actionbar_content);
        mTvGiftDetialCount = (TextView) findViewById(R.id.tv_activity_gifts_count);
        mTvGiftDetialGiftContent = (TextView) findViewById(R.id.tv_giftdetal_header_gift_content);
        mTvGiftDetialChangeContent = (TextView) findViewById(R.id.tv_giftdetal_header_change_content);

        mIvGetGift = (ImageView) findViewById(R.id.iv_giftdetal_header_gift);
        mIvGetGift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //转跳到会员登录界面
                Intent intent=new Intent(GiftDetalHeaderActivity.this,DrawerLoginActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 领取礼包详情
     *
     * **/
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_giftdetal_actionbar_back:
                finish();
                break;
            case R.id.btn_giftdetal_actionbar_shape:
                Toast.makeText(GiftDetalHeaderActivity.this, "分享到qq上", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_giftdetal_get:
                Intent intent=new Intent(GiftDetalHeaderActivity.this,DrawerLoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    /***
     *
     * 初始化ToolBar
     * */
    private void iniToolBar() {
        Toolbar mToolBbar = (Toolbar)findViewById(R.id.toolbar_giftdetal_show);
        mTvDiftDetalTitle = (TextView) findViewById(R.id.tv_giftdetal_actionbar_content);
        setSupportActionBar(mToolBbar);
    }

}
