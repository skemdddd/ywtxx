package com.example.dddkj.ywtx.ui;


import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dddkj.ywtx.Adapter.CollectAdapter;
import com.example.dddkj.ywtx.Adapter.FavoritesShopsAdapter;
import com.example.dddkj.ywtx.Base.BaseActivity;
import com.example.dddkj.ywtx.Entity.FavoritesShopsData;
import com.example.dddkj.ywtx.Entity.SecondaryRecommendationData;
import com.example.dddkj.ywtx.Fragment.MyCollectionFragment;
import com.example.dddkj.ywtx.MyApplication.MyApplication;
import com.example.dddkj.ywtx.R;
import com.example.dddkj.ywtx.common.RequesURL;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 项目名称：亿我同行
 * <我的收藏>
 * 创建时间：2017/3/23 14:23
 */

public class MyCollection extends BaseActivity {
    @BindView(R.id.TabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.title_back)
    ImageView title_back;
    @BindView(R.id.edit_collect_tv)
    TextView edit_collect_tv;
    @BindView(R.id.edit_collect_ll)
    LinearLayout edit_collect_ll;
    @BindView(R.id.pitchon_cb)
    CheckBox pitchon_cb;
    @BindView(R.id.dele_bten)
    Button dele_bten;
    @BindView(R.id.pitchonshop_cb)
    CheckBox pitchonshop_cb;
    private List<Boolean> listCheck;
    private List<SecondaryRecommendationData> list;

    private List<Boolean> listCheckShops;
    private List<FavoritesShopsData> listShops;

    MyCollectionFragment FavoritesGoods;
    MyCollectionFragment FavoritesShops;
    FragmentManager fragmentManager;

    @Override
    protected void loadViewLayout() {

        setContentView(R.layout.activity_my_collection);
        fragmentManager = getSupportFragmentManager();

    }

