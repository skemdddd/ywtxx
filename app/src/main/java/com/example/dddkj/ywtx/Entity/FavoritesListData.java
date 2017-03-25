package com.example.dddkj.ywtx.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/23 14:52
 */

public class FavoritesListData {
    @SerializedName("gId")
    private String gid;
    @SerializedName("gName")
    private String gname;
    private String price;
    private String picurl;
    public void setGid(String gid) {
        this.gid = gid;
    }
    public String getGid() {
        return gid;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }
    public String getGname() {
        return gname;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    public String getPrice() {
        return price;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }
    public String getPicurl() {
        return picurl;
    }

}
