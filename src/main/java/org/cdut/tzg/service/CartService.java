package org.cdut.tzg.service;

import org.apache.ibatis.annotations.Select;
import org.cdut.tzg.model.Cart;
import org.cdut.tzg.model.Goods;

import java.util.List;

/**
 * @author anlan
 * @date 2019/6/25 15:00
 */
public interface CartService {

    /**
     * 根据用户id,查找购物车的信息
     */
    List<Cart> findAll(Long buyerId);

    /**
     * 添加指定商品到购物车
     */
    int insertToCart(Cart cart);

    /**
     * 更新购物车中某一商品的数量
     */
    int updateGoodsInCart(Long buyerId,Long goodsId,Integer newNumber);


    /**
     * 根据用户id获取该用户的购物车信息
     */
    List<Cart> findCartByUserId(Long userId);

    /**
     * 根据用户id和商品id获得唯一商品(购物车不允许同一id商品出现多次 只允许对它在购物车中的数量进行修改)
     */
    Cart findCartByUserIdAndGoodsId(Long buyerId,Long goodsId);
}
