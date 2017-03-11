package com.example.dddkj.ywtx.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.dddkj.ywtx.R;
import com.example.dddkj.ywtx.common.RequesURL;

import java.util.List;

/**
 * 项目名称：亿我同行
 * <评价图片>
 * 创建时间：2017/3/8 13:27
 */

public class MerchandiseCommodityPropertyGVAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context=null;
    private List<String> data;

    //构造方法
    public MerchandiseCommodityPropertyGVAdapter (Context context,List<String> data) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.data = data;
    }


    @Override
    public int getCount() {
        try {
            return data.size();
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
            view=inflater.inflate(R.layout.itme_commodity_property_activity, viewGroup, false);
            holder=new Holder();
            holder.iv=(ImageView)view.findViewById(R.id.commodity_iv);
            view.setTag(holder);
        }else{
            holder=(Holder) view.getTag();
        }
        Glide.with(context)
                .load(RequesURL.URL+data.get(position))
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        holder.iv.setImageBitmap(resource);
                    }
                });
        return view;
    }
    private class Holder{

        public ImageView iv;

    }
}


