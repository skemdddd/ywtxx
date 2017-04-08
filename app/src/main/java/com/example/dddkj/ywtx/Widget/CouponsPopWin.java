package com.example.dddkj.ywtx.Widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.dddkj.ywtx.Adapter.CommodityCouponsDetailsAdapter;
import com.example.dddkj.ywtx.Entity.CouponInsert;
import com.example.dddkj.ywtx.Entity.ShopPageCouponList;
import com.example.dddkj.ywtx.MyApplication.MyApplication;
import com.example.dddkj.ywtx.R;
import com.example.dddkj.ywtx.common.RequesURL;
import com.example.dddkj.ywtx.ui.LoginAcivity;
import com.example.dddkj.ywtx.utils.LoginState;
import com.example.dddkj.ywtx.utils.T;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.orhanobut.logger.Logger;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/4/6 15:30
 */

public class CouponsPopWin extends PopupWindow {


    private View view;


    CommodityCouponsDetailsAdapter mCommodityCouponsDetailsAdapter;

    public CouponsPopWin(final Context mContext, List<ShopPageCouponList> shopPageCouponList) {

        this.view = LayoutInflater.from(mContext).inflate(R.layout.coupons_popupwindow, null);
        Button btn_cancel = (Button) view.findViewById(R.id.back_btn);
        RecyclerView RecyclerView = (RecyclerView) view.findViewById(R.id.RecyclerView);
        RecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        RecyclerView.setHasFixedSize(true);
        RecyclerView.setNestedScrollingEnabled(false);
        mCommodityCouponsDetailsAdapter = new CommodityCouponsDetailsAdapter(R.layout.item_commodity_coupons, null);
        RecyclerView.setAdapter(mCommodityCouponsDetailsAdapter);
        mCommodityCouponsDetailsAdapter.setNewData(shopPageCouponList);

        mCommodityCouponsDetailsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ShopPageCouponList shopPageCouponList1 = (ShopPageCouponList) adapter.getData().get(position);

                if (LoginState.LoginState()) {
                    Intent intent = new Intent(mContext, LoginAcivity.class);
                    mContext.startActivity(intent);
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
                                    T.showShort(mContext,couponInsert.getMessage());
                                }
                            });
                }

            }
        });


        // 取消按钮
        btn_cancel.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // 销毁弹出框
                dismiss();
            }
        });


        // 设置外部可点击
        this.setOutsideTouchable(true);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        this.view.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = view.findViewById(R.id.pop_layout).getTop();

                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });

    /* 设置弹出窗口特征 */
        // 设置视图
        this.setContentView(this.view);
        // 设置弹出窗体的宽和高
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);

        // 设置弹出窗体可点击
        this.setFocusable(true);

        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置弹出窗体的背景
        this.setBackgroundDrawable(dw);


    }

}
