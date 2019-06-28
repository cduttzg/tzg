package org.cdut.tzg.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.bcel.internal.classfile.Code;
import org.cdut.tzg.mapper.OrdersMapper;
import org.cdut.tzg.model.Goods;
import org.cdut.tzg.model.Orders;
import org.cdut.tzg.model.User;
import org.cdut.tzg.result.CodeMsg;
import org.cdut.tzg.result.Result;
import org.cdut.tzg.service.GoodsService;
import org.cdut.tzg.service.OrderService;
import org.cdut.tzg.service.UserService;
import org.cdut.tzg.utils.MapUtils;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Carhung
 * @date 2019/6/25 16:29
 */
@Controller
@RequestMapping("/api/backstage")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private UserService userService;

    /**
    * 获取后台订单信息
    * 期望返回格式：{"网站活跃度":[xxx,xxx,xxx],"网站日交易量":[xxx,xxx,xxx],"网站总交易量":xxx,"求购标签":[{"标签名1":xxx},{"标签名2":xxx},{"标签名3":xxx},...]}
    * 例子：{"网站活跃度":[12,23,34],"网站日交易量":[12,23,34],"网站总交易量":800,"求购标签":[{"标签名1":111},{"标签名2":222},{"标签名3":333},...]}
    */
    @RequestMapping(value = "/getData",method = RequestMethod.POST)
    @ResponseBody
    public Result<Map<String, Object>> getAllOrders(){
        //返回数据载体
        Map<String,Object> map = new HashMap<>();
        //存储每日活跃量、销售量
        Integer[] active = new Integer[7];
        Integer[] daySold = new Integer[7];

        //获取一周内日期
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTime(date);
        //统计前七日的活跃度、交易量
        for(int i=7;i>0;i--){
            calendar.add(calendar.DAY_OF_MONTH,-i);
            //System.out.println(sdf.format(calendar.getTime()));
            int orderCount = goodsService.getGoodsCount(calendar.getTime());
            int daySoldCount = orderService.getCompletedOrdersCount(calendar.getTime());
            active[7-i] = orderCount;
            daySold[7-i] = daySoldCount;
            calendar.setTime(date);
        }
        //统计各种求购标签的商品数量
        List<Map<String,Integer>> list = new ArrayList<>();
        String[] tags = new String[]{"水票","书籍","寝室神器","租房","文具","电脑办公","游戏道具","体育用具","乐器","电器","装饰品","其他"};
        for (int i=0; i<12; i++){
            Map<String, Integer> tagmap = new HashMap<>();
            tagmap.put(tags[i],goodsService.getGoodsNumByTags(i));
            list.add(tagmap);
        }
        int ordersCount = orderService.getAllCompletedOrdersCount();
        map.put("网站活跃度",active);
        map.put("网站日交易量",daySold);
        map.put("网站总交易量",ordersCount);
        map.put("求购数量",list);
        //System.out.println(map);
        return Result.success(map);
    }

    /*
    * 更新指定订单号订单状态为异常
    * 方法：POST
    * 数据：{"订单ID":"XXX"}
    * 期望返回格式：{"success":true/false,"content":"XXXX"}
    * */
    @RequestMapping(value = "/manageOrder",method = RequestMethod.POST)
    @ResponseBody
    public Result<Map<String,Object>> setOrderException(@RequestBody String data){
        Map<String,Object> map = new HashMap<>();
        Map maps = MapUtils.getMap(data);
        Long orderId = Long.valueOf((Integer) maps.get("orderId"));
        Orders order = orderService.getOrderById(orderId);
        if (order != null) {
            int sign = orderService.setOrderException(orderId);
            if (sign == 1) {
                map.put("success", true);
                map.put("content", "置为异常成功");
            } else if (sign == 0) {
                Result.error(CodeMsg.REPETITIVE_OPERATION);
            }
        }
        else {
            return Result.error(CodeMsg.NO_ORDER);
        }
        return Result.success(map);
    }

    /*
    * 获取所有冻结用户
    * 方法：GET
    * 数据：null
    * 期望返回格式：{"用户名":"XXX","电话":"xxx","角色":0/1/2}
    * */
    @RequestMapping(value = "/getFrozenUser",method = RequestMethod.GET)
    @ResponseBody
    public Result<List<Map<String,Object>>> getAllFreezeUsers(){
        List<Map<String,Object>> list = new ArrayList<>();
        List<User> users = userService.getAllFreezeUsers();
        if (users.size() == 0)
            return Result.error(CodeMsg.NO_FROZENUSER);
        else{
            for (int i=0; i<users.size();i++){
                User user = users.get(i);
                Map<String,Object> map = new HashMap<>();
                map.put("用户名",user.getUsername());
                map.put("学号",user.getSchoolNumber());
                map.put("电话",user.getPhoneNumber());
                map.put("角色",user.getRole());
                list.add(map);
            }
            return Result.success(list);
        }
    }

    /*
    * 冻结指定用户
    * 方法：POST
    * 数据：{"用户学号":"XXX"}
    * 期望返回格式：{"code":XXX,"msg":"xxx","data":"XXX"}
    * */
    @RequestMapping(value = "/freezeUser",method = RequestMethod.POST)
    @ResponseBody
    public Result<Map<String,Object>> setFreezeUser(@RequestBody String data){
        Map maps = MapUtils.getMap(data);
        String schoolNum = (String) maps.get("schoolNum");
        Map<String,Object> map = new HashMap<>();
        User user = userService.getUserBySchoolNum(schoolNum);
        if (user == null)
            return Result.error(CodeMsg.USER_UNDEFIND);
        else {
            int sign = userService.setFreezeUser(schoolNum);
            if (sign ==1){
                map.put("success",true);
                map.put("content","用户已冻结");
            }else{
                Result.error(CodeMsg.REPETITIVE_OPERATION);
            }
        }
        return Result.success(map);
    }

    /*
    * 根据学号设置管理员
    * 方法：POST
    * 数据：{"用户学号":"XXX"}
    * 期望返回格式：{"code":XXX,"msg":"xxx","data":"XXX"}
    * */
    @RequestMapping(value = "/addAdmin",method = RequestMethod.POST)
    @ResponseBody
    public Result<Map<String,Object>> addAdministrator(@RequestBody String data){
        Map maps = MapUtils.getMap(data);
        String schoolNum = (String) maps.get("schoolNum");
        Map<String,Object> map = new HashMap<>();
        User user = userService.getUserBySchoolNum(schoolNum);
        if (user == null)
            return Result.error(CodeMsg.USER_UNDEFIND);
        else {
            int sign = userService.setAdministrator(schoolNum);
            if (sign == 1){
                map.put("success",true);
                map.put("content","该用户升级为管理员");
            }else if (sign == 0){
                return Result.error(CodeMsg.REPETITIVE_OPERATION);
            }
        }
        return Result.success(map);
    }
    /*
    * 删除管理员
    * */
    @RequestMapping(value = "/deletAdmin",method = RequestMethod.POST)
    @ResponseBody
    public Result<Map<String,Object>> deletAdministrator(@RequestBody String data){
        Map maps = MapUtils.getMap(data);
        String schoolNum = (String) maps.get("schoolNum");
        Map<String,Object> map = new HashMap<>();
        User user = userService.getUserBySchoolNum(schoolNum);
        if (user != null){
            int sign = userService.deletAdministrator(schoolNum);
            if (sign == 1){
                map.put("success",true);
                map.put("content","删除管理员成功");
            }else if (sign == 0)
                return Result.error(CodeMsg.REPETITIVE_OPERATION);
        }else
            return Result.error(CodeMsg.USER_UNDEFIND);
        return Result.success(map);
    }
    /*
    * 获取所有管理员
    * */
    @RequestMapping(value = "/getAdmin",method = RequestMethod.POST)
    @ResponseBody
    public Result<List<Map<String,String>>> getAllAdministrator(){
        List<User> users = userService.getAllAdministrator();
        if (users.size() == 0)
            return Result.error(CodeMsg.NO_ADMINISTRATOR);
        List<Map<String,String>> list = new ArrayList<>();
        for (int i=0; i<users.size(); i++){
            User user = users.get(i);
            Map<String,String> map = new HashMap<>();
            map.put("用户名",user.getUsername());
            map.put("电话",user.getPhoneNumber());
            list.add(map);
        }
        return Result.success(list);
    }

}
