package com.example.dddkj.ywtx.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dddkj.ywtx.Base.BaseActivity;
import com.example.dddkj.ywtx.MyApplication.MyApplication;
import com.example.dddkj.ywtx.R;

import butterknife.BindView;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/27 9:18
 */

public class PersonalSettingActivity extends BaseActivity {
    @BindView(R.id.title_back)
    ImageView title_back;
    @BindView(R.id.shipping_address_rlyt)
    RelativeLayout shipping_address_rlyt;
    @BindView(R.id.sex_rl)
    RelativeLayout sex_rl;
    @BindView(R.id.sex_tv)
    TextView sex_tv;

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_personal_setting);
    }

    @Override
    protected void setListener() {
        initView();
        title_back.setOnClickListener(this);
        shipping_address_rlyt.setOnClickListener(this);
        sex_rl.setOnClickListener(this);

    }
    protected void  initView(){

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
        switch (v.getId()){
            case R.id.title_back:
                MyApplication.getInstance().finishActivity(this);
                break;
            case R.id.shipping_address_rlyt:
                Intent intent = new Intent(this, DeliveryAddrssActivity.class);
                startActivity(intent);
                break;
            case R.id.sex_rl:
                if(sex_tv.getText().toString().equals("男")){
                    sex_tv.setText("女");
                }else{
                    sex_tv.setText("男");
                }


        }
    }
}
