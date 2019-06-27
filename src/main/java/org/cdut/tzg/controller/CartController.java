package org.cdut.tzg.controller;


import org.cdut.tzg.model.Cart;
import org.cdut.tzg.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.ws.Response;


@Controller
@RequestMapping(value = "/api/cart")

public class CartController {
    @Autowired
    private UserService userService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private GoodsOrdersService goodsOrdersService;



    /**
     *URL：/api/cart/cartInfo
     * 描述：获取购物车信息
     * 方法：GET
     * 数据：{“用户名”:”XXX”}
     * 期望返回格式：[{“商品ID”:”XXX”,”商品图片”:”XXX”, ”描述”:”XXX”,”单价”:XXX,”数量”:XXX,”卖家名称”:”XXX”},]
     * 例子：[{“商品ID”:”41655”,”商品图片”:”XXX”, ”描述”:”这是一本书”,”单价”:15.5,”数量”:2, ”卖家名称”:”张三”},]
     */
    @RequestMapping(value = "/cartInfo",method = RequestMethod.GET)
    @ResponseBody
    public Response<Object> cartInfo(@RequestParam String username){
        
    }
}
