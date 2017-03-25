package com.example.dddkj.ywtx.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.dddkj.ywtx.Adapter.ClassificAtiondetailsAdapter;
import com.example.dddkj.ywtx.Adapter.FavoritesShopsAdapter;
import com.example.dddkj.ywtx.Base.BaseFragment;
import com.example.dddkj.ywtx.Entity.FavoritesShops;
import com.example.dddkj.ywtx.Entity.FavoritesShopsData;
import com.example.dddkj.ywtx.Entity.SecondaryRecommendation;
import com.example.dddkj.ywtx.Entity.SecondaryRecommendationData;
import com.example.dddkj.ywtx.MyApplication.MyApplication;
import com.example.dddkj.ywtx.R;
import com.example.dddkj.ywtx.common.RequesURL;
import com.example.dddkj.ywtx.ui.EnterStoreActivity;
import com.example.dddkj.ywtx.ui.MerchandiseNewsActivity;
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
 * <p>
 * 创建时间：2017/3/23 14:44
 */

public class MyCollectionFragment extends BaseFragment {
    @BindView(R.id.FavoritesList)
    RecyclerView mFavoritesList;
    private static final String ARG_PARAM1 = "parentCode";
    ClassificAtiondetailsAdapter mClassificAtiondetailsAdapter;
    FavoritesShopsAdapter mFavoritesShopsAdapter;
    private String mParam1;
    @BindView(R.id.ProgressActivity)
    ProgressActivity mProgressActivity;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_my_collection, container, false);
    }

    public MyCollectionFragment() {

    }

    @Override
    protected void initListener() {

        if (mParam1.equals("goods")) {
            mFavoritesList.setLayoutManager(new LinearLayoutManager(getActivity()));
            mFavoritesList.setHasFixedSize(true);
            mClassificAtiondetailsAdapter = new ClassificAtiondetailsAdapter(R.layout.item_classification_deails, null);
            mFavoritesList.setAdapter(mClassificAtiondetailsAdapter);
            mFavoritesList.addOnItemTouchListener(new OnItemClickListener() {
                @Override
                public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent = new Intent(getActivity(), MerchandiseNewsActivity.class);
                    SecondaryRecommendationData secondaryRecommendationData = (SecondaryRecommendationData)adapter.getData().get(position);
                    intent.putExtra("goodsid",secondaryRecommendationData.getGId());
                    startActivity(intent);
                }
            });
        } else {
            mFavoritesList.setLayoutManager(new LinearLayoutManager(getActivity()));
            mFavoritesList.setHasFixedSize(true);
            mFavoritesShopsAdapter = new FavoritesShopsAdapter(R.layout.item_shop_collect, null);
            mFavoritesList.setAdapter(mFavoritesShopsAdapter);
            mFavoritesList.addOnItemTouchListener(new OnItemClickListener() {
                @Override
                public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                    FavoritesShopsData favoritesShopsData = (FavoritesShopsData) adapter.getData().get(position);
                    Intent intentStore = new Intent(getActivity(),EnterStoreActivity.class);
                    intentStore.putExtra("shopid",favoritesShopsData.getShopid());
                    startActivity(intentStore);
                }
            });

        }

    }

    public static MyCollectionFragment newInstance(String param1) {
        MyCollectionFragment fragment = new MyCollectionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    protected void initData() {
        final Gson gson = new Gson();
        if (mParam1.equals("goods")) {
            OkGo.post(RequesURL.FAVORITESLIST)
                    .tag(this)
                    .params("uid", MyApplication.getInstance().getUserid())
                    .params("type", "goods")
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
                            Logger.json(s);
                            SecondaryRecommendation secondaryRecommendation = gson.fromJson(s, SecondaryRecommendation.class);
                            mClassificAtiondetailsAdapter.setNewData(secondaryRecommendation.getData());

                        }

                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            mProgressActivity.showContent();
                        }
                    });
        } else {
            OkGo.post(RequesURL.FAVORITESLIST)
                    .tag(this)
                    .params("uid", MyApplication.getInstance().getUserid())
                    .params("type", "shops")
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
                            Logger.json(s);
                            final FavoritesShops favoritesShops = gson.fromJson(s, FavoritesShops.class);
                            mFavoritesShopsAdapter.setNewData(favoritesShops.getData());
                        }

                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            mProgressActivity.showContent();
                        }
                    });
        }
    }

    @Override
    public View getScrollableView() {
        return null;
    }
}
