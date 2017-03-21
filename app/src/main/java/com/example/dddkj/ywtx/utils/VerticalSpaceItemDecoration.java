package com.example.dddkj.ywtx.utils;

import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/2/16 13:20
 */

public class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private int mDividerHeight = 2;
    private Paint mPaint;
    int type ;


    public VerticalSpaceItemDecoration(int space) {
        this.space = space;

    }
    public VerticalSpaceItemDecoration(int space,int type){
        this.space = space;
        this.type =type;
    }
    private boolean isLastRaw(RecyclerView parent, int pos,
                              int childCount)
    {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager)
        {
            if (pos-1 >= 0)// 如果是第一行，则不需要绘制底部
                return true;
        }

        return false;
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //不是第一个的格子都设一个左边和底部的间距

        int childCount = parent.getAdapter().getItemCount();
        if(type == 1){
            if(isLastRaw(parent,parent.getChildLayoutPosition(view),childCount)){
                outRect.left = space;
            }

            //由于每行都只有3个，所以第一个都是2的倍数，把左边距设为0

            if (parent.getChildLayoutPosition(view) %2==1 ) {
                outRect.left = 0;
            }
        }else{
            outRect.left = space;
            //由于每行都只有3个，所以第一个都是2的倍数，把左边距设为0
            if (parent.getChildLayoutPosition(view) %2==0 ) {
                outRect.left = 0;
            }
        }

    }
}
