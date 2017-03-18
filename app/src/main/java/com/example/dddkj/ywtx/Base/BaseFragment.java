package com.example.dddkj.ywtx.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scrollablelayout.ScrollableHelper;

import butterknife.ButterKnife;

/**
 * 项目名称：亿我同行
 *
 * 创建时间：2016/9/11 9:19
 */
public abstract class BaseFragment extends Fragment implements ScrollableHelper.ScrollableContainer{

    private View mRootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = initView(inflater,container);
        ButterKnife.bind(this, mRootView);//绑定到butterknife
        return mRootView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initListener();
        initData();

    }

    protected abstract View initView(LayoutInflater inflater,ViewGroup container);
    protected abstract void initListener();
    protected abstract void initData();
}
