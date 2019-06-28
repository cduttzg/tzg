package org.cdut.tzg.controller;

import org.cdut.tzg.model.*;
import org.cdut.tzg.result.Result;
import org.cdut.tzg.service.*;
import org.cdut.tzg.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.cdut.tzg.result.CodeMsg.NO_ORDERS;
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

    @RequestMapping("/index")
    public String index(){
        return "index";
    }


    /***
     *
     * URL：/api/home/cartInfo
     * 描述：获取购物车信息、查询用户是否被冻结、查询用户角色
     * 方法：GET
     * 数据：{“用户名”:”XXX”}
     * 期望返回格式：{"data":[{"商品名称":"XXX",”商品图片”:”XXX”, ”描述”:”XXX”,”单价”:XXX,”数量”:XXX},],”是否被冻结”:true/false,"角色":0/1/2}
     * 返回：
     *  {"code":200,"msg":"success","data":{"角色":0,"是否被冻结":false,"data":[{"商品名称":"张宇高数视频","数量":9,"商品图片":null,"单价":50.0,"描述":"张宇高数视频，包括线性代数、高数和概率学。"}]}}
     *  {"code":500201,"msg":"未找到该用户","data":null}
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
     * 返回：
     *  {"code":500202,"msg":"没有订单","data":null}
     *  {"code":200,"msg":"success","data":{"data":[{"商品名称":"张宇高数视频","买家姓名":"小红","订单时间":"2019-6-28"},{"商品名称":"张宇高数视频","买家姓名":"小红","订单时间":"2019-6-28"}]}}
     */
    @RequestMapping(value = "/OrderInfo", method = RequestMethod.GET)
    @ResponseBody
    public Result<Object> orderInfo(@RequestParam Integer number) {
        List<Orders> orders = orderService.findTheLatestOrders(number);
        if(orders==null || orders.size()==0)
            return Result.error(NO_ORDERS);
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
            map.put("订单时间", TimeUtils.date2Day(order.getCreatedTime()));
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
     * 期望返回格式：期望返回格式：{"书籍":[{”商品名称”:”XXX”,”描述”:”XXX”,”单价”:XXX,”数量”:XXX,”卖家ID”:XXX,”商品ID”:XXX,”商品图片”:”XXX”},],"virtual":[{”商品名称”:”XXX”,”描述”:”XXX”,”单价”:XXX,”数量”:XXX,”卖家ID”:XXX,”商品ID”:XXX,”商品图片”:”XXX”},],"房屋":[{”商品名称”:”XXX”,”描述”:”XXX”,”单价”:XXX,”数量”:XXX,”卖家ID”:XXX,”商品ID”:XXX,”商品图片”:”XXX”},],"其它":[{”商品名称”:”XXX”,”描述”:”XXX”,”单价”:XXX,”数量”:XXX,”卖家ID”:XXX,”商品ID”:XXX,”商品图片”:”XXX”},],"福利":[{”商品名称”:”XXX”,”描述”:”XXX”,”单价”:XXX,”数量”:XXX,”卖家ID”:XXX,”商品ID”:XXX,”商品图片”:”XXX”},]}
     * 返回：
     *  {"code":200,"msg":"success","data":{"房屋":[{"卖家ID":4,"商品名称":"东苑E12套间一套三合租","数量":1,"商品ID":6,"单价":750.0,"商品图片":null,"描述":"东苑E12栋20楼一套三求合租朋友，仅限女，房租1500一月两人平摊。"}],"福利":[{"卖家ID":2,"商品名称":"高级Web课程设计报告","数量":983,"商品ID":4,"单价":100.0,"商品图片":null,"描述":"还在为高级web的程序设计而烦恼吗？赶紧入手这份实验报告吧！"}],"其它":[],"书籍":[{"卖家ID":3,"商品名称":"张宇高数视频","数量":969,"商品ID":5,"单价":50.0,"商品图片":null,"描述":"张宇高数视频，包括线性代数、高数和概率学。"},{"卖家ID":9,"商品名称":"操场空气","数量":99999999,"商品ID":28,"单价":399.0,"商品图片":null,"描述":"还在为不想出门而烦恼吗？还在为空气沉闷而困惑吗？来吧，操场空气解决你的烦恼!!"},{"卖家ID":9,"商品名称":"操场空气","数量":99999999,"商品ID":29,"单价":399.0,"商品图片":null,"描述":"还在为不想出门而烦恼吗？还在为空气沉闷而困惑吗？来吧，操场空气解决你的烦恼!!"},{"卖家ID":9,"商品名称":"操场空气","数量":99999999,"商品ID":30,"单价":399.0,"商品图片":null,"描述":"还在为不想出门而烦恼吗？还在为空气沉闷而困惑吗？来吧，操场空气解决你的烦恼!!"},{"卖家ID":9,"商品名称":"操场空气","数量":99999999,"商品ID":31,"单价":399.0,"商品图片":"沙尘暴.jpeg","描述":"还在为不想出门而烦恼吗？还在为空气沉闷而困惑吗？来吧，操场空气解决你的烦恼!!"},{"卖家ID":9,"商品名称":"操场空气","数量":99999999,"商品ID":32,"单价":399.0,"商品图片":"沙尘暴.jpeg","描述":"还在为不想出门而烦恼吗？还在为空气沉闷而困惑吗？来吧，操场空气解决你的烦恼!!"},{"卖家ID":6,"商品名称":"法术穿透鞋","数量":975,"商品ID":34,"单价":800.0,"商品图片":"","描述":"提莫不出攻速鞋？"},{"卖家ID":6,"商品名称":"男的女的折磨","数量":970,"商品ID":35,"单价":3600.0,"商品图片":null,"描述":"大面具？打扰了"}]}}
     *  {"code":200,"msg":"success","data":{"房屋":[],...........} (没有为空)
     */
    @RequestMapping("/")
    @ResponseBody
    public Result<Object> home() {
        Map<String, Object> data = new HashMap<>();
        List<Goods> OthersGoods = goodsService.findGoodsByTypeAndLimit(0,8);
        List<Goods> virtualGoods = goodsService.findGoodsByTypeAndLimit(1,8);
        List<Goods> bookGoods = goodsService.findGoodsByTypeAndLimit(2,8);
        List<Goods> houseGoods = goodsService.findGoodsByTypeAndLimit(3,8);
        List<Goods> activityGoods = goodsService.findGoodsByTypeAndLimit(4,1);
        if(OthersGoods==null)
            data.put("其它",null);
        else
            data.put("其它",setGoodsInfo(OthersGoods));
        if(virtualGoods==null)
            data.put("虚拟",null);
        else
            data.put("虚拟",setGoodsInfo(virtualGoods));
        if(bookGoods==null)
            data.put("书籍",null);
        else
            data.put("书籍",setGoodsInfo(bookGoods));
        if(houseGoods==null)
            data.put("房屋",null);
        else
            data.put("房屋",setGoodsInfo(houseGoods));
        if(activityGoods==null)
            data.put("福利",null);
        else
            data.put("福利",setGoodsInfo(activityGoods));
        return Result.success(data);
    }


    private List setGoodsInfo(List<Goods> listGoods){
        List<Map<String, Object>> list = new ArrayList<>();
        for(Goods goods:listGoods){
            Map<String, Object> map = new HashMap<>();
            map.put("商品名称",goods.getTitle());
            map.put("描述",goods.getContent());
            map.put("单价",goods.getPrice());
            map.put("数量",goods.getStock());
            map.put("卖家ID",goods.getUserId());
            map.put("商品ID",goods.getId());
            map.put("商品图片",goods.getImage());
            list.add(map);
        }
        return list;
    }

}
