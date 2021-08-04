package com.qst.dao;

import com.qst.domain.PromotionAd;

import java.util.List;

public interface PromotionAdMapper {

    /*
    * 分页查询广告位信息
    后台需要准备的分页相关参数：1.总条数 2.每页显示几条 3.总页数 4.当前页 5.分页数据

    * */
    public List<PromotionAd> findAllPromotionAdByPage();

    /*
    * 新增广告信息
    * */

    public void savePromotionAd(PromotionAd promotionAd);

    /*
    * 接收广告ID,返回广告详细信息
    * */

    public PromotionAd findPromotionAdById(int id);

    /*
    * 修改广告信息
    * */
    public void updatePromotionAd(PromotionAd promotionAd);

    /*
    * 广告动态上下线
    * */
    public void updatePromotionAdStatus(PromotionAd promotionAd);
}
