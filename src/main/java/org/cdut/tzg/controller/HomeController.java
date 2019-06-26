package org.cdut.tzg.controller;

import org.cdut.tzg.model.*;
import org.cdut.tzg.result.Result;
import org.cdut.tzg.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.cdut.tzg.result.CodeMsg.USER_UNDEFIND;

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

    @Autowired
    private OrderService orderService;

    @Autowired
    private GoodsOrdersService goodsOrdersService;

    /***
     *
     * URL：/api/home/cartInfo
     * 描述：获取购物车信息、查询用户是否被冻结、查询用户角色
     * 方法：GET
     * 数据：{“用户名”:”XXX”}
     * 期望返回格式：{"data":[{"商品名称":"XXX",”商品图片”:”XXX”, ”描述”:”XXX”,”单价”:XXX,”数量”:XXX},],”是否被冻结”:true/false,"角色":0/1/2}
     * 例子：{"data":[{"商品名称":"《BOOK》",”商品图片”:”XXX”, ”描述”:”这是一本书”,”单价”:15.5,”数量”:2},],”是否被冻结”:false,"角色":0}
     */
    @RequestMapping(value = "/cartInfo", method = RequestMethod.GET)
    @ResponseBody
    public Result<Object> cartInfo(@RequestParam String username) {
        //获取用户数据并封装到map里
        Map<String, Object> data = new HashMap<>();
        User user = userService.findUserByName(username);
        if (user == null)
            return Result.error(USER_UNDEFIND);
        boolean isFrozen = user.getIsFrozen() == 1;
        data.put("是否被冻结", isFrozen);
        data.put("角色", user.getRole());


        //获取购物车信息并封装到map里
        List<Cart> carts = cartService.findAll(user.getId());
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < carts.size(); i++) {
            //查询商品的具体信息并封装到map里
            Map<String, Object> map = new HashMap<>();
            Goods goods = goodsService.findGoodsById(carts.get(i).getGoodsId());
            map.put("商品名称", goods.getTitle());
            map.put("描述", goods.getContent());
            map.put("商品图片", goods.getImage());
            map.put("单价", goods.getPrice());
            map.put("数量", carts.get(i).getNumber());
            list.add(map);
        }
        data.put("data", list);
        return Result.success(data);
    }

    /***
     * URL：/api/home/OrderInfo
     * 描述：实时购买展示
     * 方法：GET
     * 数据：{“number”:1}
     * 期望返回格式：{“买家姓名”:”XXX”,”,”商品名称”:”XXX”,”订单时间”:”XXX” }
     * 例子：{”买家姓名”:”张三” ,”商品名称”:”书” ,”订单时间”:”2019-6-24”}
     */
    @RequestMapping(value = "/OrderInfo", method = RequestMethod.GET)
    @ResponseBody
    public Result<Object> orderInfo(@RequestParam Integer number) {
        List<Orders> orders = orderService.findTheLatestOrders(number);
        Map<String, Object> data = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();
        for (Orders order : orders) {
            Map<String, Object> map = new HashMap<>();
            String buyerName = userService.findUserById(order.getBuyerId()).getUsername();
            map.put("买家姓名", buyerName);
            List<GoodsOrders> goodsOrders = goodsOrdersService.findTheOrdersDetailById(order.getId());
            StringBuilder goodsNames = new StringBuilder();
            for (int j = 0; j < goodsOrders.size(); j++) {
                goodsNames.append(goodsService.findGoodsById(goodsOrders.get(j).getGoodsId()).getTitle());
                if (j != goodsOrders.size() - 1) {
                    goodsNames.append("、");
                }
            }
            map.put("商品名称", goodsNames);
            map.put("订单时间", order.getCompletedTime());
            list.add(map);
        }
        data.put("data", list);
        return Result.success(data);
    }

    /***
     * URL：/api/home
     * 描述：首页商品信息展示
     * 方法：GET
     * 数据：书籍商品信息*8、虚拟商品信息*8、房屋商品信息*8、其它商品信息*8、福利商品信息*1。
     * 期望返回格式：{“商品标签”:”XXX”,”商品名称”:”XXX”,”描述”:”XXX”,”单价”:XXX,”数量”:XXX,”卖家ID”:XXX,”商品ID”:XXX,”商品图片”:”XXX”}
     * 例子：{“商品标签”:”书籍”,”商品名称”:”这是一本书”,”描述”:”这是一本很好看的书”,”单价”:15.9,”数量”:1,”卖家ID”:132,”商品ID”:5,”商品图片”:”XXX”}
     */
    @RequestMapping("")
    @ResponseBody
    public Result<Object> home() {
        return Result.success(1);
    }

}
