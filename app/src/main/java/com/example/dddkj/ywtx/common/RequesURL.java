package com.example.dddkj.ywtx.common;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/2/13 13:32
 */

public class RequesURL {
    public static final String URL="http://atarimg.ptpkj.cn/";
    private static String INDEX="index.php?";
//   首页轮播图
    public static final String BANNER_URL=URL+INDEX+"m=Customer&c=Index&a=adsList";
//    首页分类
    public static final String CLASSIFY=URL+INDEX+"m=Customer&c=Index&a=typeList";
//    热门推荐
    public static final String RECOMMEND=URL+INDEX+"m=Customer&c=Index&a=recomGoodsList";
//    人气商品
    public static final String POPULARITY=URL+INDEX+"m=Customer&c=Index&a=bestGoodsList";
//    分类页面
    public static final String CLASSIFYHOMENOE=URL+INDEX+"m=Customer&c=GoodsCats&a=typeListNoe";
    public static final String CLASSIFYHOMETWO=URL+INDEX+"m=Customer&c=GoodsCats&a=typeListTwo";
//   二级分类轮播图 reclassify
    public static final String RECLASSIFYBANNER=URL+INDEX+"m=Customer&c=Ads&a=typeGoods2";
//   二级推荐商品 Secondary commodity
    public static final String SECONDARYCOMMODITY=URL+INDEX+"m=Customer&c=Goods&a=typeGoods2";
    public static final String RECLASSIFY=URL+INDEX+"m=Customer&c=GoodsCats&a=typeList2";

}
