package com.example.dddkj.ywtx.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.dddkj.ywtx.Adapter.AreaListAdapter;
import com.example.dddkj.ywtx.Base.BaseFragment;
import com.example.dddkj.ywtx.Entity.AreaList;
import com.example.dddkj.ywtx.Entity.AreaListData;
import com.example.dddkj.ywtx.R;
import com.example.dddkj.ywtx.common.RequesURL;
import com.example.dddkj.ywtx.utils.ProgressActivity;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/22 15:25
 */

public class AreaFragment extends BaseFragment {
    @BindView(R.id.RegionsList_rv)
    RecyclerView RegionsList_rv;
    AreaListAdapter mAreaListAdapter;
    @BindView(R.id.ProgressActivity)
    ProgressActivity mProgressActivity;
    private static final String ARG_PARAM1 = "parentCode";
    private String mParam1;
    private OnFragmentInteractionListener mListener;
    private String URL;


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_city, container, false);
        return view;
    }
    public static AreaFragment newInstance(String param1) {
        AreaFragment fragment = new AreaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }
    @Override
    protected void initListener() {
        initView();

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    protected void initView(){
        RegionsList_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        RegionsList_rv.setHasFixedSize(true);
        mAreaListAdapter = new AreaListAdapter(R.layout.item_area_list,null);
        mAreaListAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        mAreaListAdapter.isFirstOnly(false);
        RegionsList_rv.setAdapter(mAreaListAdapter);
        RegionsList_rv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                AreaListData areaListData = (AreaListData) adapter.getData().get(position);
                if (areaListData==null) return;
                if (mListener!=null){
                    mListener.onFragmentInteraction(areaListData,areaListData.getPid());
                }

            }
        });

    }
    @Override
    protected void initData() {
        if(mParam1.equals("")){
            URL =RequesURL.REGIONALINFORMATION;
        }else{
            URL =RequesURL.AREASLIST;
        }
        OkGo.post(URL)
                .tag(this)
                .params("pid",mParam1)
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
                        AreaList areaList = gson.fromJson(s,AreaList.class);
                        mAreaListAdapter.setNewData(areaList.getData());
                    }
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                    }
                });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public View getScrollableView() {
        return null;
    }
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(AreaListData areaListData,String id);

    }
}
