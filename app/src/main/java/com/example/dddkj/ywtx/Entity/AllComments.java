package com.example.dddkj.ywtx.Entity;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/11 15:25
 */

public class AllComments {
    private String code;

    private String message;

    private AllCommentData data;

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
    public void setData(AllCommentData data){
        this.data = data;
    }
    public AllCommentData getData(){
        return this.data;
    }

}
