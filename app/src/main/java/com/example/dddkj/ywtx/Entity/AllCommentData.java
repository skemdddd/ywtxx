package com.example.dddkj.ywtx.Entity;

import java.util.List;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/11 15:30
 */

public class AllCommentData {
    private String num;

    private String hnum;

    private String znum;

    private String cnum;

    private String stnum;

    private List<GoogsNewspicList> appraisesList;

    public void setNum(String num){
        this.num = num;
    }
    public String getNum(){
        return this.num;
    }
    public void setHnum(String hnum){
        this.hnum = hnum;
    }
    public String getHnum(){
        return this.hnum;
    }
    public void setZnum(String znum){
        this.znum = znum;
    }
    public String getZnum(){
        return this.znum;
    }
    public void setCnum(String cnum){
        this.cnum = cnum;
    }
    public String getCnum(){
        return this.cnum;
    }
    public void setStnum(String stnum){
        this.stnum = stnum;
    }
    public String getStnum(){
        return this.stnum;
    }
    public void setAppraisesList(List<GoogsNewspicList> appraisesList){
        this.appraisesList = appraisesList;
    }
    public List<GoogsNewspicList> getAppraisesList(){
        return this.appraisesList;
    }
}
