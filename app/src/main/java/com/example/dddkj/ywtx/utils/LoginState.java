package com.example.dddkj.ywtx.utils;

import com.example.dddkj.ywtx.MyApplication.MyApplication;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/4/6 16:47
 */

public class LoginState {
    public static boolean LoginState()
    {

        if (MyApplication.getInstance().getUserid().equals(""))
        {

            return true;
        }
        return false;
    }
}
