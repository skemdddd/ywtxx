package com.example.dddkj.ywtx;

import android.content.Context;
import android.view.View;
import android.widget.RadioGroup;

import com.example.dddkj.ywtx.Base.BaseActivity;
import com.example.dddkj.ywtx.Fragment.FragmentController;
import com.example.dddkj.ywtx.MyApplication.MyApplication;

import butterknife.BindView;

/**
 * 项目名称：亿我同行
 * <主页>
 * 创建时间：2017/2/8 10:49
 */

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener{

    @BindView(R.id.hometab_radio)
    RadioGroup mHomeTab;
    public FragmentController controller;

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_main);
        controller = FragmentController.getInstance(this, R.id.hometab_context);
        controller.showFragment(0);
        MyApplication.getInstance().setUserid("40");

    }

    @Override
    protected void setListener() {
        mHomeTab.setOnCheckedChangeListener(this);
    }

    @Override
    protected void Request() {

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        FragmentController.onDestroy();
    }

    @Override
    protected Context getActivityContext() {
        return null;
    }

    @Override
    public void onClick(View view) {

    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i){
            case R.id.homeTab_rdoBtn:
                controller.showFragment(0);
                break;
            case R.id.classifyTab_rdoBtn:
                controller.showFragment(1);
                break;
            case R.id.shopTab_rdoBtn:
                controller.showFragment(2);
                break;
            case R.id.myTab_rdoBtn:
                controller.showFragment(3);

        }
    }
}
