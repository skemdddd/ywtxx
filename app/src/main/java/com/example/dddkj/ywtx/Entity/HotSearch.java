package com.example.dddkj.ywtx.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/21 8:18
 */

public class HotSearch {
    private String code;
    private String message;
    @SerializedName("data")
    private HotSearchData data;
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

    public void setData(HotSearchData data) {
        this.data = data;
    }
    public HotSearchData getData() {
        return data;
    }

}
