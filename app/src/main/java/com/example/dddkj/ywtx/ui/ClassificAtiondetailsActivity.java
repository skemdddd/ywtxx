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
import com.example.dddkj.ywtx.Adapter.ClassificAtiondetailsAdapter;
import com.example.dddkj.ywtx.Adapter.HomeBGABannerAdapter;
import com.example.dddkj.ywtx.Base.BaseActivity;
import com.example.dddkj.ywtx.Entity.SecondaryBGABanner;
import com.example.dddkj.ywtx.Entity.SecondaryReclassify;
import com.example.dddkj.ywtx.Entity.SecondaryRecommendation;
import com.example.dddkj.ywtx.R;
import com.example.dddkj.ywtx.Widget.ActivitySecondReclassify;
import com.example.dddkj.ywtx.Widget.NullStringToEmptyAdapterFactory;
import com.example.dddkj.ywtx.common.RequesURL;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.bgabanner.BGABanner;
import okhttp3.Call;
import okhttp3.Response;

import static com.lzy.okgo.OkGo.getContext;

/**
 * 项目名称：亿我同行
 * <首页分类详情1>
 * 创建时间：2017/2/27 9:33
 */

public class ClassificAtiondetailsActivity extends BaseActivity {
//    轮播
    @BindView(R.id.banner_main_zoomFade)
    BGABanner mBGABanner;
    @BindView(R.id.second_title_popularity_img)
    ImageView mImageView;
//    热门
    @BindView(R.id.second_popularity_recycler)
    RecyclerView mPopularityList;
    ClassificAtiondetailsAdapter mClassificAtiondetailsAdapter;
    @Override
    protected void loadViewLayout() {
        Logger.i("首页分类详情1");
        setContentView(R.layout.activity_reclassify);

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void Request() {
        final Gson gson = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory<>()).create();
        Intent intent = getIntent();
//        热门
        OkGo.post(RequesURL.SECONDARYCOMMODITY)
                .tag(this)
                .params("catsid",intent.getStringExtra("id"))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        SecondaryRecommendation secondaryRecommendation =gson.fromJson(s,SecondaryRecommendation.class);
                        mPopularityList.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                        mPopularityList.setHasFixedSize(true);
                        mClassificAtiondetailsAdapter = new ClassificAtiondetailsAdapter(R.layout.item_classification_deails,secondaryRecommendation.getData());
                        mPopularityList.setAdapter(mClassificAtiondetailsAdapter);

                        Glide.with(getBaseContext())
                                .load(RequesURL.URL + secondaryRecommendation.getMessage())
                                .asBitmap()
                                .into(new SimpleTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                        mImageView.setImageBitmap(resource);

                                    }
                                });


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
                        SecondaryBGABanner secondaryBGABanner =gson.fromJson(s,SecondaryBGABanner.class);
                        setBGABanner(secondaryBGABanner);

                    }
                });
//        分类
        OkGo.post(RequesURL.RECLASSIFY)
                .tag(this)
                .params("typeid",intent.getStringExtra("id"))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        SecondaryReclassify secondaryReclassify = gson.fromJson(s,SecondaryReclassify.class);
                        ActivitySecondReclassify activitySecondReclassify = new ActivitySecondReclassify(getContext(), secondaryReclassify,getRootView(ClassificAtiondetailsActivity.this),4);
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
     *
     * 轮播图
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
