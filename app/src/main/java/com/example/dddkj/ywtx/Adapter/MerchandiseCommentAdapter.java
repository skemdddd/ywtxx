package com.example.dddkj.ywtx.Adapter;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dddkj.ywtx.Entity.GoogsNewspicList;
import com.example.dddkj.ywtx.R;
import com.example.dddkj.ywtx.common.RequesURL;
import com.example.dddkj.ywtx.utils.T;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/8 10:49
 */

public class MerchandiseCommentAdapter extends BaseQuickAdapter<GoogsNewspicList,BaseViewHolder> {
    public MerchandiseCommentAdapter(int layoutResId, List<GoogsNewspicList> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoogsNewspicList item) {
        helper.setText(R.id.username_tv,item.getUName());
        helper.setText(R.id.time_tv,item.getPubtime());
        helper.setText(R.id.usercomment_tv,item.getDescription());
        helper.setText(R.id.commodity_property_tv,item.getAttrName());
        final CircleImageView circleImageView=helper.getView(R.id.userhead_image);
        Glide.with(mContext)
                .load(RequesURL.URL+item.getULogo())
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        circleImageView.setImageBitmap(resource);
                    }
                });
        GridView gridView =helper.getView(R.id.review_images_gv);
        gridView.setAdapter(new MerchandiseCommodityPropertyGVAdapter(mContext,item.getString()));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                T.showShort(mContext,"点击"+i);
            }
        });

    }


}
