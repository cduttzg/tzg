package org.cdut.tzg.service.serviceImpl;

import org.cdut.tzg.mapper.OrdersMapper;
import org.cdut.tzg.model.Orders;
import org.cdut.tzg.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrdersMapper ordersMapper;

    /*
    * 获取后台订单数据
    * */
    @Override
    public List<Orders> getAllOrders(){
        return ordersMapper.getAllOrders();
    }
}
