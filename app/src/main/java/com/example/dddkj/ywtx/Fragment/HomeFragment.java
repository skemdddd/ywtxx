package com.example.dddkj.ywtx.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.dddkj.ywtx.Adapter.HomeBGABannerAdapter;
import com.example.dddkj.ywtx.Adapter.HomePopularityAdapter;
import com.example.dddkj.ywtx.Adapter.HomeRecommendedAdapter;
import com.example.dddkj.ywtx.Base.BaseFragment;
import com.example.dddkj.ywtx.Entity.HomeAdvert;
import com.example.dddkj.ywtx.Entity.HomeClassifyRoot;
import com.example.dddkj.ywtx.Entity.HomePopularity;
import com.example.dddkj.ywtx.Entity.HomePopularityRoot;
import com.example.dddkj.ywtx.Entity.HomeRecommendRoot;
import com.example.dddkj.ywtx.MainActivity;
import com.example.dddkj.ywtx.MyApplication.MyApplication;
import com.example.dddkj.ywtx.R;
import com.example.dddkj.ywtx.Widget.HomeFragmentClassify;
import com.example.dddkj.ywtx.common.RequesURL;
import com.example.dddkj.ywtx.ui.LoginAcivity;
import com.example.dddkj.ywtx.ui.MerchandiseNewsActivity;
import com.example.dddkj.ywtx.utils.T;
import com.example.dddkj.ywtx.utils.VerticalSpaceItemDecoration;
import com.google.gson.Gson;
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

import static com.example.dddkj.ywtx.R.id.oad_Image;

