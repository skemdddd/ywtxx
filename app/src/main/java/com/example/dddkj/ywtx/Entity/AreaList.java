package com.example.dddkj.ywtx.Entity;

import java.util.List;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/22 14:45
 */

public class AreaList {
    private String code;
    private String message;
    private List<AreaListData> data;
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

    public void setData(List<AreaListData> data) {
        this.data = data;
    }
    public List<AreaListData> getData() {
        return data;
    }

}
