package com.example.dddkj.ywtx.Entity;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/2/16 9:17
 */

public class HomePopularity {
    private String gId;

    private String gName;

    private String price;

    private String picurl;

    public void setGId(String gId){
        this.gId = gId;
    }
    public String getGId(){
        return this.gId;
    }
    public void setGName(String gName){
        this.gName = gName;
    }
    public String getGName(){
        return this.gName;
    }
    public void setPrice(String price){
        this.price = price;
    }
    public String getPrice(){
        return this.price;
    }
    public void setPicurl(String picurl){
        this.picurl = picurl;
    }
    public String getPicurl(){
        return this.picurl;
    }
}
