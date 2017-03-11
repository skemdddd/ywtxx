package com.example.dddkj.ywtx.Entity;

import java.util.List;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/7 13:38
 */

public class GoogsNewsData {
    private String gId;

    private String gName;

    private String marPrice;

    private String price;

    private String warstock;

    private String sId;

    private List<String> picList ;

    private String orderNum;

    private String postAge;

    private GoodsAppraisesNum goodsAppraisesNum;

    private List<GoogsNewspicList> appraisesList ;

    private String sid;

    private String shopname;

    private String shoplogo;

    private String shopStar;

    private String goodsNum;

    private String serviceNum;

    private String timeNum;

    public void setGId(String gId){
        this.gId = gId;
    }
    public String getGId(){
        return this.gId;
    }
    public void setGName(String gName){
        this.gName = gName;
    }
    public String getGName(){
        return this.gName;
    }
    public void setMarPrice(String marPrice){
        this.marPrice = marPrice;
    }
    public String getMarPrice(){
        return this.marPrice;
    }
    public void setPrice(String price){
        this.price = price;
    }
    public String getPrice(){
        return this.price;
    }
    public void setWarstock(String warstock){
        this.warstock = warstock;
    }
    public String getWarstock(){
        return this.warstock;
    }
    public void setSId(String sId){
        this.sId = sId;
    }
    public String getSId(){
        return this.sId;
    }
    public void setString(List<String> picList){
        this.picList = picList;
    }
    public List<String> getString(){
        return this.picList;
    }
    public void setOrderNum(String orderNum){
        this.orderNum = orderNum;
    }
    public String getOrderNum(){
        return this.orderNum;
    }
    public void setPostAge(String postAge){
        this.postAge = postAge;
    }
    public String getPostAge(){
        return this.postAge;
    }
    public void setGoodsAppraisesNum(GoodsAppraisesNum goodsAppraisesNum){
        this.goodsAppraisesNum = goodsAppraisesNum;
    }
    public GoodsAppraisesNum getGoodsAppraisesNum(){
        return this.goodsAppraisesNum;
    }
    public void setAppraisesList(List<GoogsNewspicList> appraisesList){
        this.appraisesList = appraisesList;
    }
    public List<GoogsNewspicList> getAppraisesList(){
        return this.appraisesList;
    }
    public void setSid(String sid){
        this.sid = sid;
    }
    public String getSid(){
        return this.sid;
    }
    public void setShopname(String shopname){
        this.shopname = shopname;
    }
    public String getShopname(){
        return this.shopname;
    }
    public void setShoplogo(String shoplogo){
        this.shoplogo = shoplogo;
    }
    public String getShoplogo(){
        return this.shoplogo;
    }
    public void setShopStar(String shopStar){
        this.shopStar = shopStar;
    }
    public String getShopStar(){
        return this.shopStar;
    }
    public void setGoodsNum(String goodsNum){
        this.goodsNum = goodsNum;
    }
    public String getGoodsNum(){
        return this.goodsNum;
    }
    public void setServiceNum(String serviceNum){
        this.serviceNum = serviceNum;
    }
    public String getServiceNum(){
        return this.serviceNum;
    }
    public void setTimeNum(String timeNum){
        this.timeNum = timeNum;
    }
    public String getTimeNum(){
        return this.timeNum;
    }

}

