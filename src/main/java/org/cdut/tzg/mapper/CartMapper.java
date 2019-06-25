package org.cdut.tzg.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.cdut.tzg.model.Cart;

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
}
