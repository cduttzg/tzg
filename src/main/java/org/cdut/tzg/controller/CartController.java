package org.cdut.tzg.controller;


import org.cdut.tzg.model.Cart;
import org.cdut.tzg.model.Goods;
import org.cdut.tzg.model.User;
import org.cdut.tzg.result.Result;
import org.cdut.tzg.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.cdut.tzg.result.CodeMsg.EMPTY_CART;


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
    public Result<Object> cartInfo(@RequestParam String username){
        User buyer=userService.findUserByName(username);
        List<Cart> carts=cartService.findCartByUserId(buyer.getId());
        if(carts.size()<=0){
            return Result.error(EMPTY_CART);
        }
        List<Map<String,Object>> list=new ArrayList<>();
        for(int i=0;i<carts.size();++i){//遍历购物车数组 得到每条购物车记录中的商品和卖家信息
            Cart cart=carts.get(i);//当前商品
            Goods goods=goodsService.findGoodsById(cart.getGoodsId());//当前购物车该商品的信息
            User seller=userService.findUserById(cart.getSellerId());//当前购物车中该商品的买家信息
            Map<String,Object> map=new HashMap<>();
            map.put("商品ID",goods.getId());
            map.put("商品图片",goods.getImage());
            map.put("描述",goods.getContent());
            map.put("单价",goods.getPrice());
            map.put("数量",cart.getNumber());
            map.put("卖家名称",seller.getUsername());
            list.add(map);
        }
        return Result.success(list);
    }
}
