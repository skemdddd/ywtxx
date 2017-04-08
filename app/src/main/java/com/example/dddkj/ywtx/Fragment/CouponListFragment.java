package com.example.dddkj.ywtx.Fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.dddkj.ywtx.Adapter.CouponAdapter;
import com.example.dddkj.ywtx.Base.BaseFragment;
import com.example.dddkj.ywtx.Entity.CouponList;
import com.example.dddkj.ywtx.Entity.CouponListData;
import com.example.dddkj.ywtx.MyApplication.MyApplication;
import com.example.dddkj.ywtx.R;
import com.example.dddkj.ywtx.common.RequesURL;
import com.example.dddkj.ywtx.ui.EnterStoreActivity;
import com.example.dddkj.ywtx.utils.ProgressActivity;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

import butterknife.BindView;
import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/30 12:30
 */

public class CouponListFragment extends BaseFragment {
    CouponAdapter mCouponAdapter;
    @BindView(R.id.commoditiesList_rv)
    RecyclerView commoditiesList_rv;
    @BindView(R.id.ProgressActivity)
    ProgressActivity mProgressActivity;
    String type;
    String form;
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_shop_page,container,false);
    }
    public CouponListFragment(String type,String form){
        this.type =type;
        this.form =form;
    }
    @Override
    protected void initListener() {
        initView();
    }
    protected  void initView(){

        commoditiesList_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        commoditiesList_rv.setHasFixedSize(true);
        mCouponAdapter = new CouponAdapter(R.layout.item_discount_coupon, null);
        commoditiesList_rv.setAdapter(mCouponAdapter);
        commoditiesList_rv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                CouponListData couponListData =mCouponAdapter.getData().get(position);
                if(couponListData.getShopid().equals("")){
                    new SweetAlertDialog(getActivity())
                            .setTitleText("全平台使用")
                            .show();

                }else {
                    Intent intentStore = new Intent(getActivity(),EnterStoreActivity.class);
                    intentStore.putExtra("shopid",couponListData.getShopid());
                    startActivity(intentStore);
                }
            }
        });


    }
    @Override
    protected void initData() {
        final Gson gson = new Gson();
        OkGo.post(RequesURL.DISCOUNTCOUPON)
                .tag(this)
                .params("uid", MyApplication.getInstance().getUserid())
                .params("flag",form)
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
                        mProgressActivity.showContent();
                        CouponList couponList = gson.fromJson(s, CouponList.class);
                        mCouponAdapter.setNewData(couponList.getData());
                        if (couponList.getData().size() == 0) {
                            mProgressActivity.showEmpty(getResources().getDrawable(R.mipmap.ic_empty_page), "", "咦...没有任何内容，赶快领取吧", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    MyApplication.getInstance().finishActivity(getActivity());
                                }
                            });
                        }
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                    }
                });
    }

    @Override
    public View getScrollableView() {
        return null;
    }
}
