package org.cdut.tzg.controller;

import org.apache.ibatis.transaction.Transaction;
import org.cdut.tzg.model.Goods;
import org.cdut.tzg.model.User;
import org.cdut.tzg.result.Result;
import org.cdut.tzg.service.GoodsService;
import org.cdut.tzg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping(value = "/api/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private UserService userService;

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
     数据：{"商品ID":"XXX"}
     期望返回格式：{"success":true/false,"content":"XXXX"}
     */
    @RequestMapping(value = "/addToCart",method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> addToCart(@RequestParam Long goodsId){
        Goods goods=goodsService.findGoodsById(goodsId);
        Map<String,Object> map=new HashMap<>();
        if(goods.getStock()<=0){//库存为0
            map.put("success:",goods.getStock()<=0);
            map.put("content:","抱歉!该商品已售完!");
        }
        else{
            //to-do:使用事务 对商品列表对应商品库存-1 后加入购物车表 判断购物车中是否有该物品 有则number+1
            //Q: 添加到购物车需不需要指定数量?需不需要将数据库中的库存-1？
        }
        //Q:当商品库存为0时返回Result.fail还是返回Result.fail?
        return Result.success(map);
    }


    /**
     URL：/api/goods/buyNow
     描述：立即购买商品
     方法：POST
     数据：{“用户名”:”XXX”,“商品ID”:”XXX”,”数量”:XXX}
     期望返回格式：{"success":true/false,"content":"XXXX"}
     */
}
