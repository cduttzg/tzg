package org.cdut.tzg.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.cdut.tzg.model.Orders;

import java.util.List;

/**
 * @author Carhung
 * @date 2019/6/25 16:03
 */
@Mapper
public interface OrdersMapper {
    /**
     * 获取后台订单数据
     */
    @Select("select * from orders")
    List<Orders> getAllOrders();
}
