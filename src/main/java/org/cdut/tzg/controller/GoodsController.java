package org.cdut.tzg.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.transaction.Transaction;
import org.cdut.tzg.model.Cart;
import org.cdut.tzg.model.Goods;
import org.cdut.tzg.model.User;
import org.cdut.tzg.result.Result;
import org.cdut.tzg.service.CartService;
import org.cdut.tzg.service.GoodsService;
import org.cdut.tzg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
        System.out.println("1111111111");
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,Object> map = null;
        try {
             map = objectMapper.readValue(data,Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(map);
        return Result.success(map);
//        Goods cartGoods=goodsService.findGoodsById(goodsId);//商品
//        System.out.println(cartGoods);
//        if(cartGoods.getStock()-number<0){//库存不足
//            return Result.error(STOCKOUT);
//        }
//        else{
//            User buyer=userService.findUserByName(username);//买家
//            User seller=userService.findUserById(cartGoods.getUserId());//卖家
//            //从商品表更新库存
//            goodsService.updateGoodsStock(cartGoods.getId(),(cartGoods.getStock()-number));
//            //添加到购物车表
//            cartService.insertToCart(buyer.getId(),seller.getId(),cartGoods.getId(),number);
//            String data="添加到购物车成功";
//            return Result.success(data);
//        }
    }


    /**
     URL：/api/goods/buyNow
     描述：立即购买商品
     方法：POST
     数据：{“用户名”:”XXX”,“商品ID”:”XXX”,”数量”:XXX}
     期望返回格式：{"success":true/false,"content":"XXXX"}
     */
}
