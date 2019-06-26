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
}
