package com.example.dddkj.ywtx.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dddkj.ywtx.Adapter.DeliveryAddressAdapter;
import com.example.dddkj.ywtx.Base.BaseActivity;
import com.example.dddkj.ywtx.Entity.AddressList;
import com.example.dddkj.ywtx.Entity.SelectedBean;
import com.example.dddkj.ywtx.MyApplication.MyApplication;
import com.example.dddkj.ywtx.R;
import com.example.dddkj.ywtx.common.RequesURL;
import com.example.dddkj.ywtx.utils.ProgressActivity;
import com.google.gson.Gson;
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
 * <p>
 * 创建时间：2017/3/22 9:31
 */

public class DeliveryAddrssActivity extends BaseActivity {
    DeliveryAddressAdapter mDeliveryAddressAdapter;
    @BindView(R.id.addressList_rv)
    RecyclerView addressList;
    @BindView(R.id.add_address_tv)
    TextView add_address_tv;
    @BindView(R.id.title_back)
    ImageView title_back;
    @BindView(R.id.ProgressActivity)
    ProgressActivity mProgressActivity;


    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_delivery_address);
    }

    @Override
    protected void setListener() {
        initView();
        addressList.setLayoutManager(new LinearLayoutManager(this));
        addressList.setHasFixedSize(true);

        addressList.setLayoutManager(new LinearLayoutManager(this));
        addressList.setHasFixedSize(true);
        mDeliveryAddressAdapter = new DeliveryAddressAdapter(R.layout.item_delivery_address,null,DeliveryAddrssActivity.this);
        addressList.setAdapter(mDeliveryAddressAdapter);

        add_address_tv.setOnClickListener(this);
        title_back.setOnClickListener(this);
    }
    protected void initView(){

    }
    @Override
    protected void Request() {
    Submit();
    }
    public  void Submit(){
        OkGo.post(RequesURL.SHIPPINGADDRESSMANAGEMENT)
                .tag(this)
                .params("uid",MyApplication.getInstance().getUserid())
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
                        Gson gson = new Gson();
                        List<SelectedBean> datas = new ArrayList<>();
                        final AddressList addressList = gson.fromJson(s, AddressList.class);
                            for (int i = 0;  i< addressList.getData().size(); i++) {
                                datas.add(new SelectedBean(addressList.getData().get(i), addressList.getData().get(i).getIsstate().equals("1") ? true : false));
                                Logger.i("真"+datas.get(i).isSelected());
                        }
                        mDeliveryAddressAdapter.setNewData(datas);

                    }


                    @Override
                    public void onSuccess(String s, Call call, Response response) {



                    }
                });

    }

    @Override
    protected Context getActivityContext() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_address_tv:
                Intent intent = new Intent(this,EditShippingAddressActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.title_back:
                MyApplication.getInstance().finishActivity(this);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Logger.i("123"+"用了");
                Submit();
        }
    }


}
