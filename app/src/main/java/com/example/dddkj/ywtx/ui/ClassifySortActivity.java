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
import com.example.dddkj.ywtx.Entity.ThirdGoogs;
import com.example.dddkj.ywtx.MyApplication.MyApplication;
import com.example.dddkj.ywtx.R;
import com.example.dddkj.ywtx.Widget.MyScrollview;
import com.example.dddkj.ywtx.Widget.Titlebar;
import com.example.dddkj.ywtx.common.Constant;
import com.example.dddkj.ywtx.common.RequesURL;
import com.example.dddkj.ywtx.utils.ProgressActivity;
import com.example.dddkj.ywtx.utils.VerticalSpaceItemDecoration;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;


/**
 * 项目名称：亿我同行
 * <分类排序>
 * 创建时间：2017/3/6 14:57
 */

public class ClassifySortActivity extends BaseActivity implements MyScrollview.OnScrollListener,TabLayout.OnTabSelectedListener{
    @BindView(R.id.pricelist)
    TabLayout mPricelistTab;
    @BindView(R.id.goods)
    RecyclerView mGoodsList;
    @BindView(R.id.progressrv)
    ProgressActivity mProgressActivityrv;
    TertiaryDetailsAdapter mTertiaryDetailsAdapter;
    @BindView(R.id.titlebar)
    Titlebar mTitlebar;
    Intent intent;
    @BindView(R.id.search01)
    LinearLayout search01;
    @BindView(R.id.search02)
    RelativeLayout search02;
    @BindView(R.id.MyScrollview)
    MyScrollview mMyScrollview;
    boolean click = false;
    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_classify_sort);
        intent = getIntent();


    }

    @Override
    protected void setListener() {
        initView();
        mMyScrollview.setOnScrollListener(this);
        mPricelistTab.addOnTabSelectedListener(this);
        mTitlebar.setOnTitleBarClickListener(new Titlebar.TitleBarClickListener() {
            @Override
            public void onim() {

            }

            @Override
            public void Onseek() {

            }

            @Override
            public void Onback() {
                MyApplication.getInstance().finishActivity(ClassifySortActivity.this);
            }
        });


    }

    //    初始化控件
    public void initView() {
        Constant.setPricelistTab(mPricelistTab);
        mTitlebar.setText(intent.getStringExtra("title"));
        mGoodsList.setLayoutManager(new GridLayoutManager(getActivityContext(), 2));
        mGoodsList.setHasFixedSize(true);
        mTertiaryDetailsAdapter = new TertiaryDetailsAdapter(R.layout.item_home_popularity, null);
        mGoodsList.setAdapter(mTertiaryDetailsAdapter);
        mGoodsList.addItemDecoration(new VerticalSpaceItemDecoration(20));

    }

    @Override
    protected void Request() {
        Submit();
    }



    public  void  Submit(){
        final Gson gson = new Gson();
        OkGo.post(RequesURL.CLASSIFICATIONGOODS)
                .tag(this)
                .params("catsid", intent.getStringExtra("id"))
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
                                    MyApplication.getInstance().finishActivity(ClassifySortActivity.this);
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
     *
     * 分类排行；
     *
     * @param tab
     *
     * @param type
     */
    public void SubmitCommodityOrdering( TabLayout.Tab tab, String type) {
        final Gson gson = new Gson();
        OkGo.post(RequesURL.CLASSIFICATIONGOODS)
                .tag(this)
                .params("catsid",intent.getStringExtra("id") )
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
                                    MyApplication.getInstance().finishActivity(ClassifySortActivity.this);
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

    }
    //界面 悬停
    @Override
    public void onScroll(int scrollY) {
        if (scrollY >= mPricelistTab.getBottom()) {
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

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
            case 0:
                Submit();
//                Submitgoods(secondaryReclassify, tab, posid[0]);
                break;
            case 1:
                SubmitCommodityOrdering(tab, Constant.SALESVOLUME);

                break;
            case 2:
                SubmitCommodityOrdering(tab, Constant.NEWGOOGS);

                break;
            case 3:
                SubmitCommodityOrdering(tab, Constant.HIGHPRICE);
                click = true;
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
                    SubmitCommodityOrdering(tab, Constant.HIGHPRICE);
                    click = true;
                } else {
                    SubmitCommodityOrdering(tab, Constant.LOWPRICE);
                    click = false;
                }
                break;
        }
    }
}
