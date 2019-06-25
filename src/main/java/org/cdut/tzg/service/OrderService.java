package org.cdut.tzg.service;

import org.cdut.tzg.model.Orders;

import java.util.List;

/**
 * @author Carhung
 * @date 2019/6/25 16:12
 */
public interface OrderService {
    /**
     * 获取后台订单数据
     */
    List<Orders> getAllOrders();
}
