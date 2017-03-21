package com.example.dddkj.ywtx.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.dddkj.ywtx.Adapter.ShopAllGoodsAdapter;
import com.example.dddkj.ywtx.Base.BaseActivity;
import com.example.dddkj.ywtx.Entity.ShopCats;
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

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 项目名称：亿我同行
 * <全部商品>
 * 创建时间：2017/3/20 13:23
 */

public class ShopAllGoodsActivity extends BaseActivity {
    @BindView(R.id.all_goods_rv)
    RecyclerView AllGoodsList;
    @BindView(R.id.ProgressActivity)
    ProgressActivity mProgressActivity;
    @BindView(R.id.title_back)
    ImageView title_back;
    ShopAllGoodsAdapter mShopAllGoodsAdapter;
    Intent mIntent;

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_shop_classification_goods);
        mIntent =getIntent();
    }

    @Override
    protected void setListener() {
     initView();title_back.setOnClickListener(this);
    }
    protected void initView(){
        AllGoodsList.setLayoutManager(new LinearLayoutManager(this));
        AllGoodsList.setHasFixedSize(true);
        mShopAllGoodsAdapter = new ShopAllGoodsAdapter(R.layout.item_shop_classification_goods,null);
        AllGoodsList.setAdapter(mShopAllGoodsAdapter);
    }

    @Override
    protected void Request() {
        final Gson gson = new Gson();
        OkGo.post(RequesURL.STORECLASSIFICATION)
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
                        Logger.json(s);
                        ShopCats shopCats = gson.fromJson(s, ShopCats.class);
                        mShopAllGoodsAdapter.setNewData(shopCats.getData());
                        mShopAllGoodsAdapter.setHeaderView(getHeaderView());
                        Constant.setShopid(mIntent.getStringExtra("shopid"));

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
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.title_back:
               MyApplication.getInstance().finishActivity(this);
               break;
       }
    }
    public View getHeaderView(){
        View view =getLayoutInflater().inflate(R.layout.item_shop_classification_goods_head, (ViewGroup) AllGoodsList.getParent(), false);
        RelativeLayout first_classifction_rv = (RelativeLayout) view.findViewById(R.id.first_classifction_rv);
        first_classifction_rv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyApplication.getInstance(), AllShopActivity.class);
                intent.putExtra("shopid", Constant.getShopid());
                intent.putExtra("catsid","");
                intent.putExtra("title","全部商品");
                startActivity(intent);
            }
        });
        return view;
    }
}
