package com.example.giftgenius.characteristicfragment.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.giftgenius.R;
import com.example.giftgenius.openservicefragment.FrgStatePageAdapter;
import com.example.giftgenius.openservicefragment.fragment.OpenCeChildFragment;
import com.example.giftgenius.openservicefragment.fragment.OpenServiceChildFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 彭永顺 on 2016/8/5.
 */
public class CharacteristicFragment extends Fragment {
    public static final String URL_JSON="url";
    private List<Fragment> fragments=new ArrayList<>();
    private FrgStatePageAdapter mAdapter;
    private ViewPager mViewPager;
    private RadioGroup mRadioGroup;
    private int childCount;
    private Context context;
    private BDChildFragment bDChildFragment;
    private XYChildFragment xYChildFragment;
    private View mBDView;
    private View mXYView;

    public static CharacteristicFragment newInstance(String url){
        CharacteristicFragment fragment=new CharacteristicFragment();
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
        View view=inflater.inflate(R.layout.fragment_characteristic,container,false);
        initView(view);
        initFragment();
        initAdapter();
        iniListener();
        return view;
    }
    private void iniListener() {
        for (int i = 0; i < childCount; i++) {
            RadioButton view1 = (RadioButton) mRadioGroup.getChildAt(i);
            view1.setChecked(false);
        }
        RadioButton curView = (RadioButton) mRadioGroup.getChildAt(0);
        curView.setChecked(true);
        mViewPager.setCurrentItem(0);
        mBDView.setVisibility(View.VISIBLE);
        mXYView.setVisibility(View.INVISIBLE);


        //对RadioGroup监听
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_characterisitic_fragment_title_bd:
                        //切换到开服的Fragment
                        mViewPager.setCurrentItem(0);
                        mBDView.setVisibility(View.VISIBLE);
                        mXYView.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.rb_characterisitic_fragment_title_xy:
                        //切换到开测的fragment中
                        mViewPager.setCurrentItem(1);
                        mBDView.setVisibility(View.INVISIBLE);
                        mXYView.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
        //对ViewPage监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < childCount; i++) {
                    RadioButton childAt = (RadioButton) mRadioGroup.getChildAt(i);
                    childAt.setChecked(false);
                }
                RadioButton view = (RadioButton) mRadioGroup.getChildAt(position);
                view.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void initAdapter() {
        if ((fragments==null)){
            return;
        }
        mAdapter = new FrgStatePageAdapter(getChildFragmentManager(),fragments);
        mViewPager.setAdapter(mAdapter);

    }

    private void initFragment() {
        bDChildFragment= BDChildFragment.newTnastance();
        xYChildFragment= XYChildFragment.newTnastance();
        fragments.add(bDChildFragment);
        fragments.add(xYChildFragment);
    }

    private void initView(View view) {
        mViewPager= (ViewPager) view.findViewById(R.id.vp_characterisitic_fraggment);
        mRadioGroup = (RadioGroup) view.findViewById(R.id.rg_characterisitic_fragment_title);
        mBDView = view.findViewById(R.id.inditor_characterisitic_bd);
        mXYView = view.findViewById(R.id.inditor_characterisitic_xy);
        childCount = mRadioGroup.getChildCount();

    }
}
