package com.example.dddkj.ywtx.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.astuetz.PagerSlidingTabStrip;
import com.example.dddkj.ywtx.Base.BaseActivity;
import com.example.dddkj.ywtx.Entity.ClassifySkip;
import com.example.dddkj.ywtx.R;
import com.example.dddkj.ywtx.Widget.NullStringToEmptyAdapterFactory;
import com.example.dddkj.ywtx.common.RequesURL;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/2/27 9:33
 */

public class ClassificAtiondetailsActivity extends BaseActivity {
    @BindView(R.id.tabs)
    PagerSlidingTabStrip mPagerSlidingTabStrip;
    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_classification_details);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void Request() {
        Intent intent = getIntent();
        Logger.i("id"+intent.getStringExtra("id"));
        OkGo.post(RequesURL.CLASSIFYHOMETWO)
                .tag(this)
                .params("typeid",intent.getStringExtra("id"))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        final Gson gson = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory<>()).create();
                        ClassifySkip classifySkip =gson.fromJson(s,ClassifySkip.class);

                        Logger.json(s);
                    }
                    @Override
                    public void onBefore(BaseRequest request) {
                        super.onBefore(request);

                    }


                });
    }

    @Override
    protected Context getActivityContext() {
        return null;
    }

    @Override
    public void onClick(View view) {

    }
}
