package com.example.dddkj.ywtx.Entity;

import com.chad.library.adapter.base.BaseQuickAdapter;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/2/22 15:43
 */

public class  AdapterEntity {
    public BaseQuickAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(BaseQuickAdapter adapter) {
        mAdapter = adapter;
    }

    private BaseQuickAdapter mAdapter;
}
