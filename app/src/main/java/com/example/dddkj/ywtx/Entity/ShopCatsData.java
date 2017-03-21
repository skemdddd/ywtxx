package com.example.dddkj.ywtx.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/20 13:42
 */

public class ShopCatsData {
    @SerializedName("catsId")
    private String catsid;
    @SerializedName("catsName")
    private String catsname;
    @SerializedName("cats2")
    private List<ShopCats2> cats2;
    public void setCatsid(String catsid) {
        this.catsid = catsid;
    }
    public String getCatsid() {
        return catsid;
    }

    public void setCatsname(String catsname) {
        this.catsname = catsname;
    }
    public String getCatsname() {
        return catsname;
    }

    public void setCats2(List<ShopCats2> cats2) {
        this.cats2 = cats2;
    }
    public List<ShopCats2> getCats2() {
        return cats2;
    }

}
