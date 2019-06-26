package org.cdut.tzg.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.transaction.Transaction;
import org.cdut.tzg.model.Cart;
import org.cdut.tzg.model.Goods;
import org.cdut.tzg.model.Orders;
import org.cdut.tzg.model.User;
import org.cdut.tzg.result.Result;
import org.cdut.tzg.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.cdut.tzg.result.CodeMsg.STOCKOUT;


@Controller
@RequestMapping(value = "/api/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private GoodsOrdersService goodsOrdersService;

    /**
     URL：/api/goods/getInfo
     描述：获取商品信息
     方法：GET
     数据：{"商品ID":"xxx"}
     期望返回格式：{"卖家名称":"XXX","卖家头像":"XXX",卖家成功交易量":XXX}
     */

    @RequestMapping(value = "/getInfo",method = RequestMethod.GET)
    @ResponseBody
    public Result<Object> getInfo(@RequestParam Long goodsId){
        Goods goods=goodsService.findGoodsById(goodsId);
        User user=userService.findUserById(goods.getUserId());
        Map<String,Object> map=new HashMap<>();
        map.put("卖家名称:",user.getUsername());
        map.put("卖家头像:",user.getAvatar());
        map.put("卖家成功交易量:",user.getTotalSold());
        return Result.success(map);
    }


    /**
     URL：/api/goods/addToCart
     描述：添加商品到购物车
     方法：POST
     数据：{"用户名","XXX","商品ID":"XXX","商品数量":XXX}
     期望返回格式：{"code":XXX,"msg":"XXXX","data":null}
     */
    @RequestMapping(value = "/addToCart",method = RequestMethod.POST)
    @Transactional
    @ResponseBody
    public Result<Object> addToCart(@RequestBody String data){
        ObjectMapper objectMapper = new ObjectMapper();
        Map map = null;
        try {
             map = objectMapper.readValue(data,Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String username = (String) map.get("用户名");
        Long goodsId = Long.valueOf((String)map.get("商品ID"));
        Integer buyedNumber = (Integer) map.get("商品数量");
        Goods cartGoods=goodsService.findGoodsById(goodsId);//商品
        System.out.println(cartGoods);
        if(cartGoods.getStock()-buyedNumber<0){//库存不足
            return Result.error(STOCKOUT);
        }
        else{
            User buyer=userService.findUserByName(username);//买家
            User seller=userService.findUserById(cartGoods.getUserId());//卖家
            //从商品表更新库存
            goodsService.updateGoodsStock(cartGoods.getId(),cartGoods.getStock()-buyedNumber);
            //添加到购物车表
            //寻找购物车中是否存在该商品
            List<Cart> allCartGoods=cartService.findAll(buyer.getId());//获取到该用户的购物车所有商品信息
            for(int i=0;i<allCartGoods.size();++i){//遍历查找是否存在相同商品
                if(goodsId==allCartGoods.get(i).getGoodsId()){
                    cartService.updateGoodsInCart(goodsId,buyedNumber+allCartGoods.get(i).getNumber());
                    return Result.success("添加到购物车成功");
                }
            }
            //不存在直接入库
            Cart cart=new Cart();
            cart.setBuyerId(buyer.getId());
            cart.setSellerId(seller.getId());
            cart.setGoodsId(goodsId);
            cart.setNumber(buyedNumber);
            cartService.insertToCart(cart);
            return Result.success("添加到购物车成功");
        }
    }


    /**
     URL：/api/goods/buyNow
     描述：立即购买商品
     方法：POST
     数据：{“用户名”:”XXX”,“商品ID”:”XXX”,”数量”:XXX}
     期望返回格式：{"success":true/false,"content":"XXXX"}
     */
    @RequestMapping(value = "/buyNow",method =RequestMethod.GET)
    @ResponseBody
    @Transactional
    public Result<Object> buyNow(@RequestParam String data){
        //解析请求体参数
        ObjectMapper objectMapper=new ObjectMapper();
        Map map=null;
        try {
            map=objectMapper.readValue(data,Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String username=(String)map.get("用户名");
        Long goodsId=Long.valueOf((String)map.get("商品ID"));
        Integer number=Integer.valueOf((String)map.get("数量"));

        //orders订单入库
        Goods buyedGoods=goodsService.findGoodsById(goodsId);//获得需要购买的商品信息
        Orders orders=new Orders();//创建新订单
        orders.setBuyerId(buyedGoods.getUserId());//设置订单的buyer_id
        orders.setState(0);//待支付
        //重新获取该orders订单以得到orders表的id 并将GoodsOrders记录入库
        //Date date=new Date();
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return Result.success("生成订单成功,请尽快支付");
    }
}
