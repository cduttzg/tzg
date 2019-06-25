package org.cdut.tzg.controller;

import org.cdut.tzg.model.User;
import org.cdut.tzg.service.UserService;
import org.cdut.tzg.service.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author anlan
 * @date 2019/6/25 8:39
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findAll")
    @ResponseBody
    public List<User> findAll(){
        List<User> users = userService.findAll();
        return users;
    }

  /*  @RequestMapping("/find/{userId}")
    @ResponseBody
    public String find(@PathVariable Long userId){
        return "" + userId;
    }*/
  /*@RequestMapping("/find")
  @ResponseBody
  public String find(Integer age,String name){
      return ""+age+name;
  }*/

    /**
     * @author xielifeng
     * 用户注册
     */
    @RequestMapping("/register")
    @ResponseBody
  public Map register( Long id, String schoolNumber, String username, String password, String phoneNumber,
                          String address, String email,Integer isForzen, Integer totalSold, Integer grade,
                          String avatar, String moneyCode, Integer role){
        String aa= userService.isregister(id,schoolNumber,username,password,phoneNumber,address,email,isForzen,totalSold,grade,avatar,moneyCode,role);
        Map map=new HashMap();
        map.put("status",role);
        map.put("content","添加成功");
        return map;
        /*User user=new User(id,schoolNumber,username,password,phoneNumber,address,email,isForzen,totalSold,grade,avatar,moneyCode,role);
        *//*return ""+id+schoolNumber+username+password+phoneNumber+address+email+isForzen+totalSold+grade+avatar+moneyCode+role;*//*
        String aa=userService.isregister(user);
        Map map=new HashMap();
        map.put("status",role);
        map.put("content","添加成功");
        return map;*/
  }
}
