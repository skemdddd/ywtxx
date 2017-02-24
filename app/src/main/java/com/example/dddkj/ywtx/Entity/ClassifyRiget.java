package com.example.dddkj.ywtx.Entity;

import java.util.List;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/2/20 10:58
 */

public class ClassifyRiget {
    private String catsId;
    private String catsName;
    public List<ClassifyR> getData() {
        return cats2;
    }
    public void setData(List<ClassifyR> data) {
        this.cats2 = data;
    }

    private List<ClassifyR> cats2;


    public void setCatsId(String catsId){
        this.catsId = catsId;
    }
    public String getCatsId(){
        return this.catsId;
    }
    public void setCatsName(String catsName){
        this.catsName = catsName;
    }
    public String getCatsName(){
        return this.catsName;
    }

}
