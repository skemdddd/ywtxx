package com.example.dddkj.ywtx.Entity;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/2/14 14:55
 */

import java.util.List;
public class HomeClassifyRoot {
    private String code;

    private String message;

    private List<HomeClassify> data ;

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
    public void setData(List<HomeClassify> data){
        this.data = data;
    }
    public List<HomeClassify> getData(){
        return this.data;
    }

}
