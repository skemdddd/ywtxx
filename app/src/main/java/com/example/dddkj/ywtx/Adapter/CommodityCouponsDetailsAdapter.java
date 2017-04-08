package com.example.dddkj.ywtx.Adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dddkj.ywtx.Entity.ShopPageCouponList;
import com.example.dddkj.ywtx.R;

import java.util.List;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/4/7 8:26
 */

public class CommodityCouponsDetailsAdapter extends BaseQuickAdapter<ShopPageCouponList,BaseViewHolder> {
    public CommodityCouponsDetailsAdapter(int layoutResId, List<ShopPageCouponList> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopPageCouponList item) {
        helper.setText(R.id.CouponAmount_tv,"￥"+item.getJprice());
        helper.setText(R.id.jprice_tv,item.getMprice());
        helper.setText(R.id.time_tv,item.getSytime());

    }
}
