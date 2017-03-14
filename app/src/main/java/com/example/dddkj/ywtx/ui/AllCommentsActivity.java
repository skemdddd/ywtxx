package com.example.dddkj.ywtx.ui;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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

import static com.example.dddkj.ywtx.R.id.googs;

/**
 * 项目名称：亿我同行
 * <全部评论>
 * 创建时间：2017/3/11 12:48
 */

public class AllCommentsActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.comment_tv)
    RecyclerView mCommentList;
    @BindView(R.id.titlebar)
    Titlebar mTitlebar;
    @BindView(R.id.progress)
    ProgressActivity mProgressActivity;
    MerchandiseCommentAdapter mMerchandiseCommentAdapter;
    Intent mIntent;
    @BindView(R.id.evaluate)
    ProgressActivity mProgressActivityevaluate;

    Gson gson = new Gson();


    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.allcommentsactivity);
        mIntent = getIntent();
        gson = new Gson();
    }

    public void initView() {
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
        mTitlebar.setOnTitleBarClickListener(new Titlebar.TitleBarClickListener() {
            @Override
            public void onim() {

            }

            @Override
            public void Onseek() {

            }

            @Override
            public void Onback() {
                MyApplication.getInstance().finishActivity(AllCommentsActivity.this);
            }
        });


    }

    @Override
    protected void Request() {
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
                        mProgressActivityevaluate.showLoading();

                    }

                    @Override
                    public void onAfter(String s, Exception e) {
                        super.onAfter(s, e);
                        Logger.json(s);
                        mProgressActivityevaluate.showContent();
                        AllComments allComments = gson.fromJson(s, AllComments.class);
                        mMerchandiseCommentAdapter.setNewData(allComments.getData().getAppraisesList());
                        mTitlebar.setText("全部评论" + "(" + allComments.getData().getNum() + ")");
                        getHeaderView(AllCommentsActivity.this, allComments);
                        if (mMerchandiseCommentAdapter.getData().size() == 0) {
                            mProgressActivityevaluate.showEmpty(getResources().getDrawable(R.mipmap.ic_empty_page), "", "咦...没有任何内容，先去逛逛别的吧", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    MyApplication.getInstance().finishActivity(AllCommentsActivity.this);
                                }
                            });
                        }
                    }
                });

    }

    public void Submit(int type) {
        OkGo.post(RequesURL.AllCOMMENTS)
                .tag(this)
                .params("goodsid", mIntent.getStringExtra("goodsid"))
                .params("type", type)
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
                        mProgressActivity.showContent();
                        Logger.json(s);
                        AllComments allComments = gson.fromJson(s, AllComments.class);
                        mMerchandiseCommentAdapter.setNewData(allComments.getData().getAppraisesList());
                        if(mMerchandiseCommentAdapter.getData().size()==0) {
                            mProgressActivity.showEmpty(getResources().getDrawable(R.mipmap.ic_empty_page), "", "咦...没有任何内容，先去逛逛别的吧", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    MyApplication.getInstance().finishActivity(AllCommentsActivity.this);
                                }
                            });
                        }
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

    private void getHeaderView(RadioGroup.OnCheckedChangeListener listener, AllComments allComments) {

        RadioButton googs = (RadioButton) findViewById(R.id.googs);
        googs.setText("好评" + "(" + allComments.getData().getHnum() + ")");

        RadioButton evaluation = (RadioButton) findViewById(R.id.evaluation);
        evaluation.setText("中评" + "(" + allComments.getData().getZnum() + ")");

        RadioButton all_comments = (RadioButton) findViewById(R.id.all_comments_tv);
        all_comments.setText("全部" + "(" + allComments.getData().getNum() + ")");

        RadioButton negative = (RadioButton) findViewById(R.id.negative);
        negative.setText("差评" + "(" + allComments.getData().getCnum() + ")");

        RadioButton picture = (RadioButton) findViewById(R.id.picture);
        picture.setText("图片" + "(" + allComments.getData().getStnum() + ")");
        RadioGroup mRadioGroup = (RadioGroup) findViewById(R.id.evalute);
        mRadioGroup.setOnCheckedChangeListener(listener);


    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        switch (i) {
            case googs:
//                好评
                Submit(5);

                break;
            case R.id.evaluation:
//                中评
                Submit(3);
                break;
            case R.id.negative:
//                中评
                Submit(1);
                break;
            case R.id.all_comments_tv:
//                中评
                Submit(7);
                break;


        }
    }
}
