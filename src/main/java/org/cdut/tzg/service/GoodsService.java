package org.cdut.tzg.service;

import org.cdut.tzg.model.Goods;

import java.util.Date;
import java.util.List;

/**
 * @author anlan
 * @date 2019/6/25 16:23
 */
public interface GoodsService {
    /**
     * 通过id查找商品
     */
    Goods findGoodsById(Long goodId);
    /**
     * 获取指定日期上架商品
     */
    int getGoodsCount(Date date);

    int publishSeekGood(Long userId,Integer tag,String title,String content,Float price,Integer stock,String image);

    int deleteSeekGood(Long userId,Integer tag,String title);
}
