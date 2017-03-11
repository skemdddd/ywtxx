package com.example.dddkj.ywtx.ui;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.dddkj.ywtx.Adapter.TertiaryDetailsAdapter;
import com.example.dddkj.ywtx.Base.BaseActivity;
import com.example.dddkj.ywtx.Entity.SecondaryReclassify;
import com.example.dddkj.ywtx.Entity.ThirdGoogs;
import com.example.dddkj.ywtx.MyApplication.MyApplication;
import com.example.dddkj.ywtx.R;
import com.example.dddkj.ywtx.Widget.MyScrollview;
import com.example.dddkj.ywtx.Widget.NullStringToEmptyAdapterFactory;
import com.example.dddkj.ywtx.Widget.Titlebar;
import com.example.dddkj.ywtx.common.Constant;
import com.example.dddkj.ywtx.common.RequesURL;
import com.example.dddkj.ywtx.utils.ProgressActivity;
import com.example.dddkj.ywtx.utils.VerticalSpaceItemDecoration;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/3 9:48
 */

public class TertiaryDetailsActivity extends BaseActivity implements MyScrollview.OnScrollListener {
    @BindView(R.id.thirdcategory)
    TabLayout mThirdCategoryTab;
    @BindView(R.id.pricelist)
    TabLayout mPricelistTab;
    @BindView(R.id.goods)
    RecyclerView mGoodsList;
    @BindView(R.id.progress)
    ProgressActivity mProgressActivity;
    @BindView(R.id.progressrv)
    ProgressActivity mProgressActivityrv;
    @BindView(R.id.MyScrollview)
    MyScrollview mMyScrollview;
    @BindView(R.id.titlebar)
    Titlebar mTitlebar;
    @BindView(R.id.search01)
    LinearLayout search01;
    @BindView(R.id.search02)
    RelativeLayout search02;
    String typeid;
    TertiaryDetailsAdapter mTertiaryDetailsAdapter;
    boolean click = false;


    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_classification_details);
        Intent intent = getIntent();
        typeid = intent.getStringExtra("id");

    }

    @Override
    protected void setListener() {
        initView();
        mMyScrollview.setOnScrollListener(this);
        mTitlebar.setOnTitleBarClickListener(new Titlebar.TitleBarClickListener() {
            @Override
            public void onim() {

            }

            @Override
            public void Onseek() {

            }

            @Override
            public void Onback() {
                MyApplication.getInstance().finishActivity(TertiaryDetailsActivity.this);
            }
        });


    }

    //    初始化控件
    public void initView() {
        mGoodsList.setLayoutManager(new GridLayoutManager(getActivityContext(), 2));
        mGoodsList.setHasFixedSize(true);
        mTertiaryDetailsAdapter = new TertiaryDetailsAdapter(R.layout.item_home_popularity, null);
        mGoodsList.setAdapter(mTertiaryDetailsAdapter);
        mGoodsList.addItemDecoration(new VerticalSpaceItemDecoration(20));
    }

    @Override
    protected void Request() {
//        三级分类
        final Gson gson = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory<>()).create();

        OkGo.post(RequesURL.RECLASSIFY)
                .tag(this)
                .params("typeid", typeid)
                .cacheKey("cacheKey")
                .cacheMode(CacheMode.DEFAULT)
                .execute(new StringCallback() {
                    @Override
                    public void onAfter(String s, Exception e) {
                        super.onAfter(s, e);
                        Logger.json(s);
                        mProgressActivity.showContent();
                        final int[] sort = {0};
                        final SecondaryReclassify secondaryReclassify = gson.fromJson(s, SecondaryReclassify.class);
                        for (int i = 0; i < secondaryReclassify.getData().size(); i++) {
                            mThirdCategoryTab.addTab(mThirdCategoryTab.newTab().setText(secondaryReclassify.getData().get(i).getCatsName()));
                        }
                        mTitlebar.setText(secondaryReclassify.getData().get(0).getCatsName());
//                        顶部导航
                        final int[] posid = {0};
                        mThirdCategoryTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                            @Override
                            public void onTabSelected(TabLayout.Tab tab) {
                                posid[0] = tab.getPosition();
                                mTitlebar.setText(secondaryReclassify.getData().get(tab.getPosition()).getCatsName());
                                if (sort[0] == 0) {
                                    Submitgoods(secondaryReclassify, tab, posid[0]);
                                } else {
                                    switch (sort[0]) {
                                        case 1:
                                            SubmitCommodityOrdering(secondaryReclassify, tab, posid[0], Constant.SALESVOLUME);
                                            break;
                                        case 2:
                                            SubmitCommodityOrdering(secondaryReclassify, tab, posid[0], Constant.NEWGOOGS);
                                            break;
                                        case 3:
                                            SubmitCommodityOrdering(secondaryReclassify, tab, posid[0], Constant.HIGHPRICE);
                                            break;

                                    }
                                }
                            }

                            @Override
                            public void onTabUnselected(TabLayout.Tab tab) {

                            }

                            @Override
                            public void onTabReselected(TabLayout.Tab tab) {

                            }
                        });
//                        排序导航
                        mPricelistTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                            @Override
                            public void onTabSelected(TabLayout.Tab tab) {
                                switch (tab.getPosition()) {
                                    case 0:
                                        Submitgoods(secondaryReclassify, tab, posid[0]);
                                        break;
                                    case 1:
                                        SubmitCommodityOrdering(secondaryReclassify, tab, posid[0], Constant.SALESVOLUME);
                                        sort[0] = 1;
                                        break;
                                    case 2:
                                        SubmitCommodityOrdering(secondaryReclassify, tab, posid[0], Constant.NEWGOOGS);
                                        sort[0] = 2;
                                        break;
                                    case 3:
                                        SubmitCommodityOrdering(secondaryReclassify, tab, posid[0], Constant.HIGHPRICE);
                                        click = false;
                                        sort[0] = 3;
                                        break;

                                }
                            }

                            @Override
                            public void onTabUnselected(TabLayout.Tab tab) {

                            }

                            @Override
                            public void onTabReselected(TabLayout.Tab tab) {
                                switch (tab.getPosition()) {
                                    case 3:
                                        if (!click) {
                                            SubmitCommodityOrdering(secondaryReclassify, tab, posid[0], Constant.LOWPRICE);
                                            click = true;
                                        } else {
                                            SubmitCommodityOrdering(secondaryReclassify, tab, posid[0], Constant.HIGHPRICE);
                                            click = false;
                                        }

                                        break;

                                }
                            }
                        });
                        Constant.setPricelistTab(mPricelistTab);
                    }

                    @Override
                    public void onBefore(BaseRequest request) {
                        super.onBefore(request);
                        mProgressActivity.showLoading();


                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                    }
                });
    }

    /**
     * 分类下商品
     *
     * @param secondaryReclassify
     * @param tab
     */
    public void Submitgoods(SecondaryReclassify secondaryReclassify, TabLayout.Tab tab, int pos) {
        final Gson gson = new Gson();
        OkGo.post(RequesURL.CLASSIFICATIONGOODS)
                .tag(this)
                .params("catsid", secondaryReclassify.getData().get(pos).getCatsId())
                .cacheKey("cacheKey")
                .cacheMode(CacheMode.DEFAULT)
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(BaseRequest request) {
                        super.onBefore(request);
                        mProgressActivityrv.showLoading();
                    }

                    @Override
                    public void onAfter(String s, Exception e) {
                        super.onAfter(s, e);
                        mProgressActivityrv.showContent();
                        ThirdGoogs thirdGoogs = gson.fromJson(s, ThirdGoogs.class);
                        mTertiaryDetailsAdapter.setNewData(thirdGoogs.getData());
                        if (thirdGoogs.getData().size() == 0) {
                            mProgressActivityrv.showEmpty(getResources().getDrawable(R.mipmap.ic_empty_page), "", "咦...没有任何内容，先去逛逛别的吧", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    MyApplication.getInstance().finishActivity(TertiaryDetailsActivity.this);
                                }
                            });

                        }
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                    }
                });


    }


    /**
     * 商品排序
     *
     * @param secondaryReclassify
     * @param tab
     * @param pos
     * @param type
     */
    public void SubmitCommodityOrdering(SecondaryReclassify secondaryReclassify, TabLayout.Tab tab, int pos, String type) {
        final Gson gson = new Gson();
        OkGo.post(RequesURL.CLASSIFICATIONGOODS)
                .tag(this)
                .params("catsid", secondaryReclassify.getData().get(pos).getCatsId())
                .params("flag", type)
                .cacheKey("cacheKey")
                .cacheMode(CacheMode.DEFAULT)
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(BaseRequest request) {
                        super.onBefore(request);
                        mProgressActivityrv.showLoading();
                    }

                    @Override
                    public void onAfter(String s, Exception e) {
                        super.onAfter(s, e);
                        mProgressActivityrv.showContent();
                        ThirdGoogs thirdGoogs = gson.fromJson(s, ThirdGoogs.class);
                        mTertiaryDetailsAdapter.setNewData(thirdGoogs.getData());
                        if (thirdGoogs.getData().size() == 0) {
                            mProgressActivityrv.showEmpty(getResources().getDrawable(R.mipmap.ic_empty_page), "", "咦...没有任何内容，先去逛逛别的吧", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    MyApplication.getInstance().finishActivity(TertiaryDetailsActivity.this);
                                }
                            });

                        }
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                    }
                });


    }

    @Override
    protected Context getActivityContext() {
        return null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }


    //界面 悬停
    @Override
    public void onScroll(int scrollY) {
        if (scrollY >= search02.getBottom()) {
            if (mPricelistTab.getParent() != search01) {
                search02.removeView(mPricelistTab);
                search01.addView(mPricelistTab);
            }
        } else {
            if (mPricelistTab.getParent() != search02) {
                search01.removeView(mPricelistTab);
                search02.addView(mPricelistTab);
            }
        }
    }
}
