package org.cdut.tzg.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cdut.tzg.model.User;
import org.cdut.tzg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.LinkedHashMap;
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
     * 注册用户
     * @param id
     * @param schoolNumber
     * @param username
     * @param password
     * @param phoneNumber
     * @param address
     * @param email
     * @param isFrozen
     * @param totalSold
     * @param grade
     * @param avatar
     * @param moneyCode
     * @param role
     * @return
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
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    Map login(@RequestParam String username,@RequestParam String password){
        User user=userService.findUserByName(username);
        Map map = new LinkedHashMap();
        if (user.getPassword().equals(password)){
            map.put("status",0);
            boolean isFrozen = (user.getIsFrozen()==0?false:true);
            map.put("是否被冻结",isFrozen);
        }else {
            map.put("status",1);
            map.put("是否被冻结",true);
        }
        return map;
    }
}
