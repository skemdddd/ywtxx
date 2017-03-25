package com.example.dddkj.ywtx.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dddkj.ywtx.Base.BaseActivity;
import com.example.dddkj.ywtx.Entity.HotSearch;
import com.example.dddkj.ywtx.MyApplication.MyApplication;
import com.example.dddkj.ywtx.R;
import com.example.dddkj.ywtx.common.RequesURL;
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
 * <搜索页面>
 * 创建时间：2017/3/20 16:25
 */

public class SearchGoodsActivity extends BaseActivity {
    Intent mIntent;
    @BindView(R.id.hot_GV)
    GridView hot;
    @BindView(R.id.search_tv)
    TextView search_tv;
    @BindView(R.id.search_edt)
    EditText search_edt;
    @BindView(R.id.title_back)
    ImageView title_back;

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.search_activity);
        mIntent = getIntent();
    }

    @Override
    protected void setListener() {
        initView();
        search_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchGoodsActivity.this, SearchGoodsListActivity.class);
                intent.putExtra("key", search_edt.getText().toString());
                intent.putExtra("shopid", mIntent.getStringExtra("shopid"));
                intent.putExtra("title", "商品列表");
                startActivity(intent);
            }
        });
        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.getInstance().finishActivity(SearchGoodsActivity.this);
            }
        });

    }

    protected void initView() {
        OkGo.post(RequesURL.POPULARSEARCHWORDS)
                .tag(this)
                .cacheKey("cacheKey")
                .cacheMode(CacheMode.DEFAULT)
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(BaseRequest request) {
                        super.onBefore(request);

                    }

                    @Override
                    public void onAfter(String s, Exception e) {
                        super.onAfter(s, e);
                        Gson gson = new Gson();
                        final HotSearch hotSearch = gson.fromJson(s, HotSearch.class);
                        SearchGoodsGVAdapter searchGoodsGVAdapter = new SearchGoodsGVAdapter(SearchGoodsActivity.this, hotSearch);
                        hot.setAdapter(searchGoodsGVAdapter);
                        hot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(SearchGoodsActivity.this, SearchGoodsListActivity.class);
                                intent.putExtra("key", hotSearch.getData().getKey().get(position));
                                intent.putExtra("shopid",mIntent.getStringExtra("shopid"));
                                intent.putExtra("title", "商品列表");
                                startActivity(intent);
                            }
                        });
                    }


                    @Override
                    public void onSuccess(String s, Call call, Response response) {



                    }
                });

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

    }

    class SearchGoodsGVAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private Context context = null;
        private HotSearch data;


        //构造方法
        public SearchGoodsGVAdapter(Context context, HotSearch data) {
            inflater = LayoutInflater.from(context);
            this.context = context;
            this.data = data;
        }


        @Override
        public int getCount() {
            try {
                return data.getData().getKey().size();
            } catch (Exception e) {
                return 0;
            }

        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            final Holder holder;
            if (view == null) {
                view = inflater.inflate(R.layout.item_shop_classification_googs_gv, viewGroup, false);
                holder = new Holder();
                holder.tv = (TextView) view.findViewById(R.id.classification_tv);
                view.setTag(holder);
            } else {
                holder = (Holder) view.getTag();
            }
            holder.tv.setText(data.getData().getKey().get(position));
            return view;
        }

        private class Holder {

            public TextView tv;

        }
    }

}

