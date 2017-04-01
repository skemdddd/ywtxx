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
import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dddkj.ywtx.Entity.SecondaryRecommendationData;
import com.example.dddkj.ywtx.R;
import com.example.dddkj.ywtx.common.RequesURL;

import java.util.List;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/27 13:22
 */

public class CollectAdapter extends BaseItemDraggableAdapter<SecondaryRecommendationData, CollectAdapter.PlateViewHolder> {


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


    public CollectAdapter(int layoutResId, List<SecondaryRecommendationData> data) {
        super(layoutResId, data);

    }


    @Override
    public void onBindViewHolder(final PlateViewHolder holder, int position, List<Object> payloads) {
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

    @Override
    protected void convert(final PlateViewHolder helper, SecondaryRecommendationData item) {
        helper.money_text.setText("￥" + item.getPrice());
        helper.numberPeople_text.setText(item.getOrderNum() + "人付款");
        helper.freight_text.setText(item.getIsPostage());
        helper.titleNameText.setText(item.getGName());
        Glide.with(mContext)
                .load(RequesURL.URL + item.getPicurl())
                .asBitmap()
                .skipMemoryCache(false)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        helper.mImageView.setImageBitmap(resource);
                    }
                });

    }


    public class PlateViewHolder extends BaseViewHolder implements View.OnLongClickListener, View.OnClickListener, CompoundButton.OnCheckedChangeListener {
        private TextView money_text;
        private TextView numberPeople_text;
        private TextView freight_text;
        private TextView titleNameText;
        private ImageView mImageView;
        private CheckBox checkBox;
        private RelativeLayout rootView;
        private int position;

        public PlateViewHolder(View itemView) {
            super(itemView);
            rootView = (RelativeLayout) itemView.findViewById(R.id.discountCoupon_rlyt);
            money_text = (TextView) itemView.findViewById(R.id.money_text);
            numberPeople_text = (TextView) itemView.findViewById(R.id.numberPeople_text);
            freight_text = (TextView) itemView.findViewById(R.id.freight_text);
            mImageView = (ImageView) itemView.findViewById(R.id.picture_img);
            checkBox = (CheckBox) itemView.findViewById(R.id.edit_CheckBox);
            titleNameText = (TextView) itemView.findViewById(R.id.titleNameText);


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
        void setOnItemClick(int position, boolean isCheck, View view, List<SecondaryRecommendationData> data);

        boolean setOnItemLongClick(int position);

        void setOnItemCheckedChanged(int position, boolean isCheck);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


}
