package com.example.dddkj.ywtx.Widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dddkj.ywtx.R;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/1 14:18
 */

public class Titlebar extends RelativeLayout {
    private ImageView iv;
    private TextView  tv;

    public Titlebar(Context context) {
        super(context);
    }

    public Titlebar(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title,this,true);
        iv= (ImageView) findViewById(R.id.title_back);
        tv= (TextView) findViewById(R.id.title_text);
    }

    /**
     * @param text
     */
    public void setText(String text){
        tv.setText(text);
    }
    public interface OnButtonClickListener{
        void Onback();
    }
    public void setOnButtonClickListener(final OnButtonClickListener onButtonClickListener){
        if(onButtonClickListener !=null){
            if(iv !=null){
                iv.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onButtonClickListener.Onback();
                    }
                });
            }
        }
    }
}
