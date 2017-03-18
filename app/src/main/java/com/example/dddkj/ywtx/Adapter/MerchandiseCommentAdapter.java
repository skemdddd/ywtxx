package com.example.dddkj.ywtx.Adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.PaintDrawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dddkj.ywtx.Entity.GoogsNewspicList;
import com.example.dddkj.ywtx.R;
import com.example.dddkj.ywtx.common.RequesURL;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/8 10:49
 */

public class MerchandiseCommentAdapter extends BaseQuickAdapter<GoogsNewspicList, BaseViewHolder> {
    private PopupWindow mPopWindow;

    public MerchandiseCommentAdapter(int layoutResId, List<GoogsNewspicList> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final GoogsNewspicList item) {
        helper.setText(R.id.username_tv, item.getUName());
        helper.setText(R.id.time_tv, item.getPubtime());
        helper.setText(R.id.usercomment_tv, item.getDescription());
        helper.setText(R.id.commodity_property_tv, item.getAttrName());
        final CircleImageView circleImageView = helper.getView(R.id.userhead_image);
        Glide.with(mContext)
                .load(RequesURL.URL + item.getULogo())
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        circleImageView.setImageBitmap(resource);
                    }
                });
        GridView gridView = helper.getView(R.id.review_images_gv);
        gridView.setAdapter(new MerchandiseCommodityPropertyGVAdapter(mContext, item.getString()));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showPopupWindow(item,i);
            }
        });

    }

    private void showPopupWindow(final GoogsNewspicList item, final int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.evaluate_popupwindow, null);
        mPopWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindow.setContentView(view);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.ll);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindow.dismiss();
            }
        });
        viewPager.setPageMargin(10);
        mPopWindow.setFocusable(true);
        mPopWindow.setBackgroundDrawable(new PaintDrawable());
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return item.getString().size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
            @Override
            public Object instantiateItem(ViewGroup container, final int position) {
                final PhotoView view = new PhotoView(mContext);
                view.enable();
                view.setScaleType(ImageView.ScaleType.FIT_CENTER);
                Glide.with(mContext)
                        .load(RequesURL.URL + item.getString().get(position))
                        .asBitmap()
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                view.setImageBitmap(resource);
                            }
                        });
                container.addView(view);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPopWindow.dismiss();
                    }
                });
                return view;
            }
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

        });
        viewPager.setCurrentItem(i);
        mPopWindow.showAtLocation(view,  Gravity.LEFT | Gravity.TOP , view.getWidth()/2 , view.getHeight()/2 );


    }

}
