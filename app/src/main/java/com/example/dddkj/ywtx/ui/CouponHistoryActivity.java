package com.example.dddkj.ywtx.ui;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.dddkj.ywtx.Adapter.CouponAdapter;
import com.example.dddkj.ywtx.Base.BaseActivity;
import com.example.dddkj.ywtx.Entity.CouponList;
import com.example.dddkj.ywtx.MyApplication.MyApplication;
import com.example.dddkj.ywtx.R;
import com.example.dddkj.ywtx.common.RequesURL;
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
 * 创建时间：2017/3/31 10:15
 */

public class CouponHistoryActivity extends BaseActivity {
    @BindView(R.id.RecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.title_back)
    ImageView title_back;
    CouponAdapter mCouponAdapter;
    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_coupon_history);
    }

    @Override
    protected void setListener() {
        initView();
        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.getInstance().finishActivity(CouponHistoryActivity.this);
            }
        });
    }
    protected void initView(){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mCouponAdapter = new CouponAdapter(R.layout.item_discount_coupon, null);
        mRecyclerView.setAdapter(mCouponAdapter);
    }
    @Override
    protected void Request() {
        final Gson gson = new Gson();
        OkGo.post(RequesURL.DISCOUNTCOUPON)
                .tag(this)
                .params("uid", MyApplication.getInstance().getUserid())
                .params("type",1)
                .cacheKey("cacheKey")
                .cacheMode(CacheMode.DEFAULT)
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(BaseRequest request) {
                        super.onBefore(request);
                    }

                    @Override
                    public void onAfter(String s, Exception e) {
                        super.onAfter(s, e);
                        Logger.json(s);
                        CouponList couponList = gson.fromJson(s, CouponList.class);
                        mCouponAdapter.setNewData(couponList.getData());
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

    }
}
