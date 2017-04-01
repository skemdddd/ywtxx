package com.example.dddkj.ywtx.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/30 10:23
 */

public class CouponList {

    private String code;
    private String message;
    @SerializedName("data")
    private List<CouponListData> data;
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

    public void setData(List<CouponListData> data) {
        this.data = data;
    }
    public List<CouponListData> getData() {
        return data;
    }

}
