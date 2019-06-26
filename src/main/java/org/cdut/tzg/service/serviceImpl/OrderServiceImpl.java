package org.cdut.tzg.service.serviceImpl;

import org.cdut.tzg.mapper.OrdersMapper;
import org.cdut.tzg.model.Orders;
import org.cdut.tzg.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrdersMapper ordersMapper;

    //获取所有订单数量
    @Override
    public int getAllOrdersCount(){
        return ordersMapper.getAllOrdersCount();
    }

    //获取指定日期订单数量
    @Override
    public int getCompletedOrdersCount(Date date){
        return ordersMapper.getCompletedOrdersCount(date);
    }

    //获取指定id订单
    public Orders getOrderById(int orderId){
        return ordersMapper.getOrderById(orderId);
    }

    //更新指定订单号订单状态为异常
    @Override
    public int setOrderException(int orderId){
        return ordersMapper.setOrderException(orderId);
    }
}
