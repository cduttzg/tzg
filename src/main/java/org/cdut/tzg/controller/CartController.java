package org.cdut.tzg.controller;


import org.cdut.tzg.model.*;
import org.cdut.tzg.result.Result;
import org.cdut.tzg.service.*;
import org.cdut.tzg.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.cdut.tzg.result.CodeMsg.*;


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
     * 期望返回格式：[{“商品ID”:”XXX”,”商品图片”:”XXX”, ”描述”:”XXX”,”单价”:XXX,”数量”:XXX,”卖家名称”:”XXX”,”商品库存”:XXX},]
     * 例子：[{“商品ID”:”41655”,”商品图片”:”XXX”, ”描述”:”这是一本书”,”单价”:15.5,”数量”:2, ”卖家名称”:”张三”,”商品库存”:XXX},]
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
            map.put("商品库存",goods.getStock());
            list.add(map);
        }
        return Result.success(list);
    }

    /*
     *URL：/api/cart/updateCartInfo
    描述：更新购物车信息
    方法：POST
    数据：{“用户名”:”XXX”,“商品ID”:”XXX”,”add”:true/false}
    期望返回格式：{“success”:true/false,”content”:”xxxx”}
    */
    @RequestMapping(value = "/updateCartInfo",method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> updateCartInfo(@RequestBody String data){
        Map map= MapUtils.getMap(data);
        String username=(String)map.get("用户名");
        Long goodsId=Long.valueOf((String)map.get("商品ID"));
        boolean add=(boolean)map.get("add");
        User buyer=userService.findUserByName(username);
        Goods goods=goodsService.findGoodsById(goodsId);
        Cart cart=cartService.findCartByUserIdAndGoodsId(buyer.getId(),goods.getId());
        if(add){//增加数量
            if(cart.getNumber()+1>goods.getStock()){
                return Result.error(STOCKOUT);//库存不足
            }
            else {
                cartService.updateGoodsInCart(buyer.getId(),goods.getId(),cart.getNumber()+1);
                return Result.success("数量增加1成功");
            }
        }
        else {//减少数量
            cartService.updateGoodsInCart(buyer.getId(),goodsId,cart.getNumber()-1);
            return Result.success("数量减少1成功");
        }
    }


    /*
    URL：/api/cart/createOrder
    描述：创建订单
    方法：POST
    数据：{“用户名”:”XXX”}
    期望返回格式：{“sucess”:true/false,”content”:”xxxx”,”订单ID”:”xxxx”,"卖家信息":[{"卖家用户名":"XXX",收款码":"XXX","金钱":xxx},]}
     */
    @RequestMapping(value = "/createOrder",method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> createOrder(@RequestBody String data){
        Map map=MapUtils.getMap(data);
        String username=(String)map.get("用户名");

        User buyer=userService.findUserByName(username);

        List<Cart> carts=cartService.findCartByUserId(buyer.getId());
        if(carts.size()==0){//购物车为空
            return Result.error(EMPTY_CART);
        }

        List<GoodsOrders> goodsOrdersList=new ArrayList<>();
        for(int i=0;i<carts.size();++i){
            Cart cart=carts.get(i);
            Goods goods=goodsService.findGoodsById(cart.getGoodsId());
            if(cart.getNumber()<goods.getStock()){
                return Result.error(STOCKOUT);
            }
            GoodsOrders goodsOrders=new GoodsOrders();
            goodsOrders.setGoodsId(cart.getGoodsId());
            goodsOrders.setNumber(cart.getNumber());
            goodsOrders.setSellerId(cart.getSellerId());
            goodsOrdersList.add(goodsOrders);
        }
        //orders订单入库
        Orders orders=new Orders();
        orders.setBuyerId(buyer.getId());
        orders.setState(0);
        orderService.addOrders(orders);

        //新订单入库后重新从数据库读取最新插入的一条订单(就是该订单)来获得订单id
        // TODO: 2019/6/27 存在线程安全问题
        orders=orderService.findTheLatestOrders(1).get(0);

        //GoodsOrder记录入库

    }

}
