package com.example.dddkj.ywtx.Adapter;


import android.graphics.Bitmap;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dddkj.ywtx.Entity.FavoritesShopsData;
import com.example.dddkj.ywtx.MyApplication.MyApplication;
import com.example.dddkj.ywtx.R;
import com.example.dddkj.ywtx.common.RequesURL;

import java.util.List;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/25 15:13
 */

public class FavoritesShopsAdapter extends BaseQuickAdapter<FavoritesShopsData,BaseViewHolder> {


    public FavoritesShopsAdapter(int layoutResId, List<FavoritesShopsData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, FavoritesShopsData item) {
        Glide.with(MyApplication.getInstance())
                .load(RequesURL.URL +item.getShoplogo())
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        helper.setImageBitmap(R.id.shoplogo_iv,resource);
                    }
                });
        helper.setText(R.id.shopNname_iv,item.getShopname());
        helper.setText(R.id.favoritesnum_tv,item.getFavoritesnum());
        helper.setText(R.id.ordernum_tv,item.getOrdernum());


    }
}
