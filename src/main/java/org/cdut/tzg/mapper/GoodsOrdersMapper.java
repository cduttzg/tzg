package org.cdut.tzg.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.cdut.tzg.model.GoodsOrders;
import org.cdut.tzg.model.Orders;

import java.util.List;

/**
 * @author anlan
 * @date 2019/6/26 9:21
 */
@Mapper
public interface GoodsOrdersMapper {

    /**
     * 通过id获取订单的详情信息
     */
    @Select("select * from goods_orders where orders_id = #{ordersId}")
    List<GoodsOrders> findTheOrdersDetailById(Long ordersId);

    /**
     * 商品订单关系信息(GoodsOrders)入库
     */
    @Insert("insert into goods_orders (orders_id,goods_id,seller_id,number) " +
            "values(#{ordersId},#{goodsId},#{sellerId},#{number})")
    int addGoodsOrders(GoodsOrders goodsOrders);
}
