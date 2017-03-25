package com.example.dddkj.ywtx.ui;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.dddkj.ywtx.Adapter.TertiaryDetailsAdapter;
import com.example.dddkj.ywtx.Base.BaseActivity;
import com.example.dddkj.ywtx.Entity.ThirdGoogs;
import com.example.dddkj.ywtx.Entity.ThirdGoogsData;
import com.example.dddkj.ywtx.MyApplication.MyApplication;
import com.example.dddkj.ywtx.R;
import com.example.dddkj.ywtx.Widget.MySuspensionScrollview;
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
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/21 10:17
 */

public class SearchGoodsListActivity  extends BaseActivity implements MySuspensionScrollview.OnScrollListener,TabLayout.OnTabSelectedListener{
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
    MySuspensionScrollview mMyScrollview;
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
                MyApplication.getInstance().finishActivity(SearchGoodsListActivity.this);
            }

            @Override
            public void Onback() {
                MyApplication.getInstance().finishActivity(SearchGoodsListActivity.this);
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
        mGoodsList.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(SearchGoodsListActivity.this, MerchandiseNewsActivity.class);
                ThirdGoogsData thirdGoogsData = (ThirdGoogsData)adapter.getData().get(position);
                intent.putExtra("goodsid",thirdGoogsData.getGId());
                startActivity(intent);
            }
        });




    }
    @Override
    protected void Request() {
        Submit();
    }



    public  void  Submit(){
        final Gson gson = new Gson();
        OkGo.post(RequesURL.PLATFORMGOODSSTORE)
                .tag(this)
                .params("shopid", intent.getStringExtra("shopid"))
                .params("key",intent.getStringExtra("key"))
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
                        Logger.i("key   "+ intent.getStringExtra("key"));
                        mProgressActivityrv.showContent();
                        ThirdGoogs thirdGoogs = gson.fromJson(s, ThirdGoogs.class);
                        mTertiaryDetailsAdapter.setNewData(thirdGoogs.getData());
                        if (thirdGoogs.getData().size() == 0) {
                            mProgressActivityrv.showEmpty(getResources().getDrawable(R.mipmap.ic_empty_page), "", "咦...没有任何内容，先去逛逛别的吧", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    MyApplication.getInstance().finishActivity(SearchGoodsListActivity.this);
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
        OkGo.post(RequesURL.PLATFORMGOODSSTORE)
                .tag(this)
                .params("shopid",intent.getStringExtra("shopid") )
                .params("flag", type)
                .params("key",intent.getStringExtra("key"))
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
                                    MyApplication.getInstance().finishActivity(SearchGoodsListActivity.this);
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
