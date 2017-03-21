package com.example.dddkj.ywtx.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/20 13:43
 */

public class ShopCats2 {
    @SerializedName("catsId")
    private String catsid;
    @SerializedName("catsName")
    private String catsname;
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

}
