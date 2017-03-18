package com.example.dddkj.ywtx.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/15 15:55
 */

public class ShopPageData {
    private ShopPageShop shops;
    @SerializedName("couponList")
    private List<ShopPageCouponList> couponlist;

    private List<ShopPageRecomm> recomm;
    @SerializedName("Best")
    private List<ThirdGoogsData> best;


    public List<ShopPageCouponList> getCouponlist() {
        return couponlist;
    }

    public void setCouponlist(List<ShopPageCouponList> couponlist) {
        this.couponlist = couponlist;
    }

    public void setShops(ShopPageShop shops) {
        this.shops = shops;
    }

    public ShopPageShop getShops() {
        return shops;
    }


    public void setRecomm(List<ShopPageRecomm> recomm) {
        this.recomm = recomm;
    }

    public List<ShopPageRecomm> getRecomm() {
        return recomm;
    }

    public void setBest(List<ThirdGoogsData> best) {
        this.best = best;
    }

    public List<ThirdGoogsData> getBest() {
        return best;
    }

}
