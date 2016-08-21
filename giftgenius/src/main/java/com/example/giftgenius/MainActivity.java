package com.example.giftgenius;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.giftgenius.characteristicfragment.fragment.CharacteristicFragment;
import com.example.giftgenius.giftpackagefragment.fragment.GiftPackageFragment;
import com.example.giftgenius.hotfragment.fragment.HotFragment;
import com.example.giftgenius.openservicefragment.fragment.OpenServiceFragment;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioGroup mRadioGroup;
    private GiftPackageFragment mGiftPackageFragment;
    private HotFragment mHotFragment;
    private OpenServiceFragment mOpenServiceFragment;
    private CharacteristicFragment mCharacteristicFragment;
    private Fragment curFragment;
    private FragmentManager mSupportFragmentManager;
    private Button mBtnActionBar;
    private TextView mTvActionBar;
    private List<String> titles=new ArrayList<>();
    private List<Fragment> fragments=new ArrayList<>();
    private DrawerLayout mLayout;
    private RelativeLayout mDrawerLayout;
    private CircleImageView mCricleView;
    private Button mBtnSerCh;
    private Button openBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSupportFragmentManager = getSupportFragmentManager();
        initView();
        initFragment();
        initListener();
    }

    /**
     * 点击RadioButton进行切换Fragment
     *
     * ***/
    private void initListener() {
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_gift_package:
                        mBtnSerCh.setVisibility(View.VISIBLE);
                        mTvActionBar.setText(titles.get(0));
                        switchFragment(0);
                        break;
                    case R.id.rb_open_service:
                        mBtnSerCh.setVisibility(View.GONE);
                        mTvActionBar.setText(titles.get(1));
                        switchFragment(1);
                        break;
                    case R.id.rb_hot:
                        mBtnSerCh.setVisibility(View.GONE);
                        mTvActionBar.setText(titles.get(2));
                        mBtnSerCh.setVisibility(View.GONE);
                        switchFragment(2);
                        break;
                    case R.id.rb_characteristic:
                        mBtnSerCh.setVisibility(View.GONE);
                        mTvActionBar.setText(titles.get(3));
                        mBtnSerCh.setVisibility(View.GONE);
                        switchFragment(3);
                        break;

                }
            }
        });
    }

    /***
     * fragment 为要切换的Fragment
     * curFragment 为当前显示的Fragment
     * 注意：在切换时，要隐藏当前的Fragment，显示即将要切换的Fragment、需要对其进行判断是否被添加
     * **/
    private void switchFragment(int index) {
        FragmentTransaction transaction = mSupportFragmentManager.beginTransaction();
        Fragment fragment = fragments.get(index);
        if (curFragment!=null&&curFragment.isAdded()){
            transaction.hide(curFragment);
        }
        if (!fragment.isAdded()){
            transaction.add(R.id.fl_fragment_show,fragment);
        }else {
            transaction.show(fragment);
        }
        transaction.commit();
        curFragment=fragment;
    }

    /***
     * 初始化Fragment
     *
     * **/
    private void initFragment() {
        mGiftPackageFragment = GiftPackageFragment.newInstance(null);
        mHotFragment = HotFragment.newInstance(null);
        mOpenServiceFragment = OpenServiceFragment.newInstance(null);
        mCharacteristicFragment = CharacteristicFragment.newInstance(null);
        fragments.add(mGiftPackageFragment);
        fragments.add(mOpenServiceFragment);
        fragments.add(mHotFragment);
        fragments.add(mCharacteristicFragment);

        //刚进入程序初始化礼包界面
        switchFragment(0);
        mRadioGroup.check(R.id.rb_gift_package);
    }

    private void initView() {
        mRadioGroup = (RadioGroup) findViewById(R.id.rg_home_button);
        iniToolBar();
        initDrawer();
    }



    /***
     *
     * 初始化ToolBar
     * */
    private void iniToolBar() {
        titles.add("礼包精灵");
        titles.add("开服");
        titles.add("热门");
        titles.add("企家独划");
        Toolbar mToolBbar = (Toolbar)findViewById(R.id.toolbar_show);
         mBtnActionBar = (Button)findViewById(R.id.btn_actionbar);
         mTvActionBar = (TextView)findViewById(R.id.tv_actionbar);
         mBtnSerCh = (Button) findViewById(R.id.btn_service_actionbar);
        setSupportActionBar(mToolBbar);
    }
    /**
     *
     * 初始化抽屉
     *
     * **/

    private void initDrawer() {
         mLayout= (DrawerLayout) findViewById(R.id.dl_home);
         mDrawerLayout= (RelativeLayout) findViewById(R.id.rl_drawer);
         openBtn= (Button) findViewById(R.id.btn_actionbar);
        mCricleView = (CircleImageView) findViewById(R.id.civ_show);
        mCricleView.setOnClickListener(this);
        openBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_actionbar:
                //打开或隐藏抽屉
                if(mLayout.isDrawerOpen(mDrawerLayout)){
                    mLayout.closeDrawer(mDrawerLayout);
                }else{
                    mLayout.openDrawer(mDrawerLayout);
                }
                break;
            case R.id.civ_show:
                //点击转跳
                Intent intent=new Intent(MainActivity.this,DrawerLoginActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_service_actionbar:
                //转跳搜索界面
                 Intent intent1=new Intent(MainActivity.this,SerchActivity.class);
                 Bundle bundle=new Bundle();
                 bundle.putString("key","武");
                 intent1.putExtra("bundle",bundle);
                 startActivity(intent1);
                break;

        }

    }

}
