package org.cdut.tzg.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.cdut.tzg.model.Orders;

import java.util.Date;
import java.util.List;

/**
 * @author Carhung
 * @date 2019/6/25 16:03
 */
@Mapper
public interface OrdersMapper {

    /**
     * 获取总订单数量
     */
    @Select("select count(*) from orders")
    int getAllOrdersCount();
    /**
     * 获取指定日期订单数量
     */
    @Select("select count(*) from orders where datediff(completed_time,#{date}) = 0")
    int getCompletedOrdersCount(Date date);
    /**
     * 获取指定id订单
     */
    @Select("select * from orders where id = #{orderId}")
    Orders getOrderById(Long orderId);
    /**
     * 更新指定订单号订单状态为异常
     */
    @Update("update orders set state=3 where id = #{orderId}")
    int setOrderException(Long orderId);


    /**
     * 获取最新提交的前 n 条订单
     */
    @Select("SELECT * FROM orders ORDER BY created_time DESC LIMIT #{number}")
    List<Orders> findTheLatestOrders(Integer number);

}
