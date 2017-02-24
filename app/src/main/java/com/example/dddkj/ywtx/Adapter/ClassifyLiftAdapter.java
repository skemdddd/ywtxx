package com.example.dddkj.ywtx.Adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dddkj.ywtx.Entity.ClassifyLift;
import com.example.dddkj.ywtx.R;

import java.util.List;

/**
 * 项目名称：亿我同行
 * <分类左侧导航>
 * 创建时间：2017/2/20 10:50
 */

public class ClassifyLiftAdapter extends BaseQuickAdapter<ClassifyLift,BaseViewHolder> {
    public ClassifyLiftAdapter(int layoutResId, List<ClassifyLift> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert( BaseViewHolder baseViewHolder, ClassifyLift classifyData) {
        baseViewHolder.setText(R.id.classifyLift_tv,classifyData.getCatsName());

    }
}
