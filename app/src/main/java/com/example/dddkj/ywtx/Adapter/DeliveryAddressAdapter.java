package com.example.dddkj.ywtx.Adapter;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dddkj.ywtx.Entity.SelectedBean;
import com.example.dddkj.ywtx.MyApplication.MyApplication;
import com.example.dddkj.ywtx.R;
import com.example.dddkj.ywtx.common.RequesURL;
import com.example.dddkj.ywtx.ui.DeliveryAddrssActivity;
import com.example.dddkj.ywtx.ui.EditShippingAddressActivity;
import com.example.dddkj.ywtx.utils.T;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/22 10:51
 */

public class DeliveryAddressAdapter extends BaseQuickAdapter<SelectedBean, BaseViewHolder> {
    private DeliveryAddrssActivity deliveryAddrssActivity;

    public DeliveryAddressAdapter(int layoutResId, List<SelectedBean> data, DeliveryAddrssActivity deliveryAddrssActivity) {
        super(layoutResId, data);
        this.deliveryAddrssActivity = deliveryAddrssActivity;


    }


    @Override
    protected void convert(final BaseViewHolder helper, final SelectedBean item) {
        LinearLayout edit_address_ll = helper.getView(R.id.edit_address_ll);
        final RadioButton select_rbtn = helper.getView(R.id.select_rbtn);
        RadioButton editor_tben = helper.getView(R.id.editor_tben);
        select_rbtn.setChecked(item.isSelected());
        helper.setText(R.id.useName_tv, item.getAddressListData().getUname());
        helper.setText(R.id.utel_tv, item.getAddressListData().getUtel());
        helper.setText(R.id.address_tv, item.getAddressListData().getAr() + item.getAddressListData().getArtwo());
        RadioButton arDelete_rbten = helper.getView(R.id.arDelete_rbten);

        select_rbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < getData().size(); i++) {
                    getData().get(i).setSelected(false);
                }
                getData().get(helper.getLayoutPosition()).setSelected(true);
                notifyDataSetChanged();
                Submit(MyApplication.getInstance().getUserid(), item.getAddressListData().getArid());

            }
        });
        arDelete_rbten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData().remove(helper.getLayoutPosition());
                notifyDataSetChanged();
                SubmitDelete(item.getAddressListData().getArid());
            }
        });


        editor_tben.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyApplication.getInstance(), EditShippingAddressActivity.class);
                intent.putExtra("uname", item.getAddressListData().getUname());
                intent.putExtra("utel", item.getAddressListData().getUtel());
                intent.putExtra("ar", item.getAddressListData().getAr());
                intent.putExtra("artwo", item.getAddressListData().getArtwo());
                intent.putExtra("arid", item.getAddressListData().getArid());
                intent.putExtra("provinceID", item.getAddressListData().getProvinceid());
                intent.putExtra("cityID", item.getAddressListData().getCityid());
                intent.putExtra("areaID", item.getAddressListData().getAreaid());
                deliveryAddrssActivity.startActivityForResult(intent, 1);

            }
        });

        edit_address_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyApplication.getInstance(), EditShippingAddressActivity.class);
                intent.putExtra("uname", item.getAddressListData().getUname());
                intent.putExtra("utel", item.getAddressListData().getUtel());
                intent.putExtra("ar", item.getAddressListData().getAr());
                intent.putExtra("artwo", item.getAddressListData().getArtwo());
                intent.putExtra("arid", item.getAddressListData().getArid());
                intent.putExtra("provinceID", item.getAddressListData().getProvinceid());
                intent.putExtra("cityID", item.getAddressListData().getCityid());
                intent.putExtra("areaID", item.getAddressListData().getAreaid());
                deliveryAddrssActivity.startActivityForResult(intent, 1);
            }
        });
    }

    public void Submit(String uid, String arid) {
        OkGo.post(RequesURL.SHIPPINGADDRESSSTATE)
                .tag(this)
                .params("uid", uid)
                .params("arid", arid)
                .cacheKey("cacheKey")
                .cacheMode(CacheMode.DEFAULT)
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(BaseRequest request) {
                        super.onBefore(request);
                        T.showShort(mContext, "正在修改默认地址");
                    }

                    @Override
                    public void onAfter(String s, Exception e) {
                        super.onAfter(s, e);
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        T.showShort(mContext, "修改默认地址成功");
                    }
                });
    }

    public void SubmitDelete(String arid) {
        OkGo.post(RequesURL.ARDELETE)
                .tag(this)
                .params("arid", arid)
                .cacheKey("cacheKey")
                .cacheMode(CacheMode.DEFAULT)
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(BaseRequest request) {
                        super.onBefore(request);
                        T.showShort(mContext, "正在删除");
                    }

                    @Override
                    public void onAfter(String s, Exception e) {
                        super.onAfter(s, e);
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        T.showShort(mContext, "删除成功");
                    }
                });
    }
}