/**
 * 项目名称：亿我同行
 * <主页>
 * 创建时间：2017/2/8 13:30
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener {
    //    轮播图
    @BindView(R.id.banner_main_zoomFade)
    BGABanner mBGABanner;
    //    广告位
    @BindView(oad_Image)
    ImageView mOAd_Image;
    @BindView(R.id.tad_Image)
    ImageView mTAd_Image;
    @BindView(R.id.trad_Image)
    ImageView mTrAd_Image;
    @BindView(R.id.fad_Image)
    ImageView mFAd_Image;
    //   推荐标题
    @BindView(R.id.title_recommend_img)
    ImageView mRecommend;
    //    热门推荐
    @BindView(R.id.recommend_recycler)
    RecyclerView mRecommendList;
    //    人气商品
    @BindView(R.id.popularity_recycler)
    RecyclerView mPopularityList;
    @BindView(R.id.title_popularity_img)
    ImageView mPopularity;
    //    适配器
    HomeRecommendedAdapter mRecommendedAdapter;
    HomePopularityAdapter mPopularityAdapter;
    @BindView(R.id.image_log)
    ImageView mImageButton;


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    protected void initListener() {
        Logger.i("主页");
//        人气商品
        mPopularityList.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), MerchandiseNewsActivity.class);
                HomePopularity homePopularity = (HomePopularity) adapter.getData().get(position);
                intent.putExtra("goodsid",homePopularity.getGId());
                startActivity(intent);
            }


        });
        mRecommendList.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                T.showToast(getContext(), "点击" + position);
            }

        });
        mOAd_Image.setOnClickListener(this);
        mBGABanner.setDelegate(new BGABanner.Delegate() {
            @Override
            public void onBannerItemClick(BGABanner banner, View itemView, Object model, int position) {
                T.showToast(getContext(), "点击" + position);
            }
        });
        mImageButton.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        Submit();

    }

    public void Submit() {
//        轮播
        final Gson gson = new Gson();
        OkGo.get(RequesURL.BANNER_URL)
                .tag(this)
                .cacheKey("cacheKey")
                .cacheMode(CacheMode.DEFAULT)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        HomeAdvert advert = gson.fromJson(s, HomeAdvert.class);
                        setBGABanner(advert);
                        setAd_Image(advert);
                    }
                });
//       分类
        OkGo.get(RequesURL.CLASSIFY)
                .tag(this)
                .cacheKey("cacheKey")
                .cacheMode(CacheMode.DEFAULT)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        HomeClassifyRoot classIfyRoot = gson.fromJson(s, HomeClassifyRoot.class);
                        HomeFragmentClassify homeFragmentClassify = new HomeFragmentClassify(getContext(), classIfyRoot, getView(),8, (MainActivity) getActivity());
                        homeFragmentClassify.setClassify();


                    }
                });
//        热门推荐
        OkGo.get(RequesURL.RECOMMEND)
                .tag(this)
                .cacheKey("cacheKey")
                .cacheMode(CacheMode.DEFAULT)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        HomeRecommendRoot recommendRoot = gson.fromJson(s, HomeRecommendRoot.class);
                        Glide.with(getContext())
                                .load(RequesURL.URL + recommendRoot.getMessage())
                                .asBitmap()
                                .into(new SimpleTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                        mRecommend.setImageBitmap(resource);

                                    }
                                });
                        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
                        layoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
                        mRecommendList.setLayoutManager(layoutManager);
                        mRecommendList.setHasFixedSize(true);
                        mRecommendedAdapter = new HomeRecommendedAdapter(R.layout.item_home_recommended, recommendRoot.getData());
                        mRecommendList.setAdapter(mRecommendedAdapter);


                    }
                });
//        人气商品
        OkGo.get(RequesURL.POPULARITY)
                .tag(this)
                .cacheKey("cacheKey")
                .cacheMode(CacheMode.DEFAULT)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        HomePopularityRoot popularityRoot = gson.fromJson(s, HomePopularityRoot.class);
                        mPopularityList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                        mPopularityList.setHasFixedSize(true);
                        mPopularityList.setNestedScrollingEnabled(false);
                        mPopularityAdapter = new HomePopularityAdapter(R.layout.item_home_popularity, popularityRoot.getData());
                        mPopularityList.setAdapter(mPopularityAdapter);
                        mPopularityList.addItemDecoration(new VerticalSpaceItemDecoration(20));
                        Glide.with(MyApplication.getInstance())
                                .load(RequesURL.URL + popularityRoot.getMessage())
                                .asBitmap()
                                .into(new SimpleTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                        mPopularity.setImageBitmap(resource);
                                    }
                                });


                    }
                });


    }

    /**
     * 轮播图
     *
     * @param advert
     */
    public void setBGABanner(HomeAdvert advert) {
        List<String> bannerTitle = new ArrayList<String>();
        List<String> bannerImage = new ArrayList<String>();
        for (int i = 0; i < advert.getData().getTopAds().size(); i++) {
            bannerTitle.add(advert.getData().getTopAds().get(i).getAdsName());
            bannerImage.add(RequesURL.URL + advert.getData().getTopAds().get(i).getAdsFile());
        }
        mBGABanner.setAdapter(new HomeBGABannerAdapter(getActivity()));
        mBGABanner.setData(bannerImage, bannerTitle);
    }


    /**
     * 广告位
     *
     * @param advert
     */
    public void setAd_Image(HomeAdvert advert) {
        final List<ImageView> views = new ArrayList<>();
        views.add(mOAd_Image);
        views.add(mTAd_Image);
        views.add(mTrAd_Image);
        views.add(mFAd_Image);
        for (int i = 0; i < advert.getData().getTypeBottomAds().size(); i++) {
            final ImageView acc = views.get(i);
            Glide.with(getContext())
                    .load(RequesURL.URL + advert.getData().getTypeBottomAds().get(i).getAdsFile())
                    .asBitmap()
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            acc.setImageBitmap(resource);
                        }

                    });

        }

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case oad_Image:
                T.showToast(getContext(), "图片一");
                break;
            case R.id.image_log:
                Intent intent = new Intent(getActivity(), LoginAcivity.class);
                startActivity(intent);

        }
    }


}
