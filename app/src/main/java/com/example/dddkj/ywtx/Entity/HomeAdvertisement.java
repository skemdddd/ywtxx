package com.example.dddkj.ywtx.Entity;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/2/13 14:06
 */

import java.util.List;
public class HomeAdvertisement {
    private List<HomeTopAds> topAds ;

    private List<HomeTypeBottomAds> typeBottomAds ;

    public void setTopAds(List<HomeTopAds> topAds){
        this.topAds = topAds;
    }
    public List<HomeTopAds> getTopAds(){
        return this.topAds;
    }
    public void setTypeBottomAds(List<HomeTypeBottomAds> typeBottomAds){
        this.typeBottomAds = typeBottomAds;
    }
    public List<HomeTypeBottomAds> getTypeBottomAds(){
        return this.typeBottomAds;
    }

}