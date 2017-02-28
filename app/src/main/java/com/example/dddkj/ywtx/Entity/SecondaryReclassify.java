package com.example.dddkj.ywtx.Entity;

import java.util.List;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/2/28 13:57
 */

public class SecondaryReclassify {
    private String code;

    private String message;

    private List<SecondaryReclassifyData> data ;

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
    public void setData(List<SecondaryReclassifyData> data){
        this.data = data;
    }
    public List<SecondaryReclassifyData> getData(){
        return this.data;
    }

}
