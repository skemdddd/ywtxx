package com.example.dddkj.ywtx.Entity;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/16 8:55
 */

public class ShopPageCouponList {
    public String getSytime() {
        return sytime;
    }

    public void setSytime(String sytime) {
        this.sytime = sytime;
    }

    private String sytime;
    private String couid;
    private String mprice;
    private String jprice;

    public void setCouid(String couid) {
        this.couid = couid;
    }

    public String getCouid() {
        return couid;
    }

    public void setMprice(String mprice) {
        this.mprice = mprice;
    }

    public String getMprice() {
        return mprice;
    }

    public void setJprice(String jprice) {
        this.jprice = jprice;
    }

    public String getJprice() {
        return jprice;
    }
}