    @Override
    protected void setListener() {
        initView();
        title_back.setOnClickListener(this);
        edit_collect_tv.setOnClickListener(this);
        edit_collect_tv.setOnClickListener(this);
        dele_bten.setOnClickListener(this);
        pitchon_cb.setOnClickListener(this);
        pitchonshop_cb.setOnClickListener(this);


        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                selectFragment(tab.getPosition());
                if (tab.getPosition() == 0) {
                    pitchon_cb.setVisibility(View.VISIBLE);
                    pitchonshop_cb.setVisibility(View.GONE);
                } else {
                    pitchonshop_cb.setVisibility(View.VISIBLE);
                    pitchon_cb.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void selectFragment(int checkedId) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideFragment(fragmentTransaction);
        switch (checkedId) {
            case 0:
                if (null == FavoritesGoods) {
                    FavoritesGoods = MyCollectionFragment.newInstance("goods");
                    FavoritesShops = MyCollectionFragment.newInstance("shop");
                    fragmentTransaction.add(R.id.hometab_context, FavoritesGoods);
                    fragmentTransaction.add(R.id.hometab_context, FavoritesShops);
                    fragmentTransaction.hide(FavoritesShops);
                } else {
                    fragmentTransaction.show(FavoritesGoods);
                }

                break;
            case 1:
                if (null == FavoritesShops) {
                    FavoritesShops = MyCollectionFragment.newInstance("shop");
                    fragmentTransaction.add(R.id.hometab_context, FavoritesShops);
                } else {
                    fragmentTransaction.show(FavoritesShops);
                }
                break;
        }
        fragmentTransaction.commit();
    }

    protected void initView() {
        mTabLayout.addTab(mTabLayout.newTab().setText("商品"));
        mTabLayout.addTab(mTabLayout.newTab().setText("店铺"));
        FavoritesShops = MyCollectionFragment.newInstance("shop");
        selectFragment(0);


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
        final CollectAdapter collectAdapter = FavoritesGoods.getCollectAdapter();
        final FavoritesShopsAdapter favoritesShopsAdapter = FavoritesShops.getFavoritesShopsAdapter();
        switch (v.getId()) {
            case R.id.title_back:
                MyApplication.getInstance().finishActivity(this);
                break;
            case R.id.edit_collect_tv:

                list = new ArrayList<>();
                listCheck = new ArrayList<>();

                listCheckShops = new ArrayList<>();
                listShops = new ArrayList<>();

                for (int i = 0; i < collectAdapter.getData().size(); i++) {
                    listCheck.add(false);
                }

                for (int i = 0; i < favoritesShopsAdapter.getData().size(); i++) {
                    listCheckShops.add(false);
                }

                if (edit_collect_tv.getText().equals("编辑")) {
                    collectAdapter.setIsshowBox(true);
                    favoritesShopsAdapter.setIsshowBox(true);
                    collectAdapter.setListCheck(listCheck);
                    favoritesShopsAdapter.setListCheck(listCheckShops);
                    favoritesShopsAdapter.notifyDataSetChanged();
                    collectAdapter.notifyDataSetChanged();

                    list = collectAdapter.getData();
                    listShops = favoritesShopsAdapter.getData();

                    edit_collect_tv.setText("退出");
                    edit_collect_ll.setVisibility(View.VISIBLE);


                    collectAdapter.setOnItemListener(new CollectAdapter.OnItemClickListener() {

                        @Override
                        public void setOnItemClick(int position, boolean isCheck, View view, List<SecondaryRecommendationData> data) {
                            listCheck.set(position, isCheck);
                        }

                        @Override
                        public boolean setOnItemLongClick(int position) {
                            return false;
                        }

                        @Override
                        public void setOnItemCheckedChanged(int position, boolean isCheck) {
                            listCheck.set(position, isCheck);
                        }
                    });
                    favoritesShopsAdapter.setOnItemListener(new FavoritesShopsAdapter.OnItemClickListener() {
                        @Override
                        public void setOnItemClick(int position, boolean isCheck, View view, List<FavoritesShopsData> data) {
                            listCheckShops.set(position, isCheck);
                        }

                        @Override
                        public boolean setOnItemLongClick(int position) {
                            return false;
                        }

                        @Override
                        public void setOnItemCheckedChanged(int position, boolean isCheck) {
                            listCheckShops.set(position, isCheck);
                        }
                    });
                } else {
                    edit_collect_tv.setText("编辑");
                    collectAdapter.setIsshowBox(false);
                    favoritesShopsAdapter.setIsshowBox(false);
                    favoritesShopsAdapter.notifyDataSetChanged();
                    collectAdapter.notifyDataSetChanged();
                    pitchon_cb.setChecked(false);
                    pitchon_cb.setText("全选");
                    pitchonshop_cb.setChecked(false);
                    pitchonshop_cb.setText("全选");

                    edit_collect_ll.setVisibility(View.GONE);
//                    商品
                    collectAdapter.setOnItemListener(new CollectAdapter.OnItemClickListener() {
                        @Override
                        public void setOnItemClick(int position, boolean isCheck, View view, List<SecondaryRecommendationData> data) {
                            Intent intent = new Intent(MyCollection.this, MerchandiseNewsActivity.class);
                            SecondaryRecommendationData secondaryRecommendationData = data.get(position);
                            intent.putExtra("goodsid", secondaryRecommendationData.getGId());
//                            商品返回
                            startActivityForResult(intent, 1);
                        }

                        @Override
                        public boolean setOnItemLongClick(int position) {
                            return false;
                        }

                        @Override
                        public void setOnItemCheckedChanged(int position, boolean isCheck) {

                        }
                    });
//                    店铺
                    favoritesShopsAdapter.setOnItemListener(new FavoritesShopsAdapter.OnItemClickListener() {
                        @Override
                        public void setOnItemClick(int position, boolean isCheck, View view, List<FavoritesShopsData> data) {
                            FavoritesShopsData favoritesShopsData = data.get(position);
                            Intent intentStore = new Intent(MyCollection.this, EnterStoreActivity.class);
                            intentStore.putExtra("shopid", favoritesShopsData.getShopid());
//                            店铺返回
                            startActivityForResult(intentStore, 1);
                        }

                        @Override
                        public boolean setOnItemLongClick(int position) {
                            return false;
                        }

                        @Override
                        public void setOnItemCheckedChanged(int position, boolean isCheck) {

                        }
                    });
                }
                break;
            case R.id.dele_bten:
//                商品
                if (mTabLayout.getSelectedTabPosition() == 0) {
                    int length = listCheck.size();
                    for (int i = length - 1; i >= 0; i--) {
                        if (listCheck.get(i)) {
                            SubmitUnfavorite("goods", list.get(i).getFid());
                            list.remove(i);
                            listCheck.remove(i);
                            collectAdapter.notifyItemRemoved(i);
                            collectAdapter.notifyItemRangeChanged(0, list.size());

                        }
                    }
                    Logger.i("123"+ collectAdapter.getData().size());
                    if(collectAdapter.getData().size()==0){
                        FavoritesGoods.mProgressActivity.showEmpty(getResources().getDrawable(R.mipmap.ic_empty_page), "", "咦...没有任何内容!", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });
                    }
                } else {
//                    店铺
                    int lengthshop = listCheckShops.size();
                    for (int i = lengthshop - 1; i >= 0; i--) {
                        if (listCheckShops.get(i)) {
                            SubmitUnfavorite("shops", listShops.get(i).getFid());
                            listShops.remove(i);
                            listCheckShops.remove(i);
                            favoritesShopsAdapter.notifyItemRemoved(i);
                            favoritesShopsAdapter.notifyItemRangeChanged(0, listShops.size());

                        }
                    }
                    if(favoritesShopsAdapter.getData().size()==0){
                        FavoritesShops.mProgressActivity.showEmpty(getResources().getDrawable(R.mipmap.ic_empty_page), "", "咦...没有任何内容!", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });
                    }
                }


                break;
            case R.id.pitchonshop_cb:
//                店铺
                if (pitchonshop_cb.getText().equals("全选") && listCheckShops.size() != 0) {
                    for (int i = 0; i < listCheckShops.size(); i++) {
                        listCheckShops.set(i, true);
                    }
                    pitchonshop_cb.setText("取消全选");
                    favoritesShopsAdapter.notifyDataSetChanged();
                } else {
                    for (int i = 0; i < listCheckShops.size(); i++) {
                        listCheckShops.set(i, false);
                    }
                    pitchonshop_cb.setText("全选");
                    favoritesShopsAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.pitchon_cb:
                //                商品
                if (mTabLayout.getSelectedTabPosition() == 0 && listCheck.size() != 0) {

                    if (pitchon_cb.getText().equals("全选")) {
                        for (int i = 0; i < listCheck.size(); i++) {
                            listCheck.set(i, true);
                        }
                        pitchon_cb.setText("取消全选");
                        collectAdapter.notifyDataSetChanged();
                    } else {
                        for (int i = 0; i < listCheck.size(); i++) {
                            listCheck.set(i, false);
                        }
                        pitchon_cb.setText("全选");
                        collectAdapter.notifyDataSetChanged();
                    }
                }

                break;
        }
    }

    public void SubmitUnfavorite(String type, String params) {

        OkGo.post(RequesURL.UNFAVORITE)
                .tag(this)
                .params("id", params)
                .params("type", type)
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
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                    }
                });

    }

    private void hideFragment(FragmentTransaction fragmentTransaction) {
        if (null != FavoritesGoods) {
            fragmentTransaction.hide(FavoritesGoods);
        }
        if (null != FavoritesShops) {
            fragmentTransaction.hide(FavoritesShops);
        }

    }


    @Override
    protected void onRestart() {

        FavoritesGoods.Submit("goods");
        FavoritesShops.Submit("shop");

        super.onRestart();


    }
}
