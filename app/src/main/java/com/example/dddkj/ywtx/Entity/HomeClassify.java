package com.example.dddkj.ywtx.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/2/14 14:54
 */

public class HomeClassify {
    private String catsId;

    private String catsName;

    public String getPicURL() {
        return picURL;
    }

    public void setPicURL(String picURL) {
        this.picURL = picURL;
    }

    @SerializedName("picArr")
    private String picURL;

    public void setCatsId(String catsId){
        this.catsId = catsId;
    }
    public String getCatsId(){
        return this.catsId;
    }
    public void setCatsName(String catsName){
        this.catsName = catsName;
    }
    public String getCatsName(){
        return this.catsName;
    }

}