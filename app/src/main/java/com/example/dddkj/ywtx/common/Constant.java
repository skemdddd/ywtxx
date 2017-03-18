package com.example.dddkj.ywtx.common;

import android.support.design.widget.TabLayout;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/4 15:35
 */

public class Constant {
    public static String getShopid() {
        return shopid;
    }

    public static void setShopid(String shopid) {
        Constant.shopid = shopid;
    }

    public static String shopid;
    public static final String SALESVOLUME = "warnStock";
    public static final String NEWGOOGS = "time";
    public static final String HIGHPRICE = "highPrice";
    public static final String LOWPRICE = "lowPrice";
    /**
     * 销量
     */
    public static void setPricelistTab(TabLayout tab) {
        tab.addTab(tab.newTab().setText("综合"));
        tab.addTab(tab.newTab().setText("销量"));
        tab.addTab(tab.newTab().setText("新品"));
        tab.addTab(tab.newTab().setText("价格"));

    }

}
