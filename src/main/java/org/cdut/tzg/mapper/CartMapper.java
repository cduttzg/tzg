package org.cdut.tzg.mapper;

import org.apache.ibatis.annotations.*;
import org.cdut.tzg.model.Cart;
import org.cdut.tzg.model.Goods;

import java.util.List;

/**
 * @author anlan
 * @date 2019/6/25 14:57
 */
@Mapper
public interface CartMapper {

    /**
     * 根据用户id,查找购物车的信息
     */
    @Select("select * from cart where buyer_id = #{buyerId}")
    List<Cart> findAll(Long buyerId);

    /**
     * 添加到购物车
     */
    @Insert("insert into cart (buyer_id,seller_id,goods_id,number) values (#{buyerId},#{sellerId},#{goodsId},#{number})")
    int insertToCart(Cart cart);

    /**
     * 更新购物车某个商品的库存
     */
    @Insert("update cart set number=#{newNumber} where buyer_id=#{buyerId} and goods_id=#{goodsId}")
    int updateGoodsInCart(@Param("buyerId")Long buyerId,@Param("goodsId")Long goodsId,@Param("newNumber")Integer newNumber);

    /**
     * 根据用户id获取该用户的购物车信息
     */
    @Select("select * from cart where buyer_id=#{userId}")
    List<Cart> findCartByUserId(Long userId);

    /**
     * 根据用户id和商品id获得唯一商品(购物车不允许同一id商品出现多次 只允许对它在购物车中的数量进行修改)
     */
    @Select("select * from cart where buyer_id=#{buyerId} and goods_id=#{goodsId}")
    Cart findCartByUserIdAndGoodsId(@Param("buyerId") Long buyerId,@Param("goodsId")Long goodsId);

    /**
     * 清空买家的购物车(创建订单后)
     */
    @Delete("delete from cart where buyer_id=#{buyerId}")
    int clearBuyerCart(Long buyerId);
}
