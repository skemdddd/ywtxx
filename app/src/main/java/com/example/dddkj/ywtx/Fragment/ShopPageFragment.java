package com.example.dddkj.ywtx.Fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.dddkj.ywtx.Adapter.StoreCouponAdapter;
import com.example.dddkj.ywtx.Adapter.TertiaryDetailsAdapter;
import com.example.dddkj.ywtx.Base.BaseFragment;
import com.example.dddkj.ywtx.Entity.CouponInsert;
import com.example.dddkj.ywtx.Entity.ShopPageCouponList;
import com.example.dddkj.ywtx.Entity.ThirdGoogsData;
import com.example.dddkj.ywtx.MyApplication.MyApplication;
import com.example.dddkj.ywtx.R;
import com.example.dddkj.ywtx.common.RequesURL;
import com.example.dddkj.ywtx.ui.LoginAcivity;
import com.example.dddkj.ywtx.ui.MerchandiseNewsActivity;
import com.example.dddkj.ywtx.utils.LoginState;
import com.example.dddkj.ywtx.utils.T;
import com.example.dddkj.ywtx.utils.VerticalSpaceItemDecoration;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.orhanobut.logger.Logger;
import com.scrollablelayout.ScrollableHelper;

import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/17 8:56
 */

public class ShopPageFragment extends BaseFragment  implements ScrollableHelper.ScrollableContainer{
//    @BindView(R.id.coupon_rv)
//    RecyclerView CouponList;
    @BindView(R.id.commoditiesList_rv)
    RecyclerView CommoditiesList;
//    优惠券
    StoreCouponAdapter mStoreCouponAdapter;
    TertiaryDetailsAdapter mTertiaryDetailsAdapter;




    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_shop_page,container,false);
    }

    @Override
    protected void initListener() {
        initView();
//        CommoditiesList.addOnItemTouchListener(new OnItemClickListener() {
//            @Override
//            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Intent intent = new Intent(getActivity(),MerchandiseNewsActivity.class);
//                intent.putExtra("goodsid",getThirdGoogsDatas().get(position).getGId());
//                startActivity(intent);
//            }
//        });

    }
    protected void initView(){
        CommoditiesList.setLayoutManager(new GridLayoutManager(MyApplication.getInstance(), 2));
        CommoditiesList.setHasFixedSize(true);
        mTertiaryDetailsAdapter = new TertiaryDetailsAdapter(R.layout.item_home_popularity, null);
        CommoditiesList.setAdapter(mTertiaryDetailsAdapter);
        if(getShopPageCouponList() != null){
            CommoditiesList.addItemDecoration(new VerticalSpaceItemDecoration(20,1));
            mTertiaryDetailsAdapter.setHeaderView(getHeaderView());
        }else {
            CommoditiesList.addItemDecoration(new VerticalSpaceItemDecoration(20,2));
        }
        mTertiaryDetailsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(),MerchandiseNewsActivity.class);
                intent.putExtra("goodsid",getThirdGoogsDatas().get(position).getGId());
                startActivity(intent);
            }
        });

    }

    @Override
    protected void initData() {
        if(getShopPageCouponList()!=null){
            mStoreCouponAdapter.setNewData(getShopPageCouponList());
        }

        mTertiaryDetailsAdapter.setNewData(getThirdGoogsDatas());
    }
    public List<ShopPageCouponList> getShopPageCouponList() {
        return mShopPageCouponList;
    }

    public void setShopPageCouponList(List<ShopPageCouponList> shopPageCouponList) {
        mShopPageCouponList = shopPageCouponList;
    }

    List<ShopPageCouponList> mShopPageCouponList;

    public List<ThirdGoogsData> getThirdGoogsDatas() {
        return mThirdGoogsDatas;
    }

    public void setThirdGoogsDatas(List<ThirdGoogsData> thirdGoogsDatas) {
        mThirdGoogsDatas = thirdGoogsDatas;
    }

    List<ThirdGoogsData> mThirdGoogsDatas;

    @Override
    public View getScrollableView() {
        return CommoditiesList ;
    }
    public View getHeaderView(){
        View view =getActivity().getLayoutInflater().inflate(R.layout.item_shop_page_header, (ViewGroup) CommoditiesList.getParent(), false);
        RecyclerView CouponList = (RecyclerView) view.findViewById(R.id.coupon_rv);
        GridLayoutManager layoutManager = new GridLayoutManager(MyApplication.getInstance(), 1);
        layoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
        CouponList.setLayoutManager(layoutManager);
        CouponList.setHasFixedSize(true);
        mStoreCouponAdapter = new StoreCouponAdapter(R.layout.item_coupons, null);
        CouponList.setAdapter(mStoreCouponAdapter);
        mStoreCouponAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ShopPageCouponList shopPageCouponList1 = (ShopPageCouponList) adapter.getData().get(position);

                if (LoginState.LoginState()) {
                    Intent intent = new Intent(getActivity(), LoginAcivity.class);
                    getActivity().startActivity(intent);
                } else {
                    final Gson gson = new Gson();
                    OkGo.post(RequesURL.COUPONINSERT)
                            .tag(this)
                            .params("uid", MyApplication.getInstance().getUserid())
                            .params("couponId", shopPageCouponList1.getCouid())
                            .cacheKey("cacheKey")
                            .cacheMode(CacheMode.DEFAULT)
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(String s, Call call, Response response) {
                                    Logger.json(s);
                                    CouponInsert couponInsert =gson.fromJson(s,CouponInsert.class);
                                    T.showShort(getActivity(),couponInsert.getMessage());
                                }
                            });
                }
            }
        });
        return view;
    }
}
