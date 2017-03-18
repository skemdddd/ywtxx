package com.example.dddkj.ywtx.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dddkj.ywtx.Base.BaseFragment;

import java.util.List;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/17 8:47
 */

public class EnterStoreAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> list_fragment;                         //fragment列表
    private List<String> list_Title;                              //tab名的列表



    public EnterStoreAdapter(FragmentManager fm,List<BaseFragment> list_fragment,List<String> list_Title) {
        super(fm);
        this.list_fragment = list_fragment;
        this.list_Title = list_Title;
    }

    @Override
    public Fragment getItem(int position) {
        return list_fragment.get(position);
    }

    @Override
    public int getCount() {
        return list_Title.size();
    }

    //此方法用来显示tab上的名字
    @Override
    public CharSequence getPageTitle(int position) {

        return list_Title.get(position % list_Title.size());
    }
}
