package org.cdut.tzg.controller;

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
}
