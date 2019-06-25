package org.cdut.tzg.controller;

import org.cdut.tzg.model.Cart;
import org.cdut.tzg.model.Goods;
import org.cdut.tzg.model.User;
import org.cdut.tzg.result.Result;
import org.cdut.tzg.service.CartService;
import org.cdut.tzg.service.GoodsService;
import org.cdut.tzg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author anlan
 * @date 2019/6/25 14:43
 */
@Controller
@RequestMapping("/api/home")
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private GoodsService goodsService;

    /***
     *
     *
     * URL：/api/home/cartInfo
     * 描述：获取购物车信息、查询用户是否被冻结、查询用户角色
     * 方法：GET
     * 数据：{“用户名”:”XXX”}
     * 期望返回格式：{"data":[{"商品名称":"XXX",”商品图片”:”XXX”, ”描述”:”XXX”,”单价”:XXX,”数量”:XXX},],”是否被冻结”:true/false,"角色":0/1/2}
     * 例子：{"data":[{"商品名称":"《BOOK》",”商品图片”:”XXX”, ”描述”:”这是一本书”,”单价”:15.5,”数量”:2},],”是否被冻结”:false,"角色":0}
     */
    @RequestMapping(value = "/cartInfo",method = RequestMethod.GET)
    @ResponseBody
    public Result<Object> cartInfo(@RequestParam String username) {
//        获取用户数据并封装到map里
        Map<String, Object> data = new HashMap<>();
        User user = userService.findUserByName(username);
        boolean isFrozen = user.getIsFrozen() == 1;
        data.put("是否被冻结", isFrozen);
        data.put("角色",user.getRole());

//        获取购物车信息并封装到map里
        List<Cart> carts = cartService.findAll(user.getId());
        List<Map<String,Object>> list = new ArrayList<>();
        for(int i=0;i<carts.size();i++){
//            查询商品的具体信息并封装到map里
            Map<String,Object> map = new HashMap<>();
            Goods goods = goodsService.findGoodsById(carts.get(i).getGoodsId());
            map.put("商品名称",goods.getTitle());
            map.put("描述",goods.getContent());
            map.put("商品图片",goods.getImage());
            map.put("单价",goods.getPrice());
            map.put("数量",carts.get(i).getNumber());
            list.add(map);
        }
        data.put("data", list);
        System.out.println(data);
        return Result.success(data);
    }
}
