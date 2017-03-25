package com.example.dddkj.ywtx.Adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dddkj.ywtx.Entity.AreaListData;
import com.example.dddkj.ywtx.R;

import java.util.List;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/22 16:19
 */

public class AreaListAdapter extends BaseQuickAdapter<AreaListData,BaseViewHolder> {
    public AreaListAdapter(int layoutResId, List<AreaListData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AreaListData item) {
        helper.setText(R.id.area_tv,item.getAname());
    }
}
