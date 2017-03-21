package com.example.dddkj.ywtx.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/20 13:41
 */

public class ShopCats {
    private String code;
    private String message;
    @SerializedName("data")
    private List<ShopCatsData> data;
    public void setCode(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public void setData(List<ShopCatsData> data) {
        this.data = data;
    }
    public List<ShopCatsData> getData() {
        return data;
    }
}
