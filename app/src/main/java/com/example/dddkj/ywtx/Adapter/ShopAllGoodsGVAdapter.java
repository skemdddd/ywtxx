package com.example.dddkj.ywtx.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dddkj.ywtx.Entity.ShopCatsData;
import com.example.dddkj.ywtx.R;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/20 13:49
 */

public class ShopAllGoodsGVAdapter  extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context=null;
    private ShopCatsData data;


    //构造方法
    public ShopAllGoodsGVAdapter (Context context,ShopCatsData data) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.data = data;
    }


    @Override
    public int getCount() {
        try {
            return  data.getCats2().size();
        }catch (Exception e){
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
        if(view==null){
            view=inflater.inflate(R.layout.item_shop_classification_googs_gv, viewGroup, false);
            holder=new Holder();
            holder.tv= (TextView) view.findViewById(R.id.classification_tv);
            view.setTag(holder);
        }else{
            holder=(Holder) view.getTag();
        }

        holder.tv.setText(data.getCats2().get(position).getCatsname());
        return view;
    }
    private class Holder{

        public TextView tv;

    }
}
