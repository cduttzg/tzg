package org.cdut.tzg.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.cdut.tzg.model.GoodsOrders;

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
    List<GoodsOrders> findTheOrdersDetial(Integer ordersId);
}
