package com.example.dddkj.ywtx.Adapter;

import android.graphics.Bitmap;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dddkj.ywtx.Entity.MyClassifySection;
import com.example.dddkj.ywtx.R;
import com.example.dddkj.ywtx.common.RequesURL;

import java.util.List;

/**
 * 项目名称：亿我同行
 * <右侧导航>
 * 创建时间：2017/2/20 10:50
 */

public class ClassifyRigetAdapter extends BaseSectionQuickAdapter<MyClassifySection, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public ClassifyRigetAdapter(int layoutResId, int sectionHeadResId,List<MyClassifySection> data) {
        super(layoutResId, sectionHeadResId,data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, MyClassifySection item) {
        helper.setText(R.id.heard_text,item.header);
    }

    @Override
    protected void convert(final BaseViewHolder helper, MyClassifySection item) {
    helper.setText(R.id.classifyRight_tv,item.t.getCatsName());
        Glide.with(mContext)
                .load(RequesURL.URL+item.t.getCatsPic())
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                        Bitmap mBitmap = Bitmap.createScaledBitmap(resource, baseViewHolder.getView(R.id.popularity_img).getWidth(), baseViewHolder.getView(R.id.popularity_img).getHeight(), true);
                        helper.setImageBitmap(R.id.riget_img,resource);

                    }
                });

    }

}



