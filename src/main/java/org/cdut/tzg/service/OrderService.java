package org.cdut.tzg.service;

import org.cdut.tzg.model.Orders;

import java.util.Date;
import java.util.List;

/**
 * @author Carhung
 * @date 2019/6/25 16:12
 */
public interface OrderService {
    /**
     * 获取总订单数量
     */
    int getAllOrdersCount();
    /**
     * 获取指定日期订单数量
     */
    int getCompletedOrdersCount(Date date);
    /**
     * 获取指定id订单
     */
    Orders getOrderById(Long orderId);
    /**
     * 更新指定订单号订单状态为异常
     */
    int setOrderException(Long orderId);

    /**
     * 获取最新提交的前 n 条订单
     */
    List<Orders> findTheLatestOrders(Integer number);

    /**
     * 立即购买 单一商品订单入库
     */
    int addOrders(Orders orders);
}
