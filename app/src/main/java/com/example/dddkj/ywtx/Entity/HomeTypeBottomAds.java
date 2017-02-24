package com.example.dddkj.ywtx.Entity;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/2/13 14:06
 */

public class HomeTypeBottomAds {
    //    广告id
    private String adsId;
    //    广告名称
    private String adsName;
    //    广告跳转链接
    private String adsUrl;
    //     广告图片
    private String adsFile;

    public void setAdsId(String adsId){
        this.adsId = adsId;
    }
    public String getAdsId(){
        return this.adsId;
    }
    public void setAdsName(String adsName){
        this.adsName = adsName;
    }
    public String getAdsName(){
        return this.adsName;
    }
    public void setAdsUrl(String adsUrl){
        this.adsUrl = adsUrl;
    }
    public String getAdsUrl(){
        return this.adsUrl;
    }
    public void setAdsFile(String adsFile){
        this.adsFile = adsFile;
    }
    public String getAdsFile(){
        return this.adsFile;
    }

}