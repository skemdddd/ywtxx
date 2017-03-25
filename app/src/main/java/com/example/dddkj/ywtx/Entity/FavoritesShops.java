package com.example.dddkj.ywtx.Entity;

import java.util.List;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/25 15:32
 */

public class FavoritesShops {

    private String code;
    private String message;
    private List<FavoritesShopsData> data;
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

    public void setData(List<FavoritesShopsData> data) {
        this.data = data;
    }
    public List<FavoritesShopsData> getData() {
        return data;
    }
}
