package com.example.dddkj.ywtx.Entity;

import java.util.List;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/7 14:32
 */

public class GoogsNewspicList {
    private String oid;

    private String uid;

    private String description;

    private List<String> picarr ;

    private String pubtime;

    private String attrName;

    private String uName;

    private String uLogo;

    public void setOid(String oid){
        this.oid = oid;
    }
    public String getOid(){
        return this.oid;
    }
    public void setUid(String uid){
        this.uid = uid;
    }
    public String getUid(){
        return this.uid;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }
    public void setString(List<String> picarr){
        this.picarr = picarr;
    }
    public List<String> getString(){
        return this.picarr;
    }
    public void setPubtime(String pubtime){
        this.pubtime = pubtime;
    }
    public String getPubtime(){
        return this.pubtime;
    }
    public void setAttrName(String attrName){
        this.attrName = attrName;
    }
    public String getAttrName(){
        return this.attrName;
    }
    public void setUName(String uName){
        this.uName = uName;
    }
    public String getUName(){
        return this.uName;
    }
    public void setULogo(String uLogo){
        this.uLogo = uLogo;
    }
    public String getULogo(){
        return this.uLogo;
    }
}
