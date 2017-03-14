package com.example.dddkj.ywtx.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.dddkj.ywtx.Adapter.ClassificAtiondetailsAdapter;
import com.example.dddkj.ywtx.Adapter.HomeBGABannerAdapter;
import com.example.dddkj.ywtx.Base.BaseActivity;
import com.example.dddkj.ywtx.Entity.SecondaryBGABanner;
import com.example.dddkj.ywtx.Entity.SecondaryReclassify;
import com.example.dddkj.ywtx.Entity.SecondaryRecommendation;
import com.example.dddkj.ywtx.Entity.SecondaryRecommendationData;
import com.example.dddkj.ywtx.MyApplication.MyApplication;
import com.example.dddkj.ywtx.R;
import com.example.dddkj.ywtx.Widget.ActivitySecondReclassify;
import com.example.dddkj.ywtx.Widget.NullStringToEmptyAdapterFactory;
import com.example.dddkj.ywtx.Widget.Titlebar;
import com.example.dddkj.ywtx.common.RequesURL;
import com.example.dddkj.ywtx.utils.ProgressActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.bgabanner.BGABanner;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 项目名称：亿我同行
 * <首页二级分类详情1>
 * 创建时间：2017/2/27 9:33
 */

public class ClassificAtiondetailsActivity extends BaseActivity {
    //    轮播
    @BindView(R.id.banner_main_zoomFade)
    BGABanner mBGABanner;
    @BindView(R.id.second_title_popularity_img)
    ImageView mImageView;
    @BindView(R.id.titlebar)
    Titlebar mToolbar;
    @BindView(R.id.progress)
    ProgressActivity mProgressActivity;
    String titleText;
    Intent mIntent;

    //    热门
    @BindView(R.id.second_popularity_recycler)
    RecyclerView mPopularityList;
    ClassificAtiondetailsAdapter mClassificAtiondetailsAdapter;

    @Override
    protected void loadViewLayout() {
        Logger.i("首页分类详情1");
        setContentView(R.layout.activity_reclassify);
        mIntent = getIntent();
        titleText = mIntent.getStringExtra("title");


    }

    @Override
    protected void setListener() {
        initView();
        mToolbar.setVisibilityHide("seek");
        mToolbar.setOnTitleBarClickListener(new Titlebar.TitleBarClickListener() {
            @Override
            public void onim() {

            }

            @Override
            public void Onseek() {

            }

            @Override
            public void Onback() {
                MyApplication.getInstance().finishActivity(ClassificAtiondetailsActivity.this);
            }
        });
        mPopularityList.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(ClassificAtiondetailsActivity.this, MerchandiseNewsActivity.class);
                SecondaryRecommendationData secondaryRecommendationData = (SecondaryRecommendationData) adapter.getData().get(position);
                intent.putExtra("goodsid", secondaryRecommendationData.getGId());
                startActivity(intent);
            }
        });


    }

    public void initView() {
        mPopularityList.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        mPopularityList.setHasFixedSize(true);
        mClassificAtiondetailsAdapter = new ClassificAtiondetailsAdapter(R.layout.item_classification_deails, null);
        mPopularityList.setAdapter(mClassificAtiondetailsAdapter);
    }


    @Override
    protected void Request() {
        final Gson gson = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory<>()).create();
//        热门
        OkGo.post(RequesURL.SECONDARYCOMMODITY)
                .tag(this)
                .params("catsid", mIntent.getStringExtra("id"))
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(BaseRequest request) {
                        super.onBefore(request);
                        mToolbar.setText(titleText);
                        mProgressActivity.showLoading();

                    }

                    @Override
                    public void onAfter(String s, Exception e) {
                        super.onAfter(s, e);

                        mProgressActivity.showContent();
                        SecondaryRecommendation secondaryRecommendation = gson.fromJson(s, SecondaryRecommendation.class);
                        mClassificAtiondetailsAdapter.setNewData(secondaryRecommendation.getData());
                        Glide.with(getBaseContext())
                                .load(RequesURL.URL + secondaryRecommendation.getMessage())
                                .asBitmap()
                                .into(new SimpleTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                        mImageView.setImageBitmap(resource);
                                    }
                                });
                        if (mClassificAtiondetailsAdapter.getData().size() == 0) {
                            mProgressActivity.showEmpty(getResources().getDrawable(R.mipmap.ic_empty_page), "", "咦...没有任何内容，先去逛逛别的吧", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    MyApplication.getInstance().finishActivity(ClassificAtiondetailsActivity.this);
                                }
                            });
                        }

                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                    }
                });
//       轮播图
        OkGo.get(RequesURL.RECLASSIFYBANNER)
                .tag(this)
                .cacheKey("cacheKey")
                .cacheMode(CacheMode.DEFAULT)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        SecondaryBGABanner secondaryBGABanner = gson.fromJson(s, SecondaryBGABanner.class);
                        setBGABanner(secondaryBGABanner);

                    }
                });
//        分类
        OkGo.post(RequesURL.RECLASSIFY)
                .tag(this)
                .params("typeid", mIntent.getStringExtra("id"))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        final SecondaryReclassify secondaryReclassify = gson.fromJson(s, SecondaryReclassify.class);
                        ActivitySecondReclassify activitySecondReclassify = new ActivitySecondReclassify(ClassificAtiondetailsActivity.this, secondaryReclassify, getRootView(ClassificAtiondetailsActivity.this), 4);
                        activitySecondReclassify.setClassify();

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

    /**
     * 轮播图
     *
     * @param secondaryBGABanner
     */
    public void setBGABanner(SecondaryBGABanner secondaryBGABanner) {
        List<String> bannerTitle = new ArrayList<String>();
        List<String> bannerImage = new ArrayList<String>();
        for (int i = 0; i < secondaryBGABanner.getData().size(); i++) {
            bannerTitle.add(secondaryBGABanner.getData().get(i).getAdsName());
            bannerImage.add(RequesURL.URL + secondaryBGABanner.getData().get(i).getAdsFile());
        }
        mBGABanner.setAdapter(new HomeBGABannerAdapter(getBaseContext()));
        mBGABanner.setData(bannerImage, bannerTitle);

    }


}
