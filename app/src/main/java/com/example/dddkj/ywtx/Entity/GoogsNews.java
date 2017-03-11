package com.example.dddkj.ywtx.Entity;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/7 13:37
 */

public class GoogsNews {
    private String code;

    private String message;

    private GoogsNewsData data;

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
    public void setData(GoogsNewsData data){
        this.data = data;
    }
    public GoogsNewsData getData(){
        return this.data;
    }


}
