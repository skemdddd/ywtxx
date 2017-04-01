package com.example.dddkj.ywtx.Adapter;


import android.graphics.Bitmap;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dddkj.ywtx.Entity.FavoritesShopsData;
import com.example.dddkj.ywtx.R;
import com.example.dddkj.ywtx.common.RequesURL;

import java.util.List;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/25 15:13
 */

public class FavoritesShopsAdapter extends BaseQuickAdapter<FavoritesShopsData,FavoritesShopsAdapter.FavoritesPlateViewHolder> {


    public void setListCheck(List<Boolean> listCheck) {
        this.listCheck = listCheck;
    }

    private List<Boolean> listCheck;//是否被选中


    public boolean isshowBox() {
        return isshowBox;
    }

    public void setIsshowBox(boolean isshowBox) {
        this.isshowBox = isshowBox;
    }

    private boolean isshowBox = false;


    public FavoritesShopsAdapter(int layoutResId, List<FavoritesShopsData> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(final FavoritesPlateViewHolder helper, FavoritesShopsData item) {
        helper.favoritesnum_tv.setText(item.getFavoritesnum());
        helper.shopNname_iv.setText(item.getShopname());
        helper.ordernum_tv.setText(item.getOrdernum());

        Glide.with(mContext)
                .load(RequesURL.URL + item.getShoplogo())
                .asBitmap()
                .skipMemoryCache(false)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        helper.mImageView.setImageBitmap(resource);
                    }
                });
    }


    @Override
    public void onBindViewHolder(final FavoritesPlateViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        holder.position = position;
        if (listCheck != null) {
            holder.checkBox.setChecked(listCheck.get(position));
        }
        if (isshowBox) {
            holder.checkBox.setVisibility(View.VISIBLE);
        } else {
            holder.checkBox.setVisibility(View.GONE);
        }


    }


    public class FavoritesPlateViewHolder extends BaseViewHolder implements View.OnLongClickListener, View.OnClickListener, CompoundButton.OnCheckedChangeListener {
        private TextView shopNname_iv;
        private TextView favoritesnum_tv;
        private TextView ordernum_tv;
        private ImageView mImageView;

        private CheckBox checkBox;
        private RelativeLayout rootView;
        private int position;

        public FavoritesPlateViewHolder(View itemView) {
            super(itemView);
            rootView = (RelativeLayout) itemView.findViewById(R.id.discountCoupon_rlyt);

            mImageView = (ImageView) itemView.findViewById(R.id.shoplogo_iv);
            checkBox = (CheckBox) itemView.findViewById(R.id.edit_CheckBox);
            shopNname_iv = (TextView) itemView.findViewById(R.id.shopNname_iv);
            favoritesnum_tv= (TextView) itemView.findViewById(R.id.favoritesnum_tv);
            ordernum_tv= (TextView) itemView.findViewById(R.id.ordernum_tv);

            checkBox.setOnCheckedChangeListener(this);
            rootView.setOnClickListener(this);
            rootView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            if (onItemClickListener != null) {
                return onItemClickListener.setOnItemLongClick(position);
            }
            return false;
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                if (checkBox.isChecked()) {
                    checkBox.setChecked(false);
                    onItemClickListener.setOnItemClick(position, false,itemView,getData());
                } else {
                    checkBox.setChecked(true);
                    onItemClickListener.setOnItemClick(position, true,itemView,getData());
                }
            }
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (onItemClickListener != null) {
                onItemClickListener.setOnItemCheckedChanged(position, isChecked);
            }
        }
    }

    public interface OnItemClickListener {
        void setOnItemClick(int position, boolean isCheck, View view, List<FavoritesShopsData> data);

        boolean setOnItemLongClick(int position);

        void setOnItemCheckedChanged(int position, boolean isCheck);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


}
