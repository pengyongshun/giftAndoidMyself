package com.example.giftgenius.openservicefragment.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.giftgenius.R;
import com.example.giftgenius.openservicefragment.FrgStatePageAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 彭永顺 on 2016/8/5.
 */
public class OpenServiceFragment extends Fragment{
    public static final String URL_JSON="url";
    private Context context;
    private List<Fragment> fragments=new ArrayList<>();
    private FrgStatePageAdapter mAdapter;
    private ViewPager mViewPager;
    private RadioGroup mRadioGroup;
    private OpenServiceChildFragment openServiceChildFragment;
    private OpenCeChildFragment openCeChildFragment;

    private View mOpenserviceView;
    private View mOpenceView;
    private int childCount;

    public static OpenServiceFragment newInstance(String url){
        OpenServiceFragment fragment=new OpenServiceFragment();
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
        View view=inflater.inflate(R.layout.fragment_open_service,container,false);
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
        mOpenserviceView.setVisibility(View.VISIBLE);
        mOpenceView.setVisibility(View.INVISIBLE);


        //对RadioGroup监听
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_openservice_fragment_title_openservice:
                        //切换到开服的Fragment
                        mViewPager.setCurrentItem(0);
                        mOpenserviceView.setVisibility(View.VISIBLE);
                        mOpenceView.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.rb_openservice_fragment_title_opence:
                        //切换到开测的fragment中
                        mViewPager.setCurrentItem(1);
                        mOpenserviceView.setVisibility(View.INVISIBLE);
                        mOpenceView.setVisibility(View.VISIBLE);
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
        openServiceChildFragment=OpenServiceChildFragment.newTnastance();
        openCeChildFragment=OpenCeChildFragment.newTnastance().newTnastance();
        fragments.add(openServiceChildFragment);
        fragments.add(openCeChildFragment);
    }

    private void initView(View view) {
        mViewPager= (ViewPager) view.findViewById(R.id.vp_openservice_fraggment);
        mRadioGroup = (RadioGroup) view.findViewById(R.id.rg_openservice_fragment_title);
        mOpenserviceView = view.findViewById(R.id.inditor_openservice);
        mOpenceView = view.findViewById(R.id.inditor_opence);
        childCount = mRadioGroup.getChildCount();


    }

}
