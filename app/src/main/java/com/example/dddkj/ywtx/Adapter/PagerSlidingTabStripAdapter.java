package com.example.dddkj.ywtx.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dddkj.ywtx.Entity.ClassifySkip;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/2/27 12:35
 */

public class PagerSlidingTabStripAdapter extends FragmentPagerAdapter {
    ClassifySkip classifySkip;
    public PagerSlidingTabStripAdapter(FragmentManager fm,ClassifySkip classifySkip) {
        super(fm);
        this.classifySkip=classifySkip;
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return classifySkip.getData().size();
    }
}
