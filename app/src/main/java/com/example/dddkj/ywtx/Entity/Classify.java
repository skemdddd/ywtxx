package com.example.dddkj.ywtx.Entity;

import java.util.List;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/2/20 10:55
 */

public class Classify {
    private String code;

    private String message;

    private List<ClassifyLift> data ;

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
    public void setData(List<ClassifyLift> data){
        this.data = data;
    }
    public List<ClassifyLift> getData(){
        return this.data;
    }

}
