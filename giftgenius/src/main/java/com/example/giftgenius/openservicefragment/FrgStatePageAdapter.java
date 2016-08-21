package com.example.giftgenius.openservicefragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by 彭永顺 on 2016/8/16.
 */
public class FrgStatePageAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragmentList;
    public FrgStatePageAdapter(FragmentManager fm,List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList=fragmentList;

    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList==null?0:fragmentList.size();
    }


}
