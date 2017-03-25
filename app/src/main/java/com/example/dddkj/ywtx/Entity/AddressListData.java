package com.example.dddkj.ywtx.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/22 10:26
 */

public class AddressListData {
    private String arid;
    private String uname;
    private String utel;
    private String code;
    private String ar;
    private String artwo;
    @SerializedName("provinceID")
    private String provinceid;
    @SerializedName("cityID")
    private String cityid;
    @SerializedName("areaID")
    private String areaid;
    private String isstate;
    public void setArid(String arid) {
        this.arid = arid;
    }
    public String getArid() {
        return arid;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
    public String getUname() {
        return uname;
    }

    public void setUtel(String utel) {
        this.utel = utel;
    }
    public String getUtel() {
        return utel;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }

    public void setAr(String ar) {
        this.ar = ar;
    }
    public String getAr() {
        return ar;
    }

    public void setArtwo(String artwo) {
        this.artwo = artwo;
    }
    public String getArtwo() {
        return artwo;
    }

    public void setProvinceid(String provinceid) {
        this.provinceid = provinceid;
    }
    public String getProvinceid() {
        return provinceid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }
    public String getCityid() {
        return cityid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }
    public String getAreaid() {
        return areaid;
    }

    public void setIsstate(String isstate) {
        this.isstate = isstate;
    }
    public String getIsstate() {
        return isstate;
    }

}
