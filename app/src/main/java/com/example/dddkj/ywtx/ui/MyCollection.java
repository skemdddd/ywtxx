package com.example.dddkj.ywtx.ui;


import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;

import com.example.dddkj.ywtx.Base.BaseActivity;
import com.example.dddkj.ywtx.Fragment.MyCollectionFragment;
import com.example.dddkj.ywtx.MyApplication.MyApplication;
import com.example.dddkj.ywtx.R;

import butterknife.BindView;

/**
 * 项目名称：亿我同行
 * <我的收藏>
 * 创建时间：2017/3/23 14:23
 */

public class MyCollection extends BaseActivity {
    @BindView(R.id.TabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.title_back)
    ImageView title_back;
    Fragment FavoritesGoods;
    Fragment FavoritesShops;
    FragmentManager fragmentManager;


    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_my_collection);
        fragmentManager = getSupportFragmentManager();
    }

    @Override
    protected void setListener() {
        initView();
        title_back.setOnClickListener(this);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                selectFragment(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    private void selectFragment(int checkedId) {
        FragmentTransaction fragmentTransaction =  fragmentManager.beginTransaction();
        hideFragment(fragmentTransaction);
        switch (checkedId){
            case 0:

                if (null == FavoritesGoods) {
                    FavoritesGoods = MyCollectionFragment.newInstance("goods");
                    fragmentTransaction.add(R.id.hometab_context, FavoritesGoods);
                } else {
                    fragmentTransaction.show(FavoritesGoods);
                }
                break;
            case 1:
                if (null == FavoritesShops) {
                    FavoritesShops = MyCollectionFragment.newInstance("shop");
                    fragmentTransaction.add(R.id.hometab_context, FavoritesShops);
                } else {
                    fragmentTransaction.show(FavoritesShops);
                }
                break;
        }
        fragmentTransaction.commit();
    }
    protected  void initView(){
        mTabLayout.addTab(mTabLayout.newTab().setText("商品"));
        mTabLayout.addTab(mTabLayout.newTab().setText("店铺"));
        selectFragment(0);

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
            case R.id.title_back:
                MyApplication.getInstance().finishActivity(this);
        }

    }
    private void hideFragment(FragmentTransaction fragmentTransaction) {
        if(null != FavoritesGoods){
            fragmentTransaction.hide(FavoritesGoods);
        }
        if(null != FavoritesShops){
            fragmentTransaction.hide(FavoritesShops);
        }


    }

}
