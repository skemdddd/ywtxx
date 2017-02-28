package com.example.dddkj.ywtx.Entity;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/2/28 11:28
 */

public class SecondaryData {
    private String adsId;

    private String adsName;

    private String adsUrl;

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
