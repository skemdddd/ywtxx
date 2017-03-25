package com.example.dddkj.ywtx.ui;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.dddkj.ywtx.Base.BaseActivity;
import com.example.dddkj.ywtx.Entity.AreaListData;
import com.example.dddkj.ywtx.Fragment.AreaFragment;
import com.example.dddkj.ywtx.R;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/22 15:02
 */

public class AreaSelectActivity extends BaseActivity implements AreaFragment.OnFragmentInteractionListener{
    private Fragment oneFragment;
    private Fragment twoFragment;

    @BindView(R.id.back_img)
    ImageView back_img;
    private Map map = new HashMap();

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_city);
    }

    @Override
    protected void setListener() {
        initView();
        back_img.setOnClickListener(this);
    }
    protected  void initView(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        oneFragment =  AreaFragment.newInstance("");
        transaction.replace(R.id.hometab_context, oneFragment);
        transaction.commit();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                FragmentManager fragmentManager = getSupportFragmentManager();
                if (fragmentManager.getBackStackEntryCount()>0){
                    fragmentManager.popBackStack();
                }else{
                    finish();
                }
                break;
        }
        return true;
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
            case R.id.back_img:
                FragmentManager fragmentManager = getSupportFragmentManager();
                if (fragmentManager.getBackStackEntryCount()>0){
                    fragmentManager.popBackStack();
                }else{
                    finish();
                }
                break;
        }
    }

    @Override
    public void onFragmentInteraction(AreaListData areaListData, String id) {
        if (areaListData==null){
            return;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (fragmentManager.getBackStackEntryCount()){
            case  0 :
                map.put("provId",areaListData.getAid());
                map.put("provName",areaListData.getAname());
                transaction.hide(oneFragment);
                twoFragment =  AreaFragment.newInstance(areaListData.getAid());
                transaction.add(R.id.hometab_context, twoFragment).addToBackStack(null).commit();
                break;
            case 1:
                map.put("cityId",areaListData.getAid());
                map.put("cityName",areaListData.getAname());
                transaction.hide(twoFragment);
                transaction.add (R.id.hometab_context,AreaFragment.newInstance(areaListData.getAid())).addToBackStack(null).commit();
                break;
            case 2:
                map.put("districtId",areaListData.getAid());
                map.put("districtName",areaListData.getAname());
                Intent intent = new Intent();
                intent.putExtra("addressInfo", (Serializable) map);
                setResult(RESULT_OK,intent);
                finish();
                break;


        }
    }
}