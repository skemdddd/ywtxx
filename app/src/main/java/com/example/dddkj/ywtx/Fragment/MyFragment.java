package com.example.dddkj.ywtx.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dddkj.ywtx.Base.BaseFragment;
import com.example.dddkj.ywtx.R;

import butterknife.BindView;

/**
 * 项目名称：亿我同行
 * <个人>
 * 创建时间：2017/2/8 13:31
 */


public class MyFragment extends BaseFragment implements View.OnClickListener{
//    收藏
    @BindView(R.id.collect_rlyt)
    RelativeLayout mCollect;
//    优惠券
    @BindView(R.id.discountCoupon_rlyt)
    RelativeLayout mDiscountCoupon;
//    意见反馈
    @BindView(R.id.feedback_rlyt)
    RelativeLayout mFeedBack;
//    我的订单
    @BindView(R.id.myOrder_rlyt)
    RelativeLayout mMyOrder;
//    客服
    @BindView(R.id.consultation_rlyt)
    RelativeLayout mconsultation;
//    分享
    @BindView(R.id.share_rlyt)
    RelativeLayout mShare;
//    待付款
    @BindView(R.id.obligation_tv)
    TextView mObligation;
//    待收货
    @BindView(R.id.sendGoods_tv)
    TextView mSendGoods;
//    待发货
    @BindView(R.id.forGoods_tv)
    TextView mForGoods;
//    待评价
    @BindView(R.id.evaluation_tv)
    TextView mEveluation;
//    售后
    @BindView(R.id.afterSale_tv)
    TextView mAfterSale;


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_my,container,false);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {

    }
}
