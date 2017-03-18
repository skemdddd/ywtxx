package com.example.dddkj.ywtx.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.dddkj.ywtx.Adapter.EnterStoreAdapter;
import com.example.dddkj.ywtx.Base.BaseActivity;
import com.example.dddkj.ywtx.Base.BaseFragment;
import com.example.dddkj.ywtx.Entity.ShopPage;
import com.example.dddkj.ywtx.Fragment.ShopAllGoodsFragment;
import com.example.dddkj.ywtx.Fragment.ShopNewStoresFragment;
import com.example.dddkj.ywtx.Fragment.ShopPageFragment;
import com.example.dddkj.ywtx.Fragment.ShopSalesPromotionFragment;
import com.example.dddkj.ywtx.MyApplication.MyApplication;
import com.example.dddkj.ywtx.R;
import com.example.dddkj.ywtx.common.Constant;
import com.example.dddkj.ywtx.common.RequesURL;
import com.example.dddkj.ywtx.utils.ProgressActivity;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;
import com.orhanobut.logger.Logger;
import com.scrollablelayout.ScrollableLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 项目名称：亿我同行
 * <进入店铺>
 * 创建时间：2017/3/15 14:03
 */

public class EnterStoreActivity extends BaseActivity  implements ViewPager.OnPageChangeListener{
    Intent mIntent;
    @BindView(R.id.title_back)
    ImageView title_back;
    @BindView(R.id.ProgressActivity)
    ProgressActivity mProgressActivity;
    @BindView(R.id.shopbanner_iv)
    ImageView shopbanner_iv;
    @BindView(R.id.shoplogo_iv)
    ImageView shoplogo_iv;
    @BindView(R.id.shopNname_iv)
    TextView shopNname_iv;
    @BindView(R.id.favoritesnum_tv)
    TextView favoritesnum_tv;
    @BindView(R.id.ordernum_tv)
    TextView ordernum_tv;
    @BindView(R.id.about_store_rl)
    RelativeLayout about_store_rl;
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.vp_scroll)
    ViewPager mViewPager;
    EnterStoreAdapter mEnterStoreAdapter;
    ShopPageFragment mShopPageFragment;
    @BindView(R.id.sl_root)
    ScrollableLayout mScrollableLayout;
    ShopAllGoodsFragment mShopAllGoodsFragment;
    List<BaseFragment> list_fragment;
    ShopNewStoresFragment mShopNewStoresFragment;
    ShopSalesPromotionFragment mShopSalesPromotionFragment;


    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_enter_store);
        mIntent = getIntent();
        Constant.setShopid(mIntent.getStringExtra("shopid"));
    }

    @Override
    protected void setListener() {
        initView();
        about_store_rl.setOnClickListener(this);
        title_back.setOnClickListener(this);
    }

    public void initView() {

//        初始化Fragment
        list_fragment = new ArrayList<>();
        mShopPageFragment = new ShopPageFragment();
        mShopAllGoodsFragment =new ShopAllGoodsFragment();
        mShopNewStoresFragment = new ShopNewStoresFragment();
        mShopSalesPromotionFragment = new ShopSalesPromotionFragment();

        list_fragment.add(mShopPageFragment);
        list_fragment.add(mShopAllGoodsFragment);
        list_fragment.add(mShopNewStoresFragment);
        list_fragment.add(mShopSalesPromotionFragment);

        List<String> list_title = new ArrayList<>();
        list_title.add("店铺首页");
        list_title.add("全部商品");
        list_title.add("新品上架");
        list_title.add("店铺热销");
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        setPricelistTab(mTabLayout,EnterStoreActivity.this);
        mEnterStoreAdapter = new EnterStoreAdapter(this.getSupportFragmentManager(), list_fragment, list_title);
        mViewPager.setAdapter(mEnterStoreAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(this);
        mScrollableLayout.getHelper().setCurrentScrollableContainer(mShopPageFragment);



    }

    @Override
    protected void Request() {
        final Gson gson = new Gson();
        OkGo.post(RequesURL.SHOPFRONTPAGE)
                .tag(this)
                .params("shopid", mIntent.getStringExtra("shopid"))
                .cacheKey("cacheKey")
                .cacheMode(CacheMode.DEFAULT)
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(BaseRequest request) {
                        super.onBefore(request);
                        mProgressActivity.showLoading();
                    }

                    @Override
                    public void onAfter(String s, Exception e) {
                        super.onAfter(s, e);
                        mProgressActivity.showContent();

                        final ShopPage shopPage = gson.fromJson(s, ShopPage.class);

                        Glide.with(MyApplication.getInstance())
                                .load(RequesURL.URL + shopPage.getData().getShops().getShopbanner())
                                .asBitmap()
                                .into(new SimpleTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                        shopbanner_iv.setImageBitmap(resource);
                                    }
                                });
                        Glide.with(MyApplication.getInstance())
                                .load(RequesURL.URL + shopPage.getData().getShops().getShoplogo())
                                .asBitmap()
                                .into(new SimpleTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                        shoplogo_iv.setImageBitmap(resource);
                                    }
                                });
                        shopNname_iv.setText(shopPage.getData().getShops().getShopname());
                        favoritesnum_tv.setText(shopPage.getData().getShops().getFavoritesnum());
                        ordernum_tv.setText(shopPage.getData().getShops().getOrdernum());
                        mShopPageFragment.setShopPageCouponList(shopPage.getData().getCouponlist());
                        mShopPageFragment.setThirdGoogsDatas(shopPage.getData().getBest());



                    }


                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logger.json(s);


                    }
                });
    }

    @Override
    protected Context getActivityContext() {
        return null;
    }

    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.about_store_rl:
              Intent intent = new Intent(this,AboutStoreActivity.class);
              intent.putExtra("shopid",mIntent.getStringExtra("shopid"));
              startActivity(intent);
              break;
          case R.id.title_back:
              MyApplication.getInstance().finishActivity(EnterStoreActivity.this);
      }
    }



    /**
     * 分类
     *
     * @param tab
     */
    public static void setPricelistTab(TabLayout tab, Context context) {
        tab.addTab(tab.newTab().setIcon(context.getResources().getDrawable(R.drawable.store_home_shop_click)));
        tab.addTab(tab.newTab().setIcon(context.getResources().getDrawable(R.drawable.store_all_goods_click)));
        tab.addTab(tab.newTab().setIcon(context.getResources().getDrawable(R.drawable.store_new_arrivals_click)));
        tab.addTab(tab.newTab().setIcon(context.getResources().getDrawable(R.drawable.store_store_promotion_click)));

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

        mScrollableLayout.getHelper().setCurrentScrollableContainer(list_fragment.get(i));
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
