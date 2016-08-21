package com.example.giftgenius.giftpackagefragment.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.giftgenius.R;
import com.example.giftgenius.uitls.ImageLoader;

import java.util.List;

/**
 * Created by 彭永顺 on 2016/8/6.
 */
public class HeaderViewAdapter extends PagerAdapter {
    private List<String> ImageUrls;
    private Context context;

    public HeaderViewAdapter(List<String> ImageUrls,Context context) {
        this.ImageUrls = ImageUrls;
        this.context=context;
    }

    @Override
    public int getCount() {
        return ImageUrls==null?0:ImageUrls.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LinearLayout.inflate(context, R.layout.vp_header_view_one, null);
        ImageView ivHeaderView = (ImageView) view.findViewById(R.id.iv_gifts_header_photo_one);
        ImageLoader.init(context).load(ImageUrls.get(position),ivHeaderView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
