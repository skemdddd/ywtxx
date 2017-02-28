package com.example.dddkj.ywtx.Adapter;

import android.graphics.Bitmap;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dddkj.ywtx.Entity.SecondaryRecommendationData;
import com.example.dddkj.ywtx.R;
import com.example.dddkj.ywtx.common.RequesURL;

import java.util.List;

/**
 * 项目名称：亿我同行
 * <热门推荐>
 * 创建时间：2017/2/27 9:28
 */

public class ClassificAtiondetailsAdapter extends BaseQuickAdapter<SecondaryRecommendationData,BaseViewHolder> {
    public ClassificAtiondetailsAdapter(int layoutResId, List<SecondaryRecommendationData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, SecondaryRecommendationData item) {
        helper.setText(R.id.titleNameText,item.getGName());

        helper.setText(R.id.money_text,"￥"+item.getPrice());

        Glide.with(mContext)
                .load(RequesURL.URL+item.getPicurl())
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        helper.setImageBitmap(R.id.picture_img,resource);
                    }
                });
    }
}
