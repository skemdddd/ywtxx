package com.example.dddkj.ywtx.ui;


import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dddkj.ywtx.Base.BaseActivity;
import com.example.dddkj.ywtx.Fragment.CouponListFragment;
import com.example.dddkj.ywtx.MyApplication.MyApplication;
import com.example.dddkj.ywtx.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/30 9:04
 */

public class MyCoupons extends BaseActivity {
    @BindView(R.id.TabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.history_tv)
    TextView history_tv;
    @BindView(R.id.title_back)
    ImageView title_back;
    TabAdaper mAdapter;


    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_my_coupons);
    }

    @Override
    protected void setListener() {
        initView();
        history_tv.setOnClickListener(this);

    }


    protected void initView() {
        mAdapter = new TabAdaper(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        title_back.setOnClickListener(this);

    }

    @Override
    protected void Request() {

    }

    @Override
    protected Context getActivityContext() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.history_tv:
                Intent intent = new Intent(this,CouponHistoryActivity.class);
                startActivity(intent);
                break;
            case R.id.title_back:
                MyApplication.getInstance().finishActivity(MyCoupons.this);
                break;
        }
    }
    class TabAdaper extends FragmentPagerAdapter {

        List<Fragment> fragmentList = new ArrayList<>();
        // 标题数组
        String[] titles = {"平台", "店铺"};

        public TabAdaper(FragmentManager fm) {
            super(fm);
            fragmentList.add(new CouponListFragment("","1"));
            fragmentList.add(new CouponListFragment("1","2"));
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {

            return fragmentList.size();
        }
    }


}
