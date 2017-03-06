package com.example.dddkj.ywtx.Widget;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ScrollView;
/*
* 这个自定义ScrollView用于解决RecyclerView的滑动冲突
* */
public class MyScrollview extends ScrollView {
    private int downX;
    private int downY;
    private int mTouchSlop;
    private int lastScrollY;
    private OnScrollListener onScrollListener;

    public MyScrollview(Context context) {
        super(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public MyScrollview(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public MyScrollview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        int action = e.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) e.getRawX();
                downY = (int) e.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) e.getRawY();
                if (Math.abs(moveY - downY) > mTouchSlop) {
                    return true;
                }
        }
        return super.onInterceptTouchEvent(e);
    }
    public void setOnScrollListener(OnScrollListener onScrollListener){
        this.onScrollListener = onScrollListener;
    }
    /**
     * 用于用户手指离开MyScrollView的时候获取MyScrollView滚动的Y距离，然后回调给onScroll方法中
     */
    private Handler handler = new Handler() {

        public void handleMessage(android.os.Message msg) {
            int scrollY = MyScrollview.this.getScrollY();

            //此时的距离和记录下的距离不相等，在隔5毫秒给handler发送消息
            if(lastScrollY != scrollY){
                lastScrollY = scrollY;
                handler.sendMessageDelayed(handler.obtainMessage(), 5);
            }
            if(onScrollListener != null){
                onScrollListener.onScroll(scrollY);
            }

        };

    };
    /**
     * 重写onTouchEvent， 当用户的手在MyScrollView上面的时候，
     * 直接将MyScrollView滑动的Y方向距离回调给onScroll方法中，当用户抬起手的时候，
     * MyScrollView可能还在滑动，所以当用户抬起手我们隔5毫秒给handler发送消息，在handler处理
     * MyScrollView滑动的距离
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(onScrollListener != null){
            onScrollListener.onScroll(lastScrollY = this.getScrollY());
        }
        switch(ev.getAction()){
            case MotionEvent.ACTION_UP:
                handler.sendMessageDelayed(handler.obtainMessage(), 20);
                break;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 滚动的回调接口
     */
    public interface OnScrollListener{
        /**
         * 回调方法， 返回MyScrollView滑动的Y方向距离
         */
        public void onScroll(int scrollY);
    }
}