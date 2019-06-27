package org.cdut.tzg.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cdut.tzg.model.Goods;
import org.cdut.tzg.model.Orders;
import org.cdut.tzg.model.User;
import org.cdut.tzg.result.CodeMsg;
import org.cdut.tzg.result.Result;
import org.cdut.tzg.service.GoodsService;
import org.cdut.tzg.service.OrderService;
import org.cdut.tzg.service.UserService;
import org.cdut.tzg.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author anlan
 * @date 2019/6/25 8:39
 */
@Controller
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/findAll")
    @ResponseBody
    public List<User> findAll(){
        List<User> users = userService.findAll();
        return users;
    }

    @RequestMapping("/find")
    @ResponseBody
    public String find(){
        Map<String,String> map = new HashMap<String,String>();
        map.put("1111","1111");
        map.put("2222","2222");
        map.put("3333","3333");
        String test = null;
        ObjectMapper obj = new ObjectMapper();
        try {
             test = obj.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(test);
        return test;
    }

    /**
     * 注册用户!!!!!!未开发完
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public Map register(@RequestBody String data)
    {   Map map = MapUtils.getMap(data);
        User user = new User(String.valueOf(map.get("学号")),String.valueOf(map.get("用户名")),String.valueOf(map.get("密码")),String.valueOf(map.get("手机号")),String.valueOf(map.get("地址")),String.valueOf(map.get("邮箱")),(Integer)(map.get("角色")));
        int count=userService.register(user);
        Map mapdata=new HashMap();
        if(count!=0){
            mapdata.put("status",0);
            mapdata.put("content","注册成功！");
        }else{
            mapdata.put("status",1);
            mapdata.put("content","注册失败！");
        }
        return mapdata;
    }

    /**
     * 用户登录
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> login(@RequestBody String data){
        Map map=MapUtils.getMap(data);
        User user=userService.findUserByName((String)map.get("用户名"));
        Map mapdata = new LinkedHashMap();
        if (user.getPassword().equals((String)map.get("密码"))){
            mapdata.put("status",0);
            boolean isFrozen = (user.getIsFrozen()==0?false:true);
            mapdata.put("是否被冻结",isFrozen);
        }else {
            mapdata.put("status",1);
            mapdata.put("是否被冻结",true);
        }
        return Result.success(mapdata);
    }

    /**
     * 用户是否是商家
     */
    @RequestMapping(value = "/home/isSaller",method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> isSaller(@RequestBody String data){
        Map map=MapUtils.getMap(data);
        User user = userService.findUserByName((String) map.get("用户名"));
        Map <String,Object> mapdata = new LinkedHashMap<String,Object>();
        if (user.getMoneyCode() != null){
            mapdata.put("isSaller",true);
            mapdata.put("moneyCode",user.getMoneyCode());
        }else {
            mapdata.put("isSaller",false);
            mapdata.put("moneyCode",null);
        }
        return Result.success(mapdata);
    }

    /**
     * 发布/删除求购
     */
    @RequestMapping(value = "/home/handleSeek",method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> handleSeek(@RequestBody String data){
        Map map=MapUtils.getMap(data);
        Map mapdata = new HashMap();
        Long userId = userService.findIdByUserName((String) map.get("用户名"));
        if((Boolean) map.get("发布")){//发布求购信息
//            Date day=new Date();
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //int pubStatus = goodsService.publishSeekGood(userId,(Integer) map.get("商品标签"),(String) map.get("商品名称"),(String) map.get("描述"),Float.parseFloat(map.get("单价").toString()),(Integer) map.get("数量"),(String) map.get("商品图片"));
            Goods exitSeekGoods=goodsService.isExitSeekGoods(userId, (Integer) map.get("商品标签"), (String) map.get("商品名称"));
            if (exitSeekGoods==null){ //求购信息不存在的时候
                Goods good=new Goods();
                good.setUserId(userId);
                good.setTag((Integer) map.get("商品标签"));
                good.setTitle((String) map.get("商品名称"));
                good.setContent((String) map.get("描述"));
                good.setPrice(Float.parseFloat(map.get("单价").toString()));
                good.setStock((Integer) map.get("数量"));
                good.setImage((String) map.get("商品图片"));
                int pubStatus = goodsService.publishSeekGood(good);
                if (pubStatus==1){//求购信息发布成功
                    mapdata.put("success",true);
                    return Result.success(mapdata);
                }else {//求购信息发布失败
                    mapdata.put("success",false);
                    return Result.error(CodeMsg.PUBLISHGOODFAILED);
                }
            }else {//求购信息已经存在
                mapdata.put("success",false);
                return Result.error(CodeMsg.EXITSEEKGOODS);
            }

        }else {//删除求购信息
            int delStatus=goodsService.deleteSeekGood(userId, (Integer) map.get("商品标签"), (String) map.get("商品名称"));
            if (delStatus == 1) {//删除求购信息成功
                mapdata.put("success", true);
                return Result.success(mapdata);
            } else {//删除求购信息失败
                mapdata.put("success", false);
                return Result.error(CodeMsg.DELETEGOODFAILED);
            }
        }
    }

    @RequestMapping(value = "/home/SeekInfo",method = RequestMethod.GET)
    @ResponseBody
    public Result<Object> findAllSeekGoods(@RequestParam String username){
        Map map=null;
        Long userId=userService.findIdByUserName(username);
        List<Goods> seekGoods=goodsService.getAllSeekGoodsByUserId(userId);
        String userPhone=userService.findPhoneByUsername(username);
        if (userPhone==null){
            return Result.error(CodeMsg.USER_UNDEFIND);
        }
        List<Map> listdata=new ArrayList<Map>();
        //“商品标签”:”XXX”,”商品名称”:”XXX”,”描述”:”XXX”,”单价”:XXX,”数量”:XXX ,”商品图片”:”XXX”,”联系方式”:”XXX”
        for(Goods good : seekGoods){
            map=new HashMap();
            map.put("商品标签",good.getTag());
            map.put("商品名称",good.getTitle());
            map.put("描述",good.getContent());
            map.put("单价",good.getPrice());
            map.put("数量",good.getStock());
            map.put("商品图片",good.getImage());
            map.put("联系方式",userPhone);
            listdata.add(map);
        }
        return  Result.success(listdata);
    }

    /**
     * 订单置为已支付
     * @param data
     * @return
     */
    @RequestMapping(value = "/saller/paidOrder",method = RequestMethod.POST)
    @ResponseBody
    public Result<Map<String,Object>> setStateToPaid(@RequestBody String data){
        Map<String,Object> map = new HashMap<>();
        Map maps = MapUtils.getMap(data);
        String userName = (String) maps.get("username");
        Long userId = userService.findIdByUserName(userName);
        if (userId != null) {
            //用户存在
            //获取订单id
            Long orderId = Long.valueOf((Integer) maps.get("id"));
            Orders order = orderService.getOrderByIdAndUserId(orderId, userId);
            //订单存在
            if (order != null) {
                int sign = orderService.setStateToPaid(orderId, userId);
                if (sign == 1) {
                    //修改成功
                    map.put("success", true);
                    map.put("content", "订单已支付");
                } else if (sign == 0) {
                    //原本为已支付状态
                    return Result.error(CodeMsg.REPETITIVE_OPERATION);
                }
            } else{
                return Result.error(CodeMsg.NO_ORDER);
            }
        }else {
            //用户不存在
            return Result.error(CodeMsg.USER_UNDEFIND);
        }
        return Result.success(map);
    }

    /**
     * 订单置为完成
     * @param data
     * @return
     */
    @RequestMapping(value = "/saller/completeOrder",method = RequestMethod.POST)
    @ResponseBody
    public Result<Map<String,Object>> setOrderComplete(@RequestBody String data){
        Map<String, Object> map = new HashMap<>();
        Map maps = MapUtils.getMap(data);
        //获取用户id
        String userName = (String) maps.get("username");
        Long userId = userService.findIdByUserName(userName);
        if (userId != null) {
            //用户存在
            //获取订单id
            Long orderId = Long.valueOf((Integer) maps.get("id"));
            Orders order = orderService.getOrderByIdAndUserId(orderId, userId);
            //订单存在
            if (order != null) {
                int sign = orderService.setStateToCompleted(orderId, userId,new Date());
                if (sign == 1) {
                    //修改成功
                    map.put("success", true);
                    map.put("content", "订单已完成");
                } else if (sign == 0) {
                    //原本为完成状态
                    return Result.error(CodeMsg.REPETITIVE_OPERATION);
                }
            } else{
                return Result.error(CodeMsg.NO_ORDER);
            }
        }else {
            //用户不存在
            return Result.error(CodeMsg.USER_UNDEFIND);
        }
        return Result.success(map);
    }
}
