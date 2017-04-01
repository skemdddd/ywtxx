package com.example.dddkj.ywtx.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dddkj.ywtx.Entity.CouponListData;
import com.example.dddkj.ywtx.R;

import java.util.List;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/30 10:22
 */

public class CouponAdapter extends BaseQuickAdapter<CouponListData,BaseViewHolder> {
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    String type;
    public CouponAdapter(int layoutResId, List<CouponListData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CouponListData item) {
        helper.setText(R.id.shopname_tv,item.getShopname());
        helper.setText(R.id.jprice_tv,item.getJprice());
        helper.setText(R.id.mprice_tv,item.getMprice());
        helper.setText(R.id.sytime_tv,item.getSytime());

        ImageView pastDue_img =helper.getView(R.id.pastDue_img);
        ImageView backdrop_img =helper.getView(R.id.backdrop_img);
        TextView skip_tv=helper.getView(R.id.skip_tv);

        if(item.getSt().equals("1")){
            backdrop_img.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.coupon_bg_unclecked));
            pastDue_img.setVisibility(View.VISIBLE);
            pastDue_img.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.coupon_bg_use));
            skip_tv.setVisibility(View.GONE);

        }
        if(item.getSt().equals("2")){
            backdrop_img.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.coupon_bg_unclecked));
            pastDue_img.setVisibility(View.VISIBLE);
            skip_tv.setVisibility(View.GONE);
        }



    }
}
