package com.example.dddkj.ywtx.Entity;

import java.util.List;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/2/28 13:13
 */

public class SecondaryRecommendation {
    private String code;

    private String message;

    private List<SecondaryRecommendationData> data ;

    public void setCode(String code){
        this.code = code;
    }
    public String getCode(){
        return this.code;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }
    public void setData(List<SecondaryRecommendationData> data){
        this.data = data;
    }
    public List<SecondaryRecommendationData> getData(){
        return this.data;
    }
}
