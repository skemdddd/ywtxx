package com.example.dddkj.ywtx.Entity;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/2/13 14:07
 */

public class HomeAdvert {
    private String code;

    private String message;
    private HomeAdvertisement data;

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
    public void setData(HomeAdvertisement data){
        this.data = data;
    }
    public HomeAdvertisement getData(){
        return this.data;
    }

}
