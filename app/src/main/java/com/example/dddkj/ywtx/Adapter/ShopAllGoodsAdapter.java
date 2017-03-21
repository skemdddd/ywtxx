package com.example.dddkj.ywtx.Adapter;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dddkj.ywtx.Entity.ShopCatsData;
import com.example.dddkj.ywtx.MyApplication.MyApplication;
import com.example.dddkj.ywtx.R;
import com.example.dddkj.ywtx.common.Constant;
import com.example.dddkj.ywtx.ui.AllShopActivity;

import java.util.List;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/20 13:40
 */

public class ShopAllGoodsAdapter extends BaseQuickAdapter<ShopCatsData,BaseViewHolder> {
    public ShopAllGoodsAdapter(int layoutResId, List<ShopCatsData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ShopCatsData item) {
        helper.setText(R.id.classifction_tv,item.getCatsname());
        RelativeLayout relativeLayout =helper.getView(R.id.first_classifction_rv);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyApplication.getInstance(), AllShopActivity.class);
                intent.putExtra("shopid", Constant.getShopid());
                intent.putExtra("catsid",item.getCatsid());
                intent.putExtra("title",item.getCatsname());
                mContext.startActivity(intent);
            }
        });
        GridView gridView = helper.getView(R.id.review_images_gv);
        gridView.setAdapter(new ShopAllGoodsGVAdapter(mContext, item));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MyApplication.getInstance(), AllShopActivity.class);
                intent.putExtra("shopid", Constant.getShopid());
                intent.putExtra("catsid",item.getCats2().get(i).getCatsid());
                intent.putExtra("title",item.getCats2().get(i).getCatsname());
                mContext.startActivity(intent);
            }
        });

    }

}
