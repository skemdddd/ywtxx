package com.example.dddkj.ywtx.Adapter;

import android.graphics.Bitmap;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dddkj.ywtx.Entity.HomePopularity;
import com.example.dddkj.ywtx.R;
import com.example.dddkj.ywtx.common.RequesURL;

import java.util.List;

/**
 * 项目名称：亿我同行
 * <人气商品>
 * 创建时间：2017/2/16 9:20
 */

public class HomePopularityAdapter extends BaseQuickAdapter<HomePopularity,BaseViewHolder> {
    public HomePopularityAdapter(int layoutResId, List<HomePopularity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, HomePopularity popularity) {
        Glide.with(mContext)
                .load(RequesURL.URL+popularity.getPicurl())
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        Bitmap mBitmap = Bitmap.createScaledBitmap(resource, baseViewHolder.getView(R.id.popularity_img).getWidth(), baseViewHolder.getView(R.id.popularity_img).getHeight(), true);
                        baseViewHolder.setImageBitmap(R.id.popularity_img,resource);

                    }
                });
        baseViewHolder.setText(R.id.nameText,popularity.getGName());
        baseViewHolder.setText(R.id.moneyText,"￥"+popularity.getPrice());

    }
}
