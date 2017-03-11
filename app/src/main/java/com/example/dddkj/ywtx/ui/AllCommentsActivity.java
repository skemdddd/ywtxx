package com.example.dddkj.ywtx.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.dddkj.ywtx.Adapter.MerchandiseCommentAdapter;
import com.example.dddkj.ywtx.Base.BaseActivity;
import com.example.dddkj.ywtx.Entity.AllComments;
import com.example.dddkj.ywtx.MyApplication.MyApplication;
import com.example.dddkj.ywtx.R;
import com.example.dddkj.ywtx.Widget.Titlebar;
import com.example.dddkj.ywtx.common.RequesURL;
import com.example.dddkj.ywtx.utils.ProgressActivity;
import com.example.dddkj.ywtx.utils.RecycleViewDivider;
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
 * <全部评论>
 * 创建时间：2017/3/11 12:48
 */

public class AllCommentsActivity extends BaseActivity {
    @BindView(R.id.comment_tv)
    RecyclerView mCommentList;
    @BindView(R.id.titlebar)
    Titlebar mTitlebar;
    @BindView(R.id.progress)
    ProgressActivity mProgressActivity;
    MerchandiseCommentAdapter mMerchandiseCommentAdapter;
    Intent mIntent;
    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.allcommentsactivity);
        mIntent = getIntent();
    }
    public void initView() {
        mTitlebar.setText("全部评论");
        mCommentList.setLayoutManager(new LinearLayoutManager(getActivityContext()));
        mCommentList.setHasFixedSize(true);
        mCommentList.setNestedScrollingEnabled(false);
        mMerchandiseCommentAdapter = new MerchandiseCommentAdapter(R.layout.item_evaluate_merchandisenews, null);
        mCommentList.setAdapter(mMerchandiseCommentAdapter);
        RecycleViewDivider recycleViewDivider = new RecycleViewDivider(this, LinearLayoutManager.VERTICAL, 2, getResources().getColor(R.color.loginBackground), 2);
        mCommentList.addItemDecoration(recycleViewDivider);


    }
    @Override
    protected void setListener() {
        initView();


    }

    @Override
    protected void Request() {
        final Gson gson = new Gson();
        OkGo.post(RequesURL.AllCOMMENTS)
                .tag(this)
                .params("goodsid", mIntent.getStringExtra("goodsid"))
                .cacheKey("cacheKey")
                .cacheMode(CacheMode.DEFAULT)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                    }

                    @Override
                    public void onBefore(BaseRequest request) {
                        super.onBefore(request);
                        mProgressActivity.showLoading();


                    }

                    @Override
                    public void onAfter(String s, Exception e) {
                        super.onAfter(s, e);
                        Logger.json(s);
                        mProgressActivity.showContent();
                        AllComments allComments = gson.fromJson(s,AllComments.class);
                        mMerchandiseCommentAdapter.setNewData(allComments.getData().getAppraisesList());
                    }
                });

    }

    @Override
    protected Context getActivityContext() {
        return MyApplication.getInstance();
    }

    @Override
    public void onClick(View view) {

    }
}
