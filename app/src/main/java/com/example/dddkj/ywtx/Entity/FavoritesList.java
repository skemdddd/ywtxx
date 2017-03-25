package com.example.dddkj.ywtx.Entity;

import java.util.List;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/23 14:52
 */

public class FavoritesList {
    private String code;
    private String message;
    private List<FavoritesListData> data;
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

    public void setData(List<FavoritesListData> data) {
        this.data = data;
    }
    public List<FavoritesListData> getData() {
        return data;
    }

}
