package com.example.dddkj.ywtx.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/18 13:20
 */

public class ShopAboutData {
    private String shopid;
    private String shopname;
    private String shoplogo;
    private String tel;
    @SerializedName("codeImg")
    private String codeimg;
    private String ctime;
    private String content;
    private String area;
    private String favoritesnum;
    private String ordernum;
    @SerializedName("goodsScoreNum")
    private String goodsscorenum;
    @SerializedName("serviceScoreNum")
    private String servicescorenum;
    @SerializedName("timeScoreNum")
    private String timescorenum;
    public void setShopid(String shopid) {
        this.shopid = shopid;
    }
    public String getShopid() {
        return shopid;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }
    public String getShopname() {
        return shopname;
    }

    public void setShoplogo(String shoplogo) {
        this.shoplogo = shoplogo;
    }
    public String getShoplogo() {
        return shoplogo;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getTel() {
        return tel;
    }

    public void setCodeimg(String codeimg) {
        this.codeimg = codeimg;
    }
    public String getCodeimg() {
        return codeimg;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }
    public String getCtime() {
        return ctime;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }

    public void setArea(String area) {
        this.area = area;
    }
    public String getArea() {
        return area;
    }

    public void setFavoritesnum(String favoritesnum) {
        this.favoritesnum = favoritesnum;
    }
    public String getFavoritesnum() {
        return favoritesnum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
    }
    public String getOrdernum() {
        return ordernum;
    }

    public void setGoodsscorenum(String goodsscorenum) {
        this.goodsscorenum = goodsscorenum;
    }
    public String getGoodsscorenum() {
        return goodsscorenum;
    }

    public void setServicescorenum(String servicescorenum) {
        this.servicescorenum = servicescorenum;
    }
    public String getServicescorenum() {
        return servicescorenum;
    }

    public void setTimescorenum(String timescorenum) {
        this.timescorenum = timescorenum;
    }
    public String getTimescorenum() {
        return timescorenum;
    }

}
