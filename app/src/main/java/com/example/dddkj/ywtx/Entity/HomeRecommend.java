package com.example.dddkj.ywtx.Entity;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/2/15 12:59
 */

public class HomeRecommend {
    private String gId;

    private String gName;

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
    public void setPicurl(String picurl){
        this.picurl = picurl;
    }
    public String getPicurl(){
        return this.picurl;
    }
}
