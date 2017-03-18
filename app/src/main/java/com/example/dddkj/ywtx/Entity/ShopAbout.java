package com.example.dddkj.ywtx.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/18 13:19
 */

public class ShopAbout {
    private String code;
    private String message;
    @SerializedName("data")
    private ShopAboutData data;
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

    public void setData(ShopAboutData data) {
        this.data = data;
    }
    public ShopAboutData getData() {
        return data;
    }

}
