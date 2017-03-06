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
    private ImageView seek;
    private ImageView im;


    public Titlebar(Context context) {
        super(context);
    }

    public Titlebar(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title,this,true);
        iv= (ImageView) findViewById(R.id.title_back);
        tv= (TextView) findViewById(R.id.title_text);
        seek = (ImageView) findViewById(R.id.seekimage);
        im = (ImageView) findViewById(R.id.iminage);

    }


    /**
     *
     * 隐藏
     * @param nem
     */
    public void setVisibilityHide(String nem){
        if(nem.equals("seek")){
            seek.setVisibility(GONE);
        }else if (nem.equals("im")){
            im.setVisibility(GONE);
        }else{
            seek.setVisibility(GONE);
            im.setVisibility(GONE);
        }


    }
    /**
     *
     * 文字增加
     * @param text
     */
    public void setText(String text){
        tv.setText(text);
    }
   public interface TitleBarClickListener{
        void onim();
        void Onseek();
        void Onback();
    }
    public void setOnTitleBarClickListener(final TitleBarClickListener onButtonClickListener){
        if(onButtonClickListener !=null){
            if(iv !=null){
                iv.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onButtonClickListener.Onback();

                    }

                });
            }
            if(seek!=null){
                seek.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onButtonClickListener.Onseek();
                    }
                });
            }
            if(im!=null){
                im.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onButtonClickListener.onim();
                    }
                });
            }
        }

    }


}
