package com.example.dddkj.ywtx.Entity;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/15 15:54
 */

public class ShopPage {
    private String code;
    private String message;
    private ShopPageData data;
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

    public void setData(ShopPageData data) {
        this.data = data;
    }
    public ShopPageData getData() {
        return data;
    }
}
