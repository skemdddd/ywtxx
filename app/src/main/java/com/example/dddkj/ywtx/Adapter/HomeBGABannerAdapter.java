package com.example.dddkj.ywtx.Adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * 项目名称：亿我同行
 * <轮播图>
 * 创建时间：2017/2/10 15:40
 */

public class HomeBGABannerAdapter implements BGABanner.Adapter<ImageView,String> {
    private Context context;

    public HomeBGABannerAdapter(Context context) {
        this.context = context;
    }


    @Override
    public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
        Glide.with(context)
                .load(model)
                .crossFade()
                .into(itemView);
    }
}
