package com.example.dddkj.ywtx.Adapter;

import android.graphics.Bitmap;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dddkj.ywtx.Entity.ThirdGoogsData;
import com.example.dddkj.ywtx.R;
import com.example.dddkj.ywtx.common.RequesURL;

import java.util.List;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/3 16:46
 */

public class TertiaryDetailsAdapter extends BaseQuickAdapter<ThirdGoogsData,BaseViewHolder> {
    public TertiaryDetailsAdapter(int layoutResId, List<ThirdGoogsData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, ThirdGoogsData item) {
        Glide.with(mContext)
                .load(RequesURL.URL+item.getPicurl())
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        helper.setImageBitmap(R.id.popularity_img,resource);

                    }
                });
        helper.setText(R.id.nameText,item.getGName());
        helper.setText(R.id.moneyText,"￥"+item.getPrice());
        helper.setText(R.id.numberpeoText,item.getOrderNum()+"人付款");

    }

}
