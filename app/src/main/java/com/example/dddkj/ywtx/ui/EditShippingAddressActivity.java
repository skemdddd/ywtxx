package com.example.dddkj.ywtx.ui;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dddkj.ywtx.Base.BaseActivity;
import com.example.dddkj.ywtx.MyApplication.MyApplication;
import com.example.dddkj.ywtx.R;
import com.example.dddkj.ywtx.common.RequesURL;
import com.example.dddkj.ywtx.utils.T;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.request.BaseRequest;

import java.util.Map;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;


/**
 * 项目名称：亿我同行
 * <编辑地址>
 * 创建时间：2017/3/22 13:59
 */

public class EditShippingAddressActivity extends BaseActivity {
    @BindView(R.id.collect_area_rlyt)
    RelativeLayout collect_rlyt;
    @BindView(R.id.uname_tv)
    EditText uname_tv;
    @BindView(R.id.utel_edit)
    EditText utel_edit;
    @BindView(R.id.area_tv)
    TextView area_tv;
    Intent mIntent;
    @BindView(R.id.address_edit)
    EditText address_edit;
    @BindView(R.id.save_tv)
    TextView save_tv;
    @BindView(R.id.titleadd_back)
    ImageView title_back;
    HttpParams params;
    boolean back = false;


    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_edit_shipping_address);
        mIntent = getIntent();
        params = new HttpParams();

    }

    @Override
    protected void setListener() {
        initView();
        collect_rlyt.setOnClickListener(this);
        save_tv.setOnClickListener(this);
        title_back.setOnClickListener(this);
    }

    public void initView() {
        uname_tv.setText(mIntent.getStringExtra("uname"));
        utel_edit.setText(mIntent.getStringExtra("utel"));
        area_tv.setText(mIntent.getStringExtra("ar"));
        address_edit.setText(mIntent.getStringExtra("artwo"));

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

        switch (v.getId()) {
            case R.id.collect_area_rlyt:
                Intent intent = new Intent(EditShippingAddressActivity.this, AreaSelectActivity.class);
                startActivityForResult(intent, 100);
                break;
            case R.id.save_tv:
                if (uname_tv.length() == 0) {
                    T.showLong(this, "姓名不能为空！");
                    return;
                }
                if (!isMobileNO(utel_edit.getText().toString()) && utel_edit.length() == 0) {
                    T.showLong(this, "手机号不正确");
                    return;
                }
                if (area_tv.length() == 0) {
                    T.showLong(this, "地址不能为空");
                    return;
                }
                if (address_edit.length() == 0) {
                    T.showToast(this, "详细地址不能为空");
                    return;
                }
                params.put("uname", uname_tv.getText().toString());
                params.put("utel", utel_edit.getText().toString());
                params.put("uid", MyApplication.getInstance().getUserid());
                params.put("arid", mIntent.getStringExtra("arid"));
                params.put("ar", address_edit.getText().toString());
                if (!back) {
                    params.put("province", mIntent.getStringExtra("provinceID"));
                    params.put("city", mIntent.getStringExtra("cityID"));
                    params.put("area", mIntent.getStringExtra("areaID"));
                }

                OkGo.post(RequesURL.ARSAVE)
                        .tag(this)
                        .params(params)
                        .cacheKey("cacheKey")
                        .cacheMode(CacheMode.DEFAULT)
                        .execute(new StringCallback() {
                            @Override
                            public void onBefore(BaseRequest request) {
                                super.onBefore(request);
                                T.showShort(EditShippingAddressActivity.this, "正在保存");

                            }

                            @Override
                            public void onAfter(String s, Exception e) {
                                super.onAfter(s, e);
                                T.showShort(EditShippingAddressActivity.this, "保存成功");
                            }

                            @Override
                            public void onSuccess(String s, Call call, Response response) {

                            }
                        });
                Intent intentback = new Intent();
                setResult(RESULT_OK, intentback);
                MyApplication.getInstance().finishActivity(this);
                break;
            case R.id.titleadd_back:
                MyApplication.getInstance().finishActivity(EditShippingAddressActivity.this);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            Map map = (Map) data.getSerializableExtra("addressInfo");
            String areaName = String.format("%s %s %s", map.get("provName"),
                    map.get("cityName"), map.get("districtName"));
            area_tv.setText(areaName);
            params.put("province", map.get("provId").toString());
            params.put("city", map.get("cityId").toString());
            params.put("area", map.get("districtId").toString());
            back = true;

        }
    }

    public static boolean isMobileNO(String mobiles) {
    /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        String telRegex = "[1][3458]\\d{9}";
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }
}
