package com.example.dddkj.ywtx.Entity;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/27 15:50
 */

public class SelectedBeanCollect extends Selected {

    public SelectedBeanCollect(SecondaryRecommendationData data,boolean b) {
        this.SecondaryRecommendationData =data;
        setSelected(b);
    }


    public SecondaryRecommendationData getSecondaryRecommendationData() {
        return SecondaryRecommendationData;
    }

    public void setSecondaryRecommendationData(SecondaryRecommendationData secondaryRecommendationData) {
        SecondaryRecommendationData = secondaryRecommendationData;
    }

    private SecondaryRecommendationData SecondaryRecommendationData;
}
