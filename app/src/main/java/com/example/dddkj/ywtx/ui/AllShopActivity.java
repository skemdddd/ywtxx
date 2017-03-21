package com.example.dddkj.ywtx.ui;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.dddkj.ywtx.Adapter.TertiaryDetailsAdapter;
import com.example.dddkj.ywtx.Base.BaseActivity;
import com.example.dddkj.ywtx.Entity.ShopAll;
import com.example.dddkj.ywtx.MyApplication.MyApplication;
import com.example.dddkj.ywtx.R;
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
 * <全部商品>
 * 创建时间：2017/3/18 14:04
 */

public class AllShopActivity  extends BaseActivity{
    @BindView(R.id.TabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.allgoods_rv)
    RecyclerView AllGoodsList;
    @BindView(R.id.ProgressActivity)
    ProgressActivity mProgressActivity;
    TertiaryDetailsAdapter mTertiaryDetailsAdapter;
    @BindView(R.id.Titlebar)
    Titlebar mTitlebar;
    boolean click = false;
    Intent mIntent;
    @Override
    protected void loadViewLayout() {
       setContentView(R.layout.activity_all_shop_goods);
        mIntent= getIntent();

    }
    protected void initView(){
        AllGoodsList.setOverScrollMode(View.OVER_SCROLL_NEVER);
        AllGoodsList.setLayoutManager(new GridLayoutManager(MyApplication.getInstance(), 2));
        AllGoodsList.setHasFixedSize(true);
        mTertiaryDetailsAdapter = new TertiaryDetailsAdapter(R.layout.item_home_popularity, null);
        AllGoodsList.setAdapter(mTertiaryDetailsAdapter);
        AllGoodsList.addItemDecoration(new VerticalSpaceItemDecoration(20,0));
        mTitlebar.setText(mIntent.getStringExtra("title"));
        Constant.setPricelistTab(mTabLayout);
        Submitgoods("");

    }
    @Override
    protected void setListener() {
        initView();
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        Submitgoods("");
                        break;
                    case 1:
                        Submitgoods(Constant.SALESVOLUME);
                        break;
                    case 2:
                        Submitgoods(Constant.NEWGOOGS);
                        break;
                    case 3:
                        Submitgoods(Constant.HIGHPRICE);
                        click = true;
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 3:
                        if(click){
                            Submitgoods(Constant.LOWPRICE);
                            click = false;
                        }else {
                            Submitgoods(Constant.LOWPRICE);
                            click = true;
                        }

                        break;
                }
            }
        });
        mTitlebar.setOnTitleBarClickListener(new Titlebar.TitleBarClickListener() {
            @Override
            public void onim() {

            }

            @Override
            public void Onseek() {
                Intent intent = new Intent(AllShopActivity.this,SearchGoodsActivity.class);
                startActivity(intent);
            }

            @Override
            public void Onback() {
                MyApplication.getInstance().finishActivity(AllShopActivity.this);
            }
        });
    }
    public void Submitgoods(String type) {
        final Gson gson = new Gson();
        OkGo.post(RequesURL.SHOPGOODSLIST)
                .tag(this)
                .params("shopid",mIntent.getStringExtra("shopid"))
                .params("flag",type)
                .params("catsid",mIntent.getStringExtra("catsid"))
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
                        final ShopAll shopAll = gson.fromJson(s, ShopAll.class);
                        mTertiaryDetailsAdapter.setNewData(shopAll.getData());
                        if (shopAll.getData().size() == 0) {
                            mProgressActivity.showEmpty(getResources().getDrawable(R.mipmap.ic_empty_page), "", "咦...没有任何内容，先去逛逛别的吧", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    MyApplication.getInstance().finishActivity(AllShopActivity.this);
                                }
                            });
                        }
                        AllGoodsList.addOnItemTouchListener(new OnItemClickListener() {
                            @Override
                            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                                Intent intent = new Intent(AllShopActivity.this,MerchandiseNewsActivity.class);
                                intent.putExtra("goodsid",shopAll.getData().get(position).getGId());
                                startActivity(intent);
                            }
                        });
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                    }
                });


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

    }
}
