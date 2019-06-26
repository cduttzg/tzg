package org.cdut.tzg.service.serviceImpl;

import org.cdut.tzg.mapper.CartMapper;
import org.cdut.tzg.model.Cart;
import org.cdut.tzg.model.Goods;
import org.cdut.tzg.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author anlan
 * @date 2019/6/25 15:01
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Override
    public List<Cart> findAll(Long buyerId) {
        return cartMapper.findAll(buyerId);
    }

    @Override
    public int insertToCart(Cart cart) {
        return cartMapper.insertToCart(cart);
    }

    @Override
    public int updateGoodsInCart(Long goodsId,Integer newNumber) {
        return cartMapper.updateGoodsInCart(goodsId,newNumber);
    }
}
