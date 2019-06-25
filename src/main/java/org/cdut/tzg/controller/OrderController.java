package org.cdut.tzg.controller;

import org.cdut.tzg.model.Orders;
import org.cdut.tzg.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Carhung
 * @date 2019/6/25 16:29
 */
@Controller
@RequestMapping("/backstage")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("getData")
    @RequestBody
    public Map<String,Object> getAllOrders(){
        //返回数据载体
        Map<String,Object> map = new HashMap<>();
        List<Orders> list = orderService.getAllOrders();
        //
        return map;
    }
}
