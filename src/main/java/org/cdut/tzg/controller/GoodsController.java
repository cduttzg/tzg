package org.cdut.tzg.controller;


import org.cdut.tzg.model.*;
import org.cdut.tzg.result.Result;
import org.cdut.tzg.service.*;
import org.cdut.tzg.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import java.util.*;

import static org.cdut.tzg.result.CodeMsg.*;


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
        map.put("卖家名称",user.getUsername());
        map.put("卖家头像",user.getAvatar());
        map.put("卖家成功交易量",user.getTotalSold());
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
        Map map= MapUtils.getMap(data);
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
            // TODO: 2019/6/26 所有更新商品表库存操作都留在订单支付完后进行
            //goodsService.updateGoodsStock(cartGoods.getId(),cartGoods.getStock()-buyedNumber);
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
    @RequestMapping(value = "/buyNow",method =RequestMethod.POST)
    @ResponseBody
    @Transactional
    public Result<Object> buyNow(@RequestBody String data){
        //解析请求体参数
        Map map= MapUtils.getMap(data);
        String username=(String)map.get("用户名");
        Long goodsId = Long.valueOf((String)map.get("商品ID"));
        Integer number=(Integer) map.get("数量");

        //orders订单入库
        Goods buyedGoods=goodsService.findGoodsById(goodsId);//获得需要购买的商品信息
        Orders orders=new Orders();//创建新订单
        orders.setBuyerId(userService.findUserByName(username).getId());//设置订单的buyer_id
        orders.setState(0);//待支付
        //Date date=new Date();
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        orderService.addOrders(orders);

        //通过最新的一个订单信息重新获取该orders订单以得到orders表的id
        // TODO: 2019/6/26  !!!! 注意  线程安全问题 在这个时候别人插入订单后可能会导致出错 考虑加锁
        orders=orderService.findTheLatestOrders(1).get(0);

        //并将GoodsOrders记录入库
        GoodsOrders goodsOrders=new GoodsOrders();
        goodsOrders.setOrdersId(orders.getId());
        goodsOrders.setGoodsId(goodsId);
        goodsOrders.setSellerId(buyedGoods.getUserId());
        goodsOrders.setNumber(number);
        goodsOrdersService.addGoodsOrders(goodsOrders);
        return Result.success("生成订单成功,请尽快支付");
    }


    /**
     URL：/api/goods/gallery
     描述：获取所有同标签商品
     方法：GET
     数据：{"标签种类":"XXX"}
     期望返回数据：[{“商品标签”:”XXX”,”商品名称”:”XXX”,”描述”:”XXX”,”单价”:XXX,”数量”:XXX,”卖家ID”:XXX,”商品ID”:XXX,”商品图片”:”XXX”,"联系方式":"XXX"},]
     */
    @RequestMapping(value = "/gallery",method = RequestMethod.GET)
    @ResponseBody
    public Result<Object> gallary(@RequestParam Integer type){
        List<Map<String,Object>> list=new ArrayList<>();//返回的数组
        List<Goods> goodsList=goodsService.findSameTypeGoodsByType(type);//该类型所有商品数组
        if(goodsList.size()==0){
            return Result.error(EMPTY_TYPE_GOODS);
        }
        for(int i=0;i<goodsList.size();++i){
            Map<String,Object> map=new HashMap<>();
            Goods goods=goodsList.get(i);
            map.put("商品标签",goods.getType());
            map.put("商品名称",goods.getTitle());
            map.put("描述",goods.getContent());
            map.put("单价",goods.getPrice());
            map.put("数量",goods.getStock());
            map.put("卖家ID",goods.getUserId());
            map.put("商品ID",goods.getId());
            map.put("商品图片",goods.getImage());
            map.put("联系方式",userService.findUserById(goods.getUserId()).getPhoneNumber());
            list.add(map);
        }
        return Result.success(list);
    }

}
