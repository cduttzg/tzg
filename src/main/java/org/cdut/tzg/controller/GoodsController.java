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
     *URL：/api/goods/getInfo
     *描述：获取商品信息
     *方法：GET
     *数据：data:{"商品ID":"xxx"}
     *期望返回格式：{"卖家名称":"XXX","卖家头像":"XXX",卖家成功交易量":XXX}
     *
     *返回：
     *成功：{"code":200,"msg":"success","data":{"卖家成功交易量":0,"卖家名称":"jack","卖家头像":null}}
     *商品不在商品表中：{"code":500303,"msg":"商品不存在","data":null}
     *商品的卖家不在用户表中：{"code":500201,"msg":"未找到该用户","data":null}
     */

    @RequestMapping(value = "/getInfo",method = RequestMethod.GET)
    @ResponseBody
    public Result<Object> getInfo(@RequestParam Long goodsId){
        Goods goods=goodsService.findGoodsById(goodsId);
        if(goods==null){
            return Result.error(NO_GOODS);
        }
        User user=userService.findUserById(goods.getUserId());
        if(user==null) {
            return Result.error(USER_UNDEFIND);//没有这个用户
        }
        Map<String,Object> map=new HashMap<>();
        map.put("卖家名称",user.getUsername());
        map.put("卖家头像",user.getAvatar());
        map.put("卖家成功交易量",user.getTotalSold());
        return Result.success(map);
    }


    /**
     *URL：/api/goods/addToCart
     *描述：添加商品到购物车
     *方法：POST
     *数据：{"用户名","XXX","商品ID":"XXX","商品数量":XXX}
     *期望返回格式：{"code":XXX,"msg":"XXXX","data":null}
     *
     *返回:
     *成功：{"code":200,"msg":"success","data":"添加到购物车成功"}
     *失败：要添加的商品超出了商品表中的库存：{"code":500301,"msg":"库存不足","data":null}
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
                    cartService.updateGoodsInCart(buyer.getId(),goodsId,buyedNumber+allCartGoods.get(i).getNumber());
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
     *描述：立即购买商品
     *方法：POST
     *数据：{“用户名”:”XXX”,“商品ID”:”XXX”,”数量”:XXX}
     *期望返回格式：{"code":XXX,"msg":"xxx","data":{”订单ID”:”xxxx”,"卖家信息":{"卖家用户名":"XXX",收款码":"XXX","金钱":xxx}}}
     *
     *返回：
     *成功：立即购买成功：{"code":200,"msg":"success","data":{"卖家信息":{"收款码":"jack的收款码","卖家用户名":"jack","金钱":50},"订单ID":23}}
     *失败:立即购买的商品库存不足：{"code":500301,"msg":"库存不足","data":null}
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

        if(buyedGoods.getStock()<number) {//库存不足
            return Result.error(STOCKOUT);
        }

        Orders orders=new Orders();//创建新订单
        orders.setBuyerId(userService.findUserByName(username).getId());//设置订单的buyer_id
        orders.setState(0);//待支付
        //Date date=new Date();
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        orderService.addOrders(orders);

        //通过最新的一个订单信息重新获取该orders订单以得到orders表的id
        // TODO: 2019/6/26  !!!! 注意  线程安全问题 在这个时候别人插入订单后可能会导致出错 考虑加锁
        orders=orderService.findTheLatestOrders(1).get(0);

        //GoodsOrders记录入库
        GoodsOrders goodsOrders=new GoodsOrders();
        goodsOrders.setOrdersId(orders.getId());
        goodsOrders.setGoodsId(goodsId);
        goodsOrders.setSellerId(buyedGoods.getUserId());
        goodsOrders.setNumber(number);
        goodsOrdersService.addGoodsOrders(goodsOrders);

        //更新商品表库存
        goodsService.updateGoodsStock(buyedGoods.getId(), buyedGoods.getStock() - number);
        //库存是否为0
        if(buyedGoods.getStock()==number){
            goodsService.updateTypeState(goodsId,-1);
        }

        //获取交易付款消息
        User seller=userService.findUserById(buyedGoods.getUserId());
        Map<String,Object> sellerInfo=new HashMap<>();
        sellerInfo.put("卖家用户名",seller.getUsername());
        sellerInfo.put("收款码",seller.getMoneyCode());
        sellerInfo.put("金钱",buyedGoods.getPrice()*number);
        Map<String,Object> result=new HashMap<>();
        result.put("订单ID",orders.getId());
        result.put("卖家信息",sellerInfo);
        return Result.success(result);
    }


    /**
     *URL：/api/goods/gallery
     *描述：获取所有同标签商品
     *方法：GET
     *数据：{"标签种类":"XXX"}
     *期望返回数据：[{“商品标签”:”XXX”,”商品名称”:”XXX”,”描述”:”XXX”,”单价”:XXX,”数量”:XXX,”卖家ID”:XXX,”商品ID”:XXX,”商品图片”:”XXX”,"联系方式":"XXX"},]
     *
     *返回：
     *查询同种类型商品成功：
     *{"code":200,"msg":"success","data":[{"卖家ID":2,"商品名称":"Java","数量":1,"商品ID":2,"单价":64.8,"商品图片":null,"联系方式":"15196684789","商品标签":1,"描述":"Java程序设计"}
     *,{"卖家ID":2,"商品名称":"C++Primer","数量":2,"商品ID":3,"单价":64.8,"商品图片":null,"联系方式":"15196684789","商品标签":1,"描述":"C++Prime第五版"}
     *,{"卖家ID":6,"商品名称":"蘑菇蘑菇蘑菇","数量":928,"商品ID":33,"单价":40.0,"商品图片":null,"联系方式":"13512345678","商品标签":1,"描述":"提莫快来买蘑菇"}]}
     *
     *失败，没有该类型的商品：{"code":500302,"msg":"当前没有该类型商品","data":null}
     *失败，其中如果有没有卖家的商品 {"code":500201,"msg":"未找到该用户","data":null}
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
            User seller=userService.findUserById(goods.getUserId());
            if(seller==null){
                return Result.error(USER_UNDEFIND);//种类商品中可能存在没有卖家的商品/?会吗 会吧
            }
            map.put("联系方式",userService.findUserById(goods.getUserId()).getPhoneNumber());
            list.add(map);
        }
        return Result.success(list);
    }

}
