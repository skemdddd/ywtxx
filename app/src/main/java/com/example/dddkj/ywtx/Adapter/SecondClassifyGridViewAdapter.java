package com.example.dddkj.ywtx.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.dddkj.ywtx.Entity.SecondaryReclassify;
import com.example.dddkj.ywtx.R;
import com.example.dddkj.ywtx.common.RequesURL;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/2/28 14:10
 */

public class SecondClassifyGridViewAdapter extends BaseAdapter {
    private SecondaryReclassify mDatas;
    private LayoutInflater inflater;
    private Context mContext;
    /**
     * 页数下标,从0开始(当前是第几页)
     */
    private int curIndex;
    /**
     * 每一页显示的个数
     */
    private int pageSize;

    public SecondClassifyGridViewAdapter(Context context, SecondaryReclassify mDatas, int curIndex, int pageSize) {
        inflater = LayoutInflater.from(context);
        this.mDatas = mDatas;
        this.curIndex = curIndex;
        this.pageSize = pageSize;
        this.mContext = context;
    }



    /**
     * 先判断数据集的大小是否足够显示满本页,如果够，则直接返回每一页显示的最大条目个数pageSize,如果不够，则有几项就返回几,(也就是最后一页的时候就显示剩余item)
     */
    @Override
    public int getCount() {
        return mDatas.getData().size() > (curIndex + 1) * pageSize ? pageSize : (mDatas.getData().size()  - curIndex * pageSize);

    }

    @Override
    public Object getItem(int position) {
        return mDatas.getData().get(position + curIndex * pageSize);

    }

    @Override
    public long getItemId(int position) {
        return position + curIndex * pageSize;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final SecondClassifyGridViewAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_home_head_classify, parent, false);
            viewHolder = new SecondClassifyGridViewAdapter.ViewHolder();
            viewHolder.tv = (TextView) convertView.findViewById(R.id.classify_text);
            viewHolder.iv = (ImageView) convertView.findViewById(R.id.classify_img);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (SecondClassifyGridViewAdapter.ViewHolder) convertView.getTag();
        }
        /**
         * 在给View绑定显示的数据时，计算正确的position = position + curIndex * pageSize
         */
        int pos = position + curIndex * pageSize;
        viewHolder.tv.setText(mDatas.getData().get(pos).getCatsName());
        Glide.with(mContext)
                .load(RequesURL.URL+mDatas.getData().get(pos).getCatsPic())
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        viewHolder.iv.setImageBitmap(resource);
                    }
                });
        return convertView;
    }
    class ViewHolder {
        public TextView tv;
        public ImageView iv;
    }

}
