package org.cdut.tzg.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cdut.tzg.model.Goods;
import org.cdut.tzg.model.User;
import org.cdut.tzg.result.CodeMsg;
import org.cdut.tzg.result.Result;
import org.cdut.tzg.service.GoodsService;
import org.cdut.tzg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public Map register(@RequestParam Integer id,@RequestParam String schoolNumber,@RequestParam String username,@RequestParam String password,@RequestParam String phoneNumber,@RequestParam String address
     ,@RequestParam String email,@RequestParam Integer isFrozen,@RequestParam Integer totalSold,@RequestParam Integer grade,@RequestParam String avatar,@RequestParam String moneyCode,@RequestParam Integer role)
    {
        int count=userService.register(id,schoolNumber,username,password,phoneNumber,address,email,isFrozen,totalSold,grade,avatar,moneyCode,role);
        Map map=new HashMap();
        if(count!=0){
            map.put("status",0);
            map.put("content","注册成功！");
        }else{
            map.put("status",1);
            map.put("content","注册失败！");
        }
        return map;
    }

    /**
     * 用户登录
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> login(@RequestParam String username, @RequestParam String password){
        User user=userService.findUserByName(username);
        Map data = new LinkedHashMap();
        if (user.getPassword().equals(password)){
            data.put("status",0);
            boolean isFrozen = (user.getIsFrozen()==0?false:true);
            data.put("是否被冻结",isFrozen);
        }else {
            data.put("status",1);
            data.put("是否被冻结",true);
        }
        return Result.success(data);
    }

    /**
     * 用户是否是商家
     * @param username
     * @return
     */
    @RequestMapping(value = "/home/isSaller",method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> isSaller(@RequestParam String username){
        User user = userService.findUserByName(username);
        Map <String,Object> data = new LinkedHashMap<String,Object>();
        if (user.getMoneyCode() != null){
            data.put("isSaller",true);
            data.put("moneyCode",user.getMoneyCode());
        }else {
            data.put("isSaller",false);
            data.put("moneyCode",null);
        }
        return Result.success(data);
    }

    /**
     * 发布/删除求购
     */
    @RequestMapping(value = "/home/handleSeek",method = RequestMethod.GET)
    @ResponseBody
    public Result<Object> handleSeek(@RequestParam Boolean isPublish,@RequestParam String username,@RequestParam Integer tag,
                                     @RequestParam String title,@RequestParam String content,@RequestParam Float price,
                                     @RequestParam Integer stock,@RequestParam String image){

        Map data = new HashMap();
        Long userId = userService.findIdByUserName(username);
        if(isPublish){//发布求购信息
//            Date day=new Date();
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            int pubStatus = goodsService.publishSeekGood(userId,tag,title,content,price,stock,image);
            if (pubStatus==1){//求购信息发布成功
                data.put("success",true);
                return Result.success(data);
            }else {//求购信息发布失败
                data.put("success",false);
                return Result.error(CodeMsg.PUBLISHGOODFAILED);
            }
        }else {//删除求购信息
            int delStatus=goodsService.deleteSeekGood(userId, tag, title);
            if (delStatus == 1) {
                data.put("success", true);
                return Result.success(data);
            } else {
                data.put("success", false);
                return Result.error(CodeMsg.DELETEGOODFAILED);
            }
        }
    }


    /*
    * 订单完成
    * */
    @RequestMapping(value = "/saller/completeOrder",method = RequestMethod.POST)
    @ResponseBody
    public Result<Map<String,Object>> setOrderComplete(){
        Map<String,Object> map = new HashMap<>();
        return Result.success(map);
    }
}
