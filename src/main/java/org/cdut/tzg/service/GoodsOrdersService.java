package org.cdut.tzg.service;


import org.cdut.tzg.model.GoodsOrders;

import java.util.List;

/**
 * @author anlan
 * @date 2019/6/26 9:21
 */
public interface GoodsOrdersService {
    /**
     * 通过id获取订单的详情信息
     */
    List<GoodsOrders> findTheOrdersDetial(Integer ordersId);

    /**
     * 商品订单关系信息(OrdersGoods)入库
     */
    int addGoodsOrders(GoodsOrders goodsOrders);
}
