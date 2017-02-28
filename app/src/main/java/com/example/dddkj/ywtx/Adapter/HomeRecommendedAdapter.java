package com.example.dddkj.ywtx.Adapter;

import android.graphics.Bitmap;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dddkj.ywtx.Entity.HomeRecommend;
import com.example.dddkj.ywtx.R;
import com.example.dddkj.ywtx.common.RequesURL;

import java.util.List;

/**
 * 项目名称：亿我同行
 * <热门推荐>
 * 创建时间：2017/2/15 16:21
 */

public class HomeRecommendedAdapter extends BaseQuickAdapter<HomeRecommend,BaseViewHolder>{
    public HomeRecommendedAdapter(int layoutResId, List<HomeRecommend> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, final HomeRecommend recommend) {


                    Glide.with(mContext)
                            .load(RequesURL.URL+recommend.getPicurl())
                            .asBitmap()
                            .into(new SimpleTarget<Bitmap>() {
                                @Override
                                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                    baseViewHolder.setImageBitmap(R.id.recommended_img,resource);
                                }
                            });
                }






}



