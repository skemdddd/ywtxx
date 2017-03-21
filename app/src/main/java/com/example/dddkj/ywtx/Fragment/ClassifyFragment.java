package com.example.dddkj.ywtx.Fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.dddkj.ywtx.Adapter.ClassifyLiftAdapter;
import com.example.dddkj.ywtx.Adapter.ClassifyRigetAdapter;
import com.example.dddkj.ywtx.Base.BaseFragment;
import com.example.dddkj.ywtx.Entity.Classify;
import com.example.dddkj.ywtx.Entity.ClassifyLift;
import com.example.dddkj.ywtx.Entity.ClassifySkip;
import com.example.dddkj.ywtx.Entity.MyClassifySection;
import com.example.dddkj.ywtx.R;
import com.example.dddkj.ywtx.Widget.NullStringToEmptyAdapterFactory;
import com.example.dddkj.ywtx.common.RequesURL;
import com.example.dddkj.ywtx.ui.ClassifySortActivity;
import com.example.dddkj.ywtx.ui.SearchGoodsActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
 * <分类>
 * 创建时间：2017/2/8 13:30
 */

public class ClassifyFragment extends BaseFragment {
//    左侧分类
    @BindView(R.id.liftClassify_rv)
    RecyclerView mLiftClassifyList;
//    右侧
    @BindView(R.id.rightClassify_rv)
    RecyclerView mRigetClassifyList;
    ClassifyRigetAdapter classifyRigetAdapter;
    @BindView(R.id.search)
    TextView search;



    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_classify,container,false);

    }
    public void initAdapter(){
        Logger.i("分类");
        mRigetClassifyList.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        mRigetClassifyList.setHasFixedSize(true);
        classifyRigetAdapter =new ClassifyRigetAdapter(R.layout.item_classify_right,R.layout.item_classify_heard,null);
        mRigetClassifyList.setAdapter(classifyRigetAdapter);


    }
    @Override
    protected void initListener() {
        initAdapter();
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSearch =new Intent(getActivity(),SearchGoodsActivity.class);
                startActivity(intentSearch);
            }
        });
//        左侧
        mLiftClassifyList.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
                ClassifyLift classifyLift= (ClassifyLift) adapter.getData().get(position);
                classifyRigetAdapter.setNewData(null);
                OkGo.post(RequesURL.CLASSIFYHOMETWO)
                        .tag(this)
                        .params("typeid",classifyLift.getCatsId())
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                final Gson gson = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory<>()).create();
                                ClassifySkip classifySkip =gson.fromJson(s,ClassifySkip.class);
                                if(classifySkip.getCode().equals("400") ){
                                    classifyRigetAdapter.setEmptyView(R.layout.progress_empty_view, (ViewGroup) mRigetClassifyList.getParent());
                                }else {
                                    classifyRigetAdapter.setNewData(getSampleData(classifySkip));
                                }

                            }
                            @Override
                            public void onBefore(BaseRequest request) {
                                super.onBefore(request);
                                if(classifyRigetAdapter.getData().size()== 0){
                                    classifyRigetAdapter.setEmptyView(R.layout.progress_loading_view, (ViewGroup) mRigetClassifyList.getParent());

                                }
                            }
                            @Override
                            public void onAfter(String s, Exception e) {
                                super.onAfter(s, e);

                            }
                        });
            }

        });
//
        mRigetClassifyList.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mRigetClassifyList.getContext(), ClassifySortActivity.class);
                MyClassifySection myClassifySection = (MyClassifySection) adapter.getData().get(position);
                if(!myClassifySection.isHeader){
                    intent.putExtra("id",myClassifySection.t.getCatsId());
                    intent.putExtra("title",myClassifySection.t.getCatsName());
                    startActivity(intent);

                }


            }
        });

    }

    @Override
    protected void initData() {
        Submit();
        SubmitRight();
    }
    public void SubmitRight(){
        OkGo.post(RequesURL.CLASSIFYHOMETWO)
                .tag(this)
                .params("typeid","334")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        final Gson gson = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory<>()).create();
                        ClassifySkip classifySkip =gson.fromJson(s,ClassifySkip.class);
                        classifyRigetAdapter.setNewData(getSampleData(classifySkip));
                        if(classifySkip.getCode().equals("400") ){
                            classifyRigetAdapter.setNewData(null);
                        }else {
                            classifyRigetAdapter.setNewData(getSampleData(classifySkip));
                        }
                    }


                });
    }
    public void Submit(){
        final Gson gson = new Gson();
//        左侧分类
        OkGo.get(RequesURL.CLASSIFYHOMENOE)
                .tag(this)
                .cacheKey("cacheKey")
                .cacheMode(CacheMode.DEFAULT)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Classify classify = gson.fromJson(s,Classify.class);
                        ClassifyLiftAdapter classifyLiftAdapter = new ClassifyLiftAdapter(R.layout.item_classify_lift,classify.getData());
                        mLiftClassifyList.setLayoutManager(new LinearLayoutManager(getActivity()));
                        mLiftClassifyList.setHasFixedSize(true);
                        mLiftClassifyList.setAdapter(classifyLiftAdapter);
                    }
                });
    }
    public  List<MyClassifySection> getSampleData(ClassifySkip classifySkip) {
        List<MyClassifySection> list = new ArrayList<>();
        try {
            for (int i = 0; i < classifySkip.getData().size() ; i++) {
                Logger.i("数量  "+ classifySkip.getData().size());
                list.add(new MyClassifySection(true, classifySkip.getData().get(i).getCatsName()));
                for (int k = 0; k < classifySkip.getData().get(i).getData().size(); k++) {
                    list.add(new MyClassifySection(classifySkip.getData().get(i).getData().get(k)));
                }
            }
        }catch (Exception e){

           return list;
        }

        return list;
    }


    @Override
    public View getScrollableView() {
        return null;
    }
}
