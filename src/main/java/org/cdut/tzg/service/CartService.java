package org.cdut.tzg.service;

import org.cdut.tzg.model.Cart;

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
}
