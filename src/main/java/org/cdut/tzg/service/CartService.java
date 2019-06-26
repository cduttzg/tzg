package org.cdut.tzg.service;

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
    int updateGoodsInCart(Long goodsId,Integer newNumber);
}
