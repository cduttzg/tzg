package org.cdut.tzg.mapper;

import org.apache.ibatis.annotations.*;
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

    /**
     * 立即购买 单一商品订单入库
     */
    @Insert("insert into orders (buyer_id,state) values(#{buyerId},#{state})")
    int addOrders(Orders orders);

    /**
     * 根据用户id和订单id查找订单
     */
    @Select("select * from orders where id = #{orderId} and buyer_id = #{userId}")
    Orders getOrderByIdAndUserId(@Param("orderId") Long orderId,@Param("userId") Long userId);

    /**
     * 修改订单状态为已支付
     */
    @Update("update orders set state = 1 where id = #{orderId} and buyer_id = #{userId}")
    int setStateToPaid(@Param("orderId") Long orderId,@Param("userId") Long userId);

    /**
     * 修改订单状态为已完成
     */
    @Update("update orders set state = 2,completed_time = #{date} where id = #{orderId} and buyer_id = #{userId}")
    int setStateToCompleted(@Param("orderId") Long orderId,@Param("userId") Long userId,@Param("date") Date date);

    /**
     * 根据指定用户买家id获取订单信息
     */
    @Select("select * from orders where buyer_id = #{buyerId}")
    List<Orders> getOrderByBuyerId(Long buyerId);
}
