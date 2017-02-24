package com.example.dddkj.ywtx.utils;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/4.
 */

public interface ITabHostMenuHandler extends Serializable {

    public static final class TabHostSubClazzSimpleName {
        public static final String TAG_MODULE1 = "ModuleView1";
        public static final String TAG_MODULE2 = "ModuleView2";

    }

    public void applyRotation(int position, float start, float end);
}