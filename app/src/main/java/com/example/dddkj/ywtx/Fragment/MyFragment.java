package com.example.dddkj.ywtx.Fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dddkj.ywtx.Base.BaseFragment;
import com.example.dddkj.ywtx.R;
import com.example.dddkj.ywtx.ui.DeliveryAddrssActivity;
import com.example.dddkj.ywtx.ui.MyCollection;
import com.example.dddkj.ywtx.ui.MyCoupons;
import com.example.dddkj.ywtx.ui.PersonalSettingActivity;

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
//    收获地址
    @BindView(R.id.personal_address_rl)
    RelativeLayout personal_address_rl;
    @BindView(R.id.personal_setting_rl)
    RelativeLayout personal_setting_rl;



    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_my,container,false);
    }

    @Override
    protected void initListener() {
        personal_address_rl.setOnClickListener(this);
        mCollect.setOnClickListener(this);
        personal_setting_rl.setOnClickListener(this);
        mDiscountCoupon.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.personal_address_rl:
                Intent intent = new Intent(getActivity(), DeliveryAddrssActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.collect_rlyt:
                Intent intent1 = new Intent(getActivity(), MyCollection.class);
                getActivity().startActivity(intent1);
                break;
            case R.id.personal_setting_rl:
                Intent intent2 = new Intent(getActivity(), PersonalSettingActivity.class);
                getActivity().startActivity(intent2);
                break;
            case R.id.discountCoupon_rlyt:
                Intent intent3 =new Intent(getActivity(), MyCoupons.class);
                getActivity().startActivityForResult(intent3,0);
                break;
        }
    }



    @Override
    public View getScrollableView() {
        return null;
    }
}
