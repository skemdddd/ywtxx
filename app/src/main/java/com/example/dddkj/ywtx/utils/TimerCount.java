package com.example.dddkj.ywtx.utils;

import android.os.CountDownTimer;
import android.widget.Button;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/2/8 9:54
 */

public class TimerCount extends CountDownTimer {
    private Button bnt;

    public TimerCount(long millisInFuture, long countDownInterval, Button bnt) {
        super(millisInFuture, countDownInterval);
        this.bnt = bnt;
    }

    public TimerCount(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onFinish() {
        // TODO Auto-generated method stub
        bnt.setClickable(true);
        bnt.setText("发送验证码");
    }

    @Override
    public void onTick(long arg0) {
        // TODO Auto-generated method stub
        bnt.setClickable(false);
        bnt.setText(arg0 / 1000 + "秒后重新获取");
    }
}