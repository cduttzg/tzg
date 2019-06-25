package org.cdut.tzg.controller;

import org.cdut.tzg.model.Goods;
import org.cdut.tzg.model.Orders;
import org.cdut.tzg.service.GoodsService;
import org.cdut.tzg.service.OrderService;
import org.cdut.tzg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Carhung
 * @date 2019/6/25 16:29
 */
@Controller
@RequestMapping("/api/backstage")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private GoodsService goodsService;

    /**
    * 获取后台订单信息
    * 期望返回格式：{"网站活跃度":[xxx,xxx,xxx],"网站日交易量":[xxx,xxx,xxx],"网站总交易量":xxx,"求购标签":[{"标签名1":xxx},{"标签名2":xxx},{"标签名3":xxx},...]}
    * 例子：{"网站活跃度":[12,23,34],"网站日交易量":[12,23,34],"网站总交易量":800,"求购标签":[{"标签名1":111},{"标签名2":222},{"标签名3":333},...]}
    */
    @RequestMapping(value = "/getData",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getAllOrders(){
        //返回数据载体
        Map<String,Object> map = new HashMap<>();
        //存储每日活跃量、销售量
        Integer[] active = new Integer[7];
        Integer[] daySold = new Integer[7];

        //获取一周内日期
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTime(date);
        //统计前七日的活跃度、交易量
        for(int i=6;i>=0;i--){
            calendar.add(calendar.DAY_OF_MONTH,-i);
            //System.out.println(sdf.format(calendar.getTime()));
            int orderCount = goodsService.getGoodsCount(calendar.getTime());
            int daySoldCount = orderService.getCompletedOrdersCount(calendar.getTime());
            active[6-i] = orderCount;
            daySold[6-i] = daySoldCount;
            calendar.setTime(date);
        }
        int ordersCount = orderService.getAllOrdersCount();
        map.put("网站活跃度",active);
        map.put("网站日交易量",daySold);
        map.put("网站总交易量",ordersCount);
        //System.out.println(map);
        return map;
    }
}
