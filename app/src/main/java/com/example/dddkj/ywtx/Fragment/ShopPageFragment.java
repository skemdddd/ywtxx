package com.example.dddkj.ywtx.Fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.dddkj.ywtx.Adapter.StoreCouponAdapter;
import com.example.dddkj.ywtx.Adapter.TertiaryDetailsAdapter;
import com.example.dddkj.ywtx.Base.BaseFragment;
import com.example.dddkj.ywtx.Entity.ShopPageCouponList;
import com.example.dddkj.ywtx.Entity.ThirdGoogsData;
import com.example.dddkj.ywtx.MyApplication.MyApplication;
import com.example.dddkj.ywtx.R;
import com.example.dddkj.ywtx.ui.MerchandiseNewsActivity;
import com.example.dddkj.ywtx.utils.VerticalSpaceItemDecoration;
import com.scrollablelayout.ScrollableHelper;

import java.util.List;

import butterknife.BindView;

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
    StoreCouponAdapter mStoreCouponAdapter;
    TertiaryDetailsAdapter mTertiaryDetailsAdapter;




    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_shop_page,container,false);
    }

    @Override
    protected void initListener() {
        initView();
        CommoditiesList.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(),MerchandiseNewsActivity.class);
                intent.putExtra("goodsid",getThirdGoogsDatas().get(position).getGId());
                startActivity(intent);
            }
        });

    }
    protected void initView(){
        CommoditiesList.setLayoutManager(new GridLayoutManager(MyApplication.getInstance(), 2));
        CommoditiesList.setHasFixedSize(true);
        mTertiaryDetailsAdapter = new TertiaryDetailsAdapter(R.layout.item_home_popularity, null);
        CommoditiesList.setAdapter(mTertiaryDetailsAdapter);
        CommoditiesList.addItemDecoration(new VerticalSpaceItemDecoration(20,1));
        mTertiaryDetailsAdapter.setHeaderView(getHeaderView());
    }

    @Override
    protected void initData() {
        mStoreCouponAdapter.setNewData(getShopPageCouponList());
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
        return view;
    }
}
