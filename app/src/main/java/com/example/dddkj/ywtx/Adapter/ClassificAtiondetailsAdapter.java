package com.example.dddkj.ywtx.Adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dddkj.ywtx.Entity.ClassifyR;
import com.example.dddkj.ywtx.R;

import java.util.List;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/2/27 9:28
 */

public class ClassificAtiondetailsAdapter extends BaseQuickAdapter<ClassifyR,BaseViewHolder> {
    public ClassificAtiondetailsAdapter(int layoutResId, List<ClassifyR> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ClassifyR item) {
        helper.setText(R.id.titleNameText,item.getCatsName());

//        Glide.with(mContext)
//                .load(RequesURL.URL+mDatas.getData().get(pos).getPicURL())
//                .asBitmap()
//                .into(new SimpleTarget<Bitmap>() {
//                    @Override
//                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                        viewHolder.iv.setImageBitmap(resource);
//                    }
//                });
    }
}
