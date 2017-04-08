package com.example.dddkj.ywtx.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.example.dddkj.ywtx.Interfaces.onRequestListener;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/4/7 13:43
 */

public class RxPermissionsUtils {

    //    请求Camera权限
    public static void requestCamera(Context mContext, onRequestListener onRequestListener) {

        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.CAMERA}, 1);
            onRequestListener.onRequestBefore();
        } else {
            onRequestListener.onRequestLater();
        }

    }
    public static void requestRrad(Context mContext, onRequestListener onRequestListener) {

        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            onRequestListener.onRequestBefore();
        } else {
            onRequestListener.onRequestLater();
        }

    }

}
