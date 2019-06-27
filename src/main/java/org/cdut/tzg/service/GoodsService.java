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

    /**
     *  发布求购信息
     */
    //int publishSeekGood(Long userId,Integer tag,String title,String content,Float price,Integer stock,String image);
    int publishSeekGood(Goods good);

    /**
     *  删除求购信息
     */
    int deleteSeekGood(Long userId,Integer tag,String title);

    /**
     * 求购信息是否存在
     */
    Goods isExitSeekGoods(Long userId,Integer tag,String title);

    /**
     * 更新商品库存
     */
    int updateGoodsStock(Long goodsId,Integer newNumber);
    /**
     *根据用户名查找用户的求购信息
     */
    List<Goods> getAllSeekGoodsByUserId(Long userid);

    /**
     * 根据种类信息取得所有相同种类的商品
     */
    List<Goods> findSameTypeGoodsByType(Integer type);

    /**
     * 根据类型查询n条商品
     */
    List<Goods> findGoodsByTypeAndLimit(Integer type, int limit);

    /**
     * 根据用户id获取当前用户的上架信息
     */
    List<Goods> getPutGoods(Long userid);


    /**
     * 通过goodsid查找商品name
     */
    String getGoodsNameById(Long goodsid);
    /**
     * 添加新商品
     */
    int addGoods(Goods goods);

    /**
     * 修改商品状态(上下架、商品种类、求购)
     * @param goodsId
     * @param state
     * @return
     */
    int updateTypeState(Long goodsId,Integer state);
}
